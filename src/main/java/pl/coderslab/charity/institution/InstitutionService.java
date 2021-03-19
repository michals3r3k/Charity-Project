package pl.coderslab.charity.institution;

import java.util.List;

public interface InstitutionService {
    List<Institution> findAllActive();
    List<Institution> findAll();
    Institution findById(Long id);
    void save(Institution institution);
    void edit(Institution institution);
    void delete(Institution institution);
}
