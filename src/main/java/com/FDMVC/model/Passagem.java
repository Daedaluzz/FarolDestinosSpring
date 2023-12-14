package com.FDMVC.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.FDMVC.enums.Portao;
import com.FDMVC.enums.Terminal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;

@Entity
public class Passagem extends Entidade{
	
	@Column(nullable = false)
	private String cidadeDestino;
	
	@Column(nullable = false)
	private String cidadeOrigem;
	
	@Column(nullable = false, unique = true)
	private String numeroBilhete;
	
	@Column(nullable = false, unique = true)
	private String assento;
	
	@Column(nullable = false)
    @Enumerated(EnumType.STRING)
	private Portao portao;
    
	@Column(nullable = false)
    @Enumerated(EnumType.STRING)
	private Terminal terminal;
    
    @Column(name ="data_da_compra", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	private LocalDateTime Embarque;
	
    @Column(nullable = false)
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal preco;
    
	@ManyToMany(mappedBy = "passagensV", fetch = FetchType.LAZY)
	private List<Viagens> viagens;
	
	@ManyToMany(mappedBy = "passagensP", fetch = FetchType.LAZY)
	private List<Pacote> pacotes;

	public String getCidadeDestino() {
		return cidadeDestino;
	}

	public void setCidadeDestino(String cidadeDestino) {
		this.cidadeDestino = cidadeDestino;
	}

	public String getCidadeOrigem() {
		return cidadeOrigem;
	}

	public void setCidadeOrigem(String cidadeOrigem) {
		this.cidadeOrigem = cidadeOrigem;
	}

	public String getNumeroBilhete() {
		return numeroBilhete;
	}

	public void setNumeroBilhete(String numeroBilhete) {
		this.numeroBilhete = numeroBilhete;
	}

	public String getAssento() {
		return assento;
	}

	public void setAssento(String assento) {
		this.assento = assento;
	}

	public Portao getPortao() {
		return portao;
	}

	public void setPortao(Portao portao) {
		this.portao = portao;
	}

	public Terminal getTerminal() {
		return terminal;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}

	public LocalDateTime getEmbarque() {
		return Embarque;
	}

	public void setEmbarque(LocalDateTime embarque) {
		Embarque = embarque;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public List<Viagens> getViagens() {
		return viagens;
	}

	public void setViagens(List<Viagens> viagens) {
		this.viagens = viagens;
	}

	public List<Pacote> getPacotes() {
		return pacotes;
	}

	public void setPacotes(List<Pacote> pacotes) {
		this.pacotes = pacotes;
	}
	
	
}
