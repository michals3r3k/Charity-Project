package pl.coderslab.charity.service;

import pl.coderslab.charity.model.Donation;

public interface DonationService {
    Long countSum();
    Long countAll();
    void save(Donation donation);
}
