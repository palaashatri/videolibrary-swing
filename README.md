# Video Library Management System

> A modernized desktop application for managing video rental library operations with Swing GUI
> 
> **Version 2.0** - Fully modernized with security fixes, modern architecture, and Maven build system

![Java](https://img.shields.io/badge/Java-8+-orange.svg)
![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue.svg)
![License](https://img.shields.io/badge/License-MIT-green.svg)

## Features

- **Video Management**: Add, view, and manage VCD inventory with pricing and availability
- **Customer Management**: Maintain customer database with contact information
- **Transaction Management**: Track rental transactions, issue and return dates
- **Defaulters Tracking**: Identify overdue rentals automatically
- **Modern UI**: FlatLaf modern look and feel for native desktop experience
- **Security**: SQL injection protection, prepared statements, input validation
- **Architecture**: Clean MVC architecture with DAO pattern for maintainability

## What's New in 2.0

### 🔒 Security Improvements
- ✅ Fixed SQL injection vulnerabilities in all database operations
- ✅ Moved credentials to config file (not hardcoded)
- ✅ Added comprehensive input validation
- ✅ Proper resource management with try-catch-finally blocks
- ✅ User-friendly error messages (no technical details exposed)

### 🏗️ Architecture Improvements
- ✅ MVC architecture with Model, DAO, and Controller layers
- ✅ Separation of concerns (business logic separated from UI)
- ✅ Reusable DAO implementations for all entities
- ✅ Comprehensive JavaDoc documentation

### 🛠️ Build System
- ✅ Migrated from Ant to Maven
- ✅ Modern dependency management
- ✅ One-command build and packaging
- ✅ Standalone JAR with all dependencies included

### 🎨 UI Improvements
- ✅ FlatLaf modern look and feel
- ✅ Cross-platform native appearance
- ✅ Improved visual design
- ✅ Better error feedback

## Prerequisites

- **Java JDK 8 or higher**
- **Apache Maven 3.6+**
- **MySQL 8.0+** (or compatible)

## Quick Start

### 1. Set up MySQL Database

```bash
# Start MySQL server and run the setup script
mysql -u root -p < Database.txt
```

This will create:
- Database: `palaash`
- Tables: `vcd_record`, `customer_record`, `transactionrcvd`
- Sample data for testing

### 2. Configure Database Connection

Create/edit `Video_Library/src/config.properties`:

```properties
db.url=jdbc:mysql://localhost:3306/palaash
db.user=root
db.password=your_password_here
db.driver=com.mysql.cj.jdbc.Driver
```

**⚠️ Security Note**: Never commit `config.properties` with real credentials to version control.

### 3. Build the Application

```bash
# Compile and package
mvn clean package

# This creates two JARs:
# 1. target/videolibrary-swing-2.0.0.jar (requires dependencies in classpath)
# 2. target/videolibrary-swing-2.0.0-standalone.jar (all dependencies included)
```

### 4. Run the Application

```bash
# Run the standalone JAR (easiest)
java -jar target/videolibrary-swing-2.0.0-standalone.jar

# OR run from Maven
mvn exec:java -Dexec.mainClass="Forms.Intro_GUI"
```

## Development

### Project Structure

```
videolibrary-swing/
├── pom.xml                          # Maven build configuration
├── Database.txt                     # MySQL setup script
├── Video_Library/
│   └── src/
│       ├── Forms/                   # GUI classes (Swing forms)
│       ├── models/                  # Entity models (Video, Customer, Transaction)
│       ├── dao/                     # Data Access Objects (interfaces + implementations)
│       ├── utils/                   # Utility classes (DB, validation, themes)
│       ├── config/                  # Configuration management
│       └── config.properties        # Database credentials (not in git)
└── target/                          # Maven build output
```

### Building from Source

```bash
# Clean build
mvn clean

# Compile only
mvn compile

# Run tests (when added)
mvn test

# Package JAR
mvn package

# Skip tests during package
mvn package -DskipTests
```

### IDE Setup

#### NetBeans
1. Open project: `File` → `Open Project`
2. Maven will auto-configure dependencies
3. Run `Forms.Intro_GUI` as main class

#### IntelliJ IDEA
1. Import project as Maven project
2. Trust the POM file
3. Run configuration: Main class = `Forms.Intro_GUI`

#### VS Code
1. Install Java Extension Pack
2. Open folder
3. Maven will auto-detect and configure

## Usage Guide

### Main Menu Options

1. **Videos** - View all available videos in inventory
2. **Customers** - View all registered customers
3. **Transactions** - View all rental transactions
4. **Add Videos** - Add new videos to inventory
5. **Add Customers** - Register new customers
6. **New Transaction** - Create a new rental transaction
7. **Complete Transaction** - Mark a video as returned
8. **Delete Transaction** - Remove a transaction record
9. **View Defaulters** - See overdue rentals

### Input Validation

The system validates:
- ✅ ID formats (4 digits required)
- ✅ Phone numbers (10 digits)
- ✅ Date formats (YYYY/MM/DD)
- ✅ Numeric values for prices and units
- ✅ Required fields are not empty

### Security Features

- **No SQL Injection**: All queries use PreparedStatements
- **Input Validation**: All user inputs are validated before processing
- **Error Handling**: Generic error messages shown to users (technical details logged only)
- **Resource Management**: Proper cleanup of database connections

## Dependencies

- **MySQL Connector/J 8.0.33**: MySQL database driver
- **FlatLaf 3.2.5**: Modern look and feel
- **NetBeans AbsoluteLayout**: GUI layout manager (NetBeans forms)
- **JUnit 5.10.1**: Testing framework (for future tests)
- **AssertJ 3.24.2**: Fluent assertions library

## Contributing

This project is maintained for archival and educational purposes. If you'd like to contribute:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## Roadmap

- [ ] Add comprehensive unit tests
- [ ] Implement edit functionality for videos/customers
- [ ] Add search and filter capabilities
- [ ] Export reports to PDF/CSV
- [ ] Add user authentication system
- [ ] Implement audit logging
- [ ] Add connection pooling (HikariCP)

## Troubleshooting

### Database Connection Failed
- Check MySQL service is running
- Verify credentials in `config.properties`
- Ensure database `palaash` exists

### Build Fails
- Check Java version: `java -version` (needs 8+)
- Check Maven version: `mvn -version` (needs 3.6+)
- Run `mvn clean` and try again

### Application Doesn't Start
- Check if config.properties exists and has correct credentials
- Ensure standalone JAR is used: `*-standalone.jar`
- Check console for error messages

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Original concept and initial development by Palaash Atri
- Modernization with security best practices
- Built with Java Swing and MySQL
- Uses FlatLaf for modern UI theming

## Author

**Palaash Atri**

*This project holds nostalgic value as my first programming project. Version 2.0 brings it up to modern standards while preserving the original Swing/AWT desktop application approach.*
