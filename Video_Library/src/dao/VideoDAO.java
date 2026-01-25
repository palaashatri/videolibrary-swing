package dao;

import models.Video;
import java.sql.SQLException;
import java.util.List;

/**
 * Data Access Object interface for Video operations
 * 
 * @author Palaash Atri
 */
public interface VideoDAO {
    
    /**
     * Add a new video to the database
     * @param video Video object to add
     * @throws SQLException if database error occurs
     */
    void addVideo(Video video) throws SQLException;
    
    /**
     * Get a video by ID
     * @param vcdId Video ID
     * @return Video object or null if not found
     * @throws SQLException if database error occurs
     */
    Video getVideoById(String vcdId) throws SQLException;
    
    /**
     * Get all videos
     * @return List of all videos
     * @throws SQLException if database error occurs
     */
    List<Video> getAllVideos() throws SQLException;
    
    /**
     * Update a video
     * @param video Video object with updated data
     * @throws SQLException if database error occurs
     */
    void updateVideo(Video video) throws SQLException;
    
    /**
     * Delete a video
     * @param vcdId Video ID to delete
     * @throws SQLException if database error occurs
     */
    void deleteVideo(String vcdId) throws SQLException;
    
    /**
     * Update video units available
     * @param vcdId Video ID
     * @param units New units count
     * @throws SQLException if database error occurs
     */
    void updateUnitsAvailable(String vcdId, int units) throws SQLException;
}
