package pe.edu.upeu.exa1lp2.controller.general;

import pe.edu.upeu.exa1lp2.dto.DetalleAlquilerDTO;
import pe.edu.upeu.exa1lp2.service.service.DetalleAlquilerService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detalles-alquiler")
@CrossOrigin(origins = "*")
public class DetalleAlquilerController {

    private static final Logger logger = LoggerFactory.getLogger(DetalleAlquilerController.class);

    private final DetalleAlquilerService detalleAlquilerService;

    public DetalleAlquilerController(DetalleAlquilerService detalleAlquilerService) {
        this.detalleAlquilerService = detalleAlquilerService;
    }

    @GetMapping
    public ResponseEntity<List<DetalleAlquilerDTO>> findAll() {
        try {
            logger.info("Obteniendo todos los detalles de alquiler");
            List<DetalleAlquilerDTO> detalles = detalleAlquilerService.findAll();
            return ResponseEntity.ok(detalles);
        } catch (ServiceException e) {
            logger.error("Error al obtener detalles de alquiler: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleAlquilerDTO> findById(@PathVariable Long id) {
        try {
            logger.info("Buscando detalle de alquiler con id: {}", id);
            DetalleAlquilerDTO detalleDTO = detalleAlquilerService.findById(id);
            return ResponseEntity.ok(detalleDTO);
        } catch (ServiceException e) {
            logger.warn("Detalle de alquiler no encontrado con id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DetalleAlquilerDTO detalleAlquilerDTO) {
        try {
            logger.info("Creando nuevo detalle de alquiler");
            logger.debug("DTO recibido - cantidad: {}, precio: {}",
                    detalleAlquilerDTO.getCantidad(), detalleAlquilerDTO.getPrecio());

            if (detalleAlquilerDTO.getAlquiler() != null) {
                logger.debug("Alquiler ID: {}", detalleAlquilerDTO.getAlquiler().getId());
            }
            if (detalleAlquilerDTO.getEquipo() != null) {
                logger.debug("Equipo ID: {}", detalleAlquilerDTO.getEquipo().getId());
            }

            // Validaciones básicas
            if (detalleAlquilerDTO.getCantidad() == null || detalleAlquilerDTO.getCantidad() <= 0) {
                return ResponseEntity.badRequest().body("La cantidad debe ser mayor a 0");
            }
            if (detalleAlquilerDTO.getPrecio() == null || detalleAlquilerDTO.getPrecio().compareTo(java.math.BigDecimal.ZERO) < 0) {
                return ResponseEntity.badRequest().body("El precio debe ser mayor o igual a 0");
            }
            if (detalleAlquilerDTO.getAlquiler() == null || detalleAlquilerDTO.getAlquiler().getId() == null) {
                return ResponseEntity.badRequest().body("El alquiler es requerido");
            }
            if (detalleAlquilerDTO.getEquipo() == null || detalleAlquilerDTO.getEquipo().getId() == null) {
                return ResponseEntity.badRequest().body("El equipo es requerido");
            }

            DetalleAlquilerDTO detalleCreado = detalleAlquilerService.create(detalleAlquilerDTO);
            logger.info("Detalle de alquiler creado exitosamente con id: {}", detalleCreado.getId());
            return new ResponseEntity<>(detalleCreado, HttpStatus.CREATED);

        } catch (ServiceException e) {
            logger.error("Error al crear detalle de alquiler: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error inesperado al crear detalle de alquiler: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody DetalleAlquilerDTO detalleAlquilerDTO) {
        try {
            logger.info("Actualizando detalle de alquiler con id: {}", id);

            if (detalleAlquilerDTO.getCantidad() != null && detalleAlquilerDTO.getCantidad() <= 0) {
                return ResponseEntity.badRequest().body("La cantidad debe ser mayor a 0");
            }
            if (detalleAlquilerDTO.getPrecio() != null && detalleAlquilerDTO.getPrecio().compareTo(java.math.BigDecimal.ZERO) < 0) {
                return ResponseEntity.badRequest().body("El precio debe ser mayor o igual a 0");
            }

            DetalleAlquilerDTO detalleActualizado = detalleAlquilerService.update(id, detalleAlquilerDTO);
            return ResponseEntity.ok(detalleActualizado);

        } catch (ServiceException e) {
            logger.warn("Error al actualizar detalle de alquiler con id {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            logger.info("Eliminando detalle de alquiler con id: {}", id);
            detalleAlquilerService.deleteById(id);
            logger.info("Detalle de alquiler eliminado exitosamente");
            return ResponseEntity.noContent().build();

        } catch (ServiceException e) {
            logger.warn("Error al eliminar detalle de alquiler con id {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}