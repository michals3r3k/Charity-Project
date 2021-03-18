package pl.coderslab.charity.institution;

import java.util.List;

public interface InstitutionService {
    List<Institution> findAll();
    Institution findById(Long id);
    void save(Institution institution);
    void delete(Institution institution);
}
