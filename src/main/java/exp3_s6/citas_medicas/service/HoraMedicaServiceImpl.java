package exp3_s6.citas_medicas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exp3_s6.citas_medicas.model.HoraMedica;
import exp3_s6.citas_medicas.repository.HoraMedicaRepository;

@Service
public class HoraMedicaServiceImpl implements HoraMedicaService {
    @Autowired
    private HoraMedicaRepository horaMedicaRepository;

    @Override
    public List<HoraMedica> getAllHorasMedicas() {
        return horaMedicaRepository.findAll();
    }

    @Override
    public Optional<HoraMedica> getHoraMedicaById(Long id) {
        return horaMedicaRepository.findById(id);
    }

}
