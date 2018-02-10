package com.planner.service;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import com.planner.dao.LoginDao;
import com.planner.treina.entity.Usuario;

public class LoginService implements Serializable {
	@Inject
	LoginDao usuarioDao;

	public void initialService(Usuario usuario) throws NoSuchAlgorithmException {

		Usuario usuarioInitial = usuarioDao.logar(usuario);
		if (usuarioInitial != null) {
			System.out.println("ENTROU NO SERVICO DO FINDUSER");
			return;
		}
		usuarioDao.initialDao(usuario);
	}

	public Usuario logar(Usuario usuario) {
		if (usuario.getLogin() != null && usuario.getSenha() != null) {
			Usuario usuarioLogin = usuarioDao.logar(usuario);
			return usuarioLogin;
		}
		return null;
	}

}
