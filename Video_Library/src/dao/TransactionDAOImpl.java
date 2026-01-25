package dao;

import models.Transaction;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementation of TransactionDAO interface
 * Provides database operations for Transaction entities using prepared statements
 * 
 * @author Palaash Atri
 */
public class TransactionDAOImpl implements TransactionDAO {
    
    /**
     * Add a new transaction to the database
     * @param transaction Transaction object to add
     * @throws SQLException if database error occurs
     */
    @Override
    public void addTransaction(Transaction transaction) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO transactionrcvd VALUES(?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, transaction.getOrderId());
            pstmt.setString(2, transaction.getVcdId());
            pstmt.setString(3, transaction.getVcdTitle());
            pstmt.setString(4, transaction.getCustId());
            pstmt.setString(5, transaction.getCustName());
            pstmt.setDate(6, new java.sql.Date(transaction.getDateOfIssue().getTime()));
            pstmt.setDate(7, new java.sql.Date(transaction.getDateOfReturn().getTime()));
            
            if (transaction.getDateReturnedOn() != null) {
                pstmt.setDate(8, new java.sql.Date(transaction.getDateReturnedOn().getTime()));
            } else {
                pstmt.setDate(8, null);
            }
            
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
     * Get a transaction by order ID
     * @param orderId Order ID
     * @return Transaction object or null if not found
     * @throws SQLException if database error occurs
     */
    @Override
    public Transaction getTransactionById(int orderId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Transaction transaction = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM transactionrcvd WHERE order_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderId);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                transaction = new Transaction();
                transaction.setOrderId(rs.getInt("order_id"));
                transaction.setVcdId(rs.getString("vcd_id"));
                transaction.setVcdTitle(rs.getString("vcd_title"));
                transaction.setCustId(rs.getString("cust_id"));
                transaction.setCustName(rs.getString("cust_name"));
                transaction.setDateOfIssue(rs.getDate("date_of_issue"));
                transaction.setDateOfReturn(rs.getDate("date_of_return"));
                
                java.sql.Date returnedOn = rs.getDate("date_returned_on");
                if (returnedOn != null) {
                    transaction.setDateReturnedOn(returnedOn);
                }
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
        
        return transaction;
    }
    
    /**
     * Get all transactions from the database
     * @return List of all transactions
     * @throws SQLException if database error occurs
     */
    @Override
    public List<Transaction> getAllTransactions() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Transaction> transactions = new ArrayList<>();
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM transactionrcvd";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setOrderId(rs.getInt("order_id"));
                transaction.setVcdId(rs.getString("vcd_id"));
                transaction.setVcdTitle(rs.getString("vcd_title"));
                transaction.setCustId(rs.getString("cust_id"));
                transaction.setCustName(rs.getString("cust_name"));
                transaction.setDateOfIssue(rs.getDate("date_of_issue"));
                transaction.setDateOfReturn(rs.getDate("date_of_return"));
                
                java.sql.Date returnedOn = rs.getDate("date_returned_on");
                if (returnedOn != null) {
                    transaction.setDateReturnedOn(returnedOn);
                }
                
                transactions.add(transaction);
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
        
        return transactions;
    }
    
    /**
     * Complete a transaction (mark as returned)
     * @param orderId Order ID
     * @param returnDate Actual return date
     * @throws SQLException if database error occurs
     */
    @Override
    public void completeTransaction(int orderId, Date returnDate) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "UPDATE transactionrcvd SET date_returned_on = ? WHERE order_id = ?";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setDate(1, new java.sql.Date(returnDate.getTime()));
            pstmt.setInt(2, orderId);
            
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
     * Delete a transaction by order ID
     * @param orderId Order ID to delete
     * @throws SQLException if database error occurs
     */
    @Override
    public void deleteTransaction(int orderId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "DELETE FROM transactionrcvd WHERE order_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderId);
            
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
     * Get all overdue transactions (defaulters)
     * Transactions where date_returned_on is NULL and date_of_return is before today
     * @return List of overdue transactions
     * @throws SQLException if database error occurs
     */
    @Override
    public List<Transaction> getOverdueTransactions() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Transaction> transactions = new ArrayList<>();
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM transactionrcvd WHERE date_returned_on IS NULL AND date_of_return < CURDATE()";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setOrderId(rs.getInt("order_id"));
                transaction.setVcdId(rs.getString("vcd_id"));
                transaction.setVcdTitle(rs.getString("vcd_title"));
                transaction.setCustId(rs.getString("cust_id"));
                transaction.setCustName(rs.getString("cust_name"));
                transaction.setDateOfIssue(rs.getDate("date_of_issue"));
                transaction.setDateOfReturn(rs.getDate("date_of_return"));
                
                java.sql.Date returnedOn = rs.getDate("date_returned_on");
                if (returnedOn != null) {
                    transaction.setDateReturnedOn(returnedOn);
                }
                
                transactions.add(transaction);
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
        
        return transactions;
    }
    
    /**
     * Get all pending transactions (not yet returned)
     * Transactions where date_returned_on is NULL
     * @return List of pending transactions
     * @throws SQLException if database error occurs
     */
    @Override
    public List<Transaction> getPendingTransactions() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Transaction> transactions = new ArrayList<>();
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM transactionrcvd WHERE date_returned_on IS NULL";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setOrderId(rs.getInt("order_id"));
                transaction.setVcdId(rs.getString("vcd_id"));
                transaction.setVcdTitle(rs.getString("vcd_title"));
                transaction.setCustId(rs.getString("cust_id"));
                transaction.setCustName(rs.getString("cust_name"));
                transaction.setDateOfIssue(rs.getDate("date_of_issue"));
                transaction.setDateOfReturn(rs.getDate("date_of_return"));
                
                java.sql.Date returnedOn = rs.getDate("date_returned_on");
                if (returnedOn != null) {
                    transaction.setDateReturnedOn(returnedOn);
                }
                
                transactions.add(transaction);
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
        
        return transactions;
    }
}
