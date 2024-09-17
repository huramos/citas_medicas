package exp3_s6.citas_medicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exp3_s6.citas_medicas.model.CitaMedica;

public interface CitaMedicaRepository extends JpaRepository<CitaMedica, Long> {

}
