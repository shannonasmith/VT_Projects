# Project 2 - Page Views (CS 5244)

Static HTML/CSS implementation of the Welcome Page and Category Page,
translating the Project 1 Figma mockups into real markup - no JavaScript,
no frameworks, no preprocessors.

## Overview

Building on the Project 1 design, this stage implements the actual page
structure using plain HTML and CSS. The Figma mockups served as a guide,
not a strict spec - some details were free to evolve during implementation.

## Pages

- `index.html` - home page
[![](https://github.com/shannonasmith/VT_Projects/raw/CS5244/P2/Smith_Shannon_Pp2_index-html.jpg)](https://github.com/shannonasmith/VT_Projects/blob/CS5244/P2/Smith_Shannon_Pp2_index-html.jpg)
- `category.html` - category page (one category and its books, hardcoded
  for this stage; category switching comes in a later project)


## Header Requirements (Both Pages)

- Logo image and logo text both link to the welcome page
- All dropdown menu options link to the category page
- Search represented by an image (magnifying glass icon), not a button
- Shopping cart represented by an image, with cart count placed sensibly
  (over or inside the cart icon)
- Categories menu uses a down-caret/arrow, or a standard hamburger icon if
  using a hamburger menu - hover-triggered for this stage (click-triggered
  requires JavaScript, added in a later project)
- Social media links represented by images

## Design Differentiation from Starter Code

The provided `bookstore-html.zip` starter ("Another Bookstore") could not be
reused directly - the following had to be original to this site:
- Logo (not the starter logo)
- Category images (not the starter images)
- Book selection (some overlap with the starter's books is fine)
- Custom CSS properties - fonts and colors distinct from the starter,
  with properties added/removed/renamed to fit this site's own design
- Button styling - no bevels, and buttons replaced with images where
  Project 1's requirements called for icon-based controls

## CSS Architecture Note

The starter code intentionally splits CSS across many small files rather
than one per page. This anticipates the move to Vue.js in the next project,
where each Vue component bundles its own HTML, JavaScript, and CSS - so
CSS files that roughly correspond to future components reduce rework later,
even though the mapping won't be perfectly 1-to-1.

## Requirements Checklist

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
- Book title/author/price are each styled distinctly from one another,
  not copied from the starter's styling

## Notes

No JavaScript, SASS/preprocessors, or frameworks (e.g. Bootstrap) were used
at this stage, per the assignment constraints - plain HTML and CSS only.
