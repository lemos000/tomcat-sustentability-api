package br.com.fiap.beans;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
    private int id;
    private String nome;
    private String descricao;
    private List<Projeto> projetos;

    public Categoria(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.projetos = new ArrayList<>();
    }

    public void adicionarProjeto(Projeto projeto) {
        this.projetos.add(projeto);
    }

    public List<Projeto> listarProjetos() {
        return this.projetos;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}