## Environment
- Develop in Java 8.
- Use Gradle 7.6.2 as build tool
- H2 Database (in-memory database) is used as the database.
  - To see data, you can go to http://localhost:8080/h2-console on a browser

## How to start the application
- Download this project and make sure Gradle 7.6 is installed.
  - [How to install gradle](https://docs.gradle.org/current/userguide/installation.html)
- cd into this project and `./gradlew clean build`
- Run `./gradlew bootRun`
- After seeing success logs, you can send requests to `http://localhost:8080`. (default url and port)

## Assumptions
- There will only be three users (no signup endpoint, but you can insert user data to the database directly):
  - username: admin, pass: 123, ROLE = ADMIN
  - username: m1, pass: 123, ROLE = USER
  - username: m2, pass: 123, ROLE = USER
- A simple JWT authorization is added. Please see /signin, /signout endpoints.

## APIs
### Auth
- This service provides single signin/singout functions to support viewing a user's favorites
- Three users are populated in the /resource/db/migration files.
- User has its own `ROLE`  

#### POST /api/auth/signin
   The signin API is used to authenticate a user with their username and password. 
   A JWT token is generated on successful authentication, which is then set as a cookie in the response header.

- Request Body:
  - username (required, string): The username of the user to be authenticated.
  - password (required, string): The password of the user to be authenticated.
- Response Body:
  - username (string): The username of the authenticated user.
  - email (string): The email of the authenticated user.
  - roles (array of strings): The roles of the authenticated user.
- Response Headers:
  - Set-Cookie (string): A cookie containing the JWT token.

#### POST /api/auth/signout:
signout function, will set cookie to empty

- Response Body:
  - message (string): A message indicating that the user has been signed out.
- Response Headers:
  - Set-Cookie (string): A cookie with an empty JWT token, indicating that the user is no longer authenticated.

### Employee

#### GET /api/v1/employees
get all employees.

- Only ADMIN and USER ROLE can access this endpoint.
- Response Body:
  - employees (array of objects): A list of user objects containing information about each employee.
- Response Status Codes:
  - 200 OK: Returned on success.
  - 403 Forbidden: Returned if the user making the request does not have an ADMIN role.
  - 500 Internal Server Error: Returned if an error occurs on the server.

#### GET /api/v1/employee/{employee_id}
get details of a employee.

- Only signined user can access this endpoint
- Path Parameters:
  - id (required, integer): The ID of the employee to retrieve.
- Response Body:
  - employee, the object contains employee info:
    - name (string): the name of the employee
    - salary (integer): the salary of this employee
    - department (string): the department this employee belongs to
- Response Status Codes:
  - 200 OK: Returned on success.
  - 403 Forbidden: Returned if the user making the request does not have a USER or ADMIN role.
  - 404 Not Found: Returned if the requested employee does not exist.
  - 500 Internal Server Error: Returned if an error occurs on the server.

#### POST /api/v1/employee
create a new employee

- Only USER or ADMIN role can access this endpoint
- Request
  - employee
    - name (string): the name of the employee
    - salary (integer): the salary of this employee
    - department (string): the department this employee belongs to
- Response Body
  - employee
    - name (string): the name of the employee
    - salary (integer): the salary of this employee
    - department (string): the department this employee belongs to

#### PUT /api/v1/employee/{employee_id}
update an employee

- Only USER or ADMIN role can access this endpoint
- Path parameter:
  - employee_id (required, integer): the employee id of the employee you want to update.
- Request
  - employee
    - name (string): the name of the employee
    - salary (integer): the salary of this employee
    - department (string): the department this employee belongs to
- Response Body
  - employee
    - name (string): the name of the employee
    - salary (integer): the salary of this employee
    - department (string): the department this employee belongs to

#### DELETE /api/v1/employee/{employee_id}
delete an employee

- Only ADMIN role can access this endpoint
- Path parameter:
  - employee_id (required, integer): the id of the employee to be deleted
- Response Status Codes:
  - 200 OK: Returned on success.

---

## Things to be improved
- Can add redis for caching.
- Can use MySQL instead of H2 memory database.
- Can do session on server side.
- Can add an API gateway as reverse proxy.