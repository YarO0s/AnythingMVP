package com.denisov.anything.products;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product/")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
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
}
