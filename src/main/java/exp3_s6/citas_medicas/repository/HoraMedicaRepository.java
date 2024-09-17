package exp3_s6.citas_medicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exp3_s6.citas_medicas.model.HoraMedica;

public interface HoraMedicaRepository extends JpaRepository<HoraMedica, Long> {

}
