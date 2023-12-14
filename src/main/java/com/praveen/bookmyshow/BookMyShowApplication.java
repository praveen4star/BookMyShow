package com.praveen.bookmyshow;

import com.praveen.bookmyshow.controllers.UserController;
import com.praveen.bookmyshow.dtos.SignUpUserRequestDto;
import com.praveen.bookmyshow.dtos.SignUpUserResponseDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookMyShowApplication implements CommandLineRunner {
    private UserController userController;
    public BookMyShowApplication(
            UserController userController
    ) {
        this.userController = userController;
    }
    @Override
    public void run(String... args) throws Exception {
        SignUpUserRequestDto signUpUserRequestDto = new SignUpUserRequestDto();
        signUpUserRequestDto.setEmail("praveenw@gmail.com");
        signUpUserRequestDto.setPassword("praveen");
        SignUpUserResponseDto signUpUserResponseDto = userController.signUpUser(signUpUserRequestDto);
        System.out.println(signUpUserResponseDto.getUserId());
    }
    public static void main(String[] args) {
        System.out.println("Hello World");
        SpringApplication.run(BookMyShowApplication.class, args);
    }

}
