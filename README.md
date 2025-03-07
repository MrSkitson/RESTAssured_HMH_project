# **This project automates API testing for the FavQs API, specifically for:**

- List Quotes (GET /quotes)
- Favorite/Unfavorite Quotes (POST /favorites, POST /unfavorites)

The tests are written using Rest-Assured and JUnit, ensuring reliable validation of API responses.

#  Tools & Dependencies:
- Java JDk 21
- Maven (Build & Dependency Management)
- JUnit (Test Framework)
- Rest-Assured (API Testing Library)

#  Requirements:
To use this project, ensure the following are installed:
- Java JDK 21
- Apache Maven
- **You will need to create an account to generate an api key at https://favqs.com/api**

##  Configuring the Environment
  Setup Environment Variables Before running the tests, set the following environment variables: 
  
 **On Windows (Command Prompt)** create System Environment Variables:
- `setx FAVQS_API_KEY "your_api_key_here" `
- `setx FAVQS_USERNAME "your_username_here" `
- `setx FAVQS_PASSWORD "your_password_here"`

**This permanently sets the environment variables.
Restart your IDE or terminal for the change to take effect.**

On macOS/Linux (Terminal):
Run the following commands:

- `export FAVQS_API_KEY="your_api_key_here"`
- `export FAVQS_USERNAME="your_username_here"`
- `export FAVQS_PASSWORD="your_password_here"`
  
##  Usage

To compile the tests, use Maven:
`mvn clean compile`

Run All Tests
To execute all test cases, run:
`mvn test`

Run Tests in Parallel
If parallel execution is configured in pom.xml, run:
`mvn surefire:test`

Run a Specific Test Class
To run only FavQuoteTests:
`mvn -Dtest=FavQuoteTests test`

 Generating Test Reports
After test execution, generate a detailed test report:
`mvn surefire-report:report`
View the report in target/surefire-reports/index.html.

### Running Tests from an IDE
You can also execute tests directly from an IDE like IntelliJ IDEA or Eclipse:
- Open the project in your IDE.
- Navigate to src/test/java/com/FavQuote/tests.
- Right-click a test class (e.g., FavQuoteTests.java) and select Run with JUnit.

## Scenarios Not Covered
 the following scenarios have not been implemented yet:
 
- Authentication & Authorization Scenarios
- Testing API endpoints with invalid or expired tokens
- Ensuring role-based access control (RBAC) is enforced
- Concurrency & Load Testing
- Simulating multiple users favoriting/unfavoriting quotes simultaneously
- Pagination and Sorting Tests
- Fetching quotes with different page numbers & sizes
- Sorting quotes by author name or popularity
- Edge Cases & Boundary Values
- Sending extremely large payloads in requests
- Favoriting an already favorited quote
- Unfavoriting an already unfavorited quote (test case was comitted)
- Negative & Security Tests
- SQL Injection, XSS, or API rate-limiting scenarios
- Attempting to modify quotes as an unauthorized user
