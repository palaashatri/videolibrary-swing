package utils;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * UI Manager utility for applying modern look and feel
 * Uses FlatLaf for cross-platform modern UI
 * 
 * @author Palaash Atri
 */
public class UIThemeManager {
    
    public static final String THEME_LIGHT = "light";
    public static final String THEME_DARK = "dark";
    public static final String THEME_SYSTEM = "system";
    
    /**
     * Apply FlatLaf Light theme
     */
    public static void applyLightTheme() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Failed to apply Light theme: " + e.getMessage());
            applySystemTheme();
        }
    }
    
    /**
     * Apply FlatLaf Dark theme
     */
    public static void applyDarkTheme() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Failed to apply Dark theme: " + e.getMessage());
            applySystemTheme();
        }
    }
    
    /**
     * Apply system native look and feel
     */
    public static void applySystemTheme() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Failed to apply System theme: " + e.getMessage());
        }
    }
    
    /**
     * Apply theme based on preference
     * @param theme Theme name: "light", "dark", or "system"
     */
    public static void applyTheme(String theme) {
        if (theme == null) {
            applyLightTheme();
            return;
        }
        
        switch (theme.toLowerCase()) {
            case THEME_DARK:
                applyDarkTheme();
                break;
            case THEME_SYSTEM:
                applySystemTheme();
                break;
            case THEME_LIGHT:
            default:
                applyLightTheme();
                break;
        }
    }
}
