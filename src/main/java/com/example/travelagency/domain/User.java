package com.example.travelagency.domain;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Data
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String name;
    @NonNull
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany
    private List<BookTrip> bookedTrips;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public User(@NonNull String username, @NonNull String password, @NonNull String name, @NonNull String email, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
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
