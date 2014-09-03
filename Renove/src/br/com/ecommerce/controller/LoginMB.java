package br.com.ecommerce.controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.ecommerce.classe.Usuario;
import br.com.ecommerce.persistencia.UsuarioDAO;

@SessionScoped
@ManagedBean
public class LoginMB {
	private String username;
	private String password;
	private Usuario usuario;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String entrar() {
		if (username == null || username.length() == 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Usuario deve ser informado!"));
			return "";
		}
		
		if (password == null || password.length() == 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Senha deve ser informada!"));
			return "";
		}
		
		usuario.setDsLogin(username);
		usuario.setDsSenha(password);
		
		if (usuarioDAO.verificaSenha(usuario)) {
			return "item.jsf?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Usuário ou Senha incorreta!"));
			return "";
		}
	}
	
	
}
