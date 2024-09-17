package exp3_s6.citas_medicas.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exp3_s6.citas_medicas.model.CitaMedica;
import exp3_s6.citas_medicas.service.CitaMedicaService;

@RestController
@RequestMapping("/citaMedica")
public class CitaMedicaController {

    private static final Logger log = LoggerFactory.getLogger(CitaMedicaController.class);

    @Autowired
    private CitaMedicaService citaMedicaService;

    @GetMapping
    public List<CitaMedica> getAllEnvios() {
        log.info("GET /citaMedica");
        log.info("Retornando todas las Horas Medicas");
        return citaMedicaService.getAllCitasMedicas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCitaMedicaById(@PathVariable Long id) {
        Optional<CitaMedica> citaMedica = citaMedicaService.getCitaMedicaById(id);
        if (citaMedica.isEmpty()) {
            log.error("No se encontró la hora medica ID {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("No se encontró la hora medica ID " + id));
        }
        return ResponseEntity.ok(citaMedica);
    }

    @PostMapping
    public ResponseEntity<Object> createCitaMedica(@Validated @RequestBody CitaMedica citaMedica) {
        CitaMedica createdCitaMedica = citaMedicaService.createCitaMedica(citaMedica);
        if (createdCitaMedica == null) {
            log.error("Error al crear Cita Medica {}", citaMedica);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error al crear Cita Medica"));
        }
        return ResponseEntity.ok(createdCitaMedica);

    }

    @PutMapping("/{id}")
    public CitaMedica updateCitaMedica(@PathVariable Long id, @RequestBody CitaMedica citaMedica) {
        return citaMedicaService.updateCitaMedica(id, citaMedica);

    }

    @DeleteMapping("/{id}")
    public void deleteCitaMedica(@PathVariable Long id) {
        citaMedicaService.deleteCitaMedica(id);
    }

    static class ErrorResponse {
        private final String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

}
