package br.com.fiap.beans;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private List<Projeto> projetos;

    public Usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.projetos = new ArrayList<>();
    }

    public void adicionarProjeto(Projeto projeto) {
        this.projetos.add(projeto);
    }

    public List<Projeto> listarProjetos() {
        return this.projetos;
    }

    public Projeto buscarProjetoPorIdPorUser(int id) {
        return this.projetos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}
    
    

}