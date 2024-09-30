package exp3_s8.citas_medicas.service;

import java.util.List;
import java.util.Optional;

import exp3_s8.citas_medicas.model.HoraMedica;

public interface HoraMedicaService {

    List<HoraMedica> getAllHorasMedicas();

    Optional<HoraMedica> getHoraMedicaById(Long id);

}
