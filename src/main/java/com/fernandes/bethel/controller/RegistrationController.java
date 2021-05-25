package com.fernandes.bethel.controller;

import com.fernandes.bethel.society.Society;
import com.fernandes.bethel.society.SocietyService;
import com.fernandes.bethel.user.User;
import com.fernandes.bethel.user.UserRegistrationRequest;
import com.fernandes.bethel.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("registration")
public class RegistrationController {

    private final SocietyService societyService;
    private final UserService userService;

    public RegistrationController(SocietyService societyService,
                                  UserService userService) {
        this.societyService = societyService;
        this.userService = userService;
    }


    @PostMapping("/register-society")
    public String registerSociety(@RequestBody Society society){
        System.out.println("inside RegisterSociety");
        return societyService.register(society);
    }

    @PostMapping("/register-user")
    public String registerUser(@RequestBody UserRegistrationRequest userRegistrationRequest){
        System.out.println("**********REGISTRATION CONTROLLER*****registerUser()" + userRegistrationRequest);
        return userService.addUserAdmin(userRegistrationRequest);
    }
}
