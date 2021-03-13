package pl.coderslab.charity.donation;

import pl.coderslab.charity.donation.Donation;

public interface DonationService {
    Long countSum();
    Long countAll();
    void save(Donation donation);
}
