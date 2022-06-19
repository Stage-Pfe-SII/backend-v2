package com.example.filetransfertappbackendv2;

import com.example.filetransfertappbackendv2.entities.Role;
import com.example.filetransfertappbackendv2.entities.User;
import com.example.filetransfertappbackendv2.services.RoleService;
import com.example.filetransfertappbackendv2.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@RequiredArgsConstructor
public class FileTransfertAppBackendV2Application implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;

    public static void main(String[] args) {
        SpringApplication.run(FileTransfertAppBackendV2Application.class, args);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {

        roleService.save(new Role(null, "ROLE_USER"));
        roleService.save(new Role(null, "ROLE_ADMIN"));

        User user1 = new User(null,"mohammedaminerabhi17@gmail.com","password","mohammedaminerabhi17@gmail.com",null,null,new ArrayList<>());
        User user2 = new User(null,"mohammedaminerabhi20@gmail.com","password","mohammedaminerabhi20@gmail.com",null,null,new ArrayList<>());



        userService.save(user1);
        userService.save(user2);

        userService.addRoleToUser("mohammedaminerabhi17@gmail.com","ROLE_USER");
        userService.addRoleToUser("mohammedaminerabhi20@gmail.com","ROLE_USER");
        userService.addRoleToUser("mohammedaminerabhi17@gmail.com","ROLE_ADMIN");
    }


}
