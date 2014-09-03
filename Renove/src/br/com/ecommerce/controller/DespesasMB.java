package br.com.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.ecommerce.persistencia.DespesaDAO;
import br.com.ecommerce.persistencia.ItemDAO;
import br.com.ecommerce.classe.Despesa;
import br.com.ecommerce.classe.Item;

@SessionScoped
@ManagedBean
public class DespesasMB {
	private DespesaDAO despesaDao = new DespesaDAO();
	private Despesa despesaSelecionada = new Despesa();
	
	@PostConstruct
	public void init() {
		despesaSelecionada = despesaDao.getDespesa(1);
		if (despesaSelecionada == null){
			despesaSelecionada = new Despesa();
		}
	}
	
	public Despesa getDespesaSelecionada() {
		return despesaSelecionada;
	}

	public void setDespesaSelecionada(Despesa despesaSelecionada) {
		this.despesaSelecionada = despesaSelecionada;
	}
	
	public String salvar() {
		if (despesaSelecionada == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Não existe Despesa para inserir!"));
			return "";
		}
		
		if (despesaSelecionada.getVlDespesaTotal() == null || despesaSelecionada.getVlDespesaTotal().doubleValue() <= 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Despesa Total deve ser informado!"));
			return "";
		}
		
		if (despesaSelecionada.getVlMargemLucro() == null || despesaSelecionada.getVlMargemLucro().doubleValue() <= 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Margem de Lucro deve ser informada!"));
			return "";
		}
		
		despesaDao.addDespesa(despesaSelecionada);
		return "despesa.jsf";
	}
	
	
	

}
