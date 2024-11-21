package br.com.fiap.beans;

import java.util.List;

public class Parceiro {
    private int id;
    private String nome;
    private String tipo; // Tipo pode ser "Empresa", "ONG", "Indivíduo", etc.
    private String contato;
    private List<Projeto> projetosEnvolvidos;

    public Parceiro(int id, String nome, String tipo, String contato) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.contato = contato;
    }

    public void adicionarProjeto(Projeto projeto) {
        this.projetosEnvolvidos.add(projeto);
    }

    public List<Projeto> listarProjetosEnvolvidos() {
        return this.projetosEnvolvidos;
    }

    // Getters e setters 

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}