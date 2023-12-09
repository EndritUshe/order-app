package org.sda.order.app.repository;

import jakarta.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.sda.order.app.config.HibernateConfiguration;
import org.sda.order.app.entities.Products;

import java.util.List;

public class ProductsRepository {
    public void insertMenu() {

        try (SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory()) {
            for (int i = 1; i < 11; i++) {
                Session session = sessionFactory.getCurrentSession();
                session.beginTransaction();
                Products products = new Products();
                products.setName("Pilafi: " + i);
                products.setDescription("ingredients for pilafi: " + i);
                products.setPrice(150.0f);

                session.persist(products);
                session.getTransaction().commit();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printMenu() {
        List<Products> menu = menuList();
        System.out.println(String.format("%s %s %s %s", "Id,", "Name,    ", "Price,      ", "Description "));
        for (Products product : menu) {
            System.out.println(String.format("%1d, %s, %f, %s", product.getId(), product.getName(), product.getPrice(), product.getDescription()));
        }
        System.out.println();
    }

    public List<Products> menuList() {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        TypedQuery<Products> listOfProducts = session.createQuery("Select p from Products p", Products.class);
        List<Products> lista = listOfProducts.getResultList();
        session.close();
        return lista;
    }

    public Products findProductById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        return session.createQuery("Select p from Products p where p.id = ?1", Products.class).setParameter(1, id)
                .getSingleResult();

    }
}