/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidades.Categoria;

/**
 *
 * @author Gonzalo
 */
public class CategoriaDAO extends Modelo<Categoria>{

    @Override
    public String getNombreModelo() {
        return "Categoria";
    }
    
}
