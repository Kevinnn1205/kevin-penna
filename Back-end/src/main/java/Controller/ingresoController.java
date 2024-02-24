package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import interfaceService.IingresoService;
import models.ingreso;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RequestMapping("/api/v1/ingreso/")
@RestController
public class ingresoController {

    @Autowired
    private IingresoService ingresoService;

    @PostMapping("/")
    public ResponseEntity<Object> save(@ModelAttribute("ingreso") ingreso ingreso) {
        var lista_ingreso = ingresoService.findAll()
                .stream().filter(Ingreso -> ingreso.getCama()
                        .equals(ingreso.getCama()));
        if (lista_ingreso.count() != 0) {
            return new ResponseEntity<>("El ingreso ya existe", HttpStatus.BAD_REQUEST);
        }
   
        if (ingreso.getHabitacion().equals("")) {

            return new ResponseEntity<>("Habitacion es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (ingreso.getCama().equals("")) {
            
            return new ResponseEntity<>("Cama es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (ingreso.getPaciente().equals("")) {
            
            return new ResponseEntity<>("Paciente es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (ingreso.getMedico().equals("")) {
            
            return new ResponseEntity<>("Medico es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (ingreso.getFecha_ingreso().equals("")) {
            
            return new ResponseEntity<>("Fecha de ingreso es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (ingreso.getFecha_salida().equals("")) {
            
            return new ResponseEntity<>("Fecha de salida es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (ingreso.getEstado().equals("")) {
            
            return new ResponseEntity<>("Estado es obligatorio", HttpStatus.BAD_REQUEST);
        }
         
          ingresoService.save(ingreso); 
          return new ResponseEntity<>(ingreso, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> findAll() {
        var listaIngreso = ingresoService.findAll();
        return new ResponseEntity<>(listaIngreso, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        var ingreso = ingresoService.findOne(id);
        return new ResponseEntity<>(ingreso, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        var ingreso = ingresoService.findOne(id).get();
        if (ingreso != null) {
            if (ingreso.getEstado().equals("H")) {

                ingreso.setEstado("D");
                return new ResponseEntity<>("Se deshabilito correctamente", HttpStatus.OK);
            } else
            ingreso.setEstado("H");
            return new ResponseEntity<>("Se habilitado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontro el registro", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("Ingreso") ingreso ingresoUpdate) {
        var ingreso = ingresoService.findOne(id).get();
        if (ingreso != null) {

            ingreso.setHabitacion(ingresoUpdate.getHabitacion());
            ingreso.setCama(ingresoUpdate.getCama());
            ingreso.setPaciente(ingresoUpdate.getPaciente());
            ingreso.setMedico(ingresoUpdate.getMedico());
            ingreso.setFecha_ingreso(ingresoUpdate.getFecha_ingreso());
            ingreso.setFecha_salida(ingresoUpdate.getFecha_salida());
            ingreso.setEstado(ingresoUpdate.getEstado());

            ingresoService.save(ingreso);
            return new ResponseEntity<>(ingreso, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Error ingreso N0 encontrado", HttpStatus.BAD_REQUEST);
        }
    }

}