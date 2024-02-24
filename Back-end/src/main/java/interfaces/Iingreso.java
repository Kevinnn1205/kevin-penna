package interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import models.ingreso; 


@Repository
public interface Iingreso extends CrudRepository< ingreso , String>{
    
}
