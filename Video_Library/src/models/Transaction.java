package models;

import java.util.Date;

/**
 * Transaction model class
 * Represents a video rental transaction in the system
 * 
 * @author Palaash Atri
 */
public class Transaction {
    private int orderId;
    private String vcdId;
    private String vcdTitle;
    private String custId;
    private String custName;
    private Date dateOfIssue;
    private Date dateOfReturn;
    private Date dateReturnedOn;
    
    public Transaction() {
    }
    
    public Transaction(int orderId, String vcdId, String vcdTitle, String custId, String custName,
                      Date dateOfIssue, Date dateOfReturn, Date dateReturnedOn) {
        this.orderId = orderId;
        this.vcdId = vcdId;
        this.vcdTitle = vcdTitle;
        this.custId = custId;
        this.custName = custName;
        this.dateOfIssue = dateOfIssue;
        this.dateOfReturn = dateOfReturn;
        this.dateReturnedOn = dateReturnedOn;
    }
    
    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public String getVcdId() {
        return vcdId;
    }
    
    public void setVcdId(String vcdId) {
        this.vcdId = vcdId;
    }
    
    public String getVcdTitle() {
        return vcdTitle;
    }
    
    public void setVcdTitle(String vcdTitle) {
        this.vcdTitle = vcdTitle;
    }
    
    public String getCustId() {
        return custId;
    }
    
    public void setCustId(String custId) {
        this.custId = custId;
    }
    
    public String getCustName() {
        return custName;
    }
    
    public void setCustName(String custName) {
        this.custName = custName;
    }
    
    public Date getDateOfIssue() {
        return dateOfIssue;
    }
    
    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }
    
    public Date getDateOfReturn() {
        return dateOfReturn;
    }
    
    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }
    
    public Date getDateReturnedOn() {
        return dateReturnedOn;
    }
    
    public void setDateReturnedOn(Date dateReturnedOn) {
        this.dateReturnedOn = dateReturnedOn;
    }
    
    /**
     * Check if transaction is overdue
     */
    public boolean isOverdue() {
        if (dateReturnedOn != null) {
            return false; // Already returned
        }
        Date today = new Date();
        return today.after(dateOfReturn);
    }
    
    @Override
    public String toString() {
        return "Transaction{" +
                "orderId=" + orderId +
                ", vcdId='" + vcdId + '\'' +
                ", vcdTitle='" + vcdTitle + '\'' +
                ", custId='" + custId + '\'' +
                ", custName='" + custName + '\'' +
                ", dateOfIssue=" + dateOfIssue +
                ", dateOfReturn=" + dateOfReturn +
                ", dateReturnedOn=" + dateReturnedOn +
                '}';
    }
}
