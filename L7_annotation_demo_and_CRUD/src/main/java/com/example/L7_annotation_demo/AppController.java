package com.example.L7_annotation_demo;

import com.gfg.analyzer.KeywordAnalyzerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppController {


    private KeywordAnalyzerInterface keywordAnalyzerInterface;

    public AppController() {
        System.out.println("Creating object of AppController");
    }

//    @Autowired
//    public AppController(KeywordAnalyzerInterface keywordAnalyzerInterface, ProductService productService) {
//        this.keywordAnalyzerInterface = keywordAnalyzerInterface;
//        this.productService = productService;
//    }

        @Autowired
    private ProductService productService;


//    @Autowired
//    public void setProductService(ProductService productService) {
//        this.productService = productService;
//    }

    @GetMapping("getproducts")
    public List<Product> getAllProducts(){
        System.out.println(productService);
       return productService.getAllProducts();
    }


    @GetMapping("products")
    public List<Product> getAllProducts(@RequestParam("keyword") String keyword){
        System.out.println(productService);
        return productService.getAllProductsByKeyword(keyword);
    }

    @PostMapping("product")
    public void createProduct(@RequestBody Product product){
        productService.createProduct(product);
    }

    @DeleteMapping("product/{id}")
    public void deleteProduct(@PathVariable("id") Integer id){
        productService.deleteProduct(id);
    }

    @PutMapping("product")
    public void updateProduct(@RequestBody Product product){
        productService.updateProduct(product);
    }
//com.example.L7_annotation_demo.ProductService@6f78cfef
}
