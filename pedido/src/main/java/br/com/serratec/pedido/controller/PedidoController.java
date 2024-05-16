package br.com.serratec.pedido.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.pedido.dto.ClientePratoDto;
import br.com.serratec.pedido.dto.PedidoDto;
import br.com.serratec.pedido.service.PedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	
	@GetMapping
	public ResponseEntity<List<PedidoDto>>buscarTodosPedidos(){
		return ResponseEntity.ok(service.buscartodos());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoDto criarPedido(@Valid @RequestBody PedidoDto pedido){
		return service.salvarPedido(pedido);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoDto> buscarPedidoPorId(@PathVariable Long id) {
		Optional<PedidoDto> pedido = service.buscarPorId(id);
		
		if(pedido.isPresent()) {
			return ResponseEntity.ok(pedido.get());
		}
		return ResponseEntity.notFound().build();
 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PedidoDto> alterarPedido(@PathVariable Long id, @RequestBody PedidoDto pedidoAlt){
		Optional<PedidoDto> pedido = service.atualizarPedido(id, pedidoAlt);

		if(pedido.isPresent()) {
			return ResponseEntity.ok(pedido.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> apagarPedido(@PathVariable Long id){
		if(service.excluirPedido(id)){
			return ResponseEntity.noContent().build();
			}			
			return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/cliente")
	public ResponseEntity<List<PedidoDto>> obterPedidoPorCliente(@RequestBody String nomeCliente){
		return ResponseEntity.ok(service.obterPorCliente(nomeCliente));
	}
	
	@GetMapping("/prato")
	public ResponseEntity<List<PedidoDto>> obterPedidoPorPrato(@RequestBody String prato){
		return ResponseEntity.ok(service.obterPorPrato(prato));
	}
	
	@GetMapping("/valorabaixo")
	public ResponseEntity<List<PedidoDto>> obterPedidoPorValorAbaixo(@RequestBody double valor){
		return ResponseEntity.ok(service.obterPorValorAbaixo(valor));
	}
	
	@GetMapping("/valoracima")
	public ResponseEntity<List<PedidoDto>> obterPedidoPorValorAcima(@RequestBody double valor){
		return ResponseEntity.ok(service.obterPorValorAcima(valor));
	}
	
	@GetMapping("/clienteprato")
	public ResponseEntity<List<PedidoDto>> obterPedidoPorClientePrato(@RequestBody ClientePratoDto dto){
		return ResponseEntity.ok(service.obterPorClientePrato(dto.nomeCliente(),dto.prato()));
	}
	
}
