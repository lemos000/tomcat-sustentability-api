package br.com.fiap.beans;

import java.util.List;

public class Projeto {
    private int id;
    private String descricao;
    private double custo;
    private String status;
    private TipoFonte tipoFonte;
    private RegiaoSustentavel regiao;
    private List<Avaliacao> avaliacoes;

    public Projeto(int id, String descricao, double custo, String status,
                   TipoFonte tipoFonte, RegiaoSustentavel regiao) {
        this.id = id;
        this.descricao = descricao;
        this.custo = custo;
        this.status = status;
        this.tipoFonte = tipoFonte;
        this.regiao = regiao;
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
    }

    public double calcularMediaAvaliacoes() {
        return this.avaliacoes.stream().mapToInt(Avaliacao::getNota).average().orElse(0.0);
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TipoFonte getTipoFonte() {
		return tipoFonte;
	}

	public void setTipoFonte(TipoFonte tipoFonte) {
		this.tipoFonte = tipoFonte;
	}

	public RegiaoSustentavel getRegiao() {
		return regiao;
	}

	public void setRegiao(RegiaoSustentavel regiao) {
		this.regiao = regiao;
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	
	// Regras de negócios abaixo:
	
	 public void gerarRelatorio(List<Projeto> projetos) {
	        projetos.forEach(projeto -> {
	            System.out.println("Projeto ID: " + projeto.getId());
	            System.out.println("Descrição: " + projeto.getDescricao());
	            System.out.println("Custo: " + projeto.getCusto());
	            System.out.println("Status: " + projeto.getStatus());
	            System.out.println("Tipo de Fonte: " + projeto.getTipoFonte().getNome());
	            System.out.println("Região: " + projeto.getRegiao().getNome());
	            System.out.println("Média de Avaliações: " + projeto.calcularMediaAvaliacoes());
	            System.out.println("====================================");
	        });
	    }


     public static boolean validarProjeto(Projeto projeto) {
         if (projeto.getDescricao() == null || projeto.getDescricao().isEmpty()) {
             System.err.println("Descrição inválida.");
             return false;
         }
         if (projeto.getCusto() < 0) {
             System.err.println("Custo inválido.");
             return false;
         }
         if (projeto.getTipoFonte() == null) {
             System.err.println("Tipo de fonte não pode ser nulo.");
             return false;
         }
         if (projeto.getRegiao() == null) {
             System.err.println("Região não pode ser nula.");
             return false;
         }
         return true;
     }
 
}