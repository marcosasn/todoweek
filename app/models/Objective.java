package models;

public class Objective {
	
	private String nome;
	private int prioridade;
	private String descricao;
	private boolean status;
	private String deadline;
	
	public Objective() {}
	
	public Objective(String nome, String prioridade, String descricao, String deadline) {
		this.nome = nome;
		this.prioridade = Integer.parseInt(prioridade);
		this.descricao = descricao;
		this.deadline = deadline;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean getStatus() {
		return status;
	}

	public void ehAlcancada(boolean alcancada) {
		this.status = alcancada;
	}
	
	public String getDeadline() {
		return deadline;
	}
	
	public void setDeadline(String semana) {
		deadline = semana;
	}
}
