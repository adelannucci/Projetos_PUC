/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.model.dao;

import br.com.puc.tools.database.BD;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 12647202
 */
public abstract class DAO<T> implements Serializable{
    
    protected BD bd;

    public abstract T findById(int id);
    public abstract boolean update(T obj);
    public abstract List<T> findAll();
    public abstract boolean insert(T obj);
    public abstract boolean delete(int id);
    
    public int findEnd(String tableName, String idName)
    {
        try {
            int idEnd = 0;
            String cmd = "SELECT "+idName+" FROM "+tableName+" WHERE ROWNUM <=1 ORDER BY "+idName+" DESC";
            bd = new BD();
            ResultSet rs = bd.execConsulta(cmd);
            if(rs.next())
            {
              idEnd = rs.getInt(idName);  
            }
            return idEnd;
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public ResultSet query(String query)
    {
        try {
            int idEnd = 0;
            bd = new BD();
            ResultSet rs = bd.execConsulta(query);
            return rs;
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
}
