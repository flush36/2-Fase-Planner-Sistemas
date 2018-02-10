package com.planner.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.planner.dao.UsuarioDao;
import com.planner.treina.entity.Usuario;

public class UsuarioService implements Serializable {

	@Inject
	private UsuarioDao usuarioDao;
	
	public List<Usuario> findallService() {
		return usuarioDao.findAllDao();
	}

	public Usuario salvarUsuarioService(Usuario usuario) {
		return usuarioDao.salvarUsuarioDao(usuario);
	}
	
}
