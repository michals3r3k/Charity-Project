package pl.coderslab.charity.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private String street;
    private String city;
    private String zipCode;
    private String phoneNumber;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "donation_category")
    private List<Category> categories;
    @ManyToOne
    Institution institution;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickupComment;

}
