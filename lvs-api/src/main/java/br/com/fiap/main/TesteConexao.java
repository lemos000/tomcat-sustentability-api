package br.com.fiap.main;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.fiap.connection.*;


public class TesteConexao {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Connection c = null;

		try {
			c = new ConexaoFactory().conexao();
			System.out.println("Conectado com o Banco de Dados");
		} catch (Exception e) {
			System.out.println("Erro na conexão");
		} finally {

		}
		c.close();

	}

}
