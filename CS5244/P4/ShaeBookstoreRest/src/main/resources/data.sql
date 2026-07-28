# noinspection SqlwithoutWhereForFile

USE ShaeBookstoreDB;

DELETE FROM customer_order_line_item;

DELETE FROM book;
ALTER TABLE book AUTO_INCREMENT = 1001;

DELETE FROM category;
ALTER TABLE category AUTO_INCREMENT = 1001;

INSERT INTO `category` (`name`)
VALUES ('Staff Picks'),('Fiction'),('Nonfiction'),('Philosophy'),('Autobiography'),('Poetry'),('Science Fiction'),('LGBTQ+');

INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Handmaids Tale', 'Margaret Atwood', '', 1375, 0, TRUE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Cunt', 'Inga Muscio', '', 1675, 0, FALSE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Why It`s Kicking Off Everywhere', 'Paul Mason', '', 1675, 0, TRUE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Mists of Avalon', 'Marion Zimmer Bradley', '', 1475, 0, TRUE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The God of Small Things', 'Arundhati Roy', '', 1675, 0, TRUE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Come To Me', 'Amy Bloom', '', 1375, 0, TRUE, FALSE, 1001);


INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Interpreter of Maladies', 'Jhumpa Lahiri', '', 975, 0, TRUE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Vampire Chronicles', 'Anne Rice', '', 2175, 0, TRUE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Catch-22', 'Joseph Heller', '', 1375, 0, FALSE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Snows of Kilimanjaro', 'Ernest Hemingway', '', 1675, 0, TRUE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Final Martyrs', 'Shusaku Endo', '', 1375, 0, TRUE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Same Difference', 'Siobhan Vivian', '', 1175, 0, TRUE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Passage', 'Justin Cronin', '', 1675, 0, TRUE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Hunger Games', 'Suzanne Collins', '', 1475, 0, TRUE, FALSE, 1002);


INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Eros The Bittersweet', 'Anne Carson', '', 1275, 0, TRUE, FALSE, 1003);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Woman They Could Not Silence', 'Kate Moore', '', 1375, 0, TRUE, FALSE, 1003);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Outliers: The Story of Success', 'Malcolm Gladwell', '', 1675, 0, TRUE, FALSE, 1003);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Impact of Identity', 'Irina Nevzlin', '', 975, 0, TRUE, FALSE, 1003);


INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Fountainhead', 'Ayn Rand', '', 2475, 0, TRUE, FALSE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Troubled Sleep', 'Jean Paul Sartre', '', 1875, 0, TRUE, FALSE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Crime and Punishment', 'Fyodor Dostoyevsky', '', 1275, 0, TRUE, FALSE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Zen and the Art of Motorcycle Maintenance', 'Robert M Pirsig', '', 1775, 0, TRUE, FALSE, 1004);


INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Confessions of an Economic Hit Man', 'John Perkins', '', 1975, 0, TRUE, FALSE, 1005);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Anything We Love Can Be Saved', 'Alice Walker', '', 2475, 0, TRUE, FALSE, 1005);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Way of the Peaceful Warrior', 'Dan Millman', '', 1675, 0, TRUE, FALSE, 1005);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Why Be Happy When You Could Be Normal?', 'Jeanette Winterson', '', 1475, 0, TRUE, FALSE, 1005);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('I Know Why the Caged Bird Sings', 'Maya Angelou', '', 1475, 0, TRUE, FALSE, 1005);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Year of Magical Thinking', 'Joan Didion', '', 1675, 0, FALSE, FALSE, 1005);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('I Am Malala?', 'Malala Yousafzai', '', 1675, 0, TRUE, FALSE, 1005);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('We Have Always Been Here', 'Samra Habib', '', 1875, 0, TRUE, FALSE, 1005);


INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Thus Spoke Zarathustra', 'Friedrich Nietzsche', '', 1175, 0, TRUE, FALSE, 1006);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Prophet', 'Kahlil Gibran', '', 975, 0, TRUE, FALSE, 1006);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Leaves of Grass', 'Walt Whitman', '', 1375, 0, TRUE, FALSE, 1006);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Selected Poems', 'John Keats', '', 1475, 0, TRUE, FALSE, 1006);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Ariel', 'Sylvia Plath', '', 1275, 0, TRUE, FALSE, 1006);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Songs of Innocence and of Experience', 'William Blake', '', 675, 0, FALSE, FALSE, 1006);


INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Fahrenheit 451', 'Ray Bradbury', '', 1375, 0, TRUE, FALSE, 1007);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Neuromancer', 'William Gibson', '', 1675, 0, TRUE, FALSE, 1007);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Slaughterhouse Five', 'Kurt Vonnegut', '', 1475, 0, TRUE, FALSE, 1007);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('1984', 'George Orwell', '', 875, 0, TRUE, FALSE, 1007);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Stranger in a Strange Land', 'Robert A. Heinlein', '', 1575, 0, TRUE, FALSE, 1007);


INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Venus Envy', 'Rita Mae Brown', '', 675, 0, TRUE, FALSE, 1008);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Symptoms of Being Human', 'Jeff Garvin', '', 1175, 0, TRUE, FALSE, 1008);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Gender Queer: A Memoir', 'Maia Kobabe', '', 1975, 0, TRUE, FALSE, 1008);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('This Book Is Gay', 'Juno Dawson', '', 1175, 0, TRUE, FALSE, 1008);

