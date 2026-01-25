package models;

/**
 * Customer model class
 * Represents a customer record in the system
 * 
 * @author Palaash Atri
 */
public class Customer {
    private String custId;
    private String name;
    private String phoneNo;
    private String address;
    
    public Customer() {
    }
    
    public Customer(String custId, String name, String phoneNo, String address) {
        this.custId = custId;
        this.name = name;
        this.phoneNo = phoneNo;
        this.address = address;
    }
    
    // Getters and Setters
    public String getCustId() {
        return custId;
    }
    
    public void setCustId(String custId) {
        this.custId = custId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhoneNo() {
        return phoneNo;
    }
    
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Override
    public String toString() {
        return "Customer{" +
                "custId='" + custId + '\'' +
                ", name='" + name + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
