package com.fernandes.bethel.user;

import com.fernandes.bethel.bucket.BucketName;
import com.fernandes.bethel.bucket.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private final FileStore fileStore;

    @Autowired
    public UserDetailsServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            FileStore fileStore) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.fileStore = fileStore;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("**********INSIDE UserDetailsServiceImpl*****UserDetails loadUserByUsername(String username)***" + username);
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return user.map(UserDetailsImpl::new).get(); //User is Converted/Mapped into MyUserDetails
        //return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),new ArrayList<>()); //Java 1.8 Implementation
    }

    public void uploadUserProfileImage(Long id, MultipartFile file, Authentication authentication) {
//      1. Check if image is not empty
        if(file.isEmpty()) {
            System.out.println("1. Check if image is not empty");
            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + "]");
        }
//      2. If file is an image
        if (!Arrays.asList(IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
            System.out.println("2. If file is an image");
            throw new IllegalStateException("File must be an image");
        }
//      3. The user exists in our database
        System.out.println("3. The user exists in our database");
        UserDetailsImpl  userDetails = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println("3. UserDetailsImpl ");
        System.out.println(userDetails.getId().equals(id));
        System.out.println("3. UserDetailsImpl ");

        //UserProfile user = getUserProfileOrThrow(userProfileId);
        // 4. Grab some metadata from file if any
        System.out.println("4. Grab some metadata from file if any");
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        //5.Store the image to S3 and update database(userProfileImageLink) with s3 image link
        // We are implementing bucket for each user here
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), userDetails.getId());
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            System.out.println("UserDetailsImpl inside Try--------------");
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
            System.out.println("2. UserDetailsImpl inside Try--------------");
            //userDetails.setUserProfileImageLink(filename);
            //User userProfileImage = new User(filename); //working with repo on new line
            //User user = user.setUserProfileImageLink(filename);
            //UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Optional<User> optional = userRepository.findById(id);
            User user = optional.get();
            user.setUserProfileImageLink(filename);
            //user.setUserSociety(society.getId());
            //User user =
            //userDetails.setUserProfileImageLink(userProfileImage);
            userRepository.save(user);
            System.out.println("AFTER USER.SAVE UserDetailsImpl inside Try--------------");
        } catch (IOException e) {
            System.out.println("UserDetailsImpl inside CATCH--------------");
            throw new IllegalStateException(e);
        }
    }

    public byte[] downloadUserProfileImage(Long id, Authentication authent) {
        UserDetailsImpl  userDetails2 = (UserDetailsImpl) authent.getPrincipal();
        Optional<User> optional = userRepository.findById(id);
        User user = optional.get();
        user.getUserProfileImageLink();

        String path = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),
                userDetails2.getId());

        return  userDetails2.getUserProfileImageLink()
                .map(key ->fileStore.download(path, key))
                        .orElse(new byte[0]);

    }
}   
