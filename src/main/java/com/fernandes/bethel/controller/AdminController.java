package com.fernandes.bethel.controller;

import com.fernandes.bethel.maintenance.MaintenanceCharges;
import com.fernandes.bethel.user.*;
import com.fernandes.bethel.society.Society;
import com.fernandes.bethel.society.SocietyService;
import com.fernandes.bethel.jwt.JwtResponse;
import com.fernandes.bethel.jwt.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("admin")
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

    @GetMapping("/profile")
    public ResponseEntity<?> getCurrentUserDetails(Authentication authentication){
        System.out.println("Inside Profile - ADMIN");
        System.out.println("Hello Worldddddddddddddddddddddddddddddddddddddd");
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
                        userDetails.getUserProfileImageLink(),
                        userDetails.getSocietyId()
                )
            );
    }

    @GetMapping("/viewprofile/{profileId}")
    public ResponseEntity<?> viewProfileInformation(@PathVariable Long profileId){
        Optional<User> user = userService.getUser(profileId);
        System.out.println("View Profile - ADMIN");
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        Set<String> roles = user.get().getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toSet());

        System.out.println(user.get().getFirstName());
        return ResponseEntity.ok(
                new JwtResponse(
                        user.get().getId(),
                        user.get().getUsername(),
//                        roles,authorities,
                        user.get().getSociety().getSocietyId()
                )
        );
    }




//    //NOT REQUIRED
//    @GetMapping("society-list")
//    public List getAllSocieties(){
//        return societyService.getAllSocieties();
//    }

    @GetMapping("all-society-members")
    public List<User> getAllSocietyMembers(Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userService.getAllSocietyMembersList(userDetails.getSocietyId());
    }

//    @GetMapping("/page/{pageNo}")
//    public String findPaginated(@PathVariable (value = "pageNo") int pageNo, Model model) {
//        int pageSize = 5;
//
//        Page<User> page = userService.findPaginated(pageNo, pageSize);
//        List<User> usersList = page.getContent();
//
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("totalItems", page.getTotalElements());
//        model.addAttribute("usersList", usersList);
//        return "index";
//    }

    @PostMapping("user-maintenance-charges-form/{profileId}")
    public String setMaintenanceCharges(@PathVariable Long profileId,
                                        @RequestBody MaintenanceCharges maintenanceCharges){
        System.out.println("INSIDE setMaintenanceCharges");
        userService.setMaintenanceCharges(profileId, maintenanceCharges);
        return "Maintenance Charges have been saved successfully";
    }
}
