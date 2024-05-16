package br.com.serratec.pedido.dto;

import br.com.serratec.pedido.model.Pedido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PedidoDto ( Long id,
	 @NotBlank(message= "Nome do cliente deve ser preenchido")
	 String nomeCliente,
	 @NotBlank(message= "Informe o prato pedido")
	 String prato,
	 @NotNull
	 //@Pattern(regexp = "\\d+(\\.\\d+)?", message = "Informe um valor válido.")
	 double valor,
	 String observacao) {

	public Pedido toEntity() {
		return new Pedido (this.id,this.nomeCliente,this.prato,this.valor,this.observacao);
	}
}
