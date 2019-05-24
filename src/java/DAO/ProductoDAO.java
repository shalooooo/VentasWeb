/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidades.Producto;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Gonzalo
 */
public class ProductoDAO extends Modelo<Producto>{

    @Override
    public String getNombreModelo() {
        return "Producto";
    }

    

}
