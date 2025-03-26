package it.finalproject.auto_collection.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Set;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    @Lazy
    private AppUserService appUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("oAuth2User attributes: " + oAuth2User.getAttributes());


        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        System.out.println("Email: " + email + ", Name: " + name);
        // Determina il provider (Google o GitHub)
        String registrationId = (String) request.getSession().getAttribute("OAuth2Provider");
        System.out.println("Registration ID: " + registrationId);

        AuthProvider provider = "github".equalsIgnoreCase(registrationId) ? AuthProvider.GITHUB : AuthProvider.GOOGLE;
        System.out.println("AuthProvider: " + provider);



        // Verifica se l'utente esiste già nel database
        AppUser user = appUserRepository.findByEmail(email)
                .orElseGet(() -> {
                    // Se l'utente non esiste, crealo con una password fittizia hashata
                    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                    String fakePassword = passwordEncoder.encode("oauth2_placeholder");

                    AppUser newUser = new AppUser();
                    newUser.setEmail(email);
                    newUser.setUsername(name);
                    newUser.setProvider(provider);
                    newUser.setPassword(fakePassword); // Password fittizia
                    newUser.setRoles(Set.of(Role.ROLE_USER)); // Assegna un ruolo predefinito

                    return appUserRepository.save(newUser);
                });

        // Crea UserDetails per generare il token JWT
        UserDetails userDetails = new User(
                user.getUsername(),
                user.getPassword(), // Anche se non serve, evita problemi di autenticazione
                user.getAuthorities()
        );

        // Genera il token JWT
        String token = jwtTokenUtil.generateToken(userDetails);

        // Costruisci l'URL di reindirizzamento con il token
        String redirectUrl = UriComponentsBuilder.fromUriString("http://localhost:5173/")
                .queryParam("token", token)
                .build().toUriString();

        // Reindirizza alla homepage del frontend con il token
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);

    }
}