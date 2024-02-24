package interfaceService;

import java.util.List;
import java.util.Optional;

import models.medico; 

public interface ImedicoService {

    public String save(medico Medico);

    public List<medico> findAll();

    public Optional<medico> findOne(String id);

    public int delete(String id);
}
