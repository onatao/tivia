package com.neidev.tivia.domain.core.model;

import com.neidev.tivia.security.enums.CargoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    private String login;
    private String senha;
    private CargoEnum cargo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.cargo == CargoEnum.ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority("admin"),
                    new SimpleGrantedAuthority("user")
            );
        }
        return List.of(new SimpleGrantedAuthority(this.cargo.getDescricao()));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
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
