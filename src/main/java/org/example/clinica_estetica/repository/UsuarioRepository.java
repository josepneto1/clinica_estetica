package org.example.clinica_estetica.repository;

import org.example.clinica_estetica.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository  extends CrudRepository<Usuario, Long> {

    @Query(value = "select u from Usuario u where u.login = ?1")
    Usuario encontrarUsuarioPorLogin(String login);
}
