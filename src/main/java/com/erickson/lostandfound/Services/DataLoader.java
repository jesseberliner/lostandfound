package com.erickson.lostandfound.Services;

import com.erickson.lostandfound.Models.Role ;
import com.erickson.lostandfound.Models.User;
import com.erickson.lostandfound.Repositories.RoleRepository;
import com.erickson.lostandfound.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception
    {
        if(roleRepository.count()==0) {
            System.out.println("Data loader run");

            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("ADMIN"));

            Role userRole = roleRepository.findByRole("USER");
            Role adminRole = roleRepository.findByRole("ADMIN");

            User user = new User("jim@jim.com", "password", "Jim", "Jimmerson", true, "jim");
            user.setRoles(Arrays.asList(userRole));
            userRepository.save(user);

            user = new User("admin@admin.com", "password", "Admin", "Admin", true, "admin");
            user.setRoles(Arrays.asList(userRole));
            userRepository.save(user);
        }

    }
}
