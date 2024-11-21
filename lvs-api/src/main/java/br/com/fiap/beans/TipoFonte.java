package br.com.fiap.beans;

public class TipoFonte {
    private int id;
    private String nome;
    
    
    public TipoFonte(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public TipoFonte() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}