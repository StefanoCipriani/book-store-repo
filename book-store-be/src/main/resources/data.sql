DROP TABLE IF EXISTS BOOK_AUTHORS ;
DROP TABLE IF EXISTS AUTHOR;
DROP TABLE IF EXISTS BOOK;
DROP TABLE IF EXISTS EDITORE ;

CREATE TABLE BOOK (
  isbn VARCHAR(250) PRIMARY KEY,
  titolo VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS AUTHOR;

CREATE TABLE AUTHOR (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  cognome VARCHAR(250) NOT NULL,
  nome VARCHAR(250) NOT NULL
);
