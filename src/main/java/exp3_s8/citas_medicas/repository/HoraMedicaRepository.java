package exp3_s8.citas_medicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exp3_s8.citas_medicas.model.HoraMedica;

public interface HoraMedicaRepository extends JpaRepository<HoraMedica, Long> {

}
