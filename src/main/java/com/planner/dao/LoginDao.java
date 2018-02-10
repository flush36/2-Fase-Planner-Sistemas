package com.planner.dao;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.planner.treina.entity.Usuario;
import com.planner.util.Md5Util;

public class LoginDao implements Serializable {

	@Inject
	EntityManager em;

	public void initialDao(Usuario usuario) throws NoSuchAlgorithmException {
		em.getTransaction().begin();
		usuario.setSenha(Md5Util.convertPasswordToMD5(usuario.getSenha().toString()));
		em.merge(usuario);
		em.getTransaction().commit();
		System.out.println("Usuario inicial: " + usuario.getUsuario() + " adicionado com sucesso!");
	}

	public Usuario logar(Usuario usuario) {
		try {
			TypedQuery<Usuario> query = em.createQuery(
					"select u from Usuario u where u.login = :pLogin and u.senha = :pSenha", Usuario.class);
			query.setParameter("pLogin", usuario.getLogin());
			query.setParameter("pSenha", Md5Util.convertPasswordToMD5(usuario.getSenha().toString()));
			return query.getSingleResult();

		} catch (Exception e) {
			return null;
		}
	}

}
