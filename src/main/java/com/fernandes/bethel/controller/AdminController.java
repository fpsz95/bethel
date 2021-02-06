package com.fernandes.bethel.controller;

import com.fernandes.bethel.user.*;
import com.fernandes.bethel.society.Society;
import com.fernandes.bethel.society.SocietyService;
import com.fernandes.bethel.jwt.JwtResponse;
import com.fernandes.bethel.jwt.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final JwtToken jwtToken;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final SocietyService societyService;
    private final UserService userService;

    @Autowired
    public AdminController(JwtToken jwtToken,
                           UserDetailsServiceImpl userDetailsServiceImpl,
                           SocietyService societyService,
                           UserService userService) {
        this.jwtToken = jwtToken;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.societyService = societyService;
        this.userService = userService;
    }

    @PostMapping("user-registration")
    public String registerUser(@RequestBody UserRegistrationRequest userRegistrationRequest){
        return userService.addUser(userRegistrationRequest);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getCurrentUserDetails(Authentication authentication){
        System.out.println("Inside Profile");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Set<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toSet());

        return ResponseEntity.ok(
                new JwtResponse(
                        jwtToken.getToken(),
                        userDetails.getId(),
                        userDetails.getUsername(),
//                        roles,
                        userDetails.getUserProfileImageLink()
                )
            );
    }

    @PostMapping(value = "{id}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadUserProfileImage(@PathVariable("id") Long id,
                                       @RequestParam("file") MultipartFile file,
                                       Authentication authentication){
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        Long  = userDetails.getId();
        System.out.println("File To Be Uploaded");
        userDetailsServiceImpl.uploadUserProfileImage(id, file, authentication);
        System.out.println("File has been Uploaded");

    }

    @GetMapping("{id}/image/download")
    public byte[] downloadUserProfileImage(@PathVariable("id") Long id, Authentication authentication){
        return userDetailsServiceImpl.downloadUserProfileImage(id, authentication);
    }

    @PostMapping("society-registration")
    public String registerSociety(@RequestBody Society society){
        return societyService.register(society);
    }

    @GetMapping("society-list")
    public List getAllSocieties(){
        return societyService.getAllSocieties();
    }

//    @GetMapping("society-user/{name}")
//    public List<Society> getSocietyUser(@PathVariable final String name){
//        return societyRegistrationService.getAll(name);
//    }

    @GetMapping("user-society/{id}")
    public Optional<User> getUserSociety(@PathVariable final Long id){
        return userService.getUser(id);
    }
}
