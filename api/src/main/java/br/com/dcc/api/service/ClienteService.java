package br.com.dcc.api.service;

import br.com.dcc.api.exception.ExceptionNotFound;
import br.com.dcc.api.model.Cliente;
import br.com.dcc.api.model.Contato;
import br.com.dcc.api.model.Endereco;
import br.com.dcc.api.repository.IClienteRepository;
import br.com.dcc.api.utils.CpfCnpjUtils;
import br.com.dcc.api.utils.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository repository;

    @Override
    public ValidationResult add(Cliente cliente) {
        ValidationResult validacaoNome = validaNome(cliente);
        if (validacaoNome.getError()) return validacaoNome;

        if (CpfCnpjUtils.isValid(cliente.getCpfCnpj())) {
            ValidationResult validacaoEndereco = validaEndereco(cliente);
            if (validacaoEndereco.getError())
                return validacaoEndereco;
            else
                return save(cliente);
        }

        else
            return new ValidationResult(true, "CPF/CNPJ inválido.");
    }

    @Override
    public ValidationResult delete(long id) {
        Optional<Cliente> optionalCliente = repository.findById(id);
        try {
            optionalCliente.orElseThrow(() -> new ExceptionNotFound(MessageFormat.format("Cliente não encontrado com id: {0}", id)));
        } catch (ExceptionNotFound e) {
            return new ValidationResult(true, e.getMessage());
        }
        repository.deleteById(id);

        return new ValidationResult(optionalCliente, false, null);
    }

    @Override
    public ValidationResult update(long id, Cliente cliente) {
        Cliente lcliente;
        ValidationResult validacaoNome = validaNome(cliente);
        if (validacaoNome.getError()) return validacaoNome;

        if (CpfCnpjUtils.isValid(cliente.getCpfCnpj())) {
            try {
                lcliente = getCliente(id);
            } catch (ExceptionNotFound e) {
                return new ValidationResult(true, e.getMessage());
            }

            if (lcliente != null) {
                lcliente = cliente;
                ValidationResult validacaoEndereco = validaEndereco(lcliente);
                if(validacaoEndereco.getError())
                    return validacaoEndereco;

                for( Endereco endereco : lcliente.getEnderecos()) endereco.setCliente_endereco(lcliente);
                for( Contato contato : lcliente.getContatos()) contato.setCliente_contato(lcliente);

                return save(lcliente);
            } else {
                return new ValidationResult(true, "Ocorreu um erro ao atualizar o cliente.");
            }
        } else
            return new ValidationResult(true, "CPF/CNPJ inválido.");
    }

    @Override
    public List<Cliente> getClientes() {
        return repository.findAll();
    }

    @Override
    public ValidationResult getClienteById(long id) {
        Optional<Cliente> optionalCliente = repository.findById(id);
        try {
            optionalCliente.orElseThrow(() -> new ExceptionNotFound(MessageFormat.format("Cliente não encontrado com id: {0}", id)));
        } catch (ExceptionNotFound e) {
            return new ValidationResult(true, e.getMessage());
        }
        return new ValidationResult(optionalCliente, false, null);
    }

    private Cliente getCliente(long id) {
        Optional<Cliente> optionalCliente = repository.findById(id);

        return optionalCliente.orElseThrow(() -> new ExceptionNotFound(MessageFormat.format("Cliente não encontrado com id: {0}", id)));
    }

    private ValidationResult validaEndereco(Cliente cliente){
        ValidationResult validateInputs = validaEnderecoCampos(cliente);
        if (cliente.getEnderecos().isEmpty())
            return new ValidationResult(true, "Deve ter ao menos 1 endereço.");
        else if (validateInputs.getError())
            return validateInputs;

        long enderecoPrincipal = cliente.getEnderecos().stream().filter(Endereco::isPrincipal).count();
        if (enderecoPrincipal == 0)
            return new ValidationResult(true, "Deve ter 1 endereço principal.");
        else if (enderecoPrincipal > 1)
            return new ValidationResult(true, "Deve ter apenas 1 endereço principal.");

        return new ValidationResult(false);
    }

    private ValidationResult save(Cliente cliente) {
        try {
            cliente.getContatos().forEach((contato) -> contato.setCliente_contato(cliente));
            cliente.getEnderecos().forEach((endereco) -> endereco.setCliente_endereco(cliente));
            repository.save(cliente);

            return new ValidationResult(false);
        } catch (DataIntegrityViolationException e) {
            return new ValidationResult(true, "CPF/CNPJ já foi utilizado em nossos cadastros.");
        }
    }

    private ValidationResult validaNome(Cliente cliente) {
        if (cliente.getNome().isBlank())
            return new ValidationResult(true, "Informe o campo nome.");
        else
            return new ValidationResult(false);
    }

    private ValidationResult validaEnderecoCampos(Cliente cliente) {
        for (Endereco endereco : cliente.getEnderecos()) {
            if (endereco.getRua().isBlank())
                return new ValidationResult(true, "Informe o campo rua.");

            if (endereco.getBairro().isBlank())
                return new ValidationResult(true, "Informe o campo bairro.");
        }

        return new ValidationResult(false);
    }
}
