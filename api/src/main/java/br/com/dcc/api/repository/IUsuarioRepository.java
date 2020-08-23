package br.com.dcc.api.repository;

import br.com.dcc.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository  extends JpaRepository<Usuario, Long> {

    Usuario findByLogin(String login);

    Usuario findByLoginAndSenha(String login, String senha);
}
