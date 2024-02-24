package interfaceService;

import java.util.List;
import java.util.Optional;

import models.paciente;

public interface IpacienteService {

    public String save(paciente Paciente);

    public List<paciente> findAll();

    public Optional<paciente> findOne(String id);

    public int delete(String id);
}
