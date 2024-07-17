# Forum Hub

ForumHub is a forum project developed as part of the ONE (Oracle Next Education) program. This project demonstrates how to use Java, Spring Boot, and PostgreSQL to create a forum application where users can create topics, post responses, and manage user authentication and authorization.

## Description

ForumHub allows users to create new discussion topics, post responses to topics, and manage their accounts. The project includes features such as user authentication and authorization using JWT, and it uses PostgreSQL for data storage.

## Features

- **User Registration and Authentication**: Register new users and authenticate existing users using JWT.
- **Create Topics**: Users can create new discussion topics.
- **Post Responses**: Users can post responses to existing topics.
- **List Topics**: View a list of all topics, sorted by creation date.
- **View Topic Details**: View details of a specific topic, including all responses.
- **Update Topics**: Update the details of existing topics.
- **Delete Topics**: Delete existing topics.
- **User Authorization**: Ensure only authorized users can update or delete their topics.

## Technologies

- **Java 17**: Programming language used for development.
- **Spring Boot 3.3.1**: Framework for building Java applications.
- **Maven**: Automation tool for building and dependency management.
- **PostgreSQL**: Open-source relational database management system.
- **Flyway**: Database migration tool.
- **JWT (JSON Web Tokens)**: For user authentication and authorization.
- **Spring Security**: Provides security services for Java applications.
- **Spring Data JPA**: For data access and manipulation.
- **Lombok**: Reduces boilerplate code.
- **Springdoc OpenAPI**: For API documentation.

## Setup and Installation

1. **Clone the Repository:**

```sh
git clone https://github.com/yourusername/challenge-one-community
```

2. **Change the Directory:**

```sh
cd /path/to/challenge-one-community
```

3. **Create a Database User and Database in PostgreSQL:**

   Open pgAdmin and follow these steps:

   - **Create a New Database:**

     - Right-click on `Databases` in the left navigation pane and select `Create > Database...`.
     - Enter `forumHub` as the database name and select the owner (user).

   - **Create a New User/Role:**

     - Right-click on `Login/Group Roles` and select `Create > Login/Group Role...`.
     - Enter `your_username` as the username.
     - Go to the `Definition` tab and enter the password.
     - Go to the `Privileges` tab and grant all necessary permissions (`Can login`, `Create DB`, etc.).

   - **Grant Permissions to the User:**

     - Right-click on the `forumHub` database, select `Properties`, and go to the `Privileges` tab.
     - Add the user (`your_username`) and grant the necessary permissions.

4. **Configure Environment Variables:**

   Set the following environment variables on your system:

##### On Windows:

Open Command Prompt and enter:

```sh
setx FORUMHUB_DB_HOST "localhost"
setx FORUMHUB_DB_PORT "5432"
setx FORUMHUB_DB_NAME "forumHub"
setx FORUMHUB_DB_USERNAME "your_username"
setx FORUMHUB_DB_PASSWORD "your_password"
```

##### On Linux/Mac:

Open a terminal and enter:

```sh
export FORUMHUB_DB_HOST="localhost"
export FORUMHUB_DB_PORT="5432"
export FORUMHUB_DB_NAME="forumHub"
export FORUMHUB_DB_USERNAME="your_username"
export FORUMHUB_DB_PASSWORD="your_password"
```

**Note:** On Linux/Mac, you might want to add these lines to your `.bashrc` or `.zshrc` file to make the environment variables persistent across terminal sessions.

### Running the Application with Maven

If you are using Maven, you can use the following command to run your Spring Boot application:

1. **Navigate to Your Project Directory:**

   Open a terminal or command prompt and navigate to the root directory of your Spring Boot project (the directory containing `pom.xml`).

```sh
cd path/to/forumHub
```

2. **Run the Application Using Maven:**

   Use the following command to run your application:

```sh
mvn spring-boot:run
```

3. **Using the Application:**

   Run the application and interact with the API to perform various operations such as creating topics, posting responses, and managing user accounts.

## Configuration Parameters

- **spring.application.name**: Defines the name of the application. In this case, it is set to `forumHub`.
- **spring.datasource.url**: URL for the PostgreSQL database connection, using environment variables to avoid exposing sensitive credentials.
- **spring.datasource.username**: Database username, defined as an environment variable.
- **spring.datasource.password**: Database password, defined as an environment variable.
- **spring.jpa.hibernate.ddl-auto**: Hibernate setting to automatically update the database schema.
- **spring.jpa.properties.hibernate.dialect**: Sets the Hibernate dialect for PostgreSQL.

## Contribution

Feel free to fork this repository and contribute by submitting a pull request.

1. Fork the project.
2. Create a branch for your feature (`git checkout -b feature/AmazingFeature`).
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4. Push to the branch (`git push origin feature/AmazingFeature`).
5. Open a Pull Request.

## License

This project is licensed under the MIT License. See [LICENSE](/LICENSE) for more information.

## Contact

Jonnathan Ribeiro - jrib_st@outlook.com

Project Link: https://github.com/jonnrib/challenge-one-community
