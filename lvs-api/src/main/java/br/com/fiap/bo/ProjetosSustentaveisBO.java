package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.beans.ProjetoSustentavel;
import br.com.fiap.dao.ProjetosSustentaveisDAO;

public class ProjetosSustentaveisBO {
    private ProjetosSustentaveisDAO projetoDAO;

    public ProjetosSustentaveisBO() throws SQLException, ClassNotFoundException {
        this.projetoDAO = new ProjetosSustentaveisDAO();
    }

    public boolean cadastrarProjeto(ProjetoSustentavel projeto) throws SQLException {
        try {
            return projetoDAO.inserir(projeto);
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar projeto: " + e.getMessage());
            throw e;
        }
    }

    public boolean deletarProjeto(int projetoId) throws SQLException {
        try {
            return projetoDAO.deletar(projetoId);
        } catch (SQLException e) {
            System.out.println("Erro ao deletar projeto: " + e.getMessage());
            throw e;
        }
    }

    public String atualizarProjeto(ProjetoSustentavel projeto) throws SQLException {
        try {
            return projetoDAO.atualizar(projeto);
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar projeto: " + e.getMessage());
            throw e;
        }
    }

    public ProjetoSustentavel selecionarProjeto(int projetoId) throws SQLException {
        try {
            return projetoDAO.selecionar(projetoId);
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar projeto: " + e.getMessage());
            throw e;
        }
    }

    public List<ProjetoSustentavel> selecionarTodosProjetos() throws SQLException {
        try {
            return projetoDAO.selecionarTodos();
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar todos os projetos: " + e.getMessage());
            throw e;
        }
    }
}