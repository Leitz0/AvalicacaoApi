package br.com.serratec.pedido.model;

import br.com.serratec.pedido.dto.PedidoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeCliente;
	private String prato;
	private double valor;
	private String observacao;
	
	
	
	public Pedido(){}
	
	public Pedido(Long id, String nomeCliente, String prato, double valor, String observacao) {
		super();
		this.id = id;
		this.nomeCliente = nomeCliente;
		this.prato = prato;
		this.valor = valor;
		this.observacao = observacao;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getPrato() {
		return prato;
	}
	public void setPrato(String prato) {
		this.prato = prato;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public PedidoDto toDto() {
		return new PedidoDto (this.id,this.nomeCliente,this.prato,this.valor,this.observacao);
	}
}
