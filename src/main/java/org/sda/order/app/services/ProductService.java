package org.sda.order.app.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sda.order.app.config.HibernateConfiguration;
import org.sda.order.app.entities.Product;
import org.sda.order.app.repository.ProductsRepository;

import java.util.List;

public class ProductService {
    private ProductsRepository productsRepository;
    public ProductService(ProductsRepository productsRepository) {

        this.productsRepository = productsRepository;
    }

    public void printMenu() {
        // 1. Get product list
        List<Product> menu = this.productsRepository.getProducts();
        // 2. Iterate products and print in console
        System.out.println(String.format("%s %s %s %s", "Id:", "Name:        ", "    Price:        ", "Description: "));
        for (Product product : menu) {
            System.out.println(String.format("%1d, %s, %f, %s", product.getId(), product.getName(), product.getPrice(), product.getDescription()));
        }
        System.out.println();
    }

    public void createMenu() {
        Product firstProduct = productsRepository.findProductByName("Pilaf me tasqebap");
        if (firstProduct == null) {
            firstProduct = new Product();
            firstProduct.setName("Pilaf me tasqebap");
            firstProduct.setDescription("Pilafi me i mire ne bot ");
            firstProduct.setPrice(150.0f);
            productsRepository.save(firstProduct);
        }


        Product secondProduct = productsRepository.findProductByName("Pizza margarita");
        if (secondProduct == null) {
            secondProduct = new Product();
            secondProduct.setName("Pizza margarita");
            secondProduct.setDescription("Pizza me salce domate dhe brum");
            secondProduct.setPrice(450.0f);
            productsRepository.save(secondProduct);
        }

        Product thirdProduct = productsRepository.findProductByName("Pizza me proshute");
        if (thirdProduct == null) {
            thirdProduct = new Product();
            thirdProduct.setName("Pizza me proshute");
            thirdProduct.setDescription("Pizza me proshute, salce, dhe brum");
            thirdProduct.setPrice(450.0f);
            productsRepository.save(thirdProduct);
        }
        Product fourthProduct = productsRepository.findProductByName("Supe peshku");
        if (fourthProduct == null) {
            fourthProduct = new Product();
            fourthProduct.setName("Supe peshku");
            fourthProduct.setDescription("Pizza me proshute, salce, dhe brum");
            fourthProduct.setPrice(450.0f);
            productsRepository.save(fourthProduct);
        }

    }
}
