<div align="center">

# 🗄️ Project 4 — DAO Pattern and REST API

### Introducing a real, database-backed server with a DAO layer and a JAX-RS REST API

![Focus](https://img.shields.io/badge/Focus-DAO%20Pattern%20%7C%20REST-blueviolet)
![Language](https://img.shields.io/badge/Language-Java-007396)
![Platform](https://img.shields.io/badge/Platform-Jakarta%20EE%20%7C%20Tomcat-black)
![Status](https://img.shields.io/badge/Status-Complete-brightgreen)

</div>

---

## 🧠 What It Does

Project 4 introduces a database-backed server built around the DAO (Data Access Object) pattern, with two ways to reach the same underlying data: a JSP page that renders book and category data directly via JSTL SQL tags, and a JAX-RS (Jersey) REST API exposing that same data as JSON over a set of `/api/...` endpoints.

The server is organized into two packages: `business`, holding the DAO interfaces, their JDBC implementations, and the `Book`/`Category` model classes; and `api`, holding the JAX-RS resource class that exposes that data over HTTP. `ApiApplication` declares the REST layer's base path as `/api`; `ApiResource` then defines endpoints for listing all categories, fetching a single category or book by ID or by name, listing a category's books, and fetching a random sample of "suggested" books from a category.

## 🎯 Why It Matters

This is where the site's data stops being hardcoded and starts coming from a real database, and where the DAO pattern is introduced as the layer that isolates SQL from the rest of the application — `Book` and `Category` model classes never touch JDBC directly; that's entirely the job of `BookDaoJdbc` and `CategoryDaoJdbc`. The REST API built here is also the foundation the Vue client will actually call starting in Project 5 (Fetch), replacing the client-side-only category/book data used in Project 3.

## ✨ Features

- `Book` and `Category` model classes with corresponding DAO interfaces and JDBC implementations
- `ApplicationContext` singleton owning and wiring up the DAO instances
- JSP page rendering live database content via JSTL's `<sql:query>` tag, independent of the REST layer
- JAX-RS REST API under `/api`, exposing categories and books as JSON
- Category lookup by ID and by name
- Book lookup by ID, by category ID, and a random "suggested books" sample

## 🛠️ Requirements

- JDK 17
- IntelliJ IDEA
- Tomcat 10 (not 11)
- A local MySQL 8 instance with the `[Name]BookstoreDB` schema created and seeded (see `schema.sql` / `data.sql` under `src/main/resources`)

## ▶️ How to Run

1. Open `ShaeBookstoreRest` in IntelliJ and let Gradle sync
2. Update `src/main/webapp/META-INF/context.xml` to point at your local MySQL instance (`localhost`, your local credentials)
3. Set up a Tomcat 10 run configuration with application context `/ShaeBookstoreRest`
4. Run it
5. Visit `http://localhost:8080/ShaeBookstoreRest/` for the JSP data check, or `http://localhost:8080/ShaeBookstoreRest/api/categories` (and similar) for the REST API

## 🧪 Testing Approach

Verified manually: the JSP page was checked against the local database to confirm it renders the full, correct set of categories and books via JSTL. The REST API was exercised directly through browser requests to each endpoint family — all categories, a category by ID, a category by name, a book by ID, a category's books, and the suggested-books sample — confirming each returns the expected JSON.

## ✅ Expected Output

<div align="center"><img src="p4-jsp-screenshot.png" width="800"></div>
<div align="center"><img src="p4-api-screenshot.png" width="1000"></div>

The JSP page displays a full HTML table of every category and book pulled live from the database. Hitting `/api/categories` returns a JSON array of all eight categories; `/api/categories/{id}/books` returns the books belonging to that category.

## 💻 Setting This Up on Another PC

1. Clone this repository
2. Open `ShaeBookstoreRest` in IntelliJ, let Gradle sync
3. Set up a local MySQL instance, create and seed the `[Name]BookstoreDB` schema using the provided `schema.sql` / `data.sql`
4. Update `context.xml` with your local database credentials
5. Configure a Tomcat 10 run configuration with application context `/ShaeBookstoreRest`, then run

## 📝 Notes

The REST resource paths are plural (`categories`, `books`), not singular — a request to `/api/category` will 404; the correct path is `/api/categories`. The `@ApplicationPath` that actually controls the REST base path is declared on `ApiApplication` (which extends `jakarta.ws.rs.core.Application`), not on `ApiResource` itself — an `@ApplicationPath` annotation directly on a resource class has no effect. No client project exists at this stage; the REST API isn't consumed by the front end until Project 5.
