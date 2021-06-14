package br.com.ecommerce.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.ecommerce.domain.Pedido;

public interface PedidoRepository extends  MongoRepository<Pedido, String> {

}
