package br.com.ecommerce.application.fechamento.dtos;

public class PedidoResponse {

	private String id;

	public PedidoResponse() {
		super();

	}

	public PedidoResponse(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
