package com.planner.bean;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.planner.service.UsuarioService;
import com.planner.treina.entity.Usuario;
import com.planner.util.Md5Util;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {
	
	private List<Usuario> usuarios = new ArrayList<>();
	
	@Inject
	private Usuario usuarioSelecionado;
	
	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private FacesContext context;

	private String senhaAtual;
	
	private String novaSenha;
	
	@PostConstruct
	public void findAll() {
		this.usuarios = usuarioService.findallService();
	}
	
	public void salvarEdicao() throws NoSuchAlgorithmException {
		if (this.usuarioSelecionado != null && this.usuarioSelecionado.getSenha().equals(Md5Util.convertPasswordToMD5(this.senhaAtual))) {
			this.usuarioSelecionado.setSenha(Md5Util.convertPasswordToMD5(novaSenha));
			Usuario usuarioModificado = usuarioService.salvarUsuarioService(this.usuarioSelecionado);
			
			this.usuarios.remove(usuarioSelecionado);
			this.usuarios.add(usuarioModificado);
			RequestContext.getCurrentInstance().update("tabelaUser");
			RequestContext.getCurrentInstance().execute("PF('changePassword').hide()");
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO , "Senha alterada com sucesso", "Senha alterada com sucesso"));
			return;
		}
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Senha invalida", "Senha invalida"));
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}
	
	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	
	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

}
