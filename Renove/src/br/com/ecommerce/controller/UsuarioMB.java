package br.com.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.ecommerce.classe.Usuario;
import br.com.ecommerce.persistencia.UsuarioDAO;

@SessionScoped
@ManagedBean
public class UsuarioMB {
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private UsuarioDAO usuarioDao = new UsuarioDAO();
	private Usuario usuarioSelecionado = new Usuario();
	
	@PostConstruct
	public void init() {
		usuarios = usuarioDao.getUsuario();
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public String novo() {
		usuarioSelecionado = new Usuario();
		return "formUsuario.jsf";
	}
	
	public String alterar() {
		if (usuarioSelecionado != null) {
			return "formUsuario.jsf";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Selecione um item para Alterar!"));
			return "";
		}
	}
	
	public void excluir() {
		if (usuarioSelecionado != null) {
			usuarioDao.removeUsuario(usuarioSelecionado);
			usuarios = usuarioDao.getUsuario();
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Selecione um item para Excluir!"));
		}
	}
	
	public String salvar() {
		if (usuarioSelecionado == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Não existe Usuário para inserir!"));
			return "";
		}
		if (usuarioSelecionado.getDsUsuario() == null || usuarioSelecionado.getDsUsuario().length() <= 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Nome do Usuário deve ser informado!"));
			return "";
		}
		if (usuarioSelecionado.getDsLogin() == null || usuarioSelecionado.getDsLogin().length() <= 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Login do Usuário deve ser informado!"));
			return "";
		}
		
		if (usuarioSelecionado.getDsSenha() == null || usuarioSelecionado.getDsSenha().length() <= 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Senha do Usuário deve ser informado!"));
			return "";
		}
		
		usuarioDao.addUsuario(usuarioSelecionado);
		usuarios = usuarioDao.getUsuario();
		return "usuario.jsf";
	}
	
	public String voltar() {
		usuarios = usuarioDao.getUsuario();
		return "usuario.jsf";
	}
	
	
	

}
