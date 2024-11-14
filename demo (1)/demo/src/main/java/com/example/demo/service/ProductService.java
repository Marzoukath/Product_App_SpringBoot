package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repo.IProductRepo;

@Service
public class ProductService {

    @Autowired
    IProductRepo repo;

    public List<Product> getAllProductItems() {
        ArrayList<Product> productList = new ArrayList<>();
        repo.findAll().forEach(product -> productList.add(product));

        return productList;
    }

    public Product getProductItemById(Long id) {
        return repo.findById(id).get();
    }

    public boolean updateStatus(Long id) {
        Product product = getProductItemById(id);
        product.setStatus("Sold");

        return saveOrUpdateProductItem(product);
    }

    public boolean saveOrUpdateProductItem(Product product) {
        Product updatedObj = repo.save(product);

        if (getProductItemById(updatedObj.getId()) != null) {
            return true;
        }

        return false;
    }

    public boolean deleteProductItem(Long id) {
        repo.deleteById(id);

        if (repo.findById(id).isEmpty()) {
            return true;
        }

        return false;
    }

}
