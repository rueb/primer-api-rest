package com.std.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.model.dao.ClienteDao;
import com.std.model.dto.ClienteDto;
import com.std.model.entity.Cliente;
import com.std.service.ICliente;

@Service
public class ClienteImpl implements ICliente{
	
	@Autowired
	private ClienteDao clienteDao;

	@Transactional
	//@Override
	public Cliente save(ClienteDto clienteDto) {
		// TODO Auto-generated method stub
		Cliente cliente = Cliente.builder()
				.idCliente(clienteDto.getIdCliente())
				.nombre(clienteDto.getNombre())
				.apellido(clienteDto.getApellido())
				.correo(clienteDto.getCorreo())
				.fechaRegistro(clienteDto.getFechaRegistro())
				.build();
		return clienteDao.save(cliente);
	}

	@Transactional(readOnly = true)
	public Cliente findById(Integer id) {
		// TODO Auto-generated method stub
		return clienteDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Cliente clientes) {
		// TODO Auto-generated method stub
		Cliente cliente = Cliente.builder()
				.idCliente(clientes.getIdCliente())
				.nombre(clientes.getNombre())
				.apellido(clientes.getApellido())
				.correo(clientes.getCorreo())
				.fechaRegistro(clientes.getFechaRegistro())
				.build();
		clienteDao.delete(cliente);
		
	}	

}
