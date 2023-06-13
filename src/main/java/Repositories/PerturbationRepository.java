package Repositories;

import org.sid.airtrafficcontrolbackend.entities.Perturbation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerturbationRepository extends JpaRepository<Perturbation,Integer> {
}
