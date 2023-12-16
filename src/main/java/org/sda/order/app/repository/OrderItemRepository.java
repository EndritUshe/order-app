package org.sda.order.app.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.sda.order.app.entities.OrderItem;
import org.sda.order.app.entities.Product;

import java.util.List;

public class OrderItemRepository {
    private List<OrderItem> orderItems;
    private SessionFactory sessionFactory;
    public OrderItemRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    public void save(OrderItem orderItem) {
        Transaction tx = null;
        try {
            Session session = sessionFactory.isOpen() ? sessionFactory.getCurrentSession() : sessionFactory.openSession();
            tx = session.beginTransaction();
            session.persist(orderItem);
            tx.commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            if  (tx != null) {
                tx.rollback();
            }
        }
    }

    public List<OrderItem> findAllByOrderId(Long orderId) {
        Session session = sessionFactory.openSession();
        return  session.createQuery(" from OrderItem oi where oi.orderId = :orderId", OrderItem.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }
}
