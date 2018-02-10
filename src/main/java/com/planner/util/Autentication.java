package com.planner.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import com.planner.treina.entity.Usuario;

public class Autentication implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {

		FacesContext context = event.getFacesContext();
		String page = context.getViewRoot().getViewId();

		System.out.println("PAGINA: " + page);

		if ("/login.xhtml".equals(page)) {
			return;
		}

		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("logado");

		if (usuario != null) {
			return;
		}

		NavigationHandler navigation = context.getApplication().getNavigationHandler();
		navigation.handleNavigation(context, null, "/login?faces-redirect=true");
		context.renderResponse();

	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
