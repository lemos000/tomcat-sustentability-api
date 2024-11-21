package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.beans.TipoFonte;
import br.com.fiap.dao.TipoFontesDAO;

public class TipoFonteBO {
    private TipoFontesDAO tipoFonteDAO;

    public TipoFonteBO() throws SQLException, ClassNotFoundException {
        this.tipoFonteDAO = new TipoFontesDAO();
    }

    public boolean cadastrarTipoFonte(TipoFonte tipoFonte) throws SQLException {
        try {
            return tipoFonteDAO.inserir(tipoFonte);
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar tipo de fonte: " + e.getMessage());
            throw e;
        }
    }

    public boolean deletarTipoFonte(int tipoFonteId) throws SQLException {
        try {
            return tipoFonteDAO.deletar(tipoFonteId);
        } catch (SQLException e) {
            System.out.println("Erro ao deletar tipo de fonte: " + e.getMessage());
            throw e;
        }
    }

    public String atualizarTipoFonte(TipoFonte tipoFonte) throws SQLException {
        try {
            return tipoFonteDAO.atualizar(tipoFonte);
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar tipo de fonte: " + e.getMessage());
            throw e;
        }
    }

    public TipoFonte selecionarTipoFonte(int tipoFonteId) throws SQLException {
        try {
            return tipoFonteDAO.selecionar(tipoFonteId);
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar tipo de fonte: " + e.getMessage());
            throw e;
        }
    }

    public List<TipoFonte> selecionarTodosTiposFontes() throws SQLException {
        try {
            return tipoFonteDAO.selecionarTodos();
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar todos os tipos de fontes: " + e.getMessage());
            throw e;
        }
    }
}