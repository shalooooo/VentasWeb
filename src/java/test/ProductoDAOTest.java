/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import DAO.CategoriaDAO;
import DAO.ProductoDAO;
import entidades.Categoria;
import entidades.Producto;

/**
 *
 * @author Gonzalo
 */
public class ProductoDAOTest {
    public static void main(String[] args){
        ProductoDAO pDAO = new ProductoDAO();
        for (Producto producto : pDAO.listar()) {
            System.out.println(producto.getNombre());
        }
        
        CategoriaDAO cDAO = new CategoriaDAO();
        for (Categoria categoria : cDAO.listar()) {
            System.out.println(categoria.getNombre());
        }
        
    }
}
