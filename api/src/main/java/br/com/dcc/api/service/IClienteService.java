package br.com.dcc.api.service;

import br.com.dcc.api.model.Cliente;
import br.com.dcc.api.utils.ValidationResult;

import java.util.List;

public interface IClienteService{
    ValidationResult add(Cliente cliente);
    ValidationResult delete(long id);
    ValidationResult update(long id, Cliente cliente);
    ValidationResult getClienteById(long id);
    List<Cliente> getClientes();
}
