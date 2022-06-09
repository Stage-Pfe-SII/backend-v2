package com.example.filetransfertappbackendv2;

import com.example.filetransfertappbackendv2.entities.User;
import com.example.filetransfertappbackendv2.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class FileTransfertAppBackendV2Application implements CommandLineRunner {

    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(FileTransfertAppBackendV2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User(null,"mohammedaminerabhi17@gmail.com","password",null,null);
        User user2 = new User(null,"mohammedaminerabhi20@gmail.com","password",null,null);

        userService.save(user1);
        userService.save(user2);
    }
}
