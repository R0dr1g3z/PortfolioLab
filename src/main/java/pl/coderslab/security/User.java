package pl.coderslab.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.charity.Donation;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    private String username;
    @Min(value = 1,message = "Przynajmniej jedna liczba")
    @Size(min = 8,message = "Minimalna ilość znaków to 8")
    @Pattern(regexp = "[a-z]",message = "Przynamniej jedna mała litera")
    @Pattern(regexp = "[A-Z]",message = "Przynajmniej jedna wielka litera")
    @Pattern(regexp = "[!@#$%^&*()_+-={};':,./<>?]",message = "Przynajmniej jeden znak specjalny")
    private String password;
    @Transient
    private String rePassword;
    private String firstName;
    private String lastName;
    private String fullName;
    private boolean enabled;
    private String token;
    private String tokenPassword;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @OneToMany
    private List<Donation> donations;
}
