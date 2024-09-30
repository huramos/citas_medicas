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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exp3_s8.citas_medicas.model.HoraMedica;
import exp3_s8.citas_medicas.service.HoraMedicaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/horasMedicas")
@RequiredArgsConstructor
public class HoraMedicaController {

    private static final Logger log = LoggerFactory.getLogger(HoraMedicaController.class);

    @Autowired
    private HoraMedicaService horaMedicaService;

    @GetMapping
    public CollectionModel<EntityModel<HoraMedica>> getAllHorasMedicas() {
        List<HoraMedica> horasMedicas = horaMedicaService.getAllHorasMedicas();
        log.info("GET /horasMedicas");
        log.info("Retornando todas las Horas Medicas");
        List<EntityModel<HoraMedica>> horasMedicasResources = horasMedicas.stream()
                .map(horaMedica -> EntityModel.of(horaMedica,
                        WebMvcLinkBuilder.linkTo(
                                WebMvcLinkBuilder.methodOn(this.getClass())
                                        .getHoraMedicaById(horaMedica.getIdHoraMedica()))
                                .withSelfRel()))
                .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllHorasMedicas());
        CollectionModel<EntityModel<HoraMedica>> resources = CollectionModel.of(horasMedicasResources,
                linkTo.withRel("horasMedicas"));

        return resources;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<HoraMedica>> getHoraMedicaById(@PathVariable Long id) {
        log.info("Getting a HoraMedica by ID: {}", id);
        Optional<HoraMedica> horaMedica = horaMedicaService.getHoraMedicaById(id);
        if (horaMedica.isPresent()) {
            EntityModel<HoraMedica> horaMedicaModel = EntityModel.of(horaMedica.get());
            horaMedicaModel.add(linkTo(methodOn(HoraMedicaController.class).getHoraMedicaById(id)).withSelfRel());

            return ResponseEntity.ok(horaMedicaModel);
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