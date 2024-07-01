package com.excelData.controller;


import com.excelData.entity.Product;
import com.excelData.helper.Helper;
import com.excelData.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/upload")
    public ResponseEntity<?>upload(@RequestParam("file")MultipartFile file){
        if(Helper.checkExcelFormat(file)){
            this.productService.save(file);
            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file");
    }
    @GetMapping
    public List<Product>getAllProduct(){
        return this.productService.getAllProduct();
    }

}
