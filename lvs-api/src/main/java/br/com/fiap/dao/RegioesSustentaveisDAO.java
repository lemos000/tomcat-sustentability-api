package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.RegiaoSustentavel;
import br.com.fiap.connection.*;

public class RegioesSustentaveisDAO {

    public Connection minhaConexao;

    public RegioesSustentaveisDAO() throws ClassNotFoundException, SQLException {
        super();
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public boolean inserir(RegiaoSustentavel regiao) throws SQLException {
        String sql = "INSERT INTO tb_REGiOES_SUSTENTAVEIS (nome) VALUES (?)";
        String[] generatedColumns = {"id_regiao"};

        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql, generatedColumns)) {
            stmt.setString(1, regiao.getNome());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        regiao.setId(rs.getInt(1));
                    }
                }
                return true;
            }
            stmt.close();

            return false;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir região sustentável: " + e.getMessage());
            throw e;
        }
    }

    public boolean deletar(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "DELETE FROM tb_REGiOES_SUSTENTAVEIS WHERE id_regiao = ?");
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
        return true;
    }

    public String atualizar(RegiaoSustentavel regiao) throws SQLException {
        String sql = "UPDATE tb_REGiOES_SUSTENTAVEIS SET nome = ? WHERE id_regiao = ?";
        PreparedStatement stmt = minhaConexao.prepareStatement(sql);
        stmt.setString(1, regiao.getNome());
        stmt.setInt(2, regiao.getId());
        stmt.executeUpdate();
        stmt.close();
        return "Atualizado com Sucesso!";
    }

    public RegiaoSustentavel selecionar(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM tb_REGiOES_SUSTENTAVEIS WHERE id_regiao = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            RegiaoSustentavel regiao = new RegiaoSustentavel();
            regiao.setId(rs.getInt("id_regiao"));
            regiao.setNome(rs.getString("nome"));
            rs.close();
            stmt.close();
            return regiao;
        }
        return null;
    }

    public List<RegiaoSustentavel> selecionarTodos() throws SQLException {
        List<RegiaoSustentavel> listaRegioes = new ArrayList<>();

        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM tb_REGiOES_SUSTENTAVEIS ORDER BY id_regiao");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            RegiaoSustentavel regiao = new RegiaoSustentavel();
            regiao.setId(rs.getInt("id_regiao"));
            regiao.setNome(rs.getString("nome"));
            listaRegioes.add(regiao);
        }

        rs.close();
        stmt.close();
        return listaRegioes;
    }
}