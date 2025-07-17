# Testing the POC

- Start the containers:

`` docker-compose up -d ``

- Run the Spring Boot application

- Send a test message using curl:
> curl -X POST http://localhost:8080/api/books \
-H "Content-Type: application/json" \
-d '{
"isbn": "978-3-16-148410-0",
"title": "Effective Java",
"author": "Joshua Bloch",
"publicationDate": "2018-01-06",
"price": 54.99
}'

- Check the application logs - you should see:
> Sent book:BookDTO[isbn=978-3-16-148410-0, title=Effective Java, author=Joshua Bloch, publicationDate=2018-01-06, price=54.99]
  Received book:BookDTO[isbn=978-3-16-148410-0, title=Effective Java, author=Joshua Bloch, publicationDate=2018-01-06, price=54.99]

- Access the Artemis web console at http://localhost:8161 (username: admin, password: admin)