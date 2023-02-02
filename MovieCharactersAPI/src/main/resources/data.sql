-- Franchises
INSERT INTO franchise ("name") VALUES ('The Lord of the Rings');
INSERT INTO franchise ("name") VALUES ('Ghostbusters');


-- Movies
INSERT INTO movie ("title", "director", "genre", "release_year", "movie_poster", "trailer", "franchise_id") VALUES
    ('The Lord of the Rings: The Fellowship of the Ring','Peter Jackson', 'Action/Adventure/Drama', 2001, 'shorturl.at/inxTX',
     'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 1);

INSERT INTO movie ("title", "director", "genre", "release_year", "movie_poster", "trailer", "franchise_id") VALUES
                ('The Lord of the Rings: The Two Towers','Peter Jackson', 'Action/Adventure/Drama', 2002, 'shorturl.at/inxTX',
                 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 1);

INSERT INTO movie ("title", "director", "genre", "release_year", "movie_poster", "trailer", "franchise_id") VALUES
    ('The Lord of the Rings: The Return of the King','Peter Jackson', 'Action/Adventure/Drama', 2003, 'shorturl.at/inxTX',
     'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 1);

INSERT INTO movie ("title", "director", "genre", "release_year", "movie_poster", "trailer", "franchise_id") VALUES
    ('Ghostbusters','Ivan Reitman', 'Action/Comedy/Fantasy', 1984, 'shorturl.at/inxTX',
     'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 2);
INSERT INTO movie ("title", "director", "genre", "release_year", "movie_poster", "trailer", "franchise_id") VALUES
    ('Ghostbusters II','Ivan Reitman', 'Action/Comedy/Fantasy', 1989, 'shorturl.at/inxTX',
     'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 2);
INSERT INTO movie ("title", "director", "genre", "release_year", "movie_poster", "trailer", "franchise_id") VALUES
    ('Ghostbusters: Answer the Call','Paul Feig', 'Action/Comedy/Fantasy', 2016, 'shorturl.at/inxTX',
     'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 2);


-- Characters
INSERT INTO character ("name", "alias", "gender", "picture") VALUES ('Frodo Baggins', 'The Ring Bearer', 'Male',
                                                                     'shorturl.at/inxTX');
INSERT INTO character ("name", "alias", "gender", "picture") VALUES ('Gandalf', 'Gandalf the Grey', 'Male',
                                                                     'shorturl.at/inxTX');
INSERT INTO character ("name", "alias", "gender", "picture") VALUES ('Aragorn', 'Son of Arathorn', 'Male',
                                                                     'shorturl.at/inxTX');

INSERT INTO character ("name", "alias", "gender", "picture") VALUES ('Peter Venkman', 'Dr. Peter Venkman', 'Male',
                                                                     'shorturl.at/inxTX');
INSERT INTO character ("name", "alias", "gender", "picture") VALUES ('Raymond Stantz', 'Ray', 'Male',
                                                                     'shorturl.at/inxTX');
INSERT INTO character ("name", "alias", "gender", "picture") VALUES ('Egon Spengler', 'Dirt Farmer', 'Male',
                                                                     'shorturl.at/inxTX');


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