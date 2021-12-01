CREATE TABLE movie AS SELECT * FROM CSVREAD('classpath:/movielist.csv', null, 'fieldSeparator=;', 'rowSeparator=;');
ALTER TABLE movie ADD COLUMN ID IDENTITY;