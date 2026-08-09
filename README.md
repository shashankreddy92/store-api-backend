# storeapi

A minimal REST API built with Spring Boot to demonstrate backend development 
and API design skills, alongside a companion REST Assured test suite in my 
[ecommerce-automation-framework](https://github.com/shashankreddy92/ecommerce-automation-framework) repo.

I built this specifically to round out my QA/SDET portfolio — rather than only 
testing third-party APIs, this project let me test an API I designed and built 
myself, end to end.

---

## Endpoints

| Method | Endpoint | Description | Success | Failure |
|---|---|---|---|---|
| GET | `/products` | List all products | `200` + JSON array | — |
| GET | `/products/{id}` | Get a single product by ID | `200` + JSON object | `404` if not found |
| POST | `/cart` | Add an item to the cart | `201` + created item | `400` on bad payload |
| GET | `/cart` | View current cart contents | `200` + JSON array | — |
| POST |'/orders' | Create an order (async processing)|'202' + {id, status: PENDING}	—
GET	/orders/{id}	Get order status	200 + {id, status}	404 if not found

### Example requests

```bash
curl http://localhost:8080/products

curl http://localhost:8080/products/1

curl -X POST http://localhost:8080/cart \
  -H "Content-Type: application/json" \
  -d '{"productId": 1, "quantity": 2}'

curl http://localhost:8080/cart

curl -i -X POST http://localhost:8080/orders
curl http://localhost:8080/orders/{id}
```

### Example response — `GET /products/1`

```json
{
  "id": 1,
  "name": "Sauce Labs Backpack",
  "price": 29.99,
  "description": "Fits 15-inch laptops"
}
```
Example response — POST /orders (immediate)
{
  "id": "c1eb8e80-845c-4650-be62-2c881ba77568",
  "status": "PENDING"
}

Example response — GET /orders/{id} (a few seconds later)
{
  "id": "c1eb8e80-845c-4650-be62-2c881ba77568",
  "status": "COMPLETED"
}

---

## Running locally

Requires Java 21 and Maven.

```bash
git clone https://github.com/shashankreddy92/storeapi.git
cd storeapi
mvn spring-boot:run
```

The server starts on `http://localhost:8080`.

---

## Project structure

```
src/main/java/com/shashank/storeapi/
├── StoreApiApplication.java
├── model/
│   ├── Product.java
│   └── CartItem.java
├──├── service/
│   ├── ProductService.java     # in-memory product data
│   ├── CartService.java        # in-memory cart data
│   ├── OrderService.java       # creates orders, tracks status
│   └── OrderProcessor.java     # simulates async processing (separate bean, required for @Async to actually run on a background thread)
└── controller/
    ├── ProductController.java
    ├── CartController.java
    └── OrderController.java
```

---

## Design notes

- **In-memory storage, no database.** Data resets on every restart. This was a 
  deliberate choice to keep the project focused on API design and testing 
  rather than persistence — a natural next step if extended further.
- **Layered structure** (Controller → Service → Model) follows standard Spring 
  Boot conventions, even at this small scale, to reflect how a real production 
  service would be organized.
- **Products/Cart** model was chosen to mirror the domain of my 
  [SauceDemo UI automation project](https://github.com/shashankreddy92/ecommerce-automation-framework), 
  so both projects sit under a consistent e-commerce theme — though the two 
  systems are independent and not functionally connected.
- Orders are processed asynchronously to demonstrate testing eventual consistency: POST /orders returns 202 Accepted immediately with status PENDING, while a background thread completes processing a few seconds later. This required splitting the async logic into its own OrderProcessor bean — calling an @Async method from within the same class silently runs it synchronously, since Spring's proxy only intercepts calls from outside the class.

---

## Tested by

This API is covered by a REST Assured test suite (`api.storeapi` package) in 
my [ecommerce-automation-framework](https://github.com/shashankreddy92/ecommerce-automation-framework) 
repo — covering positive and negative cases for each endpoint. That repo's 
README has instructions for running those tests against this API locally.

---

## Tech stack

Java 21 · Spring Boot 4.1 · Maven · Spring Web
