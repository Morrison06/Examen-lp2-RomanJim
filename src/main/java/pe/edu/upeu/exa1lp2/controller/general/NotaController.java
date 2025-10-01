package pe.edu.upeu.exa1lp2.controller.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.exa1lp2.dto.NotaDTO;
import pe.edu.upeu.exa1lp2.service.service.NotaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @GetMapping
    public ResponseEntity<List<NotaDTO>> findAll() {
        return ResponseEntity.ok(notaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(notaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<NotaDTO> create(@RequestBody NotaDTO notaDTO) {
        return new ResponseEntity<>(notaService.create(notaDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        notaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}