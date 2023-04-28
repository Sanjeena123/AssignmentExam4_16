package exam.doa;

import exam.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;

public class CustomerDataSource {
    public SessionFactory getConnection() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Customer.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        return sessionFactory;

    }

    public void storeCustomerDetail(Customer customer, SessionFactory sessionFactory) {
        //SessionFactory sessionFactory = getConnection();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(customer);


            transaction.commit();

        } catch (Exception e) {
            System.err.println("Error Details ::" + e.getMessage());
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Customer checkEmailRegistration(String userName, SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Customer customer = null;
        try {
            transaction = session.beginTransaction();
            String selectHql = "From Customer c where c.custEmail =" + userName + "";

            javax.persistence.Query query = session.createQuery(selectHql);
            customer = (Customer) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error Details ::" + e.getMessage());
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return customer;
    }

    public void updateCustomer(String updateName, int userId, SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String updateHql = "UPDATE Customer set custName = :n where custId = :i";
            Query query = session.createQuery(updateHql);
            query.setParameter("n", updateName);
            query.setParameter("i", userId);
            int rowsUpdate = query.executeUpdate();
            System.out.println(rowsUpdate + " row successfully updated!");
        } catch (Exception e) {
            System.err.println("enter detail::" + e.getMessage());
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Customer fetchCustomerDetailWithMobile(long mobile, SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Customer customer = null;
        try {
            transaction = session.beginTransaction();
            String selectHql = "Feom customer c where c.custmobile= " + mobile;
            Query query = session.createQuery(selectHql);
            customer = (Customer) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("erroe detail" + e.getMessage());
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return customer;
    }
}



