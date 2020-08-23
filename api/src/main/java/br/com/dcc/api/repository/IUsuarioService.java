package br.com.dcc.api.repository;

import br.com.dcc.api.model.Usuario;
import br.com.dcc.api.utils.ValidationResult;

import java.util.List;

public interface IUsuarioService {
    ValidationResult add(Usuario usuario);
    ValidationResult getUsuarioById(long id);
    List<Usuario> getUsuarios();
}
