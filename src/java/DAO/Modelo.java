/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Gonzalo
 */
public abstract class Modelo<T> {
    private String nombreModelo;

    public String getNombreModelo() {
        return nombreModelo;
    }
    
    // Metodo que permite listar los registros de la base de datos de una entidad
    public List<T> listar(){
        List<T> entidades = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "from " + getNombreModelo();
            Query q = sesion.createQuery(hql);
            entidades = q.list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sesion.close();
        }
        return entidades;        
    }
    
    public boolean agregar(T entidad){
        boolean fueAgregado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = sesion.beginTransaction();
            sesion.save(entidad); 
            tx.commit();
            fueAgregado = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sesion.close();
        }
        return fueAgregado;
    }
    
    public boolean modificar(T entidad){
        boolean fueModificado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = sesion.beginTransaction();
            sesion.update(entidad); 
            tx.commit();
            fueModificado = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sesion.close();
        }
        return fueModificado;
    }
    
    public boolean eliminar(T entidad){
        boolean fueEliminado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = sesion.beginTransaction();
            sesion.delete(entidad); 
            tx.commit();
            fueEliminado = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sesion.close();
        }
        return fueEliminado;
    }
    
    public T buscarPorId(int id){
        T entidad = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select p from " + getNombreModelo() + " p where p.id = :id";
            Query q = sesion.createQuery(hql);
            q.setInteger("id", id);
            entidad = (T) q.list().get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sesion.close();
        }
        return entidad;
    }
}
