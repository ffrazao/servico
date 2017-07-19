package br.com.servico.banco_dados.h2.padrao.dao.sistema;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.servico.banco_dados.h2.padrao.modelo.sistema.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Integer> {

}