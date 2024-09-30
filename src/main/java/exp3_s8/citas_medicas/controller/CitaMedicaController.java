package exp3_s8.citas_medicas.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

import exp3_s8.citas_medicas.model.CitaMedica;
import exp3_s8.citas_medicas.service.CitaMedicaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/citasMedicas")
@RequiredArgsConstructor
public class CitaMedicaController {

    private static final Logger log = LoggerFactory.getLogger(CitaMedicaController.class);

    @Autowired
    private CitaMedicaService citaMedicaService;

    @GetMapping
    public CollectionModel<EntityModel<CitaMedica>> getAllCitasMedicas() {
        List<CitaMedica> citasMedicas = citaMedicaService.getAllCitasMedicas();
        log.info("GET /citasMedicas");
        log.info("Retornando todas las Citas Medicas");
        List<EntityModel<CitaMedica>> citasMedicasResources = citasMedicas.stream()
                .map(citaMedica -> EntityModel.of(citaMedica,
                        WebMvcLinkBuilder.linkTo(
                                WebMvcLinkBuilder.methodOn(this.getClass()).getCitaMedicaById(citaMedica.getIdCita()))
                                .withSelfRel()))
                .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllCitasMedicas());
        CollectionModel<EntityModel<CitaMedica>> resources = CollectionModel.of(citasMedicasResources,
                linkTo.withRel("citasMedicas"));

        return resources;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CitaMedica>> getCitaMedicaById(@PathVariable Long id) {
        log.info("Getting a Cita Medica by ID: {}", id);
        Optional<CitaMedica> citaMedica = citaMedicaService.getCitaMedicaById(id);
        if (citaMedica.isPresent()) {
            EntityModel<CitaMedica> citaMedicaModel = EntityModel.of(citaMedica.get());
            citaMedicaModel.add(linkTo(methodOn(CitaMedicaController.class).getCitaMedicaById(id)).withSelfRel());

            return ResponseEntity.ok(citaMedicaModel);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<EntityModel<CitaMedica>> createCitaMedica(@Validated @RequestBody CitaMedica citaMedica) {
        log.info("Creating a new citaMedica with request: {}", citaMedica);
        CitaMedica createCitaMedica = citaMedicaService.createCitaMedica(citaMedica);
        log.info("Cita Medica created successfully. Cita Medica ID: {}", createCitaMedica.getIdCita());

        EntityModel<CitaMedica> citaMedicaModel = EntityModel.of(createCitaMedica);
        citaMedicaModel.add(linkTo(methodOn(CitaMedicaController.class).getCitaMedicaById(createCitaMedica.getIdCita()))
                .withSelfRel());

        return ResponseEntity.created(
                linkTo(methodOn(CitaMedicaController.class).getCitaMedicaById(createCitaMedica.getIdCita())).toUri())
                .body(citaMedicaModel);

    }

    @PutMapping("/{id}")
    public CitaMedica updateCitaMedica(@PathVariable Long id, @RequestBody CitaMedica citaMedica) {
        return citaMedicaService.updateCitaMedica(id, citaMedica);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCitaMedica(@PathVariable Long id) {
        log.info("Deleting a Cita Medica with ID: {}", id);
        Optional<CitaMedica> citaMedica = citaMedicaService.getCitaMedicaById(id);
        if (citaMedica.isPresent()) {
            citaMedicaService.deleteCitaMedica(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
