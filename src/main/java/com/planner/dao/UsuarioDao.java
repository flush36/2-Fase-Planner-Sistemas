package com.planner.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.planner.treina.entity.Usuario;

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

}
