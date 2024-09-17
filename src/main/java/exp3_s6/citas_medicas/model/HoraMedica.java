package exp3_s6.citas_medicas.model;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "hora_medica")
public class HoraMedica extends RepresentationModel<HoraMedica> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHoraMedica")
    private Long idHoraMedica;
    @Size(max=10)
    @NotBlank(message = "No puede ingresar una fecha vacia")
    @Column(name = "fechaCita")
    private String fechaCita;
    @Size(max=5)
    @NotBlank(message = "No puede ingresar una hora vacia")
    @Column(name = "horaCita")
    private String horaCita;
    @Size(max=20)
    @NotBlank(message = "No puede ingresar una disponibilidad vacia")
    @Column(name = "disponibilidad")
    private String disponibilidad;

    public Long getIdHoraMedica() {
        return idHoraMedica;
    }

    public void setIdHoraMedica(Long idHoraMedica) {
        this.idHoraMedica = idHoraMedica;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

}
