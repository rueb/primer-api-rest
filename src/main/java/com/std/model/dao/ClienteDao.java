package com.std.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.std.model.entity.Cliente;


public interface ClienteDao extends CrudRepository<Cliente, Integer>{

}
