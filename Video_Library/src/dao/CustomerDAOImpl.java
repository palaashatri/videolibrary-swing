package dao;

import models.Customer;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of CustomerDAO interface
 * Provides database operations for Customer entities using prepared statements
 * 
 * @author Palaash Atri
 */
public class CustomerDAOImpl implements CustomerDAO {
    
    /**
     * Add a new customer to the database
     * @param customer Customer object to add
     * @throws SQLException if database error occurs
     */
    @Override
    public void addCustomer(Customer customer) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO customer_record VALUES(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, customer.getCustId());
            pstmt.setString(2, customer.getName());
            pstmt.setString(3, customer.getPhoneNo());
            pstmt.setString(4, customer.getAddress());
            
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    // Log error
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // Log error
                }
            }
        }
    }
    
    /**
     * Get a customer by ID
     * @param custId Customer ID
     * @return Customer object or null if not found
     * @throws SQLException if database error occurs
     */
    @Override
    public Customer getCustomerById(String custId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Customer customer = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM customer_record WHERE cust_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, custId);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                customer = new Customer();
                customer.setCustId(rs.getString("cust_id"));
                customer.setName(rs.getString("name"));
                customer.setPhoneNo(rs.getString("phone_no"));
                customer.setAddress(rs.getString("address"));
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // Log error
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    // Log error
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // Log error
                }
            }
        }
        
        return customer;
    }
    
    /**
     * Get all customers from the database
     * @return List of all customers
     * @throws SQLException if database error occurs
     */
    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Customer> customers = new ArrayList<>();
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM customer_record";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustId(rs.getString("cust_id"));
                customer.setName(rs.getString("name"));
                customer.setPhoneNo(rs.getString("phone_no"));
                customer.setAddress(rs.getString("address"));
                customers.add(customer);
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // Log error
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    // Log error
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // Log error
                }
            }
        }
        
        return customers;
    }
    
    /**
     * Update an existing customer
     * @param customer Customer object with updated data
     * @throws SQLException if database error occurs
     */
    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "UPDATE customer_record SET name = ?, phone_no = ?, address = ? WHERE cust_id = ?";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getPhoneNo());
            pstmt.setString(3, customer.getAddress());
            pstmt.setString(4, customer.getCustId());
            
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    // Log error
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // Log error
                }
            }
        }
    }
    
    /**
     * Delete a customer by ID
     * @param custId Customer ID to delete
     * @throws SQLException if database error occurs
     */
    @Override
    public void deleteCustomer(String custId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "DELETE FROM customer_record WHERE cust_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, custId);
            
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    // Log error
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // Log error
                }
            }
        }
    }
}
