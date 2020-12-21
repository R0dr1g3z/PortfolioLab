package pl.coderslab.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class CurrentUser extends User {
    private final pl.coderslab.security.User user;

    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.coderslab.security.User user) {
        super(username, password, authorities);
        this.user = user;
    }

    public pl.coderslab.security.User getUser() {
        return user;
    }
}
