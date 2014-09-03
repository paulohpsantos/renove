/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ecommerce.persistencia;

import java.util.Date;
import java.util.List;

import br.com.ecommerce.classe.Despesa;
import br.com.ecommerce.classe.Item;

import org.hibernate.Session;

/**
 *
 * @author Marlon
 */
public class DespesaDAO extends GenericDAO{
    private Session session;

    public DespesaDAO(Session session) {
        this.session = session;
    }

    public DespesaDAO() {}

    public void addDespesa(Despesa despesa){
    	despesa.setCdDespesa(1);
        saveOrUpdatePojo(despesa);        
    }
    
    public void updateDespesa(Despesa despesa){
        saveOrUpdatePojo(despesa);
    }

    public void removeItem(Despesa despesa){
        removePojo(despesa);
    }
    
    public List getDespesa(){
        return getListPojo(Item.class, "from Despesa despesa");
    }
    
    public Despesa getDespesa(Integer despesa){
        return (Despesa)getPurePojo("select despesa from Despesa despesa where despesa.cdDespesa = " + despesa);
    }

}
