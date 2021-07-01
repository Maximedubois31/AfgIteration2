package fr.afg.iteration1.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type My users details.
 */
public class MyUsersDetails implements UserDetails {

    /**
     * The email of my user
     */
    private String email;
    /**
     * The password of my user
     */
    private String password;
    /**
     * Know if the user have an active account
     */
    private boolean active;
    /**
     * The id of my user
     */
    private Long id;
    /**
     * The authorities of my user
     */
    private List<GrantedAuthority> authorities;

    /**
     * Instantiates a new My users details.
     *
     * @param user the user
     */
    public MyUsersDetails(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authorities = Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * Instantiates a new My users details.
     */
    public MyUsersDetails() {
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
        return id.toString();
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
        return active;
    }
}
