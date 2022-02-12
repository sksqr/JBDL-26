package com.example.L7_annotation_demo;

import com.gfg.analyzer.KeywordAnalyzerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ProductService {

//    @Autowired(required = false)
    @Autowired
    @Qualifier("MyKeywordAnalyzer")
    private KeywordAnalyzerInterface keywordAnalyzerInterface;

    @Value("${keyword.api.endpoint}")
    private String endpoint;

    private HashMap<Integer,Product> map;

    @PostConstruct
    public void initMethod(){
        map = new HashMap<>();
        map.put(1,new Product(1,"laptop"));
    }

    public ProductService() {
        System.out.println("Creating object of ProductService");
    }

    public List<Product> getAllProducts(){
        List<Product> list = new ArrayList<>();
        for(int id:map.keySet()){
            list.add(map.get(id));
        }
        return list;
    }

    public List<Product> getAllProductsByKeyword(String keyword){
        /*
          call external API to with the keyword
         */
        System.out.println("EndPoint:"+endpoint);
        List<Product> list = new ArrayList<>();
        for(int id:map.keySet()){
            list.add(map.get(id));
        }
        return list;
    }

    public void createProduct(Product product){
        if(map.get(product.getId())==null){
            map.put(product.getId(),product);
        }
    }

    public void deleteProduct(Integer id){
        map.remove(id);
    }

    public void updateProduct(Product product){
        map.put(product.getId(),product);
    }
}
