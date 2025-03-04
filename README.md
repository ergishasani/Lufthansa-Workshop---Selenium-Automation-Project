# Lufthansa Workshop - Selenium Automation Project

## Overview
The **Lufthansa Workshop - Selenium Automation Project** is a robust test automation framework designed for web UI testing using Selenium WebDriver. This project ensures seamless and efficient automated testing for Lufthansa's web applications, providing comprehensive test coverage and detailed reporting. It helps improve software quality by automating regression testing, reducing manual effort, and increasing overall efficiency.

## Features
- **Automated Web UI Testing**: Eliminates manual testing effort by automating repetitive tasks.
- **Page Object Model (POM) Implementation**: Enhances test maintainability and readability.
- **Maven-Based Project**: Simplifies dependency management and build execution.
- **TestNG Integration**: Supports parallel execution, test grouping, and flexible configurations.
- **Detailed Reporting**: Uses Extent Reports for structured test result documentation.
- **Cross-Browser Testing**: Ensures compatibility across different web browsers.
- **Parameterized Testing**: Supports data-driven testing with parameterized inputs.
- **CI/CD Integration**: Easily integrates with Jenkins or GitHub Actions for continuous testing.
- **Exception Handling**: Implements robust error handling to improve test stability.

## Technologies Used
- **Java** - Core programming language for the automation scripts.
- **Selenium WebDriver** - For web browser automation.
- **Maven** - For dependency and build management.
- **TestNG** - For managing and executing test cases.
- **Extent Reports** - For generating detailed test execution reports.
- **Jenkins/GitHub Actions** - For continuous integration and deployment.

## Installation Guide
1. **Clone the repository**:
   ```sh
   git clone https://github.com/ergishasani/LufthansaWorkshop-.git
   ```
2. **Navigate to the project directory**:
   ```sh
   cd LufthansaWorkshop-
   ```
3. **Install dependencies and build the project**:
   ```sh
   mvn clean install
   ```

## Running Tests
To execute the test suite, use the following command:
```sh
mvn test
```
After execution, test results and reports will be generated in the `target/surefire-reports` directory.

### Running Specific Tests
To run a specific test class:
```sh
mvn -Dtest=TestClassName test
```

### Running Tests in Parallel
You can enable parallel execution by configuring the TestNG XML file:
```xml
<suite name="Suite" parallel="tests" thread-count="2">
```

## Project Structure
```
LufthansaWorkshop-/
│── src/
│   ├── main/
│   │   ├── java/ (Core automation framework and utilities)
│   ├── test/
│   │   ├── java/ (Test cases and execution logic)
│── reports/ (Generated test reports)
│── pom.xml (Maven configuration file)
│── README.md (Project documentation)
```

LinkedIn: [Ergi Hasani](https://www.linkedin.com/in/ergis-hasani-bb9ba0174/?originalSubdomain=al)