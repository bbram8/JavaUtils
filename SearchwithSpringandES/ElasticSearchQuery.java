package com.bbram.springbootesapp;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
//Repository class to conduct all queries on ElasticSearch
public class ElasticSearchQuery {

    @Autowired

    private ElasticsearchClient esClient;
    private final String indexName = "Product_Catalog";

    public String createOrUpdateDocument(Product_Model product) throws IOException
    {   String product_id = product.getID();
        IndexResponse response = esClient.index(index -> index
                .index(indexName)
                .id(product.getID())
                .document(product));
        if (response.result().name().equals("Created"))
        {
            return new StringBuilder("Document successfully created").toString();
        } else if (response.result().name().equals("Updated")) {

            return new StringBuilder("Document successfully updated").toString();
        }

        return new StringBuilder("Error occurred in creating / updating document").toString();

    }

    public Product_Model getDocumentByID(String productID) throws IOException
    {
        Product_Model product = null;
        GetResponse<Product_Model> response = esClient.get(doc -> doc
                .index(indexName)
                .id(productID) ,
                Product_Model.class);
        if (response.found())
        {
            product = response.source();
            System.out.println("Product Name" + product.getProduct_name());
        }
        else
        {
            System.out.println("Product not found");
        }

        return product;
    }

    public String deleteDocumentByID(String productID) throws IOException{
        DeleteRequest request = DeleteRequest.of(del -> del
                .index(indexName)
                .id(productID) );
        DeleteResponse deleteResponse = esClient.delete(request);

        if (Objects.nonNull(deleteResponse.result()) && !deleteResponse.result().name().equals("Not Found")
        {
            return new StringBuilder("Product ID" + deleteResponse.id() + "is now deleted").toString();
        }

        System.out.println("Product not found");
        return new StringBuilder("Product ID" + deleteResponse.id() + "does not exist").toString();
    }

    public List<Product_Model> searchAllDocs() throws IOException
    {
        SearchRequest searchRequest = SearchRequest.of(req -> req.index(indexName));
        SearchResponse searchResponse = esClient.search(searchRequest, Product_Model.class);

        List<Hit> doc_hits = new ArrayList<>();
        List<Product_Model> product_models = new ArrayList<>();

        for (Hit object:doc_hits)
        {
            System.out.println(((Product_Model) object.source()));
            product_models.add((Product_Model) object.source());
        }
        return product_models; 
    }
}
