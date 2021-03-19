package pl.coderslab.charity.institution;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.donation.DonationService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InstitutionServiceImpl implements InstitutionService {
    private final InstitutionRepository institutionRepository;

    @Override
    public Institution findById(Long id) {
        return institutionRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("Institution with id=%s does not exists", id))
                );
    }

    @Override
    @Transactional
    public void delete(Institution institution) {
        institution.setActive(false);
        edit(institution);
    }

    @Override
    public void edit(Institution institution) {
        institutionRepository.save(institution);
    }

    @Override
    public void save(Institution institution) {
        institution.setActive(true);
        institutionRepository.save(institution);
    }

    @Override
    public List<Institution> findAllActive() {
        return institutionRepository.findAll()
                .stream()
                .filter(Institution::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<Institution> findAll() {
        return institutionRepository.findAll();
    }
}
