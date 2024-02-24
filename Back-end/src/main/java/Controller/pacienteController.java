package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import interfaceService.IpacienteService;
import models.paciente;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RequestMapping("/api/v1/paciente/")
@RestController
public class pacienteController {

    @Autowired
    private IpacienteService pacienteService;

    @PostMapping("/")
    public ResponseEntity<Object> save(@ModelAttribute("paciente") paciente paciente) {
        var lista_paciente = pacienteService.findAll()
                .stream().filter(Paciente -> paciente.getDocumento_identidad()
                        .equals(paciente.getDocumento_identidad()));
        if (lista_paciente.count() != 0) {
            return new ResponseEntity<>("El paciente ya existe", HttpStatus.BAD_REQUEST);
        }
        
        if (paciente.getDocumento_identidad().equals("")) {

            return new ResponseEntity<>("Documento de identidad es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (paciente.getPrimer_nombre().equals("")) {
            
            return new ResponseEntity<>("Primer nombre es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (paciente.getPrimer_apellido().equals("")) {
            
            return new ResponseEntity<>("Primer apellido es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (paciente.getCelular().equals("")) {
            
            return new ResponseEntity<>("El numero de celular es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (paciente.getCorreo().equals("")) {
            
            return new ResponseEntity<>("El correo es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (paciente.getNombre_persona_contacto().equals("")) {
            
            return new ResponseEntity<>("El Nombre Persona Contacto es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (paciente.getTelefono_persona_contacto().equals("")) {
            
            return new ResponseEntity<>(" El Telefono Persona Contacto es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

    
        pacienteService.save(paciente); 
        return new ResponseEntity<>(paciente, HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<Object> findAll() {
        var lista_paciente = pacienteService.findAll();
        return new ResponseEntity<>(lista_paciente, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var paciente = pacienteService.findOne(id);
        return new ResponseEntity<>(paciente, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        var paciente = pacienteService.findOne(id).get();
        if (paciente != null) {
            if (paciente.getEstado().equals("H")) {

                paciente.setEstado("D");
                return new ResponseEntity<>("Se deshabilito correctamente", HttpStatus.OK);
            } else
            paciente.setEstado("H");
            return new ResponseEntity<>("Se habilito correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No encontro registro", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("paciente") paciente pacienteUpdate) {
        var paciente = pacienteService.findOne(id).get();
        if (paciente != null) {

            paciente.setDocumento_identidad(pacienteUpdate.getDocumento_identidad());
            paciente.setPrimer_nombre(pacienteUpdate.getPrimer_nombre());
            paciente.setSegundo_nombre(pacienteUpdate.getSegundo_nombre());
            paciente.setPrimer_apellido(pacienteUpdate.getPrimer_apellido());
            paciente.setSegundo_apellido(pacienteUpdate.getSegundo_apellido());
            paciente.setCelular(pacienteUpdate.getCelular());
            paciente.setCorreo(pacienteUpdate.getCorreo());
            paciente.setNombre_persona_contacto(pacienteUpdate.getNombre_persona_contacto());
            paciente.setTelefono_persona_contacto(pacienteUpdate.getTelefono_persona_contacto());
            paciente.setEstado(pacienteUpdate.getEstado());
            
           

            pacienteService.save(paciente);
            return new ResponseEntity<>(paciente, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Error Paciente NO Encontrado", HttpStatus.BAD_REQUEST);
        }
    }

}

