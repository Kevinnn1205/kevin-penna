package interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import models.medico;


@Repository
public interface Imedico extends CrudRepository< medico , String>{
    
}
