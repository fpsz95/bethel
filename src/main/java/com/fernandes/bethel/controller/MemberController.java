package com.fernandes.bethel.controller;


import com.fernandes.bethel.payments.paytm.JwtPaymentDetailsRedirect;
import com.fernandes.bethel.payments.paytm.PaytmDetails;
import com.fernandes.bethel.payments.paytm.UserPaymentDetails;
import com.fernandes.bethel.user.*;
import com.fernandes.bethel.society.SocietyService;
import com.fernandes.bethel.jwt.JwtResponse;
import com.fernandes.bethel.jwt.JwtToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.paytm.pg.merchant.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("member")
public class MemberController {

    private final JwtToken jwtToken;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final SocietyService societyService;
    private final UserService userService;
    private final PaytmDetails paytmDetails;

    @Autowired
    private Environment environment;

    @Autowired
    public MemberController(JwtToken jwtToken,
                            UserDetailsServiceImpl userDetailsServiceImpl,
                            SocietyService societyService,
                            UserService userService,
                            PaytmDetails paytmDetails) {
        this.jwtToken = jwtToken;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.societyService = societyService;
        this.userService = userService;
        this.paytmDetails = paytmDetails;
    }

    @GetMapping("/memberprofile")
    public ResponseEntity<?> getCurrentUserDetails(Authentication authentication){
        System.out.println("Inside Profile - MEMBER");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Set<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toSet());

        return ResponseEntity.ok(
                new JwtResponse(
                        jwtToken.getToken(),
                        userDetails.getId(),
                        userDetails.getUsername(),
                        roles,
      //                  userDetails.getAuthorities(),
                        userDetails.getUserProfileImageLink(),
                        userDetails.getSocietyId()
                )
        );
    }

//    @PostMapping(value = "{id}/image/upload",
//            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public void uploadUserProfileImage(@PathVariable("id") Long id,
//                                       @RequestParam("file") MultipartFile file,
//                                       Authentication authentication){
////        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
////        Long  = userDetails.getId();
//        System.out.println("File To Be Uploaded");
//        userDetailsServiceImpl.uploadUserProfileImage(id, file, authentication);
//        System.out.println("File has been Uploaded");
//
//    }
//
//    @GetMapping("{id}/image/download")
//    public byte[] downloadUserProfileImage(@PathVariable("id") Long id, Authentication authentication){
//        return userDetailsServiceImpl.downloadUserProfileImage(id, authentication);
//    }
//
    @PostMapping(value="user-registration"
//            ,consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String registerUser(@RequestBody UserRegistrationRequest userRegistrationRequest){
        System.out.println("**********ADMIN COntroller*****registerUser()" + userRegistrationRequest);
        return userService.addUserMember(userRegistrationRequest);
    }
//
//    @PostMapping("society-registration")
//    public String registerSociety(@RequestBody Society society){
//        return societyService.register(society);
//    }
//
//    @GetMapping("society-list")
//    public List getAllSocieties(){
//        return societyService.getAllSocieties();
//    }
//
//    @GetMapping("user-society/{id}")
//    public Optional<User> getUserSociety(@PathVariable final Long id){
//        return userService.getUser(id);
//    }
//
//    @GetMapping("society-users/{id}")
//    public Optional<Society> getSocietyUsers(@PathVariable final Long id){
//        return userService.getSociety(id);
//    }

    @GetMapping("/findallusers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


}

