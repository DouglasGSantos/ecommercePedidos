package br.com.ecommerce.presentation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.application.fechamento.FecharPedidoApplicationService;
import br.com.ecommerce.application.fechamento.dtos.PedidoRequest;
import br.com.ecommerce.application.fechamento.dtos.PedidoResponse;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private FecharPedidoApplicationService fecharPedidoApplicationService;
	
	@PostMapping("/")
	public ResponseEntity<PedidoResponse> novoPedido(@Valid @RequestBody PedidoRequest novoPedido){
		
		PedidoResponse executar = fecharPedidoApplicationService.executar(novoPedido);
		
		return ResponseEntity.ok(executar);
	}
	
}
