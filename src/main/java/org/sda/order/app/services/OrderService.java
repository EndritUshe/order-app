package org.sda.order.app.services;
import org.sda.order.app.dto.OrderItemRead;
import org.sda.order.app.repository.ProductsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderService {
    private ProductsRepository productsRepository;
    public OrderService(ProductsRepository productsRepository){
        this.productsRepository = productsRepository;
    }
    public void showOrderMenu(){
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        System.out.println();
        System.out.println();
        System.out.println("-------- Welcome to Order-Faster ----------");

        while(!"exit".equals(userInput)){
            System.out.println("Press 1 to Order:");
            System.out.println();
            System.out.println("Press 2 to see the menu again.");
            System.out.println();
            System.out.println("Type exit to close the application:");
            userInput = scanner.nextLine();

            if("1".equals(userInput)) {
                String exitOrderRead = "";
                List<OrderItemRead> orderItems = new ArrayList<>();
                while (!"done".equals(exitOrderRead)) {
                    System.out.println("Please enter product number you would like to order: ");
                    Long productId = scanner.nextLong();
                    System.out.println("Please enter the quantity you would like to order: ");
                    int quantity = scanner.nextInt();

                    if (quantity < 1) {
                        System.out.println("Not a valid quantity");
                    }

                    OrderItemRead newOrderItem = new OrderItemRead();
                    newOrderItem.setProductId(productId);
                    newOrderItem.setQuantity(quantity);
                    orderItems.add(newOrderItem);

                    System.out.println("Type 'done' to close the order or press enter to continue!");
                   //Tento njeher me new scanner
                    exitOrderRead = scanner.nextLine();
                }
            }
            else if("2".equals(userInput)){

            } else{
                System.out.println("Not a valid selection");
            }
        }


    }


}
