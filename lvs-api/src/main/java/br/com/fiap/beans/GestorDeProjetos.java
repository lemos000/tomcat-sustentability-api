package br.com.fiap.beans;

import java.util.ArrayList;
import java.util.List;

public class GestorDeProjetos {
    private List<Projeto> projetos;

    public GestorDeProjetos() {
        this.projetos = new ArrayList<>();
    }

    public void adicionarProjeto(Projeto projeto) {
        this.projetos.add(projeto);
    }

    public void atualizarProjeto(int id, Projeto projetoAtualizado) {
        Projeto projeto = buscarProjetoPorId(id);
        if (projeto != null) {
            projeto.setDescricao(projetoAtualizado.getDescricao());
            projeto.setCusto(projetoAtualizado.getCusto());
            projeto.setStatus(projetoAtualizado.getStatus());
            projeto.setTipoFonte(projetoAtualizado.getTipoFonte());
            projeto.setRegiao(projetoAtualizado.getRegiao());
        }
    }

    public Projeto buscarProjetoPorId(int id) {
        return this.projetos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public List<Projeto> listarTodosProjetos() {
        return this.projetos;
    }
}