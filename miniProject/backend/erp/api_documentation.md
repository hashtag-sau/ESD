#### **Base URL:** `http://localhost:8080/api/v1/employee`

## Auth Api

### **1\. Refresh Token**

* **Endpoint**: `/api/v1/employee/refresh-token`  
* **Method**: `POST`  
* **Headers**:  
  * `Authorization`: Bearer token (Refresh token)  
* **Request Body**: None  
* **Responses**:  
  * **200 OK**: If the refresh token is valid, a new access token and refresh token will be returned.  
  * **401 Unauthorized**: If the refresh token is invalid or expired. The response message will explain the issue.

**Example Response**:  
`{`  
  `"access_token": "newAccessToken",`  
  `"refresh_token": "newRefreshToken"`  
`}`

### **2\. Signup (Register a New Employee)**

* **Endpoint**: `/api/v1/employee/signup`  
* **Method**: `POST`  
* **Request Body**:  
  **`EmployeeRegisterRequest`** (JSON body):  
  {  
   "first\_name": "Saurabh",  
   "last\_name": "Dubey",  
   "email": "saurabh.dubey@iiitb.ac.in",  
   "title": "SWE",  
   "department": 1,  
   "password": "1234"  
  }  
* **Responses**:  
  * **200 OK**: Returns the access and refresh tokens for the newly registered employee.  
  * **400 Bad Request**: If the email is already registered, or if the input data is invalid (e.g., missing required fields).

  **Example Response**:  
    `{`

    `"access_token": "jwtAccessToken",`

    `"refresh_token": "jwtRefreshToken"`

  `}`

### **3\. Login (Employee Authentication)**

* **Endpoint**: `/api/v1/employee/login`  
* **Method**: `POST`  
* **Description**: Authenticates an employee using their email and password. If successful, an access token and refresh token will be returned.  
* **Request Body**:  
  **`EmployeeLoginRequest`** (JSON body):  
  `{`  
    `"email": "john.doe@example.com",`  
    `"password": "securePassword"`  
  `}`  
* **Responses**:  
  * **200 OK**: Returns the access and refresh tokens for the authenticated employee.  
  * **401 Unauthorized**: If the email or password is incorrect.

  **Example Response**:

    `{`

    `"access_token": "jwtAccessToken",`

    `"refresh_token": "jwtRefreshToken"`

  `}`

## Employee Services Api

### **4\. Get Employee Details**

* **Endpoint**: `/api/v1/employee/detail`  
* **Method**: `GET`  
* **Description**: Fetches details of the authenticated employee based on the JWT token.  
* **Headers**:  
  * `Authorization`: Bearer token (JWT access token)  
* **Request Body**: None  
* **Responses**:  
  * **200 OK**: Returns the employee details (e.g., name, position, email).  
  * **401 Unauthorized**: If the JWT token is invalid or expired.

**Example Response**:  
{  
   "first\_name": "Saurabh",  
   "last\_name": "Dubey",  
   "email": "saurabh.dubey@iiitb.ac.in",  
   "title": "SWE",  
   "department": 1,  
   "photograph\_path": **null**  
}

## Salary Service Api

### **1\. Get All Salaries for an Employee**

* **Endpoint**: `/salary/all`  
* **Method**: `GET`  
* **Headers**:  
  * `Authorization`: Bearer token (JWT token)  
* **Request Body**: None  
* **Query Parameters**: None  
* **Responses**:  
  * **200 OK**: Returns a list of salary records for the authenticated employee.  
  * **401 Unauthorized**: If the JWT token is invalid or expired.

**Example Response**:

\[  
   {  
       "employee\_id": 52,  
       "payment\_date": "2024-01-14T18:30:00.000+00:00",  
       "amount": 1500.0,  
       "description": "Salary for January 2024"  
   },  
   {  
       "employee\_id": 52,  
       "payment\_date": "2024-01-24T18:30:00.000+00:00",  
       "amount": 500.0,  
       "description": "Bonus for January 2024"  
   }  
\]

### **2\. Get Salaries by Date Range**

* **Endpoint**: `/salaries`  
* **Method**: `GET`  
* **Description**: Retrieves salary records for the authenticated employee filtered by a specific date range. The `startDate` and `endDate` query parameters specify the date range.  
* **Query Parameters**:  
  * `startDate`: The start date of the range in `yyyy-MM-dd` format.  
  * `endDate`: The end date of the range in `yyyy-MM-dd` format.  
* **Headers**:  
  * `Authorization`: Bearer token (JWT token)  
* **Request Body**: None  
* **Responses**:  
  * **200 OK**: Returns a list of salary records for the authenticated employee within the specified date range.  
  * **401 Unauthorized**: If the JWT token is invalid or expired.  
  * **400 Bad Request**: If the `startDate` or `endDate` format is incorrect.

Eg: [http://localhost:8080/api/v1/employee/salaries?startDate=2024-01-01\&endDate=2024-12-31](http://localhost:8080/api/v1/employee/salaries?startDate=2024-01-01&endDate=2024-12-31)

Same response body

### **3\. Get Salaries by Month and Year**

* **Endpoint**: `/salary`  
* **Method**: `GET`  
* **Description**: Retrieves salary records for the authenticated employee filtered by the specific month and year. The `month` and `year` query parameters are used to specify the month and year of the salary records.  
* **Query Parameters**:  
  * `month`: The month to filter by (1 to 12).  
  * `year`: The year to filter by (e.g., 2024).  
* **Headers**:  
  * `Authorization`: Bearer token (JWT token)  
* **Request Body**: None  
* **Responses**:  
  * **200 OK**: Returns a list of salary records for the authenticated employee for the specified month and year.  
  * **401 Unauthorized**: If the JWT token is invalid or expired.  
  * **400 Bad Request**: If the `month` or `year` values are invalid.

**Example Request**:  
`GET http://localhost:8080/api/v1/employee/salary?month=10&year=2024`

