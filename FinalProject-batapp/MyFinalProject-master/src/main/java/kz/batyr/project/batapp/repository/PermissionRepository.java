package kz.batyr.project.batapp.repository;

import kz.batyr.project.batapp.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByRole(String role);
}
