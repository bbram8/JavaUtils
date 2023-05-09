package com.bbram.springbootesapp;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

// specify the index name
@Document(indexName = "Product_Catalog")

// Model class to specify the fields in the product index
public class Product_Model {


    //Product ID which is unique for each document in Elasticsearch
    @Id
    private String product_id;

    @Field(type = FieldType.Text, name = "product name")
    private String product_name;

    @Field(type = FieldType.Text, name = "product description")
    private String product_description;

    @Field(type = FieldType.Double, name = "product_price")
    private double product_price;

    public String getID() {
        return product_id;
    }
}
