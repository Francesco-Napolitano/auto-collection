package it.finalproject.auto_collection.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class AuthRunner implements ApplicationRunner {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

//        // Creazione Admin
//        Optional<AppUser> adminUser = appUserService.findByUsername("${ADMIN_USERNAME}");
//        if (adminUser.isEmpty()) {
//            appUserService.registerUser("${ADMIN_USERNAME}", "${ADMIN_PASSWORD}", Set.of(Role.ROLE_ADMIN), "${ADMIN_EMAIL}");
//        }
//
//        // Creazione User
//        Optional<AppUser> normalUser = appUserService.findByUsername("${USER_USERNAME}");
//        if (normalUser.isEmpty()) {
//            appUserService.registerUser("${USER_USERNAME}", "${USER_PASSWORD}", Set.of(Role.ROLE_USER), "${USER_EMAIL}");
//        }

    }
}
