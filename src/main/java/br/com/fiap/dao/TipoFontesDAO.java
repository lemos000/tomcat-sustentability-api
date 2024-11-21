package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.TipoFonte;
import br.com.fiap.connection.ConexaoFactory;

public class TipoFontesDAO {

    public Connection minhaConexao;

    public TipoFontesDAO() throws ClassNotFoundException, SQLException {
        super();
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public boolean inserir(TipoFonte tipoFonte) throws SQLException {
        String sql = "INSERT INTO PF0645.TIPO_FONTES (nome) VALUES (?)";
        String[] generatedColumns = {"id_tipo_fonte"};

        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql, generatedColumns)) {
            stmt.setString(1, tipoFonte.getNome());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        tipoFonte.setId(rs.getInt(1));
                    }
                }
                return true;
            }
            stmt.close();

            return false;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir tipo de fonte: " + e.getMessage());
            throw e;
        }
    }

    public boolean deletar(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "DELETE FROM PF0645.TIPO_FONTES WHERE id_tipo_fonte = ?");
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
        return true;
    }

    public String atualizar(TipoFonte tipoFonte) throws SQLException {
        String sql = "UPDATE PF0645.TIPO_FONTES SET nome = ? WHERE id_tipo_fonte = ?";
        PreparedStatement stmt = minhaConexao.prepareStatement(sql);
        stmt.setString(1, tipoFonte.getNome());
        stmt.setInt(2, tipoFonte.getId());
        stmt.executeUpdate();
        stmt.close();
        return "Atualizado com Sucesso!";
    }

    public TipoFonte selecionar(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM PF0645.TIPO_FONTES WHERE id_tipo_fonte = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            TipoFonte tipoFonte = new TipoFonte();
            tipoFonte.setId(rs.getInt("id_tipo_fonte"));
            tipoFonte.setNome(rs.getString("nome"));
            rs.close();
            stmt.close();
            return tipoFonte;
        }
        return null;
    }

    public List<TipoFonte> selecionarTodos() throws SQLException {
        List<TipoFonte> listaTipoFontes = new ArrayList<>();

        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM PF0645.TIPO_FONTES ORDER BY id_tipo_fonte");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            TipoFonte tipoFonte = new TipoFonte();
            tipoFonte.setId(rs.getInt("id_tipo_fonte"));
            tipoFonte.setNome(rs.getString("nome"));
            listaTipoFontes.add(tipoFonte);
        }

        rs.close();
        stmt.close();
        return listaTipoFontes;
    }
}