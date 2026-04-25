package hce.web.hce_dmo_web1.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hce.web.hce_dmo_web1.models.Product;
import hce.web.hce_dmo_web1.models.ProductAPI;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public List<Product> listProducts() {
        return ProductAPI.getAll();
    }
}