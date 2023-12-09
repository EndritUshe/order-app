package org.sda.order.app;

import org.sda.order.app.config.HibernateConfiguration;
import org.sda.order.app.repository.ProductsRepository;
import org.sda.order.app.services.OrderService;
import org.sda.order.app.services.ProductService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductsRepository productsRepository = new ProductsRepository(HibernateConfiguration.getSessionFactory());
        ProductService productService = new ProductService(productsRepository);
        OrderService orderService = new OrderService(productsRepository);

        productService.createMenu();
        productService.printMenu();
        orderService.showOrderMenu();




        /*int orderNumber;
        String somethingElse = "yes";
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to oder?");
        orderNumber = scanner.nextInt();
        System.out.println();
        System.out.println("You have ordered: " + productsRepository.findProductById(orderNumber));

        System.out.println("What would you like to continue? yes/no");
        String answer = scanner.nextLine();

        if(answer.equals(somethingElse)){
            System.out.println("What would you like to oder?");
            orderNumber = scanner.nextInt();
            System.out.println("You have ordered: " + productsRepository.findProductById(orderNumber));
        }*/
//        do{
//            System.out.println("What would you like to oder?");
//            orderNumber = scanner.nextInt();
//            System.out.println("You have ordered: " + productsRepository.findProductById(orderNumber));
//        }while (answer.equals(somethingElse));
//
//        if(answer.equals(somethingElse)){
//            orderNumber = scanner.nextInt();
//            System.out.println("What would you like to oder?");
//
//        }
    }
}


//U printua menuja
//Klienti zgjedh ushqimet nga lista
//Kur mbaron se zgjedhuri