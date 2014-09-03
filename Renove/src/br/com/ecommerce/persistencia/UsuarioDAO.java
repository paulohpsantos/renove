/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ecommerce.persistencia;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.ecommerce.classe.Usuario;

import org.hibernate.Session;

/**
 *
 * @author Marlon
 */
public class UsuarioDAO extends GenericDAO{
    private Session session;

    public UsuarioDAO(Session session) {
        this.session = session;
    }

    public UsuarioDAO() {}

    public Integer addUsuario(Usuario usuario){
    	if (usuario.getCdUsuario() == null || usuario.getCdUsuario() <= 0){
    		usuario.setCdUsuario(getCdUsuario());
    	}
    	usuario.setStUsuario('A');
    	usuario.setDtRegistro(new Date());
        saveOrUpdatePojo(usuario);
        return usuario.getCdUsuario();
    }

    public void updateUsuario(Usuario usuario){
        saveOrUpdatePojo(usuario);
    }

    public void removeUsuario(Usuario usuario){
        removePojo(usuario);
    }
    
    public void removeUsuarioNoCommit(Usuario usuario, Session session){
        removePojoNoCommit(usuario, session);
    }

    public List getUsuario(){
        return getListPojo(Usuario.class, "from Usuario usuario");
    }

    public Usuario getUsuario(Integer id){
        return getPojo(Usuario.class,id);
    }
    
    public boolean verificaSenha(Usuario usuario){
        if ((Usuario)getPurePojo(
                "select usuario from Usuario usuario where usuario.dsLogin = '" + usuario.getDsLogin() 
              + "' and usuario.dsSenha = '" + usuario.getDsSenha()+ "'") != null){        
            return true;
        }
        return false;
    }
    
    public Usuario getUsuario(Usuario usuario){
        return (Usuario)getPurePojo("select usuario from Usuario usuario"
                                  + " where usuario.dsLogin = '" + usuario.getDsLogin() + "'"
                                  + "   and usuario.dsSenha = '" + usuario.getDsSenha()+ "'");
    }
    
    public Integer getCdUsuario(){
        return (Integer) getPurePojoSQLQuery("select max(usuario.cd_usuario)+1 from Usuario usuario");
    }
}
