-- Franchises
INSERT INTO franchise ("name") VALUES ('The Lord of the Rings');
INSERT INTO franchise ("name") VALUES ('Ghostbusters');


-- Movies
INSERT INTO movie ("title", "director", "release_year", "franchise_id") VALUES
    ('The Lord of the Rings: The Fellowship of the Ring','Peter Jackson', 2001, 1);
INSERT INTO movie ("title", "director", "release_year", "franchise_id") VALUES
                ('The Lord of the Rings: The Two Towers','Peter Jackson', 2002, 1);
INSERT INTO movie ("title", "director", "release_year", "franchise_id") VALUES
    ('The Lord of the Rings: The Return of the King','Peter Jackson', 2003, 1);

INSERT INTO movie ("title", "director", "release_year", "franchise_id") VALUES
    ('Ghostbusters','Ivan Reitman', 1984, 2);
INSERT INTO movie ("title", "director", "release_year", "franchise_id") VALUES
    ('Ghostbusters II','Ivan Reitman', 1989, 2);
INSERT INTO movie ("title", "director", "release_year", "franchise_id") VALUES
    ('Ghostbusters: Answer the Call','Paul Feig', 2016, 2);


-- Characters
INSERT INTO character ("name") VALUES ('Frodo Baggins');
INSERT INTO character ("name") VALUES ('Gandalf');
INSERT INTO character ("name") VALUES ('Aragorn');

INSERT INTO character ("name") VALUES ('Dr. Peter Venkman');
INSERT INTO character ("name") VALUES ('Dr. Raymond Stantz');
INSERT INTO character ("name") VALUES ('Dr. Egon Spengler');


-- Characters in movie
INSERT INTO movie_characters (movies_id, characters_id) VALUES (1,1);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (1,2);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (1,3);

INSERT INTO movie_characters (movies_id, characters_id) VALUES (2,1);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (2,2);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (2,3);

INSERT INTO movie_characters (movies_id, characters_id) VALUES (3,1);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (3,2);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (3,3);

INSERT INTO movie_characters (movies_id, characters_id) VALUES (4,4);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (4,5);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (4,6);

INSERT INTO movie_characters (movies_id, characters_id) VALUES (5,4);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (5,5);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (5,6);

INSERT INTO movie_characters (movies_id, characters_id) VALUES (6,4);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (6,5);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (6,6);