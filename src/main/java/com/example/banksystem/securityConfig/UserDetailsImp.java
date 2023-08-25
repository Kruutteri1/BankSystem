package com.example.banksystem.securityConfig;

import com.example.banksystem.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class UserDetailsImp implements UserDetails {

    private Long id;
    private String name;
    private String password;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImp(Long id, String name, String password, String email, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    public static UserDetailsImp build(User user) {
        List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(user.getERole().name()));
        return new UserDetailsImp(user.getId(),
                user.getName(),
                user.getPassword(),
                user.getEmail(),
                authorityList);
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
        return email;
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
}
