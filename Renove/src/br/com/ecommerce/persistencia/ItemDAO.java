/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ecommerce.persistencia;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import br.com.ecommerce.classe.Item;

import org.hibernate.Session;

/**
 *
 * @author Marlon
 */
public class ItemDAO extends GenericDAO{
    private Session session;

    public ItemDAO(Session session) {
        this.session = session;
    }

    public ItemDAO() {}

    public void addItem(Item item){
    	if (item.getCdItem() == null || item.getCdItem() <= 0) {
    		item.setCdItem(getCdItem());
    	}
        item.setDtRegistro(new Date());
        saveOrUpdatePojo(item);        
    }
    
    public void updateItem(Item item){
        saveOrUpdatePojo(item);
    }

    public void removeItem(Item item){
        removePojo(item);
    }
    
    public List getItem(){
        return getListPojo(Item.class, "from Item item");
    }
    
    public Integer getCdItem(){
        return (Integer) getPurePojoSQLQuery("select max(item.cd_item)+1 from Item item");
    }
    
    public Integer getCountItem(){
        return (Integer) getPurePojoSQLQuery("select count(*) from item");
    }

}
