<div align="center">

# ✅ P8 — Client-Side Validation

### A full checkout form with Vuelidate-powered validation, custom validators, and inline error messaging

![Focus](https://img.shields.io/badge/Focus-Form%20Validation%20%7C%20Vuelidate-blueviolet)
![Language](https://img.shields.io/badge/Language-TypeScript%20%7C%20Java-42b883)
![Platform](https://img.shields.io/badge/Platform-Vite%20%7C%20Jakarta%20EE-black)
![Status](https://img.shields.io/badge/Status-Complete-brightgreen)

</div>

---

## 🧠 What It Does

Project 8 migrates `ShaeBookstoreValidate` from Project 7's cart-only checkout stub to a fully built-out checkout form: customer name, address, phone, email, and credit card details, all validated client-side with [Vuelidate](https://vuelidate-next.netlify.app/) before the order can proceed. A mostly-blank confirmation page caps off the flow — full server-side order processing is deferred to a later project.

## 🎯 Why It Matters

Client-side validation is the first line of defense against malformed data reaching the server, and it's what gives users immediate, specific feedback instead of a failed request after the fact. This project introduces a declarative validation pattern (Vuelidate's `rules` object plus `useVuelidate`) rather than hand-rolled `if` statements scattered through the component, and pairs custom regex-based validators (credit card, US mobile phone) with Vuelidate's built-ins (`required`, `email`, `minLength`, `maxLength`).

## ✨ Features

- **Checkout form** (`CheckoutView.vue`) — name, address, phone, email, credit card number, and expiry month/year, all bound through Vuelidate's `v$` object
- **Custom validators** (`validators.ts`) — `isMobilePhone` (US phone number pattern) and `isCreditCard` (Luhn-algorithm card number check), sourced from validator.js patterns
- **Inline field errors** (`CheckoutFieldError.vue`) — a small reusable component that renders every active Vuelidate error message for a given field, styled in red italic text distinct from labels/inputs
- **No HTML5 validation** — every input is `type="text"` with no `required` or `minlength` attributes; all validation is handled entirely by Vuelidate
- **Expiry year selector** — dynamically generates the current year plus the next 15 years (16 total options), rather than a hardcoded list
- **Empty-cart handling** — checkout page shows a "cart is empty" message and a Continue Shopping link (routed to the last-viewed category via the category store) rather than rendering the form at all
- **Order total summary** — subtotal, surcharge, and total displayed prominently next to the Complete Purchase button
- **Simulated submission flow** — `submitOrder` runs Vuelidate's `$validate()`, blocks submission with an on-page error state if any field fails, and otherwise walks through a simulated PENDING → OK flow before routing to a new confirmation page
- **`ConfirmationView.vue`** — a minimal confirmation page and route, reached only after a successful (simulated) order submission

## 🛠️ Requirements

- JDK 17
- IntelliJ IDEA
- Tomcat 10 (not 11)
- Node.js (LTS) and npm
- A local MySQL 8 instance with the `ShaeBookstoreDB` schema created and seeded
- `pinia` (`^2.0.32`), `@vuelidate/core`, and `@vuelidate/validators` as client dependencies

## ▶️ How to Run

**Server:**
1. Open `ShaeBookstoreValidate` in IntelliJ, let Gradle sync
2. Confirm `context.xml` points at your local MySQL instance (JNDI resource `jdbc/ShaeBookstore`)
3. Confirm nothing else is already bound to ports 8080/8005 before starting
4. Configure a Tomcat 10 run configuration with application context `/ShaeBookstoreValidate`, then run

**Client:**
1. Open `shae-bookstore-validate-client` in a terminal
2. Run `npm install`
3. Run `npm run dev`
4. Open the printed `localhost:5173` URL

The server must be running before the client.

## 🧪 Testing Approach

- Verified every field triggers its correct validation message: an under-length name/address, an invalid email, an invalid phone number, and an invalid credit card number were each tested individually and in combination
- Verified a genuinely valid credit card number (`4444333322221111`) passes `isCreditCard`'s Luhn check
- Verified clicking "Complete Purchase" with zero interaction (no fields touched) still triggers all validators via `$validate()`, rather than only validating on blur/input
- Verified the empty-cart state correctly hides the form entirely and shows the Continue Shopping message instead
- Verified a fully valid submission walks through the PENDING → OK states and lands on `/confirmation`
- Verified in both the Vite dev server (5173) and the Tomcat-bundled build (8080) — see `TROUBLESHOOTING.md` for the deploy pipeline this still requires per project copy

## ✅ Expected Output

<div align="center"><img src="p8-checkout-validation-screenshot.png" width="800"></div>

Submitting the checkout form with invalid data in every field displays a distinct, specific error message above each one, in red italic text, without ever submitting the form or reloading the page. The order total, subtotal, and surcharge remain visible throughout.

## 💻 Setting This Up on Another PC

1. Clone this repository
2. Set up a local MySQL instance with the `ShaeBookstoreDB` schema (see `schema.sql` / `data.sql`)
3. Open `ShaeBookstoreValidate` in IntelliJ, update `context.xml` (see `context.xml.example`), configure Tomcat, run
4. Open `shae-bookstore-validate-client`, run `npm install` then `npm run dev`
5. Before checking the Tomcat-bundled version (port 8080) rather than the dev server, see `TROUBLESHOOTING.md` for the manual build-and-copy steps this project's Gradle build does **not** automate

## 📝 Notes

- Expiry date is intentionally **not** validated client-side per the assignment spec — that validation is deferred to the server in a later project.
- Order submission is currently simulated with `setTimeout` delays rather than a real API call; real order persistence is expected in a later project.
- As with the prior two projects, a fresh project copy starts with no `webapp/index.html` or `assets/` until the client is built and copied over — visiting port 8080 before that step will show either the raw `index.jsp` database dump or 404s on stylesheet/script requests referencing a previous project's stale asset hashes. See `TROUBLESHOOTING.md`.
