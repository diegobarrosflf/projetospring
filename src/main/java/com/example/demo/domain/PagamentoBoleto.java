package com.example.demo.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.example.demo.domain.enums.StatusPagamento;

@Entity
public class PagamentoBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;

	private Date dataVencimento;
	private Date dataPagamento;

	public PagamentoBoleto() {

	}

	public PagamentoBoleto(Integer id, StatusPagamento estado, Pedido pedido, Date vencimento, Date pagamento) {
		super(id, estado, pedido);
		this.dataVencimento = vencimento;
		this.dataPagamento = pagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
