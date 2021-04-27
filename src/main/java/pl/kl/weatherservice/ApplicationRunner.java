package pl.kl.weatherservice;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.kl.weatherservice.security.User;
import pl.kl.weatherservice.security.UserRepository;

import java.util.Collections;

@Profile("DEV")
@RequiredArgsConstructor
@Component
public class ApplicationRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        userRepository.deleteAll();
        userRepository.save(new User("user", passwordEncoder.encode("user1"), Collections.singletonList(() -> "ROLE_USER")));
        userRepository.save(new User("admin", passwordEncoder.encode("admin1"), Collections.singletonList(() -> "ROLE_USER;ROLE_ADMIN")));
    }
}
