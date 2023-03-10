package clinica.repositories;

import clinica.models.ERol;
import clinica.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol,Long>  {
    Optional<Rol> findByName(ERol name);
}
