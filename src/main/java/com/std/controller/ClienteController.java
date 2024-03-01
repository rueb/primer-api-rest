package com.std.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.std.model.dto.ClienteDto;
import com.std.model.entity.Cliente;
import com.std.model.payload.MensajeResponse;
import com.std.service.ICliente;


@RestController
@RequestMapping("/api/v1")
public class ClienteController {
	
	@Autowired
	private ICliente iCliente;
	
	@PostMapping("cliente")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto) {
		Cliente cliente = null;
		try {
			cliente = iCliente.save(clienteDto);
			
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Guardado correctamente")
					.object(ClienteDto.builder()
							.idCliente(cliente.getIdCliente())
							.nombre(cliente.getNombre())
							.apellido(cliente.getApellido())
							.correo(cliente.getCorreo())
							.fechaRegistro(cliente.getFechaRegistro())
							.build())
					.build(),HttpStatus.CREATED);
			
		}catch(DataAccessException ex) {
			return new ResponseEntity<>(
					MensajeResponse.builder()
					 .mensaje(ex.getMessage())
					 .object(null)
					 .build()
					 ,HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
		
			
		
		//return iCliente.save(cliente);
	}
	//1:26
	
	@PutMapping("cliente")
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDto update(@RequestBody ClienteDto clienteDto) {
		//return iCliente.save(cliente);
		Cliente cliente = iCliente.save(clienteDto);
		return ClienteDto.builder()
			.idCliente(cliente.getIdCliente())
			.nombre(cliente.getNombre())
			.apellido(cliente.getApellido())
			.correo(cliente.getCorreo())
			.fechaRegistro(cliente.getFechaRegistro())
			.build();
	}
	
	@DeleteMapping("cliente/{id}")
	// @ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		//Map<String,Object> response = new HashMap<>();
		
		try {
			Cliente clienetDelete =iCliente.findById(id);
			iCliente.delete(clienetDelete);
			return new ResponseEntity<>(clienetDelete,HttpStatus.NO_CONTENT);
		}catch(DataAccessException ex ) {
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje(ex.getMessage())
					.object(null)
					.build(),HttpStatus.INTERNAL_SERVER_ERROR);			
			//response.put("mensaje",ex.getMessage());
		//	response.put("mensaje", null);
		}
	}
	
	@GetMapping("cliente/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ClienteDto showById(@PathVariable Integer id) {
		Cliente cliente = iCliente.findById(id);
		return ClienteDto.builder()
				.idCliente(cliente.getIdCliente())
				.nombre(cliente.getNombre())
				.apellido(cliente.getApellido())
				.correo(cliente.getCorreo())
				.fechaRegistro(cliente.getFechaRegistro())
				.build();
	}
}
