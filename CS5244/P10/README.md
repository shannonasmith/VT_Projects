<div align="center">

# 💳 P10 / P10H — Transactions & Hardening

### Real database transactions across three tables, a confirmation page built from live order data, and a final hardening pass for navigation, reloads, and edge cases

![Focus](https://img.shields.io/badge/Focus-JDBC%20Transactions%20%7C%20Hardening-blueviolet)
![Language](https://img.shields.io/badge/Language-TypeScript%20%7C%20Java-42b883)
![Platform](https://img.shields.io/badge/Platform-Vite%20%7C%20Jakarta%20EE-black)
![Status](https://img.shields.io/badge/Status-Complete-brightgreen)

</div>

---

## 🧠 What It Does

Project 10 is the payoff for everything built in P6–P9: `ShaeBookstoreTransact` actually places real orders. `placeOrder` now writes across three related tables — `customer`, `customer_order`, and `customer_order_line_item` — inside a single manually-managed JDBC transaction, committing only if every insert succeeds and rolling back cleanly otherwise. The confirmation page pulls the resulting `OrderDetails` (order, customer, books, and line items) straight from that transaction and renders it as a real receipt. Project 10H is a final hardening pass, making sure the site behaves correctly under real browser navigation patterns rather than just the "happy path" of clicking through it in order.

## 🎯 Why It Matters

Everything up to this point validated data and modeled state, but nothing actually persisted an order. This project introduces the core pattern behind any multi-table write: acquire a connection, disable autocommit, perform each dependent insert (customer → order → line items, in that order, since each step needs the previous step's generated ID), and either commit the whole thing or roll all of it back — never leaving the database in a half-written state. The hardening pass matters just as much in practice: a site that only works when clicked through in the "intended" order isn't actually production-ready — real users reload pages, hit back, bookmark deep links, and type URLs directly, and the site needs to hold up under all of it.

## ✨ Features

- **Three-table transaction** (`DefaultOrderService.performPlaceOrderTransaction`) — creates a `customer` row, then a `customer_order` row (using the generated `customer_id`), then a `customer_order_line_item` row per cart item (using the generated `customer_order_id`), all inside one `connection.setAutoCommit(false)` / `commit()` / `rollback()` block
- **`getOrderDetails`** — reassembles a full order (order + customer + line items + the actual book records) by ID, used both immediately after a successful `placeOrder` and available for future lookups
- **Server-side card masking at the data layer** — `Customer` is a Java record whose `ccNumber()` accessor is overridden to always return only the last 4 digits; since this happens before Jackson serializes the response, the full card number never actually leaves the server, not even hidden client-side
- **`orderDetails.ts` Pinia store** — backed by `sessionStorage` (deliberately, not `localStorage`), so a placed order's confirmation details survive a page reload but are cleared when the browser is closed and reopened
- **Real confirmation page** — confirmation number, timestamp, full customer info, masked card, month/year-only expiry, and a per-item table (image, title, quantity, price) with subtotal/surcharge/total, built from live `OrderDetails` data rather than static content
- **Hardening pass**: homepage reachable via `/`, `/index.html`, and `/home`; correct behavior on reload for every page; correct back-button behavior; a real 404 page for unmatched routes; checkout button disabled during order submission to prevent duplicate orders; empty-cart messaging on both `/cart` and `/checkout`

## 🛠️ Requirements

- JDK 17
- IntelliJ IDEA
- Tomcat 10 (not 11)
- Node.js (LTS) and npm
- A local MySQL 8 instance with the `ShaeBookstoreDB` schema created and seeded
- `pinia`, `@vuelidate/core`, and `@vuelidate/validators` as client dependencies

## ▶️ How to Run

**Server:**
1. Open `ShaeBookstoreTransact` in IntelliJ, let Gradle sync
2. Confirm `context.xml` points at your local MySQL instance (JNDI resource `jdbc/ShaeBookstore`)
3. Confirm nothing else is already bound to ports 8080/8005 before starting
4. Create a new Tomcat run configuration for this project (does not carry over from prior projects) — **explicitly set the Application context to `/ShaeBookstoreTransact`** in the Deployment tab
5. Run

**Client:**
1. Open `shae-bookstore-transact-client` in a terminal
2. Run `npm install`
3. Run `npm run dev`
4. Open the printed `localhost:5173` URL — double-check the port; if 5173 is already in use by a leftover dev server from a prior project, Vite silently opens on 5174 instead, which will break the API base URL logic

## 🧪 Testing Approach

- Verified a full order placement end-to-end on the live Tomcat deployment: added items to the cart, submitted valid checkout data (using the standard Luhn-valid test card `4444333322221111` — no real payment processor is ever involved), and confirmed a real confirmation number, correct subtotal/surcharge/total math, and correctly masked card number on the resulting confirmation page
- Cross-checked every SQL statement in `CustomerDaoJdbc`, `OrderDaoJdbc`, and `LineItemDaoJdbc` against the actual `schema.sql` table definitions, column by column, to confirm no schema/code mismatches
- Verified the P10H checklist live in the browser: homepage reachable via all required paths, reload behaves correctly on every page, back-button behaves correctly, direct address-bar entry to `/category`, `/cart`, `/checkout` all work, and an unmatched route (`/category/NotARealCategory`) correctly shows the 404 page
- Verified `sessionStorage`-backed order details persist through a page reload on `/confirmation`, and that visiting `/confirmation` with no order present shows the correct "you have not placed an order yet" message

## ✅ Expected Output

<div align="center"><img src="p10-confirmation-screenshot.png" width="800"></div>

Placing a real order lands on a confirmation page showing a genuine confirmation number, an accurate timestamp, correctly masked payment details, and a per-item breakdown pulled live from the database — not placeholder or static content.

## 💻 Setting This Up on Another PC

1. Clone this repository
2. Set up a local MySQL instance with the `ShaeBookstoreDB` schema (see `schema.sql` / `create.sql`)
3. Open `ShaeBookstoreTransact` in IntelliJ, update `context.xml` (see `context.xml.example`), configure a new Tomcat run with an **explicit Application context**, run
4. Open `shae-bookstore-transact-client`, run `npm install` then `npm run dev`
5. Before checking the Tomcat-bundled version (port 8080) rather than the dev server, see `TROUBLESHOOTING.md` for the manual build-and-copy steps this project's Gradle build does **not** automate

## 📝 Notes

- Test orders use the standard Luhn-valid fake card number `4444333322221111` — this number passes credit-card format validation but is not a real account and is never charged; nothing in this project connects to an actual payment processor.
- As with prior projects, a fresh project copy starts with no `webapp/index.html`/`assets/` until the client is built and copied over, no Tomcat run configuration, and the `.jpg`/`.png` book-image filename bug reappears since it lives in component source that gets copied forward unmodified. See `TROUBLESHOOTING.md`.
- From a security-review standpoint (not part of the assignment, but worth noting for anyone extending this project): `getOrderDetails(orderId)` has no ownership check, so any endpoint that exposed it directly by ID would be vulnerable to IDOR; server-side validation independently re-checks price and category against the database rather than trusting the client's cart, which is the correct defense against a tampered request. This project would make a reasonable basis for a personal, isolated pentesting/CTF-style exercise in the future.
