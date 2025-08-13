package com.challenges.forumhub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional; // Verifique se esta importação existe

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}