package br.com.ecommerce.application.atualizar;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.ecommerce.application.cancelar.CancelarPedidoServiceImpl;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.StatusPedido;
import br.com.ecommerce.infrastructure.repository.PedidoRepository;

@Service
public class AtualizarPedidoApplicationService {
	private static final Logger logger = LoggerFactory.getLogger(AtualizarPedidoApplicationService.class);

	@Autowired
	private PedidoRepository repository;

	@KafkaListener(containerFactory = "pedidoKafkaListenerContainerFactory", topics = "ECOMMERCE_PEDIDO_ESTOQUE_RESERVADO")
	public void reservarPedidoComEstoque(Pedido pedido) {

		logger.info("o pedido {} foi reservado pois contém estoque disponível",pedido.getId());

		String id = pedido.getId();

		Optional<Pedido> findById = repository.findById(id);
		if (findById.isPresent()) {

			pedido.setStatus(StatusPedido.ESTOQUE_RESERVADO);
			pedido.setMensagem("O estoque foi reservado com sucesso");
			repository.save(pedido);
		}
	}
}
