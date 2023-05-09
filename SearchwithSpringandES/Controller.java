package com.bbram.springbootesapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class Controller {

    @Autowired
    private ESQuery esQuery;

    @PostMapping(value = "/createOrUpdateDocument")

    public ResponseEntity<Object> createOrUpdateDocument(@RequestBody Product_Model product_model) throws IOException {
         String response = esQuery.createOrUpdateDocument(product_model);
         return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/getDocument")
    public ResponseEntity<Object> getDocumentByID(@RequestParam String productID) throws IOException{
        Product_Model product_model = esQuery.getDocumentByID(productID);
        return new ResponseEntity<>(product_model, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteDocument")
    public ResponseEntity<Object> deleteDocumentByID(@RequestParam String productID) throws IOException{
        String response = esQuery.deleteDocumentByID(productID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/searchDocument")
    public ResponseEntity<Object> searchDocument() throws IOException {

        List<Product_Model> products = esQuery.searchAllDocs();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
