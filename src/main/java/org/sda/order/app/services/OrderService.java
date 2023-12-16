package org.sda.order.app.services;
import org.sda.order.app.dto.OrderItemRead;
import org.sda.order.app.entities.Order;
import org.sda.order.app.entities.OrderItem;
import org.sda.order.app.entities.Product;
import org.sda.order.app.repository.OrderItemRepository;
import org.sda.order.app.repository.OrderRepository;
import org.sda.order.app.repository.ProductsRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrderService {
    private final ProductsRepository productsRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(ProductsRepository productsRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository){
        this.orderRepository = orderRepository;
        this.productsRepository = productsRepository;
        this.orderItemRepository = orderItemRepository;
    }
    public void showOrderMenu(){
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        System.out.println();
        System.out.println();
        System.out.println("-------- Welcome to Order-Faster ----------");

        while(!userInput.equals("exit")){
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
                    System.out.println("Quantity " + newOrderItem.getQuantity());
                   //Tento njeher me new scanner
                    Scanner scanner1 =  new Scanner(System.in);
                    exitOrderRead = scanner1.nextLine();
                }

                if (orderItems.isEmpty()) {
                    System.out.println("No items added!");
                }
                else {
                    Long id = saveOrder(orderItems);
                    System.out.println("Order saved successfully: " + id);
                }
            }
            else if("2".equals(userInput)){
                System.out.println("Insert order id to check details");
                Scanner idScanner =  new Scanner(System.in);
                Long orderId = idScanner.nextLong();

                if (orderId != null) {
                    Order dbOrder = orderRepository.getById(orderId);
                    if (dbOrder == null) {
                        System.out.println("Order with id: " + orderId + " is not present in database");
                    }
                    {
                        List<OrderItem> orderItems = orderItemRepository.findAllByOrderId(orderId);
                        System.out.println("----------- Invoice details ---------------");
                        System.out.println("Invoice Number: " + dbOrder.getId());
                        System.out.println("Created date: " + dbOrder.getDateCreated());
                        orderItems.forEach(o -> {
                            System.out.println(String.format("%s %s %s", o.getProductName(), o.getProductPrice(), o.getQuantity()));
                        });
                        System.out.println("Total: " + dbOrder.getTotal() + "L");
                        System.out.println("----------- End invoice details ---------------");
                    }
                }
            } else{
                System.out.println("Not a valid selection");

            }
        }


    }

    public Long saveOrder(List<OrderItemRead> orderItems) {
        Order order = new Order();
        order.setDateCreated(new Date());
        order = orderRepository.save(order);

        Float total = 0f;
        for (OrderItemRead orderItemRead : orderItems) {
            Product selecteProduct = productsRepository.findProductById(orderItemRead.getProductId());
            if (selecteProduct == null) throw new RuntimeException("Product not found");

            total += orderItemRead.getQuantity() * selecteProduct.getPrice();
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getId());
            orderItem.setProductId(selecteProduct.getId());
            orderItem.setQuantity(orderItemRead.getQuantity());
            orderItem.setProductName(selecteProduct.getName());
            orderItem.setProductPrice(selecteProduct.getPrice());
            orderItemRepository.save(orderItem);
        }
        order.setTotal(total);
        orderRepository.save(order);
        return order.getId();
    }


}
