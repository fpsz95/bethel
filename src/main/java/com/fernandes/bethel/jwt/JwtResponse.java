package com.fernandes.bethel.jwt;


import java.util.Optional;
import java.util.Set;

public class JwtResponse {
    private String token;
    private Long id;
    private String username;
    private Set<String> authorities;
    private Optional<String> userProfileImageLink;
    private final Long societyId;

    public JwtResponse(String token,
                       Long id,
                       String username,
                       Set<String> authorities,
                       Optional<String> userProfileImageLink,
                       Long societyId) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.authorities = authorities;
        this.userProfileImageLink = userProfileImageLink;
        this.societyId = societyId;
    }

    public JwtResponse(Long id,
                       String username,
                       Long societyId) {
        this.id = id;
        this.username = username;
        this.societyId = societyId;
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

    public Set<String> getAuthorities() {
        return authorities;
    }

}