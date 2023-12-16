package org.sda.order.app.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.sda.order.app.entities.Order;
import org.sda.order.app.entities.Product;

import java.util.List;

public class OrderRepository {
    private final SessionFactory sessionFactory;

    public OrderRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Order save(Order order) {
        Transaction tx = null;
        try {
            Session session = sessionFactory.isOpen() ? sessionFactory.getCurrentSession() : sessionFactory.openSession();
            tx = session.beginTransaction();
            if (session.contains(order))
                session.persist(order);
            else
                session.merge(order);
            tx.commit();
            return order;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            if  (tx != null) {
                tx.rollback();
            }
        }

        throw new RuntimeException("Error saving order");
    }

    public Order getById(Long orderId) {
        Session session = sessionFactory.openSession();
        List<Order> result = session.createQuery("from Order p where p.id = :id", Order.class)
                .setParameter("id", orderId)
                .getResultList();
        return !result.isEmpty() ? result.get(0) : null ;
    }
}
