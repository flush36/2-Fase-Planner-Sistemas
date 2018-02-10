package com.planner.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.planner.service.TarefasService;
import com.planner.treina.entity.PrioridadeEnum;
import com.planner.treina.entity.Tarefas;
import com.planner.treina.entity.Usuario;

@Named
@ViewScoped
public class TarefasBean implements Serializable{

	@Inject
	private Tarefas tarefas;
	
	private List<Tarefas> tarefasList = new ArrayList<>();
	
	private List<PrioridadeEnum> prioridade;
	
	@Inject
	private FacesContext context;
	
	@Inject
	private TarefasService tarefaService;
	
	@Inject
	private Usuario usuarioLocal;
	
	public void salvarTarefa() {
		tarefaService.salvarTarefaService(this.tarefas);
		
		RequestContext.getCurrentInstance().update("tabelaTarefas");
		RequestContext.getCurrentInstance().execute("PF('cadastrarTarefa').hide()");
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO , "Tarefa salva com sucesso!", "Tarefa salva com sucesso!"));
	}
	
	@PostConstruct
	public void init() {
		this.usuarioLocal = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		this.tarefas.setUsuario(usuarioLocal);
		this.prioridade = Arrays.asList(PrioridadeEnum.values());
		this.tarefasList = tarefaService.findallService();
	}
	
	public Tarefas getTarefas() {
		return tarefas;
	}
	public void setTarefas(Tarefas tarefas) {
		this.tarefas = tarefas;
	}

	public List<PrioridadeEnum> getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(List<PrioridadeEnum> prioridade) {
		this.prioridade = prioridade;
	}

	public TarefasService getTarefaService() {
		return tarefaService;
	}

	public List<Tarefas> getTarefasList() {
		return tarefasList;
	}

	public void setTarefasList(List<Tarefas> tarefasList) {
		this.tarefasList = tarefasList;
	}

}
