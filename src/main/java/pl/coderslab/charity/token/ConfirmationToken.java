package pl.coderslab.charity.token;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.coderslab.charity.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDateTime confirmedAt;
    @ManyToOne
    private User user;

}
