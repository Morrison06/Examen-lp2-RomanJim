package pe.edu.upeu.exa1lp2.controller.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.exa1lp2.dto.MateriaDTO;
import pe.edu.upeu.exa1lp2.service.service.MateriaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/materias")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public ResponseEntity<List<MateriaDTO>> findAll() {
        return ResponseEntity.ok(materiaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(materiaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MateriaDTO> create(@RequestBody MateriaDTO materiaDTO) {
        return new ResponseEntity<>(materiaService.create(materiaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaDTO> update(@PathVariable Long id, @RequestBody MateriaDTO materiaDTO) {
        return ResponseEntity.ok(materiaService.update(id, materiaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        materiaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}