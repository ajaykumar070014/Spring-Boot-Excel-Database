package com.excelData.service;

import com.excelData.entity.Product;
import com.excelData.helper.Helper;
import com.excelData.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public void save(MultipartFile file){
        try {
            List<Product> products = Helper.convertExcelToListOfProduct(file.getInputStream());
            this.productRepository.saveAll(products);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Product> getAllProduct(){
        return this.productRepository.findAll();
    }
}
