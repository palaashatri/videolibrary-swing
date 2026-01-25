package dao;

import models.Video;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of VideoDAO interface
 * Provides database operations for Video entities using prepared statements
 * 
 * @author Palaash Atri
 */
public class VideoDAOImpl implements VideoDAO {
    
    /**
     * Add a new video to the database
     * @param video Video object to add
     * @throws SQLException if database error occurs
     */
    @Override
    public void addVideo(Video video) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO vcd_record VALUES(?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, video.getVcdId());
            pstmt.setString(2, video.getTitle());
            pstmt.setString(3, video.getGenre());
            pstmt.setString(4, video.getRating());
            pstmt.setInt(5, video.getPriceInRupees());
            pstmt.setInt(6, video.getUnitsAvailable());
            
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
     * Get a video by ID
     * @param vcdId Video ID
     * @return Video object or null if not found
     * @throws SQLException if database error occurs
     */
    @Override
    public Video getVideoById(String vcdId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Video video = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM vcd_record WHERE vcd_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vcdId);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                video = new Video();
                video.setVcdId(rs.getString("vcd_id"));
                video.setTitle(rs.getString("title"));
                video.setGenre(rs.getString("genre"));
                video.setRating(rs.getString("rating"));
                video.setPriceInRupees(rs.getInt("price_in_rupees"));
                video.setUnitsAvailable(rs.getInt("NO_OF_UNITS_AVAILABLE"));
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
        
        return video;
    }
    
    /**
     * Get all videos from the database
     * @return List of all videos
     * @throws SQLException if database error occurs
     */
    @Override
    public List<Video> getAllVideos() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Video> videos = new ArrayList<>();
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM vcd_record";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Video video = new Video();
                video.setVcdId(rs.getString("vcd_id"));
                video.setTitle(rs.getString("title"));
                video.setGenre(rs.getString("genre"));
                video.setRating(rs.getString("rating"));
                video.setPriceInRupees(rs.getInt("price_in_rupees"));
                video.setUnitsAvailable(rs.getInt("NO_OF_UNITS_AVAILABLE"));
                videos.add(video);
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
        
        return videos;
    }
    
    /**
     * Update an existing video
     * @param video Video object with updated data
     * @throws SQLException if database error occurs
     */
    @Override
    public void updateVideo(Video video) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "UPDATE vcd_record SET title = ?, genre = ?, rating = ?, " +
                        "price_in_rupees = ?, NO_OF_UNITS_AVAILABLE = ? WHERE vcd_id = ?";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, video.getTitle());
            pstmt.setString(2, video.getGenre());
            pstmt.setString(3, video.getRating());
            pstmt.setInt(4, video.getPriceInRupees());
            pstmt.setInt(5, video.getUnitsAvailable());
            pstmt.setString(6, video.getVcdId());
            
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
     * Delete a video by ID
     * @param vcdId Video ID to delete
     * @throws SQLException if database error occurs
     */
    @Override
    public void deleteVideo(String vcdId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "DELETE FROM vcd_record WHERE vcd_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vcdId);
            
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
     * Update the number of units available for a video
     * @param vcdId Video ID
     * @param units New units count
     * @throws SQLException if database error occurs
     */
    @Override
    public void updateUnitsAvailable(String vcdId, int units) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "UPDATE vcd_record SET NO_OF_UNITS_AVAILABLE = ? WHERE vcd_id = ?";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, units);
            pstmt.setString(2, vcdId);
            
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
