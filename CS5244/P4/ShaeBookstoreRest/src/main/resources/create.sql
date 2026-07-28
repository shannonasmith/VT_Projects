--
-- EXECUTE THIS SCRIPT FROM THE COMMAND LINE
-- USING AN ADMINISTRATIVE MYSQL USER.
--
--   $ mysql -u root -p < create.sql
--
-- Creates a database and a user with all privileges
-- on that database for the project.
--
-- Included in the application here for completeness.
--

DROP SCHEMA IF EXISTS `ShaeBookstoreDB`;
CREATE SCHEMA IF NOT EXISTS `ShaeBookstoreDB`
    DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE USER IF NOT EXISTS 'shae1223'@'%' IDENTIFIED BY '5003';
GRANT ALL PRIVILEGES ON ShaeBookstoreDB.* to 'shae1223'@'%';
FLUSH PRIVILEGES;