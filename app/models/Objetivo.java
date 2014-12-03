package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Objetivo implements Comparable<Objetivo> {
	
	@Id
    @GeneratedValue
    private long id;

    private final String FAZER = "FAZER";
    private final String FEITO = "FEITO";
    private final String FALHOU = "FALHOU";

    private String nome;
    private String descricao;
    private int semana;
    private Prioridade prioridade;
    private String status;

	public Objetivo() {}
	
	public Objetivo(String nome, Prioridade prioridade, String descricao, int semana) {
		this.nome = nome;
		this.prioridade = prioridade;
		this.descricao = descricao;
		this.semana = semana;
		this.status = FAZER;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		switch (status) {
		case FEITO:
			this.status = FEITO;
			break;
		case FAZER:
			this.status = FAZER;
			break;
		case FALHOU:
			this.status = FALHOU;
			break;
		default:
			break;
		}
	}
	
	public int getSemana() {
		return semana;
	}
	
	public void setSemana(int semana) {
		this.semana = semana;
	}
	
	public long getId() {
		return id;
	}
	
	@Override
	public int compareTo(Objetivo arg0) {
		return 0;
	}
}
