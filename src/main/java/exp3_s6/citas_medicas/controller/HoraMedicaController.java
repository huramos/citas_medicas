package exp3_s6.citas_medicas.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exp3_s6.citas_medicas.model.HoraMedica;
import exp3_s6.citas_medicas.service.HoraMedicaService;

@RestController
@RequestMapping("/horasMedicas")
public class HoraMedicaController {

    private static final Logger log = LoggerFactory.getLogger(HoraMedicaController.class);

    @Autowired
    private HoraMedicaService horaMedicaService;

    @GetMapping
    public List<HoraMedica> getAllHorasMedicas() {
        log.info("GET /citaMedica");
        log.info("Retornando todas las Horas Medicas");
        return horaMedicaService.getAllHorasMedicas();
    }

    /*
     * @GetMapping("/{id}")
     * public ResponseEntity<Object> getCitaMedicaById(@PathVariable Long id) {
     * Optional<HoraMedica> citaMedica = horaMedicaService.getHoraMedicaById(id);
     * if (horaMedica.isEmpty()) {
     * log.error("No se encontró la hora medica ID {}", id);
     * return ResponseEntity.status(HttpStatus.NOT_FOUND)
     * .body(new ErrorResponse("No se encontró la hora medica ID " + id));
     * }
     * return ResponseEntity.ok(horaMedica);
     * }
     */

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