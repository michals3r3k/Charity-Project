package pl.coderslab.charity.donation;


import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.user.User;

import java.util.List;

public interface DonationService {
    Long countSum();
    Long countAll();
    void save(Donation donation);
    List<Donation> findAllByInstitution(Institution institution);
    List<Donation> findAllByUser(User user);
    List<Donation> findAll();
    void switchTaken(Long donationId);
    Donation findById(Long id);
}
