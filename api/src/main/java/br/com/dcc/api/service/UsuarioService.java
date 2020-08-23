package br.com.dcc.api.service;

import br.com.dcc.api.exception.ExceptionNotFound;
import br.com.dcc.api.model.Usuario;
import br.com.dcc.api.repository.IUsuarioRepository;
import br.com.dcc.api.repository.IUsuarioService;
import br.com.dcc.api.utils.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    IUsuarioRepository repository;

    @Override
    public List<Usuario> getUsuarios() {
        return repository.findAll();
    }

    @Override
    public ValidationResult add(Usuario usuario) {
        Optional<Usuario> optionalUsuario = Optional.ofNullable(repository.findByLogin(usuario.getLogin()));
        if(!optionalUsuario.isEmpty())
            return new ValidationResult(true, "Usuário já cadastrado.");

        if(usuario.getLogin().isEmpty() || usuario.getLogin().isBlank())
            return new ValidationResult(true, "Informe um login.");

        if(usuario.getSenha().isEmpty() || usuario.getSenha().isBlank())
            return new ValidationResult(true, "Informe uma senha.");

        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        repository.save(toEntity(usuario));

        return new ValidationResult(false);
    }

    @Override
    public ValidationResult getUsuarioById(long id) {
        Optional<Usuario> optionalUsuario = repository.findById(id);
        try {
            optionalUsuario.orElseThrow(() -> new ExceptionNotFound("Usuário não encontrado com id: " + id));
        }catch (ExceptionNotFound e){
            return new ValidationResult(true, e.getMessage());
        }
        return new ValidationResult(optionalUsuario, false, null);
    }

    private Usuario toEntity(Usuario usuario) {
        Usuario entity = usuario;
        return entity;
    }
}
