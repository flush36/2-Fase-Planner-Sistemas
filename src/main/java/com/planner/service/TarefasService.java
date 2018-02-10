package com.planner.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.planner.dao.TarefasDao;
import com.planner.treina.entity.Tarefas;
import com.planner.treina.entity.Usuario;

public class TarefasService implements Serializable{
	
	@Inject
	private TarefasDao tarefaDao;

	public void salvarTarefaService(Tarefas tarefa) {
		if (tarefa != null) {
			tarefaDao.salvarTarefaDao(tarefa);
		}
	}
	
	public List<Tarefas> findallService() {
		return tarefaDao.findAllDAO();
	}

}
