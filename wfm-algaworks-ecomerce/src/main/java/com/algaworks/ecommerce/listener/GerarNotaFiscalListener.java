package com.algaworks.ecommerce.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.service.NotaFiscalService;

public class GerarNotaFiscalListener {

	//Neste caso não utilizaremos conteiner de injeção de dependências
	//@Inject //EE
	//@Autowired //Spring
	private NotaFiscalService notaFiscalService = new NotaFiscalService();
	
	@PrePersist
	@PreUpdate
	public void gerar(Pedido pedido) {
		if(pedido.isPago() && pedido.getNotaFiscal() == null) {
			notaFiscalService.gerar(pedido);
		}
	}
}
