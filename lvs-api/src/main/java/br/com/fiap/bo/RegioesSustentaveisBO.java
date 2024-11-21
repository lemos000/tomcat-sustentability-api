package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.beans.RegiaoSustentavel;
import br.com.fiap.dao.RegioesSustentaveisDAO;

public class RegioesSustentaveisBO {
    private RegioesSustentaveisDAO regiaoDAO;

    public RegioesSustentaveisBO() throws SQLException, ClassNotFoundException {
        this.regiaoDAO = new RegioesSustentaveisDAO();
    }

    public boolean cadastrarRegiao(RegiaoSustentavel regiao) throws SQLException {
        try {
            return regiaoDAO.inserir(regiao);
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar região sustentável: " + e.getMessage());
            throw e;
        }
    }

    public boolean deletarRegiao(int regiaoId) throws SQLException {
        try {
            return regiaoDAO.deletar(regiaoId);
        } catch (SQLException e) {
            System.out.println("Erro ao deletar região sustentável: " + e.getMessage());
            throw e;
        }
    }

    public String atualizarRegiao(RegiaoSustentavel regiao) throws SQLException {
        try {
            return regiaoDAO.atualizar(regiao);
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar região sustentável: " + e.getMessage());
            throw e;
        }
    }

    public RegiaoSustentavel selecionarRegiao(int regiaoId) throws SQLException {
        try {
            return regiaoDAO.selecionar(regiaoId);
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar região sustentável: " + e.getMessage());
            throw e;
        }
    }

    public List<RegiaoSustentavel> selecionarTodasRegioes() throws SQLException {
        try {
            return regiaoDAO.selecionarTodos();
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar todas as regiões sustentáveis: " + e.getMessage());
            throw e;
        }
    }
}