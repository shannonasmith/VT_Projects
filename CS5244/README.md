# CS5244 Bookstore Project Progression

Reference table for structuring the GitHub repo - project number, folder
name(s), and what each stage covers.

| Project | Folder(s) | Covers |
|---|---|---|
| Project 1 | *(no code folder — Figma only)* | Wireframes/mockups |
| Project 2 | *(no code folder — Figma only)* | Design refinement, still Figma |
| Project 3 | `ShaeBookstoreVue` (+ `shae-bookstore-vue-client`) | First actual Vue.js implementation — static site using `db.json`, no backend yet |
| Project 4 | `ShaeBookstoreRest` | DAO Pattern + REST API |
| Project 5 | `ShaeBookstoreFetch` (+ client) | Vue fetches from REST API instead of `db.json` |
| Project 6 | `ShaeBookstoreState` (+ client) | Pinia state management |
| Project 7 | `ShaeBookstoreSession` (+ client) | Cart page + localStorage persistence |
| Project 8 | `ShaeBookstoreValidate` (+ client) | Client-side checkout validation |
| Project 9 | `ShaeBookstoreOrder` (+ client) | Server-side validation |
| Project 10 / 10H | `ShaeBookstoreTransact` (+ client) | Transactions + hardening |

## Notes

- Every stage from Project 4 onward depends on a MySQL database on VT's
  server (`cs5244.cs.vt.edu`), which is no longer reachable post-enrollment.
  A replacement database setup (local MySQL, MySQL self-hosted on Ubuntu/
  CasaOS, or H2) needs to be decided before any stage can run again.
- Each stage from Project 5 onward has two parts: a server folder
  (`ShaeBookstoreX`) and a matching client folder (`shae-bookstore-x-client`)
  — both need to be brought forward together.
- Still need to confirm: whether Projects 1-2 produced any Figma
  exports/screenshots worth archiving alongside the code, even without a
  runnable folder.
