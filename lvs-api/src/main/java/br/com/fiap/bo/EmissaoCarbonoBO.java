package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.beans.EmissaoCarbono;
import br.com.fiap.dao.EmissoesCarbonoDAO;

public class EmissaoCarbonoBO {
    private EmissoesCarbonoDAO emissaoDAO;

    public EmissaoCarbonoBO() throws SQLException, ClassNotFoundException {
        this.emissaoDAO = new EmissoesCarbonoDAO();
    }

    public boolean cadastrarEmissao(EmissaoCarbono emissao) throws SQLException {
        try {
            return emissaoDAO.inserir(emissao);
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar emissão de carbono: " + e.getMessage());
            throw e;
        }
    }

    public boolean deletarEmissao(int emissaoId) throws SQLException {
        try {
            return emissaoDAO.deletar(emissaoId);
        } catch (SQLException e) {
            System.out.println("Erro ao deletar emissão de carbono: " + e.getMessage());
            throw e;
        }
    }

    public String atualizarEmissao(EmissaoCarbono emissao) throws SQLException {
        try {
            return emissaoDAO.atualizar(emissao);
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar emissão de carbono: " + e.getMessage());
            throw e;
        }
    }

    public EmissaoCarbono selecionarEmissao(int emissaoId) throws SQLException {
        try {
            return emissaoDAO.selecionar(emissaoId);
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar emissão de carbono: " + e.getMessage());
            throw e;
        }
    }

    public List<EmissaoCarbono> selecionarTodasEmissoes() throws SQLException {
        try {
            return emissaoDAO.selecionarTodos();
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar todas as emissões de carbono: " + e.getMessage());
            throw e;
        }
    }
}