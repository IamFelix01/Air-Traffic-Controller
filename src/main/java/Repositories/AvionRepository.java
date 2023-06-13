package Repositories;

import org.sid.airtrafficcontrolbackend.entities.Avion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvionRepository extends JpaRepository<Avion,Integer> {
}
