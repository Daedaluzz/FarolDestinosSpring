package com.FDMVC.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Pacote extends Entidade{
	
    @Column(nullable = false)
	private String nome;
    
    @Column(nullable = false)
	private String destino;
	
    @Column(name ="dias_de_hospedagem", nullable = false)
	private int diasHospedagem;
	
    @Column(name ="data_da_ida", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
	private LocalDateTime ida;
    
    @Column(name ="data_da_volta", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
	private LocalDateTime volta;
    
    @Column(nullable = false)
	private boolean promocao;
	
    @Column(nullable = false)
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal preco;
	
	@ManyToMany(mappedBy = "pacotes", fetch = FetchType.LAZY)
	private Set<Viagem> viagens = new HashSet<Viagem>();

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name ="pacote_passagens",
	joinColumns = @JoinColumn(name = "pacote_id_fk"),
	inverseJoinColumns = @JoinColumn(name = "passagem_id_fk")
	)
	private Set<Passagem> passagensP = new HashSet<Passagem>();



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public int getDiasHospedagem() {
		return diasHospedagem;
	}

	public void setDiasHospedagem(int diasHospedagem) {
		this.diasHospedagem = diasHospedagem;
	}

	public LocalDateTime getIda() {
		return ida;
	}

	public void setIda(LocalDateTime ida) {
		this.ida = ida;
	}

	public LocalDateTime getVolta() {
		return volta;
	}

	public void setVolta(LocalDateTime volta) {
		this.volta = volta;
	}

	public boolean isPromocao() {
		return promocao;
	}

	public void setPromocao(boolean promocao) {
		this.promocao = promocao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Set<Viagem> getViagens() {
		return viagens;
	}

	public void setViagens(Set<Viagem> viagens) {
		this.viagens = viagens;
	}

	public Set<Passagem> getPassagensP() {
		return passagensP;
	}

	public void setPassagensP(Set<Passagem> passagensP) {
		this.passagensP = passagensP;
	}

	public void addpassagem(Passagem passagem) {
		passagensP.add(passagem);
		passagem.getPacotes().add(this);
	}
		
	public void removePassagem(Passagem passagem) {
		passagensP.remove(passagem);
		passagem.getPacotes().remove(this);
	}
	
	

}
