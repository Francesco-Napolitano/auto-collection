package it.finalproject.auto_collection.auth;

import it.finalproject.auto_collection.auth.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/oauth2")
public class OAuth2Controller {

    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping("/user")
    public Map<String, Object> getAuthenticatedUser(@AuthenticationPrincipal OAuth2User oAuth2User) {
        if (oAuth2User == null) {
            return Map.of("error", "Nessun utente autenticato");
        }

        String email = oAuth2User.getAttribute("email");
        AppUser user = appUserRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return Map.of("error", "Utente non trovato");
        }

        return Map.of(
                "username", user.getUsername(),
                "email", user.getEmail(),
                "roles", user.getRoles(),
                "provider", user.getProvider()
        );
    }
}
