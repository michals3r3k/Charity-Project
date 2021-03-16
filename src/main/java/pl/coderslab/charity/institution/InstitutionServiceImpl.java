package pl.coderslab.charity.institution;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService {
    private final InstitutionRepository institutionRepository;

    public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public Institution findById(Long id) {
        return institutionRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("Institution with id=%s does not exists", id))
                );
    }

    @Override
    public void delete(Institution institution) {
        institutionRepository.delete(institution);
    }

    @Override
    public void save(Institution institution) {
        institutionRepository.save(institution);
    }

    @Override
    public List<Institution> findAll() {
        return institutionRepository.findAll();
    }


}
