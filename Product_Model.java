package com.bbram.springbootesapp;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

// specify the index name
@Document(indexName = "Product_Catalog")

// Model class to specify the fields in the product index
public class Product_Model {


    //Product ID which is unique for each document in Elasticsearch
    @Id
    private String id;

    @Field(type = FieldType.Text, name = "product name")
    private String product_name;

    @Field(type = FieldType.Text, name = "product description")
    private String product_description;

    @Field(type = FieldType.Double, name = "product_price")
    private double product_price;

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getProduct_description() {
        return product_description;
    }
    
    public void setProduct_description(String description){
        product_description = description; 
    }
    
    public String getProduct_name()
    {
        return product_name; 
    }
    
    public void setProduct_name(String name){
        product_name = name; 
    }
}

