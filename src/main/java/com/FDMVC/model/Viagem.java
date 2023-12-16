package com.FDMVC.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Viagem extends Entidade {

    
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id_fk", nullable = false)
	private Usuario usuario;
    
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name ="viagens_pacotes",
	joinColumns = @JoinColumn(name = "viagem_id_fk"),
	inverseJoinColumns = @JoinColumn(name = "pacote_id_fk")
	)
	private Set<Pacote> pacotes = new HashSet<Pacote>();
    
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name ="viagens_passagens",
	joinColumns = @JoinColumn(name = "viagem_id_fk"),
	inverseJoinColumns = @JoinColumn(name = "passagem_id_fk")
	)
	private Set<Passagem> passagensV = new HashSet<Passagem>();
    
    @Column(name ="data_da_compra", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
    private LocalDateTime dataCompra;
    
    @Column(nullable = false)
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    private BigDecimal precoTotal;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Pacote> getPacotes() {
		return pacotes;
	}

	public void setPacotes(Set<Pacote> pacotes) {
		this.pacotes = pacotes;
	}

	public Set<Passagem> getPassagensV() {
		return passagensV;
	}

	public void setPassagensV(Set<Passagem> passagensV) {
		this.passagensV = passagensV;
	}

	public LocalDateTime getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(LocalDateTime dataCompra) {
		this.dataCompra = dataCompra;
	}

	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
	}


    
}
