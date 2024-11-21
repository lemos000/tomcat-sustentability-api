package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.EmissaoCarbono;
import br.com.fiap.connection.*;

public class EmissoesCarbonoDAO {

    public Connection minhaConexao;

    public EmissoesCarbonoDAO() throws ClassNotFoundException, SQLException {
        super();
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public boolean inserir(EmissaoCarbono emissao) throws SQLException {
        String sql = "INSERT INTO PF0645.EMISSOES_CARBONO (id_tipo_fonte, emissao) VALUES (?, ?)";
        String[] generatedColumns = {"id_emissao"};

        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql, generatedColumns)) {
            stmt.setInt(1, emissao.getIdTipoFonte());
            stmt.setDouble(2, emissao.getEmissao());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        emissao.setId(rs.getInt(1));
                    }
                }
                return true;
            }
            stmt.close();

            return false;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir emissão de carbono: " + e.getMessage());
            throw e;
        }
    }

    public boolean deletar(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "DELETE FROM PF0645.EMISSOES_CARBONO WHERE id_emissao = ?");
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
        return true;
    }

    public String atualizar(EmissaoCarbono emissao) throws SQLException {
        String sql = "UPDATE PF0645.EMISSOES_CARBONO SET id_tipo_fonte = ?, emissao = ? WHERE id_emissao = ?";
        PreparedStatement stmt = minhaConexao.prepareStatement(sql);
        stmt.setInt(1, emissao.getIdTipoFonte());
        stmt.setDouble(2, emissao.getEmissao());
        stmt.setInt(3, emissao.getId());
        stmt.executeUpdate();
        stmt.close();
        return "Atualizado com Sucesso!";
    }

    public EmissaoCarbono selecionar(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM PF0645.EMISSOES_CARBONO WHERE id_emissao = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            EmissaoCarbono emissao = new EmissaoCarbono();
            emissao.setId(rs.getInt("id_emissao"));
            emissao.setIdTipoFonte(rs.getInt("id_tipo_fonte"));
            emissao.setEmissao(rs.getDouble("emissao"));
            rs.close();
            stmt.close();
            return emissao;
        }
        return null;
    }

    public List<EmissaoCarbono> selecionarTodos() throws SQLException {
        List<EmissaoCarbono> listaEmissoes = new ArrayList<>();

        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM PF0645.EMISSOES_CARBONO ORDER BY id_emissao");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            EmissaoCarbono emissao = new EmissaoCarbono();
            emissao.setId(rs.getInt("id_emissao"));
            emissao.setIdTipoFonte(rs.getInt("id_tipo_fonte"));
            emissao.setEmissao(rs.getDouble("emissao"));
            listaEmissoes.add(emissao);
        }

        rs.close();
        stmt.close();
        return listaEmissoes;
    }
}