package com.denisov.anything.products;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;

@RestController
@RequestMapping(value ="product/", produces = "application/json")
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository){
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @PostMapping("new")
    public String addProduct(@RequestParam("name")String products){
        JSONObject result = new JSONObject().put("result", "successful:");
        String str = products;
        String[] productsRequest = str.split(",");
        long id = 0;

        try {
            for(String product : productsRequest) {
                id = productService.addProduct(products);
            }
            } catch(Exception e){
            e.printStackTrace();
            result.put("result", "error: unknown error");
            return result.toString();
        }
        result.put("result", "successful: generated id: " + id);
        return result.toString();
    }

    @GetMapping("findAll")
    public String addProduct(){
        JSONObject result = new JSONObject();
        ArrayList<ProductEntity> products = new ArrayList<>();
        Iterator<ProductEntity> iterator = productRepository.findAll().iterator();
        while(iterator.hasNext()){
            products.add(iterator.next());
        }
        if(products.size()==0){
            return new JSONObject().put("result", "error products not found").toString();
        } else {
            result.put("result", "successful: ");
            result.put("products", products);
            return result.toString();
        }
    }
}
