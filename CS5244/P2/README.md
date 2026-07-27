<div align="center">

# 🏗️ CS 5244 – P2: Page Views (HTML/CSS)

![Focus](https://img.shields.io/badge/Focus-Static%20HTML%2FCSS-blue?style=for-the-badge)

</div>

---

## 🧠 What It Does

Static HTML/CSS implementation of the Welcome Page and Category Page, translating the P1
Figma mockups into real markup - no JavaScript, no frameworks, no preprocessors.

## 🎯 Why It Matters

Confirms the design holds up as real, responsive markup before any framework complexity is
introduced - the same visual system, now actually rendered by a browser instead of a
static image.

---

## 📄 Pages

- `index.html` - home page

<div align="center">
  <img src="Smith_Shannon_Pp2_index-html.jpg" width="800">
</div>

- `category.html` - category page (one category and its books, hardcoded for this stage;
  category switching comes in a later project)

<div align="center">
  <img src="Smith_Shannon_Pp2_category-html.jpg" width="800">
</div>

---

## 🧩 Header Requirements (Both Pages)

- Logo image and logo text both link to the welcome page
- All dropdown menu options link to the category page
- Search represented by an image (magnifying glass icon), not a button
- Shopping cart represented by an image, with cart count placed sensibly (over or inside
  the cart icon)
- Categories menu uses a down-caret/arrow, or a standard hamburger icon if using a
  hamburger menu - hover-triggered for this stage
- Social media links represented by images

---

## 🎨 Design Differentiation from Starter Code

The provided starter code ("Another Bookstore") could not be reused directly - the
following had to be original to this site:
- Logo, category images, and book selection (not the starter's)
- Custom CSS properties - fonts and colors distinct from the starter
- Button styling - no bevels, and buttons replaced with images where P1's requirements
  called for icon-based controls

## 🗂️ CSS Architecture Note

The starter code intentionally splits CSS across many small files rather than one per
page. This anticipates the move to Vue.js in the next project, where each Vue component
bundles its own HTML, JavaScript, and CSS - so CSS files that roughly correspond to future
components reduce rework later.

---

## ✅ Requirements Checklist

- Layout holds up between 1000px-1400px width
- Book images sized around 200px in height
- Header includes both logo image and logo text
- Padding present around all elements
- Cursor changes to pointer on hover over the category dropdown
- Hovered dropdown category has a subtle style change
- Clicking a category navigates to the (single) category page
- Hovered category button on the category page has a subtle style change
- Selected category button is styled distinctly from unselected ones
- Book boxes wrap appropriately as page width changes
- At least one book shows a "Read Now" button; at least one does not
- Book title/author/price are each styled distinctly from one another

---

## 📝 Notes

No JavaScript, SASS/preprocessors, or frameworks (e.g. Bootstrap) were used at this stage,
per the assignment constraints - plain HTML and CSS only.
