package com.rp.crm;

import org.springframework.batch.item.ItemProcessor;

import com.rp.crm.model.ClientePlataforma;

public class ClientePlataformaItemProcessor implements ItemProcessor<ClientePlataforma, ClientePlataforma> {

	@Override
	public ClientePlataforma process(ClientePlataforma item) throws Exception {

		return item;
	}

}
