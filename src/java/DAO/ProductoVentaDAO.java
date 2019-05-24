/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidades.ProductoVenta;

/**
 *
 * @author Gonzalo
 */
public class ProductoVentaDAO extends Modelo<ProductoVenta>{

    @Override
    public String getNombreModelo() {
        return "ProductoVenta";
    }
    
}
