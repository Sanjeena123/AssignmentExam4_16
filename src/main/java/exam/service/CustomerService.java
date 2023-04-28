package exam.service;

import exam.doa.CustomerDataSource;
import exam.entity.Customer;
import org.hibernate.SessionFactory;

import java.util.Scanner;

public class CustomerService {
    public void storeRegestrationCustomerDetail(Customer customer, Scanner scanner, CustomerDataSource dataSource, SessionFactory sessionFactory) {
        for (int i = 0; i < 3; i++) {
            System.out.println("please enter customer Name::");
            String customerName = scanner.next();
            customer.setCustName(customerName);

            System.out.println("please enter customer mobile number:: ");
            long customerMobile = scanner.nextLong();
            customer.setCustmobile(customerMobile);

            System.out.println("please enter customer email::");
            String customerEmail = scanner.next();
            customer.setCustEmail(customerEmail);

            System.out.println("please enter password::");
            String customerPassword = scanner.next();
            customer.setCustPassword(customerPassword);
            dataSource.storeCustomerDetail(customer, sessionFactory);
        }
    }

    public void loginCustomer(Customer customer, Scanner scanner, CustomerDataSource customerDataSource, SessionFactory sessionFactory) {
        System.out.println("Login to continue");
        System.out.println("Enter your username:");
        String userName = scanner.next();

        System.out.println("Enter your password:");
        String password = scanner.next();

        Customer customer1 = customerDataSource.checkEmailRegistration(userName, sessionFactory);
        System.out.println(customer.getCustEmail());
        System.out.println(customer.getCustPassword());

        boolean isLoggedIn = false;
        /*if(customer == null){
            System.out.println("hello");
        }*/

        if (customer != null) {
            if (customer.getCustEmail() != null && customer.getCustPassword().equalsIgnoreCase(password)) {
                System.out.println("Logged in Successfully.");
                isLoggedIn = true;
            }
        } else {
            System.out.println("Login Failure!");
        }

    }

    public static void updateCustomer(Customer customer, Scanner scanner, CustomerDataSource customerDataSource, SessionFactory sessionFactory) {
        System.out.println("please enter a name as you want to update::");
        String updateName = scanner.next();

        System.out.println("please enter mobile number::");
        long mobile = scanner.nextLong();
        if (updateName != null && mobile != 0) {
            customerDataSource.fetchCustomerDetailWithMobile(mobile, sessionFactory);
            if (customer != null) {
                if (customer.getCustmobile() != 0) {
                    customerDataSource.updateCustomer(updateName, customer.getCustId(), sessionFactory);
                }
            } else {
                System.out.println("invalid mobile");
            }


        }

    }

}

