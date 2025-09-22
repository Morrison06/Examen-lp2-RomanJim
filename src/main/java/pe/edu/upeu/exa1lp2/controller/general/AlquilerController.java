package pe.edu.upeu.exa1lp2.controller.general;

import pe.edu.upeu.exa1lp2.dto.AlquilerDTO;
import pe.edu.upeu.exa1lp2.service.service.AlquilerService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alquileres")
public class AlquilerController {

    private final AlquilerService alquilerService;

    public AlquilerController(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    @GetMapping
    public ResponseEntity<List<AlquilerDTO>> findAll() {
        try {
            return ResponseEntity.ok(alquilerService.findAll());
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlquilerDTO> findById(@PathVariable Long id) {
        try {
            AlquilerDTO alquilerDTO = alquilerService.findById(id);
            return ResponseEntity.ok(alquilerDTO);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AlquilerDTO> create(@RequestBody AlquilerDTO alquilerDTO) {
        try {
            AlquilerDTO alquilerCreado = alquilerService.create(alquilerDTO);
            return new ResponseEntity<>(alquilerCreado, HttpStatus.CREATED);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlquilerDTO> update(@PathVariable Long id, @RequestBody AlquilerDTO alquilerDTO) {
        try {
            System.out.println("Actualizando alquiler ID: " + id);
            System.out.println("Datos recibidos: " + alquilerDTO);

            AlquilerDTO alquilerActualizado = alquilerService.update(id, alquilerDTO);
            return ResponseEntity.ok(alquilerActualizado);

        } catch (ServiceException e) {

            if (e.getMessage().contains("No existe el alquiler")) {
                return ResponseEntity.notFound().build();
            } else if (e.getMessage().contains("No existe la empresa")) {
                return ResponseEntity.badRequest().build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            alquilerService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
