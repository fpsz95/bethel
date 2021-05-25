package com.fernandes.bethel.user;

import com.fernandes.bethel.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;


public class UserDetailsImpl implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final Set<? extends GrantedAuthority> authorities;
    private String userProfileImageLink;
    private final Long societyId;

    //Converting User Instance into UserDetailsImpl
    public UserDetailsImpl(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new).collect(Collectors.toSet());//Getting Roles, Split with comma and map it with SimpleGrantedAuthority Constructor
        this.userProfileImageLink = user.getUserProfileImageLink();
        this.societyId = user.getSociety().getSocietyId();
    }

    public Long getId() {
        return id;
    }

    public Optional<String> getUserProfileImageLink() {
        return Optional.ofNullable(userProfileImageLink);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getSocietyId() {
        return societyId;
    }
}
