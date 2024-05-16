package br.com.serratec.pedido.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.pedido.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	List<Pedido>findByNomeClienteContainingIgnoreCase(String nomeCliente);
	List<Pedido>findByPratoContainingIgnoreCase(String prato);
	List<Pedido>findByValorLessThan(double valor);
	List<Pedido>findByValorGreaterThan(double valor);
	List<Pedido>findByNomeClienteAndPratoContainingIgnoreCase(String nomeCliente,String prato);
	
}
