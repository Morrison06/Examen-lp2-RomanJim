package pe.edu.upeu.exa1lp2.controller.general;

import pe.edu.upeu.exa1lp2.dto.EquipoDTO;
import pe.edu.upeu.exa1lp2.service.service.EquipoService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/equipos")
public class EquipoController {

    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @GetMapping
    public ResponseEntity<List<EquipoDTO>> findAll() {
        try {
            return ResponseEntity.ok(equipoService.findAll());
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipoDTO> findById(@PathVariable Long id) {
        try {
            EquipoDTO equipoDTO = equipoService.findById(id);
            return ResponseEntity.ok(equipoDTO);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EquipoDTO> create(@RequestBody EquipoDTO equipoDTO) {
        try {
            EquipoDTO equipoCreado = equipoService.create(equipoDTO);
            return new ResponseEntity<>(equipoCreado, HttpStatus.CREATED);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipoDTO> update(@PathVariable Long id, @RequestBody EquipoDTO equipoDTO) {
        try {
            EquipoDTO equipoActualizado = equipoService.update(id, equipoDTO);
            return ResponseEntity.ok(equipoActualizado);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            equipoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
    }
}