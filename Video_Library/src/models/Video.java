package models;

/**
 * Video (VCD) model class
 * Represents a video record in the system
 * 
 * @author Palaash Atri
 */
public class Video {
    private String vcdId;
    private String title;
    private String genre;
    private String rating;
    private int priceInRupees;
    private int unitsAvailable;
    
    public Video() {
    }
    
    public Video(String vcdId, String title, String genre, String rating, int priceInRupees, int unitsAvailable) {
        this.vcdId = vcdId;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.priceInRupees = priceInRupees;
        this.unitsAvailable = unitsAvailable;
    }
    
    // Getters and Setters
    public String getVcdId() {
        return vcdId;
    }
    
    public void setVcdId(String vcdId) {
        this.vcdId = vcdId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public String getRating() {
        return rating;
    }
    
    public void setRating(String rating) {
        this.rating = rating;
    }
    
    public int getPriceInRupees() {
        return priceInRupees;
    }
    
    public void setPriceInRupees(int priceInRupees) {
        this.priceInRupees = priceInRupees;
    }
    
    public int getUnitsAvailable() {
        return unitsAvailable;
    }
    
    public void setUnitsAvailable(int unitsAvailable) {
        this.unitsAvailable = unitsAvailable;
    }
    
    @Override
    public String toString() {
        return "Video{" +
                "vcdId='" + vcdId + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", rating='" + rating + '\'' +
                ", priceInRupees=" + priceInRupees +
                ", unitsAvailable=" + unitsAvailable +
                '}';
    }
}
