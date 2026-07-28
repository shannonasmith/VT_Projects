<div align="center">

# 🌐 P5 — Fetch

### Connecting the Vue client to the live REST API, replacing local/static book and category data with real fetch calls

![Focus](https://img.shields.io/badge/Focus-Fetch%20API%20%7C%20CORS-blueviolet)
![Language](https://img.shields.io/badge/Language-TypeScript%20%7C%20Java-42b883)
![Platform](https://img.shields.io/badge/Platform-Vite%20%7C%20Jakarta%20EE-black)
![Status](https://img.shields.io/badge/Status-Complete-brightgreen)

</div>

---

## 🧠 What It Does

Project 5 connects the Vue client to the REST API built in Project 4, replacing the client's local/static category and book data with live `fetch()` calls against `ShaeBookstoreFetch`. The server adds a `CorsFilter` to permit cross-origin requests from the Vite dev server (port 5173) to the Tomcat-hosted API (port 8080), and the client's `CategoryBookListItem` component fetches book image assets by deriving a filename directly from each book's title returned by the API.

## 🎯 Why It Matters

This is where the client stops being a self-contained demo and becomes a genuine consumer of a real backend — every category and book shown in the UI now reflects the actual state of the database, not a hardcoded local file. It also introduces CORS as a practical concern: any browser-based client talking to a server on a different origin (different port counts as a different origin) needs the server to explicitly allow it, or every request silently fails.

## ✨ Features

- Live category and book data fetched from `ShaeBookstoreFetch`'s REST API, replacing local/static content
- `CorsFilter` on the server permitting cross-origin requests from the Vite dev server
- Book cover images resolved by deriving a filename from the book's title at render time
- Full instrumented `.http` test suite (via IntelliJ's HTTP Client) covering all REST endpoints: category listing, valid/invalid category lookup by ID and by name, books by category, and suggested books with and without a result limit

## 🛠️ Requirements

- JDK 17
- IntelliJ IDEA
- Tomcat 10 (not 11)
- Node.js (LTS) and npm
- A local MySQL 8 instance with the `[Name]BookstoreDB` schema created and seeded

## ▶️ How to Run

**Server:**
1. Open `ShaeBookstoreFetch` in IntelliJ, let Gradle sync
2. Update `context.xml` to point at your local MySQL instance
3. Configure a Tomcat 10 run configuration with application context `/ShaeBookstoreFetch`, then run

**Client:**
1. Open `shae-bookstore-fetch-client` in a terminal
2. Run `npm install`
3. Run `npm run dev`
4. Open the printed `localhost:5173` URL

The server must be running before the client, since the client fetches its data live from it.

## 🧪 Testing Approach

Verified on both sides. Server-side: the instructor-provided `category-books.http` test suite (IntelliJ HTTP Client), covering all REST endpoints with response-status and content-type assertions — 27 individual assertions across 9 request blocks, all passing against the local database. Client-side: manual verification that book and category data render correctly, that images load for every book, that switching categories triggers a new fetch and displays the correct data, and that no CORS or console errors occur.

## ✅ Expected Output

<div align="center"><img src="p5-category-screenshot.png" width="800"></div>

Selecting any category displays that category's books, fetched live from the REST API, with correct titles, authors, prices, and cover images.

## 💻 Setting This Up on Another PC

1. Clone this repository
2. Set up a local MySQL instance with the `[Name]BookstoreDB` schema (see `schema.sql` / `data.sql`)
3. Open `ShaeBookstoreFetch` in IntelliJ, update `context.xml`, configure Tomcat, run
4. Open `shae-bookstore-fetch-client`, run `npm install` then `npm run dev`
5. Before running the `.http` test suite, confirm the `localhost` environment's `project` value in `http-client.env.json` matches this project's actual application context

## 📝 Notes

Book image filenames are generated at runtime from the book's title (lowercased, spaces replaced with hyphens, punctuation stripped), so any book image file must exactly match that generated pattern — including hyphens versus literal spaces — or it will silently fail to load with no other visible error.
