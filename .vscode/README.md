# VS Code Configuration for Video Library

This folder contains VS Code workspace settings for the Video Library project.

## Files

### launch.json
Debug and run configurations:
- **Launch Video Library** - Run the application with intro screen
- **Debug Video Library** - Run with debugging enabled
- **Launch Main Menu (Direct)** - Skip intro and go directly to main menu

### tasks.json
Build tasks:
- **Maven: Clean** - Clean build artifacts
- **Maven: Compile** - Compile the project (default build task: Ctrl+Shift+B)
- **Maven: Package** - Create JAR files
- **Maven: Clean + Package** - Full rebuild
- **Run Standalone JAR** - Build and run the standalone JAR

### settings.json
Project-specific VS Code settings:
- Java configuration
- Maven integration
- Code formatting preferences
- Source path configuration

## Usage

### To Run the Application:
1. Press `F5` or click "Run and Debug" in the sidebar
2. Select "Launch Video Library" from the dropdown
3. Click the green play button

### To Build:
1. Press `Ctrl+Shift+B` (or `Cmd+Shift+B` on Mac)
2. Or open Command Palette (`Ctrl+Shift+P`) and type "Run Task"

### Prerequisites:
- Java Extension Pack installed in VS Code
- Maven installed and in PATH
- MySQL server running with database configured
