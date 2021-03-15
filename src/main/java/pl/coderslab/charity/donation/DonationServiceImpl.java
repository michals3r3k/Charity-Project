package pl.coderslab.charity.donation;

import org.springframework.stereotype.Service;

@Service
public class DonationServiceImpl implements DonationService {
    private final DonationRepository donationRepository;

    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public void save(Donation donation) {
        donationRepository.save(donation);
    }

    @Override
    public Long countAll() {
        return donationRepository.countAll();
    }

    @Override
    public Long countSum() {
        return donationRepository.countSum();
    }
}
