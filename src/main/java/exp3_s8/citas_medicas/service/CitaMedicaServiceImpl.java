package exp3_s8.citas_medicas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exp3_s8.citas_medicas.model.CitaMedica;
import exp3_s8.citas_medicas.repository.CitaMedicaRepository;

@Service
public class CitaMedicaServiceImpl implements CitaMedicaService {
    @Autowired
    private CitaMedicaRepository citaMedicaRepository;

    @Override
    public List<CitaMedica> getAllCitasMedicas() {
        return citaMedicaRepository.findAll();
    }

    @Override
    public Optional<CitaMedica> getCitaMedicaById(Long id) {
        return citaMedicaRepository.findById(id);
    }

    @Override
    public CitaMedica createCitaMedica(CitaMedica citaMedica) {
        return citaMedicaRepository.save(citaMedica);
    }

    @Override
    public CitaMedica updateCitaMedica(Long id, CitaMedica citaMedica) {
        if (citaMedicaRepository.existsById(id)) {
            citaMedica.setIdCita(id);
            return citaMedicaRepository.save(citaMedica);
        } else {
            return null;
        }
    }

    @Override
    public void deleteCitaMedica(Long id) {
        citaMedicaRepository.deleteById(id);
    }
}
