# Read Me First


#Abstract
ReadingIsGood is an online books retail firm which operates only on the Internet. Main 
target of ReadingIsGood is to deliver books from its one centralized warehouse to their 
customers within the same day. That is why stock consistency is the first priority for their 
vision operations.

# Getting Started


#Authentication
A static sample user is created for securing end points with JWT bearer tokens with following details
username = testuser
password = nopassword

url -> http://localhost:8080/authenticate

Sample Request Body : 
{
	"username":"testUser",
	"password":"nopassword"
}

Sample Response : 
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0VXNlciIsImV4cCI6MTY0NzIyNDczOCwiaWF0IjoxNjQ3MjA2NzM4fQ.03oK5pAU0qS3uFAG69xJSkONRe70OKcbL5h_dkAOBBiO6g24B0fwjyzsCEbl50dtvJcpP0sItkgPJ9Qyo1rv_A"
}

We can add and load users and respective roles from the database, however here, just for demo purpose, I have created static user with details mentioned above, which can be used to generate token by firing below http.post request


After firing above request, you will recieve a token string, please copy that token, and use it to authorize your requests in order to gain access to the application services
Go to request header, type "Authorization" in the key, and in the value, type "Bearer" followed up with a space and paste your copied token from above, then fire the request, access will be authorized.

For example, above token has to be used like this - "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0VXNlciIsImV4cCI6MTY0NzIyNDczOCwiaWF0IjoxNjQ3MjA2NzM4fQ.03oK5pAU0qS3uFAG69xJSkONRe70OKcbL5h_dkAOBBiO6g24B0fwjyzsCEbl50dtvJcpP0sItkgPJ9Qyo1rv_A"





#Sample Services

#Customer Service
--To add customer (HTTP.POST)
URL : http://localhost:8080/customers  
Request :  
{
    "name": "sampleUser",
    "email": "sampleUse@gmail.com"
}


--To query all orders for the customer (HTTP.GET)
URL : http://localhost:8080/customers/{customerId}/orders 


Sample request :  http://localhost:8080/customers/101/orders
Sample Response : 

{
    "customerName": "Utsav Garg",
    "orders": [
        {
            "bookName": "Digital Fortress",
            "bookCost": 200,
            "orderDate": "2022-03-14",
            "orderDescription": "payment via credit card",
            "quantity": 2
        },
        {
            "bookName": "Lord of the rings",
            "bookCost": 1900,
            "orderDate": "2022-03-14",
            "orderDescription": "payment via credit card",
            "quantity": 3
        }
    ],
    "totalAmountSpent": 6100,
    "totalNoOfBooksOrdered": 5,
    "lastOrderedDate": "2022-03-14"
}



#Order Detail Service

--retrieve order detail between dates (HTTP.GET)
URL : http://localhost:8080/orders/{fromDate}/{toDate}

Sample Request : 
URL : http://localhost:8080/orders/20220101/20220402

Response : 

[
    {
        "id": 2,
        "orderDate": "2022-03-14",
        "orderDescription": "payment via credit card",
        "quantity": 2,
        "totalBill": 400,
        "book": {
            "id": 1002,
            "name": "Digital Fortress",
            "amount": 200,
            "quantity": 3
        }
    },
    {
        "id": 3,
        "orderDate": "2022-03-14",
        "orderDescription": "payment via credit card",
        "quantity": 3,
        "totalBill": 5700,
        "book": {
            "id": 1005,
            "name": "Lord of the rings",
            "amount": 1900,
            "quantity": 17
        }
    }
]

--retrieve order detail for an order id (HTTP.GET)
URL : http://localhost:8080/orders/{orderId}

Sample Request : http://localhost:8080/orders/2

Sample Response : 
{
    "id": 2,
    "orderDate": "2022-03-14",
    "orderDescription": "payment via credit card",
    "quantity": 2,
    "totalBill": 400,
    "book": {
        "id": 1002,
        "name": "Digital Fortress",
        "amount": 200,
        "quantity": 3
    }
}

--to place an order for a customer against a book (HTTP POST)
URL : http://localhost:8080/customers/{customerId}/books/{bookId}/orders

Sample request :  http://localhost:8080/customers/101/orders
{
    "orderDescription": "payment via credit card",
    "quantity": 3
}



#Books Service

--to add books (HTTP.POST)

URL : http://localhost:8080/books
Sample Request : 
{
	"amount": 1200,
	"name": "Sherlock Holmes",
	"quantity": 40
}


--to update book quantity (HTTP.PUT)

URL : http://localhost:8080/books/{bookId}/{newQuantity}

Sample Request : http://localhost:8080/books/1001/12

Sample Response : 
{
    "id": 1001,
    "name": "The Da Vinci Code",
    "amount": 500,
    "quantity": 12
}

(Quantity updated from 2 to 12)



#Statistics Service

--to get customer monthly statistics(HTTP.GET)

URL : http://localhost:8080/statistics/{customerId}


Sample Request  : http://localhost:8080/statistics/101
Sample Response : 
[
    {
        "month": "MARCH",
        "totalOrderCount": 5,
        "totalBookCount": 2,
        "totalPurchasedAmount": 2100
    }
]

#Database
H2 In memory database is used, which can be accessed by visiting following link
http://localhost:8080/h2-console/login.do
There is some sample data which gets loaded in memory in H2 using data.sql file during application startup. 


### Reference Documentation
Please open below link in order to access Swagger documentation which will have information on the API usage, please disable tokens if you wish to access api's via swagger. 
http://localhost:8080/swagger-ui/index.html




