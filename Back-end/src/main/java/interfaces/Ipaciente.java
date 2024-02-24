package interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import models.paciente;


@Repository
public interface Ipaciente extends CrudRepository< paciente , String>{
    
}

