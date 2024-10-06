package exp3_s8.citas_medicas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import exp3_s8.citas_medicas.model.HoraMedica;
import exp3_s8.citas_medicas.repository.HoraMedicaRepository;
import exp3_s8.citas_medicas.service.HoraMedicaServiceImpl;

@ExtendWith(MockitoExtension.class)
public class HoraMedicaServiceImplTest {

    @Mock
    private HoraMedicaRepository horaMedicaRepository;

    @InjectMocks
    private HoraMedicaServiceImpl horaMedicaService;

    private HoraMedica horaMedica;

    @BeforeEach
    void setUp() {
        horaMedica = new HoraMedica();
        horaMedica.setIdHoraMedica(1L);
        horaMedica.setFechaCita("2024-09-30");
        horaMedica.setHoraCita("10:30");
        horaMedica.setDisponibilidad("DISPONIBLE");
    }

    @Test
    void getHoraMedicaById() {
        // Arrange
        when(horaMedicaRepository.findById(horaMedica.getIdHoraMedica())).thenReturn(Optional.of(horaMedica));

        // Act
        Optional<HoraMedica> foundHoraMedica = horaMedicaService.getHoraMedicaById(horaMedica.getIdHoraMedica());

        // Assert
        assertTrue(foundHoraMedica.isPresent());
        assertEquals(horaMedica, foundHoraMedica.get());
        verify(horaMedicaRepository).findById(horaMedica.getIdHoraMedica());
    }

    @Test
    void getAllHorasMedicas() {
        // Arrange
        when(horaMedicaRepository.findAll()).thenReturn(List.of(horaMedica));

        // Act
        List<HoraMedica> foundHoraSMedicas = horaMedicaService.getAllHorasMedicas();

        // Assert
        assertEquals(List.of(horaMedica), foundHoraSMedicas);
        verify(horaMedicaRepository).findAll();
    }
}
