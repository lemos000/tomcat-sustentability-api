package br.com.fiap.main;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.beans.ProjetoSustentavel;
import br.com.fiap.dao.ProjetosSustentaveisDAO;

public class TesteSelecionar {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ProjetosSustentaveisDAO dao = new ProjetosSustentaveisDAO();
		List<ProjetoSustentavel> ps = dao.selecionarTodos();
		if(ps != null) {
			System.out.println(ps);
			}
		}

	}


