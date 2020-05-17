package com.example.demo.domain;

import javax.persistence.Entity;

import com.example.demo.domain.enums.StatusPagamento;

@Entity
public class PagamentoCredito extends Pagamento {
	private static final long serialVersionUID = 1L;

	private Integer parcelas;

	public PagamentoCredito() {

	}

	public PagamentoCredito(Integer id, StatusPagamento estado, Pedido pedido, Integer parcelas) {
		super(id, estado, pedido);
		this.parcelas = parcelas;
	}

	public Integer getParcelas() {
		return parcelas;
	}

	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}

}
