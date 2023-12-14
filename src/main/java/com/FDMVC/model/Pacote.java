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

@Entity
public class Pacote extends Entidade{
	
    @Column(nullable = false)
	private String nome;
    
    @Column(nullable = false)
	private String destino;
	
    @Column(name ="dias_de_hospedagem", nullable = false)
	private int diasHospedagem;
	
    @Column(name ="data_da_ida", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	private LocalDateTime ida;
    
    @Column(name ="data_da_volta", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	private LocalDateTime volta;
    
    @Column(nullable = false)
	private boolean promocao;
	
    @Column(nullable = false)
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal preco;
	
	@ManyToMany(mappedBy = "pacotes", fetch = FetchType.LAZY)
	private List<Viagens> viagens;

	@ManyToMany
	@JoinTable(name ="pacote_passagens",
	joinColumns = @JoinColumn(name = "pacote_id_fk"),
	inverseJoinColumns = @JoinColumn(name = "passagem_id_fk")
	)
	private List<Passagem> passagensP;

}
