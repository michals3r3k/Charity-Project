package pl.coderslab.charity.donation;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.user.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
        return donationRepository.countSum().orElse(0L);
    }

    @Override
    public List<Donation> findAllByInstitution(Institution institution) {
        return donationRepository.findAllByInstitution(institution);
    }

    @Override
    public List<Donation> findAllByUser(User user) {
        return donationRepository.findAllByUser(user);
    }

    @Override
    public List<Donation> findAll() {
        return donationRepository.findAll();
    }

    @Override
    public void switchTaken(Long donationId) {
        Donation donation = findById(donationId);
        donation.setTaken(!donation.isTaken());
        donation.setPickedUpDate(LocalDate.now());
        donation.setPickedUpTime(LocalTime.now());
        save(donation);
    }

    @Override
    public Donation findById(Long id) {
        return donationRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Donation does not exists"));
    }
}
