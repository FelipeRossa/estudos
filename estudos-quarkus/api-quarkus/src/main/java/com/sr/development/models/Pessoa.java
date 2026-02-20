package com.sr.development.models;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Pessoa extends PanacheEntity {

	public String nome;
	public int anoNascimento;

	public static List<Pessoa> findByAnoNascimento(int anoNascimento) {
		return find("anoNascimento", anoNascimento).list();
	}
}
