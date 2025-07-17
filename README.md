# Testing the POC

- Start the containers:

`` docker-compose up -d ``

- Run the Spring Boot application

- Test the queue endpoint:
> curl -X POST http://localhost:8080/api/books \
-H "Content-Type: application/json" \
-d '{
"isbn": "978-3-16-148410-0",
"title": "Effective Java",
"author": "Joshua Bloch",
"publicationDate": "2018-01-06",
"price": 54.99
}'

- Test the topic broadcast:
> curl -X POST http://localhost:8080/api/books/broadcast \
-H "Content-Type: application/json" \
-d '{
"isbn": "978-1-33-887909-6",
"title": "Spring in Action",
"author": "Craig Walls",
"publicationDate": "2022-11-15",
"price": 49.99
}'

- Valid ISBNS:
> ISBN 978-0-596-52068-7  
ISBN-13: 978-0-596-52068-7  
978 0 596 52068 7
9780596520687
0-596-52068-9  
0 512 52068 9  
ISBN-10 0-596-52068-9
ISBN-10: 0-596-52068-9

- Access H2 database:
- Access console at http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:bookdb
- Username: sa
- Password: (empty)

- Access the Artemis web console at http://localhost:8161 (username: admin, password: admin)