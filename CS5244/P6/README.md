<div align="center">

# 🗃️ P6 — State Management

### Centralizing server access and global state with Pinia, replacing scattered fetch calls and provide/inject with a proper store layer

![Focus](https://img.shields.io/badge/Focus-Pinia%20%7C%20Global%20State-blueviolet)
![Language](https://img.shields.io/badge/Language-TypeScript%20%7C%20Java-42b883)
![Platform](https://img.shields.io/badge/Platform-Vite%20%7C%20Jakarta%20EE-black)
![Status](https://img.shields.io/badge/Status-Complete-brightgreen)

</div>

---

## 🧠 What It Does

Project 6 migrates `ShaeBookstoreState` from Project 5's scattered per-component `fetch()` calls to a centralized state management layer built on Pinia, Vue 3's default store solution. A single `api.ts` file now computes the API base URL once, and three Pinia stores — category, book, and cart — own all global application state that was previously duplicated across components or passed around via `provide`/`inject`.

## 🎯 Why It Matters

Before this project, every component that needed category or book data fetched it independently, and the category list was threaded through the component tree via Vue's provide/inject mechanism. That meant redundant network requests, no single source of truth, and state that was awkward to share between unrelated components (like keeping a cart badge in the header in sync with an "add to cart" click three components away). Centralizing state in Pinia stores fixes all three: one fetch per data type, one source of truth, and any component can read or mutate shared state directly by importing the relevant store.

## ✨ Features

- **`api.ts`** — single source of truth for the API base URL, correctly switching between the Vite dev server port (5173) and the Tomcat-served port (8080) depending on how the client is running
- **Category store** (`stores/category.ts`) — fetches and holds the category list once at app startup via `App.vue`
- **Book store** (`stores/book.ts`) — fetches the book list for the currently selected category, with a fallback that still queries the server directly by name if the category store hasn't populated yet
- **Cart store** (`stores/cart.ts`) — wraps a `ShoppingCart`/`ShoppingCartItem` model class, exposing `count`, `addToCart`, `updateBookQuantity`, and `clearCart`
- Removal of the old `provide`/`inject` pattern for categories, and removal of `<Suspense>` from `App.vue` now that data loading is store-driven rather than component-driven

## 🛠️ Requirements

- JDK 17
- IntelliJ IDEA
- Tomcat 10 (not 11)
- Node.js (LTS) and npm
- A local MySQL 8 instance with the `ShaeBookstoreDB` schema created and seeded
- `pinia` (`^2.0.32`) as a client dependency

## ▶️ How to Run

**Server:**
1. Open `ShaeBookstoreState` in IntelliJ, let Gradle sync
2. Confirm `context.xml` points at your local MySQL instance (JNDI resource `jdbc/ShaeBookstore`)
3. Confirm nothing else is already bound to ports 8080/8005 (`netstat -ano | findstr :8080`) before starting
4. Configure a Tomcat 10 run configuration with application context `/ShaeBookstoreState`, then run

**Client:**
1. Open `shae-bookstore-state-client` in a terminal
2. Run `npm install`
3. Run `npm run dev`
4. Open the printed `localhost:5173` URL

The server must be running before the client — the category and book stores fetch live from it on load.

## 🧪 Testing Approach

Verified store-by-store against the assignment spec:
- **`api.ts`** — confirmed it correctly resolves to port 8080 when accessed via the dev server (5173) and preserves the current port otherwise
- **Category store** — confirmed `fetchCategories()` is called once from `App.vue`, and that `TheHeaderDropdown` reads `categoryStore.categoryList` directly with no redundant fetch and no leftover `provide`/`inject`
- **Book store** — confirmed `fetchBooks(categoryName)` is triggered from `CategoryView` via a `watch` on the route's `name` param, and that `TheCategoryBookList` reads `bookStore.bookList` directly with the `bookList` prop removed
- **Cart store** — confirmed the add-to-cart button in `CategoryBookListItem` calls `cartStore.addToCart(book)`, and that `TheHeader`'s cart badge is bound to `cartStore.count` rather than a local ref
- Manual verification that clicking through categories still shows correct data with no console errors, and that the cart badge updates live on add-to-cart clicks

## ✅ Expected Output

<div align="center"><img src="p6-category-screenshot.png" width="800"></div>

Category browsing behaves identically to Project 5 from the user's perspective, but all state now lives in Pinia stores rather than component-local state or provide/inject, and the cart badge in the header updates immediately when a book is added.

## 💻 Setting This Up on Another PC

1. Clone this repository
2. Set up a local MySQL instance with the `ShaeBookstoreDB` schema (see `schema.sql` / `data.sql`)
3. Open `ShaeBookstoreState` in IntelliJ, update `context.xml`, configure Tomcat, run
4. Open `shae-bookstore-state-client`, run `npm install` then `npm run dev`
5. If deploying the client bundled into the WAR rather than via the dev server, see `TROUBLESHOOTING.md` for the manual build-and-copy steps this project's Gradle build does **not** automate

## 📝 Notes

- The `apiUrl` trick in `api.ts` (checking `location.port === "5173"`) only works correctly when the dev server and Tomcat are both running locally on their default ports — if either port is customized, update `api.ts` accordingly.
- Book image filenames are still generated at runtime from the book's title (lowercased, spaces replaced with hyphens, punctuation stripped) and must end in `.png` to match the actual asset files — see `TROUBLESHOOTING.md` for how this broke silently in an earlier session.
