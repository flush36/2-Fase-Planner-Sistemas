package com.planner.dao;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.planner.treina.entity.Tarefas;
import com.planner.treina.entity.Usuario;

public class TarefasDao implements Serializable {

	@Inject
	private EntityManager em;

	public void salvarTarefaDao(Tarefas tarefa) {

		Date dataCriacao = new Date(System.currentTimeMillis());
		tarefa.setDataCriacao(dataCriacao);
		em.getTransaction().begin();
		em.merge(tarefa);
		em.getTransaction().commit();
	}
	
	public List<Tarefas> findAllDAO() {
		return em.createQuery("from Tarefas", Tarefas.class).getResultList();
	}

}
