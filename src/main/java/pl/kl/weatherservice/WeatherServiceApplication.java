package pl.kl.weatherservice;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.kl.weatherservice.security.User;
import pl.kl.weatherservice.security.UserRepository;

import java.util.Collections;

@SpringBootApplication
@RequiredArgsConstructor
public class WeatherServiceApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(WeatherServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        userRepository.save(new User("user", passwordEncoder.encode("user1"), Collections.singletonList(() -> "ROLE_USER")));
        userRepository.save(new User("admin", passwordEncoder.encode("admin1"), Collections.singletonList(() -> "ROLE_USER;ROLE_ADMIN")));
    }
}
