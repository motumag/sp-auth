package com.motuma.security;

import com.motuma.security.model.UserRole;
import com.motuma.security.payload.RegisterRequest;
import com.motuma.security.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.motuma.security.model.UserRole.ADMIN;

@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
//    @Bean
//    public CommandLineRunner commandLineRunner(
//             AuthenticationService authenticationService
//    ) {
//        return args -> {
//            var admin = RegisterRequest.builder()
//                    .userName("admin2@mail.com")
//                    .password("password")
//                    .confirmPassword("password")
//                    .userRole(ADMIN)
//                    .build();
//            System.out.println("Admin token: " + authenticationService.register(admin).getAccessToken());
//
//            var manager = RegisterRequest.builder()
//                    .userName("admin1@mail.com")
//                    .password("password")
//                    .confirmPassword("password")
//                    .userRole(ADMIN)
//                    .build();
//            System.out.println("Manager token: " + authenticationService.register(manager).getAccessToken());
//
//        };
//    }

}
