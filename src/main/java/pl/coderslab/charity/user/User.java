package pl.coderslab.charity.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.coderslab.charity.donation.Donation;
import pl.coderslab.charity.role.Role;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;
    @OneToMany
    private List<Donation> donations;
    private boolean enabled;
}
