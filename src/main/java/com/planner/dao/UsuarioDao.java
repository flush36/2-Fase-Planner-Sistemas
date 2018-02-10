package com.planner.dao;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.planner.treina.entity.Usuario;
import com.planner.util.Md5Util;

public class UsuarioDao implements Serializable{

	@Inject
	private EntityManager em;
	
	public List<Usuario> findAllDao() {
		return em.createQuery("from Usuario", Usuario.class).getResultList();
	}
	
	public Usuario salvarUsuarioDao(Usuario usuario) {
		Usuario usuarioModificado = null;
		
		em.getTransaction().begin();
		usuarioModificado = em.merge(usuario);
		em.getTransaction().commit();
		
		return usuarioModificado;
	}
	
	public void salvarNovoUsuario(Usuario usuario) throws NoSuchAlgorithmException {
		
		em.getTransaction().begin();
		usuario.setSenha(Md5Util.convertPasswordToMD5(usuario.getSenha()));
	    em.merge(usuario);
		em.getTransaction().commit();
	}

}
