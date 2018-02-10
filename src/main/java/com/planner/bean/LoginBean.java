package com.planner.bean;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.planner.service.LoginService;
import com.planner.treina.entity.Usuario;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -8699654255891466192L;

	@Inject
	private EntityManager em;

	@Inject
	private Usuario usuario;

	@Inject
	private LoginService usuarioService;

	@Inject
	private FacesContext context;

	private String title;

	@PostConstruct
	private void init() throws NoSuchAlgorithmException {
		this.title = "Login - do bean";
		initialUser();
		select();
	}

	public String logar() {

		this.usuario = usuarioService.logar(this.usuario);
		if (this.usuario != null) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			System.out.println("USUARIO NOME : " + this.usuario.getUsuario());
			return "listarUsuarios?faces-redirect=true";
		}

		this.usuario = new Usuario();
		context.addMessage(null, new FacesMessage("Usuario não encontrado"));

		return null;
	}

	public String deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}

	private void select() {

		List<Usuario> usuarios = em.createQuery("from Usuario", Usuario.class).getResultList();

		for (Usuario usuario : usuarios) {
			System.out.println(usuario.getUsuario());
		}

	}

	private void initialUser() throws NoSuchAlgorithmException {
		this.usuario.setLogin("thiago.ferreira");
		this.usuario.setSenha("123");
		this.usuario.setUsuario("Thiago Ferreira");
		usuarioService.initialService(this.usuario);
		this.usuario = new Usuario();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
