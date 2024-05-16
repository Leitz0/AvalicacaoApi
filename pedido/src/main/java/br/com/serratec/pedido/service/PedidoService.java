package br.com.serratec.pedido.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.pedido.dto.ClientePratoDto;
import br.com.serratec.pedido.dto.PedidoDto;
import br.com.serratec.pedido.model.Pedido;
import br.com.serratec.pedido.repository.PedidoRepository;

@Service
public class PedidoService {

		@Autowired
		private PedidoRepository repository;

		public List<PedidoDto> buscartodos() {
			return repository.findAll().stream().map(p-> new PedidoDto(p.getId(),
					p.getNomeCliente(),p.getPrato(),p.getValor(),p.getObservacao())).toList();
		}

		public PedidoDto salvarPedido(PedidoDto pedido) {
			Pedido pedidoEntity = repository.save(pedido.toEntity());
			return pedidoEntity.toDto();
		}

		public Optional<PedidoDto> buscarPorId(Long id) {
			Optional<Pedido> candidato = repository.findById(id);
			if (candidato.isPresent()) {
				return Optional.of(candidato.get().toDto());
			}
			return Optional.empty();
		}

		public Optional<PedidoDto> atualizarPedido(Long id,PedidoDto pedido) {
			Pedido pedidoEntity = pedido.toEntity();
			
			if(repository.existsById(id)) {
				pedidoEntity.setId(id);
				repository.save(pedidoEntity);
				return Optional.of(pedidoEntity.toDto());
			}
			return Optional.empty();
		
		}

		public boolean excluirPedido(Long id) {
			if(!repository.existsById(id)) {
				return false;
			}
			
			repository.deleteById(id);
			return true;
		}

		public  List<PedidoDto> obterPorCliente(String nomeCliente) {
			return repository.findByNomeClienteContainingIgnoreCase(nomeCliente).stream().map(p-> new PedidoDto(p.getId(),
					p.getNomeCliente(),p.getPrato(),p.getValor(),p.getObservacao())).toList();
		}

		public List<PedidoDto> obterPorPrato(String prato) {
			return repository.findByPratoContainingIgnoreCase(prato).stream().map(p-> new PedidoDto(p.getId(),
					p.getNomeCliente(),p.getPrato(),p.getValor(),p.getObservacao())).toList();
		}

		public List<PedidoDto> obterPorValorAbaixo(double valor) {
			return repository.findByValorLessThan(valor).stream().map(p-> new PedidoDto(p.getId(),
					p.getNomeCliente(),p.getPrato(),p.getValor(),p.getObservacao())).toList();
		}

		public List<PedidoDto> obterPorValorAcima(double valor) {
			return repository.findByValorGreaterThan(valor).stream().map(p-> new PedidoDto(p.getId(),
					p.getNomeCliente(),p.getPrato(),p.getValor(),p.getObservacao())).toList();
		}

		public List<PedidoDto> obterPorClientePrato( String cliente,String prato) {
			return repository.findByNomeClienteAndPratoContainingIgnoreCase(cliente,prato).stream().map(p-> new PedidoDto(p.getId(),
					p.getNomeCliente(),p.getPrato(),p.getValor(),p.getObservacao())).toList();
		}
		
		
		


		
		
		
}


