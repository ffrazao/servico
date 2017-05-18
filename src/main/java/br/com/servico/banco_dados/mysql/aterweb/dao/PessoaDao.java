package br.com.servico.banco_dados.mysql.aterweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.servico.banco_dados.mysql.aterweb.modelo.pessoa.Pessoa;

public interface PessoaDao extends JpaRepository<Pessoa, Integer>, PessoaDaoCustom {

}