package br.com.fiap.beans;

public class Avaliacao {
    private int id;
    private int nota;
    private String comentario;
    private Usuario usuario;
    private Projeto projeto;

    public Avaliacao(int id, int nota, String comentario, Usuario usuario, Projeto projeto) {
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
        this.usuario = usuario;
        this.projeto = projeto;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
    

}