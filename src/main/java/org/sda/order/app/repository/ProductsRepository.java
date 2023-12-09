package org.sda.order.app.repository;

import jakarta.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sda.order.app.config.HibernateConfiguration;
import org.sda.order.app.entities.Product;

import java.util.List;

public class ProductsRepository {

    private SessionFactory sessionFactory;

    public ProductsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Product> getProducts() {
        Session session = sessionFactory.openSession();
        TypedQuery<Product> listOfProducts = session.createQuery("Select p from Product p", Product.class);
        List<Product> lista = listOfProducts.getResultList();
        session.close();
        return lista;
    }

    public Product findProductById(Integer id) {
        Session session = sessionFactory.openSession();
        return session.createQuery("Select p from Product p where p.id = ?1", Product.class).setParameter(1, id)
                .getSingleResult();

    }

    public void save(Product product) {
        Transaction tx = null;
        try {
            Session session = sessionFactory.isOpen() ? sessionFactory.getCurrentSession() : sessionFactory.openSession();
            tx = session.beginTransaction();
            session.persist(product);
            tx.commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            if  (tx != null) {
                tx.rollback();
            }
        }
    }

    public Product findProductByName(String productName) {
        Transaction tx = null;

        try  {
            Session session = sessionFactory.openSession();
            List<Product> result = session.createQuery(" from Product p where p.name = :name ", Product.class)
                    .setParameter("name", productName)
                    .getResultList();

            return !result.isEmpty() ? result.get(0) : null;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            if  (tx != null) {
                tx.rollback();
            }
        }
        return null;
    }
}