package com.example.BookShop_Springboot.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.BookShop_Springboot.model.Admin;
import com.example.BookShop_Springboot.model.Permission;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AdminDetail implements UserDetails {

    private Admin admin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Permission permission : admin.getRole().getPermissions()) {
            authorities.add(new SimpleGrantedAuthority(permission.getName()));
        }
        authorities.add(new SimpleGrantedAuthority(admin.getRole().getName()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return admin.isStatus();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
