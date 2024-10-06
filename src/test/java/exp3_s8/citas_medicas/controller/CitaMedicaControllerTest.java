package exp3_s8.citas_medicas.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import exp3_s8.citas_medicas.model.CitaMedica;
import exp3_s8.citas_medicas.service.CitaMedicaService;

@ExtendWith(MockitoExtension.class)
public class CitaMedicaControllerTest {

    @Mock
    private CitaMedicaService citaMedicaService;

    @InjectMocks
    private CitaMedicaController citaMedicaController;

    private CitaMedica citaMedica;

    @BeforeEach
    void setUp() {
        citaMedica = new CitaMedica();
        citaMedica.setIdCita(1L);
        citaMedica.setFechaCita("2024-09-30");
        citaMedica.setHoraCita("10:30");
        citaMedica.setNombrePaciente("Hugo Ramos");

    }

    @Test
    void createCitaMedica() {

        // Arrange

        when(citaMedicaService.createCitaMedica(any(CitaMedica.class))).thenReturn(citaMedica);
        // Act
        ResponseEntity<EntityModel<CitaMedica>> response = citaMedicaController.createCitaMedica(citaMedica);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        EntityModel<CitaMedica> savedCitaMedicaModel = response.getBody();
        assertNotNull(savedCitaMedicaModel);
        assertEquals(citaMedica.getIdCita(), savedCitaMedicaModel.getContent().getIdCita());
        assertTrue(savedCitaMedicaModel.hasLink("self"));
        verify(citaMedicaService).createCitaMedica(any(CitaMedica.class));
    }

    @Test
    void getCitaMedicaById() {
        // Arrange
        when(citaMedicaService.getCitaMedicaById(1L)).thenReturn(Optional.of(citaMedica));

        // Act
        ResponseEntity<EntityModel<CitaMedica>> response = citaMedicaController.getCitaMedicaById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        EntityModel<CitaMedica> foundCitaMedicaModel = response.getBody();
        assertNotNull(foundCitaMedicaModel);
        assertEquals(citaMedica.getIdCita(), foundCitaMedicaModel.getContent().getIdCita());
        assertTrue(foundCitaMedicaModel.hasLink("self"));
        verify(citaMedicaService).getCitaMedicaById(1L);
    }

}
