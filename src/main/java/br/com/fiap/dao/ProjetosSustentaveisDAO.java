package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.ProjetoSustentavel;
import br.com.fiap.connection.*;

public class ProjetosSustentaveisDAO {

    public Connection minhaConexao;

    public ProjetosSustentaveisDAO() throws ClassNotFoundException, SQLException {
        super();
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public boolean inserir(ProjetoSustentavel projeto) throws SQLException {
        String sql = "INSERT INTO PF0645.PROJETOS_SUSTENTAVEIS (descricao, custo, status, id_tipo_fonte, id_regiao) VALUES (?, ?, ?, ?, ?)";
        String[] generatedColumns = {"id_projeto"};

        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql, generatedColumns)) {
            stmt.setString(1, projeto.getDescricao());
            stmt.setDouble(2, projeto.getCusto());
            stmt.setString(3, projeto.getStatus());
            stmt.setInt(4, projeto.getIdTipoFonte());
            stmt.setInt(5, projeto.getIdRegiao());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        projeto.setId(rs.getInt(1));
                    }
                }
                return true;
            }
            stmt.close();

            return false;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir projeto sustentável: " + e.getMessage());
            throw e;
        }
    }

    public boolean deletar(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "DELETE FROM PF0645.PROJETOS_SUSTENTAVEIS WHERE id_projeto = ?");
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
        return true;
    }

    public String atualizar(ProjetoSustentavel projeto) throws SQLException {
        String sql = "UPDATE PF0645.PROJETOS_SUSTENTAVEIS SET descricao = ?, custo = ?, status = ?, id_tipo_fonte = ?, id_regiao = ? WHERE id_projeto = ?";
        PreparedStatement stmt = minhaConexao.prepareStatement(sql);
        stmt.setString(1, projeto.getDescricao());
        stmt.setDouble(2, projeto.getCusto());
        stmt.setString(3, projeto.getStatus());
        stmt.setInt(4, projeto.getIdTipoFonte());
        stmt.setInt(5, projeto.getIdRegiao());
        stmt.setInt(6, projeto.getId());
        stmt.executeUpdate();
        stmt.close();
        return "Atualizado com Sucesso!";
    }

    public ProjetoSustentavel selecionar(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM PF0645.PROJETOS_SUSTENTAVEIS WHERE id_projeto = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            ProjetoSustentavel projeto = new ProjetoSustentavel();
            projeto.setId(rs.getInt("id_projeto"));
            projeto.setDescricao(rs.getString("descricao"));
            projeto.setCusto(rs.getDouble("custo"));
            projeto.setStatus(rs.getString("status"));
            projeto.setIdTipoFonte(rs.getInt("id_tipo_fonte"));
            projeto.setIdRegiao(rs.getInt("id_regiao"));
            rs.close();
            stmt.close();
            return projeto;
        }
        return null;
    }

    public List<ProjetoSustentavel> selecionarTodos() throws SQLException {
        List<ProjetoSustentavel> listaProjetos = new ArrayList<>();

        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM PF0645.PROJETOS_SUSTENTAVEIS ORDER BY id_projeto");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            ProjetoSustentavel projeto = new ProjetoSustentavel();
            projeto.setId(rs.getInt("id_projeto"));
            projeto.setDescricao(rs.getString("descricao"));
            projeto.setCusto(rs.getDouble("custo"));
            projeto.setStatus(rs.getString("status"));
            projeto.setIdTipoFonte(rs.getInt("id_tipo_fonte"));
            projeto.setIdRegiao(rs.getInt("id_regiao"));
            listaProjetos.add(projeto);
        }

        rs.close();
        stmt.close();
        return listaProjetos;
    }
}