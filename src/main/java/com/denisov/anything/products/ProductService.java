package com.denisov.anything.products;

import com.denisov.anything.recepies.RecipeEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    //TODO: add functionality that says what products do not exist and what of them are present and send result on present
    public ArrayList<ProductEntity> selectProductsByName(String[] names){
        ArrayList<ProductEntity> products = new ArrayList<ProductEntity>();
        for(String name : names){
            Optional<ProductEntity> opt = productRepository.findByName(name);
            if(opt.isPresent()){
                products.add(opt.get());
            }
        }
        return products;
    }
}
