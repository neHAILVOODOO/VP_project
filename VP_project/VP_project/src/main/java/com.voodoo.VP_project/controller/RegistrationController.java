package com.voodoo.VP_project.controller;

import com.voodoo.VP_project.domain.Role;
import com.voodoo.VP_project.domain.User;
import com.voodoo.VP_project.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;


@Controller
public class RegistrationController {
@Autowired
private UserRepo userRepo;
@GetMapping("/registration")
    public String registration() {
    return "registration";
}

@PostMapping("registration")
    public String addUser(User user, Map<String, Object> model) {

    User userFromDb = userRepo.findByUsername(user.getUsername());

    if (userFromDb != null) {
    model.put("message", "user exists!");
    return "registration";
    }

   user.setActive(String.valueOf(true));
    user.setRoles(Collections.singleton(Role.USER));
    userRepo.save(user);

    return "redirect:/login";
}

}
