package com.fernandes.bethel.controller;

import com.fernandes.bethel.jwt.JwtResponse;
import com.fernandes.bethel.user.User;
import com.fernandes.bethel.user.UserDetailsServiceImpl;
import com.fernandes.bethel.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("profile-picture")
public class ProfilePictureController {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final UserService userService;

    @Autowired
    public ProfilePictureController(UserDetailsServiceImpl userDetailsServiceImpl,
                                    UserService userService) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.userService = userService;
    }


    @PostMapping(value = "{id}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadUserProfileImage(@PathVariable("id") Long id,
                                       @RequestParam("file") MultipartFile file,
                                       Authentication authentication){
        System.out.println("authentication from upload" + authentication);
        System.out.println("File To Be Uploaded");
        userDetailsServiceImpl.uploadUserProfileImage(id, file, authentication);
        System.out.println("File has been Uploaded");
    }

    @GetMapping("{id}/image/download")
    public byte[] downloadUserProfileImage(@PathVariable("id") Long id, Authentication authentication){
        System.out.println(authentication);
        return userDetailsServiceImpl.downloadUserProfileImage(id, authentication);
    }
}
