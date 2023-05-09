package com.bbram.springbootesapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class ViewControl {

    @Autowired
    private ESQuery esQuery;

    @GetMapping(value = "/")
    public String viewMainPage(Model model) throws IOException{
        model.addAttribute("getProductDocs", esQuery.searchAllDocs());
    }

    @PostMapping(value = "/saveProduct")
    public String saveProduct(@ModelAttribute("product") Product_Model product_model) throws IOException{
        esQuery.createOrUpdateDocument(product_model);
        return "redirect:/";
    }

    @GetMapping(value = "/showFormForProductUpdate{id}")
    public String showFormForProductUpdate(@PathVariable(value = "id") String id, Model model) throws IOException{
        Product_Model product_model = esQuery.getDocumentByID(id);
        model.addAttribute("product_model", product_model);
        return "UpdateProductDocument";
    }

    @GetMapping(value = "/showNewProductForm")
    public String showNewProductForm(Model model) throws IOException{
        Product_Model product_model = new Product_Model();
        model.addAttribute("product_model", product_model);
        return "NewProductDocument";
    }

    @GetMapping(value = "/deleteProduct/{id}")
    public String deleteProduct(@PathVariable (value= "id"), String id) throws IOException{
        this.esQuery.deleteDocumentByID(id);
        return "redirect:/";
    }

}
