package br.com.ecommerce.application.cancelar;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.StatusPedido;
import br.com.ecommerce.infrastructure.repository.PedidoRepository;

@Service
public class CancelarPedidoServiceImpl {

	private static final Logger logger = LoggerFactory.getLogger(CancelarPedidoServiceImpl.class);

	@Autowired
	private PedidoRepository repository;

	@KafkaListener(containerFactory = "pedidoKafkaListenerContainerFactory", topics = "ECOMMERCE_PEDIDO_ESTOQUE_INVALIDO")
	public void cancelarPedidoPorFaltaDeEstoque(Pedido pedido) {

		logger.info("o pedido {} foi cancelado por falta de estoque",pedido.getId());

		String id = pedido.getId();

		Optional<Pedido> findById = repository.findById(id);
		if (findById.isPresent()) {

			pedido.setStatus(StatusPedido.ESTOQUE_INDISPONIVEL);
			pedido.setMensagem("Desculpa o produto não possuí mais estoque");
			repository.save(pedido);
		}
	}

}
