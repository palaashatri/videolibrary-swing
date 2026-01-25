package dao;

import models.Customer;
import java.sql.SQLException;
import java.util.List;

/**
 * Data Access Object interface for Customer operations
 * 
 * @author Palaash Atri
 */
public interface CustomerDAO {
    
    /**
     * Add a new customer to the database
     * @param customer Customer object to add
     * @throws SQLException if database error occurs
     */
    void addCustomer(Customer customer) throws SQLException;
    
    /**
     * Get a customer by ID
     * @param custId Customer ID
     * @return Customer object or null if not found
     * @throws SQLException if database error occurs
     */
    Customer getCustomerById(String custId) throws SQLException;
    
    /**
     * Get all customers
     * @return List of all customers
     * @throws SQLException if database error occurs
     */
    List<Customer> getAllCustomers() throws SQLException;
    
    /**
     * Update a customer
     * @param customer Customer object with updated data
     * @throws SQLException if database error occurs
     */
    void updateCustomer(Customer customer) throws SQLException;
    
    /**
     * Delete a customer
     * @param custId Customer ID to delete
     * @throws SQLException if database error occurs
     */
    void deleteCustomer(String custId) throws SQLException;
}
