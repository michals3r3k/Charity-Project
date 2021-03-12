package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.RoleType;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleType(RoleType roleType);
}