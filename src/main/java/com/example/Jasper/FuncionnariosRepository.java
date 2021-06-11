package com.example.Jasper;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Jasper.banco.Funcionarios;

@Repository
public interface FuncionnariosRepository extends CrudRepository<Funcionarios, Integer> {
	
	@Query(value = "select "
			+ "f.id_funcionario, f.nome, f.data_nascimento,f. salario, "
			+ "e.razao_social, e.cnpj, e.telefone, e.email "
			+ "from funcionarios f, empresas e", nativeQuery = true)
	public List<Funcionarios> getAll();
	
}
