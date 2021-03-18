package pl.coderslab.charity.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    @Query("SELECT SUM(d.quantity) FROM Donation d")
    Optional<Long> countSum();

    @Query("SELECT COUNT(d) FROM Donation d")
    Long countAll();

    List<Donation> findAllByInstitution(Institution institution);
    List<Donation> findAllByUser(User user);
}
