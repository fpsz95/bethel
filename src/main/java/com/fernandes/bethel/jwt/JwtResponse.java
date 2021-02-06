package com.fernandes.bethel.jwt;


import java.util.Optional;

public class JwtResponse {
    private String token;
    private Long id;
    private String username;
//    private final Set<String> authorities;
    private final Optional<String> userProfileImageLink;

    public JwtResponse(String token,
                       Long id,
                       String username,
//                       Set<String> authorities,
                       Optional<String> userProfileImageLink) {
        this.token = token;
        this.id = id;
        this.username = username;
//        this.authorities = authorities;
        this.userProfileImageLink = userProfileImageLink;
    }
    public Optional<String> getUserProfileImageLink() {
        return userProfileImageLink;
    }

    public String getToken() {
        return token;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


//    public Set<String> getAuthorities() {
//        return authorities;
//    }

}