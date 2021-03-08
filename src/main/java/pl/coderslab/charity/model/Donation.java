package pl.coderslab.charity.model;

import lombok.Data;
import lombok.NoArgsConstructor;

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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "donation_category")
    private List<Category> categories;
    @ManyToOne
    Institution institution;
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickupComment;

}
