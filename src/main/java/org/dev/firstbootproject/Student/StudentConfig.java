package org.dev.firstbootproject.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.util.Calendar.DECEMBER;
import static java.util.Calendar.JUNE;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            StudentModel jayanth = new StudentModel(
                    "jayanth",
                    "jayanthreddy7777@gmail.com",
                    LocalDate.of(1997, DECEMBER, 3)

            );

            StudentModel Sravani = new StudentModel(
                    "Sravani",
                    "Sravani534@gmail.com",
                    LocalDate.of(1998, JUNE, 26)
            );
            repository.saveAll(
                    List.of(jayanth, Sravani)
             );
        };
    }
}
