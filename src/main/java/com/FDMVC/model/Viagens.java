package com.FDMVC.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
public class Viagens extends Entidade {

    
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id_fk", nullable = false)
	private Usuario usuario;
    
	@ManyToMany
	@JoinTable(name ="viagens_pacotes",
	joinColumns = @JoinColumn(name = "viagens_id_fk"),
	inverseJoinColumns = @JoinColumn(name = "pacote_id_fk")
	)
	private List<Pacote> pacotes;
    
	@ManyToMany
	@JoinTable(name ="viagens_passagens",
	joinColumns = @JoinColumn(name = "viagens_id_fk"),
	inverseJoinColumns = @JoinColumn(name = "passagem_id_fk")
	)
	private List<Passagem> passagensV;
    
    @Column(name ="data_da_compra", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
    private LocalDateTime dataCompra;
    
    @Column(nullable = false)
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    private BigDecimal precoTotal;
    
    
}
