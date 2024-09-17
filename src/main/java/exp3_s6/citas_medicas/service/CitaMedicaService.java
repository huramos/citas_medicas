package exp3_s6.citas_medicas.service;

import java.util.List;
import java.util.Optional;

import exp3_s6.citas_medicas.model.CitaMedica;

public interface CitaMedicaService {

    List<CitaMedica> getAllCitasMedicas();

    Optional<CitaMedica> getCitaMedicaById(Long id);

    CitaMedica createCitaMedica(CitaMedica citaMedica);

    CitaMedica updateCitaMedica(Long id, CitaMedica citaMedica);

    void deleteCitaMedica(Long id);

}
