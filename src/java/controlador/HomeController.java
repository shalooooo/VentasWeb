/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Gonzalo
 */
@Controller
public class HomeController {
    
    @RequestMapping(value="/home", method=RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("mensaje","mensaje desde el controlador, fuck yeah chuchetumare!");
        return "home";
    }
    
}
