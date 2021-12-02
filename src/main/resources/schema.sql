CREATE TABLE movie AS SELECT * FROM CSVREAD('classpath:/movielist.csv', null, 'fieldSeparator=;', 'rowSeparator=;');
ALTER TABLE movie ADD COLUMN ID IDENTITY;

CREATE TABLE producer (id int IDENTITY, name_producer varchar NOT NULL, CONSTRAINT producer_pkey PRIMARY KEY (id));

CREATE TABLE movie_producer (movie_id int NOT NULL, producer_id int NOT NULL, CONSTRAINT movie_producer_pkey PRIMARY KEY (movie_id, producer_id));
ALTER TABLE movie_producer ADD CONSTRAINT movie_producer_producer_id_fkey FOREIGN KEY (movie_id) REFERENCES movie(id) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE movie_producer ADD CONSTRAINT movie_producer_movie_id_fkey FOREIGN KEY (producer_id) REFERENCES producer(id) ON DELETE RESTRICT ON UPDATE RESTRICT;