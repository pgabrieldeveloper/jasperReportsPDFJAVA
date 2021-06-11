package com.example.Jasper.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.sql.DataSource;

import org.springframework.data.annotation.Id;

@Entity
public class Funcionarios {

	@Id
	@Column(name = "id_funcionario")
	private Integer idFuncionario;
	@Column(name = "nome")
	private String nome;
	@Column(name = "data_nascimento")
	private DataSource dataNascimento;
	@Column(name = "salario")
	private Double salario;
	@Column(name = "razao_social")
	private String razaoSocial;
	@Column(name = "cnpj")
	private String cnpj;
	@Column(name = "telefone")
	private String telefone;
	@Column(name = "email")
	private String email;

}
