package br.com.ecommerce.application.fechamento.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PedidoRequest {

	@NotBlank
	private String codigoCliente;
	
	@NotBlank
	private String codigoProduto;
	
	@NotNull
	private Integer quantidade;
	
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getCodigoProduto() {
		return codigoProduto;
	}
	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
	
}
