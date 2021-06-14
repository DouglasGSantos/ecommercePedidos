package br.com.ecommerce.application.fechamento;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.ecommerce.application.fechamento.dtos.PedidoRequest;
import br.com.ecommerce.application.fechamento.dtos.PedidoResponse;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.StatusPedido;
import br.com.ecommerce.infrastructure.exception.types.BusinessException;
import br.com.ecommerce.infrastructure.kafka.KafkaTopics;
import br.com.ecommerce.infrastructure.repository.PedidoRepository;

@Service
public class FecharPedidoApplicationService {

	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private KafkaTemplate<String, Pedido> kafkaTemplate;
	
	
	public PedidoResponse executar(PedidoRequest request) {
		
		verificarSePedidoNulo(request);
		Pedido pedido = criarPedido(request);	
		Pedido pedidoSalvo = repository.save(pedido);
		notificarNovoPedido(pedidoSalvo);
		return new PedidoResponse(pedidoSalvo.getId());
		
	}

	private void notificarNovoPedido(Pedido pedidoSalvo) {
		kafkaTemplate.send(KafkaTopics.ECOMMERCE_PEDIDO_NOVO.toString(), pedidoSalvo);
	}

	private Pedido criarPedido(PedidoRequest request) {
		Pedido pedido = new Pedido();
		pedido.setCodigoCliente(request.getCodigoCliente());
		pedido.setCodigoProduto(request.getCodigoProduto());
		pedido.setQuantidade(request.getQuantidade());
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setStatus(StatusPedido.EM_PROCESSAMENTO);
		return pedido;
	}

	private void verificarSePedidoNulo(PedidoRequest request) {
		if(Objects.isNull(request)) {
			throw new BusinessException("pedido não pode ser nulo");
		}
	}
	
	
}
