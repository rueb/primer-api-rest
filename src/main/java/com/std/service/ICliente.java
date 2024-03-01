package com.std.service;

import com.std.model.dto.ClienteDto;
import com.std.model.entity.Cliente;

public interface ICliente {

	Cliente save(ClienteDto clienteDto);
	
	Cliente findById(Integer id);
	
	void delete(Cliente cliente);
}
