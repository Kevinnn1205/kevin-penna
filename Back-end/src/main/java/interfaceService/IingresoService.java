package interfaceService;

import java.util.List;
import java.util.Optional;

import models.ingreso; 

public interface IingresoService {

    public String save(ingreso ingreso);

    public List<ingreso> findAll();

    public Optional<ingreso> findOne(String id);

    public int delete(String id);
}