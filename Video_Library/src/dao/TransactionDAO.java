package dao;

import models.Transaction;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Data Access Object interface for Transaction operations
 * 
 * @author Palaash Atri
 */
public interface TransactionDAO {
    
    /**
     * Add a new transaction
     * @param transaction Transaction object to add
     * @throws SQLException if database error occurs
     */
    void addTransaction(Transaction transaction) throws SQLException;
    
    /**
     * Get a transaction by order ID
     * @param orderId Order ID
     * @return Transaction object or null if not found
     * @throws SQLException if database error occurs
     */
    Transaction getTransactionById(int orderId) throws SQLException;
    
    /**
     * Get all transactions
     * @return List of all transactions
     * @throws SQLException if database error occurs
     */
    List<Transaction> getAllTransactions() throws SQLException;
    
    /**
     * Complete a transaction (mark as returned)
     * @param orderId Order ID
     * @param returnDate Actual return date
     * @throws SQLException if database error occurs
     */
    void completeTransaction(int orderId, Date returnDate) throws SQLException;
    
    /**
     * Delete a transaction
     * @param orderId Order ID to delete
     * @throws SQLException if database error occurs
     */
    void deleteTransaction(int orderId) throws SQLException;
    
    /**
     * Get all overdue transactions (defaulters)
     * @return List of overdue transactions
     * @throws SQLException if database error occurs
     */
    List<Transaction> getOverdueTransactions() throws SQLException;
    
    /**
     * Get all pending transactions (not yet returned)
     * @return List of pending transactions
     * @throws SQLException if database error occurs
     */
    List<Transaction> getPendingTransactions() throws SQLException;
}
