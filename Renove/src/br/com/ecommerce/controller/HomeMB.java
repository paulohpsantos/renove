package br.com.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.ecommerce.persistencia.ItemDAO;
import br.com.ecommerce.classe.Item;

@SessionScoped
@ManagedBean
public class HomeMB {
	private List<Item> itens = new ArrayList<Item>();
	private ItemDAO itemDao = new ItemDAO();
	
	
	@PostConstruct
	public void init() {
		itens = itemDao.getItem();
	}
	
	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public String getMessage() {
		return "Hello World JSF!";
	}
	
	public String login() {
		return "login.jsf?faces-redirect=true";
	}
	
	public String logout() {
		return "index.jsf?faces-redirect=true";
	}
	

}
