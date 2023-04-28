package exam.main;

import exam.doa.CustomerDataSource;
import exam.entity.Customer;
import exam.service.CustomerService;
import org.hibernate.SessionFactory;

import java.util.Scanner;

public class CustomerMain {
    public static Customer customer= new Customer();
    public static CustomerDataSource source = new CustomerDataSource();
    public static CustomerService customerService = new CustomerService();
   public static SessionFactory sessionFactory = source.getConnection();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //customerService.storeRegestrationCustomerDetail(customer,scanner,source,sessionFactory);
        customerService.loginCustomer(customer,scanner,source,sessionFactory);

    }
}
