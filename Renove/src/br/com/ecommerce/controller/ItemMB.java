package br.com.ecommerce.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import java.io.File;

import org.apache.commons.io.FileUtils;

import br.com.ecommerce.persistencia.ItemDAO;
import br.com.ecommerce.classe.Item;

@SessionScoped
@ManagedBean
public class ItemMB {
	private List<Item> itens = new ArrayList<Item>();
	private ItemDAO itemDao = new ItemDAO();
	private Item itemSelecionado = new Item();
	private UploadedFile file;
	
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

	public Item getItemSelecionado() {
		return itemSelecionado;
	}

	public void setItemSelecionado(Item itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}
	
	/*public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }*/
	
	public String novo() {
		itemSelecionado = new Item();
		return "formItem.jsf";
	}
	
	public String alterar() {
		if (itemSelecionado != null) {
			return "formItem.jsf";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Selecione um item para Alterar!"));
			return "";
		}
	}
	
	public void excluir() {
		if (itemSelecionado != null) {
			itemDao.removeItem(itemSelecionado);
			itens = itemDao.getItem();
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Selecione um item para Excluir!"));
		}
	}
	
	public String salvar() {
		if (itemSelecionado == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Não existe Item para inserir!"));
			return "";
		}
		if (itemSelecionado.getDsItem() == null || itemSelecionado.getDsItem().length() <= 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Descrição do Item deve ser informada!"));
			return "";
		}
		if (itemSelecionado.getVlVenda() == null || itemSelecionado.getVlVenda().doubleValue() <= 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Valor deve ser informado!"));
			return "";
		}
		
		
		
		/*UploadedFile uploadedFile = file;
        String fileNameUploaded = uploadedFile.getFileName();
        long fileSizeUploaded = uploadedFile.getSize();

        //ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "\\WebContent\\resources\\img" + File.separator + "imagemEmpresa" + File.separator + itemSelecionado.getCdItem() + uploadedFile.getFileName();

        InputStream inputStr;
        try {
            inputStr = uploadedFile.getInputstream();

            File destFile = new File(newFileName);

            FileUtils.copyInputStreamToFile(inputStr, destFile);
        } catch (IOException ex) {
            Logger.getLogger(ItemMB.class.getName()).log(Level.SEVERE, null, ex);
        }

        String infoAboutFile = "<br/> Arquivo recebido: <b>" + fileNameUploaded + "</b><br/>" + "Tamanho do Arquivo: <b>" + fileSizeUploaded + "</b>";
        itemSelecionado.setLgItem(fileNameUploaded);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage("Sucesso", infoAboutFile));
		*/
		
		
		
		itemDao.addItem(itemSelecionado);
		itens = itemDao.getItem();
		return "item.jsf";
	}
	
	public String voltar() {
		itens = itemDao.getItem();
		return "item.jsf";
	}
	
	
	

}
