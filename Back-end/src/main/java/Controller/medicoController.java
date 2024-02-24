package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import interfaceService.ImedicoService;
import models.medico; 

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RequestMapping("/api/v1/medico/")
@RestController
public class medicoController {

    @Autowired
    private ImedicoService medicoService;

    @PostMapping("/")
    public ResponseEntity<Object> save(@ModelAttribute("medico") medico medico) {

       
        var lista_medico = medicoService.findAll()
                .stream().filter(Medico -> medico.getDocumento_identidad()
                        .equals(medico.getDocumento_identidad()));
        if (lista_medico.count() != 0) {
            return new ResponseEntity<>("El medico ya existe", HttpStatus.BAD_REQUEST);
        }
        
        if (medico.getDocumento_identidad().equals("")) {

            return new ResponseEntity<>("Documento de identidad es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (medico.getPrimer_nombre().equals("")) {
            
            return new ResponseEntity<>("Primer nombre es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (medico.getPrimer_apellido().equals("")) {
            
            return new ResponseEntity<>("Primer apellido es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (medico.getCelular().equals("")) {
            
            return new ResponseEntity<>("Telefono es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (medico.getCorreo().equals("")) {
            
            return new ResponseEntity<>("La direccion de correo es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        
      
        medicoService.save(medico);
        return new ResponseEntity<>(medico, HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<Object> findAll() {
        var lista_medico = medicoService.findAll();
        return new ResponseEntity<>(lista_medico, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var medico = medicoService.findOne(id);
        return new ResponseEntity<>(medico, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        var medico = medicoService.findOne(id).get();
        if (medico != null) {
            if (medico.getEstado().equals("H")) {

                medico.setEstado("D");
                return new ResponseEntity<>("Se deshabilito correctamente", HttpStatus.OK);
            } else
                medico.setEstado("H");
            return new ResponseEntity<>("Se habilito correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontrado el medico", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("medico") medico medicoUpdate) {
        var medico = medicoService.findOne(id).get();
        if (medico != null) {

            medico.setDocumento_identidad(medicoUpdate.getDocumento_identidad());
            medico.setPrimer_nombre(medicoUpdate.getPrimer_nombre());
            medico.setSegundo_nombre(medicoUpdate.getSegundo_nombre());
            medico.setPrimer_apellido(medicoUpdate.getPrimer_apellido());
            medico.setSegundo_apellido(medicoUpdate.getSegundo_apellido());
            medico.setCelular(medicoUpdate.getCelular());
            medico.setCorreo(medicoUpdate.getCorreo());
            medico.setEstado(medicoUpdate.getEstado());

            medicoService.save(medico);
            return new ResponseEntity<>(medico, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Error medico N0 encontrado", HttpStatus.BAD_REQUEST);
        }
    }

}

