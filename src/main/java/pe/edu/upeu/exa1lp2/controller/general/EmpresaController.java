package pe.edu.upeu.exa1lp2.controller;

import pe.edu.upeu.exa1lp2.dto.EmpresaDTO;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.exa1lp2.service.service.EmpresaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/empresas")
public class EmpresaController {

    private final pe.edu.upeu.exa1lp2.service.service.EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> findAll() {
        try {
            return ResponseEntity.ok(empresaService.findAll());
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> findById(@PathVariable Long id) {
        try {
            EmpresaDTO empresaDTO = empresaService.findById(id);
            return ResponseEntity.ok(empresaDTO);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EmpresaDTO> create(@RequestBody EmpresaDTO empresaDTO) {
        try {
            EmpresaDTO empresaCreada = empresaService.create(empresaDTO);
            return new ResponseEntity<>(empresaCreada, HttpStatus.CREATED);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO> update(@PathVariable Long id, @RequestBody EmpresaDTO empresaDTO) {
        try {
            EmpresaDTO empresaActualizada = empresaService.update(id, empresaDTO);
            return ResponseEntity.ok(empresaActualizada);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            empresaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
    }
}