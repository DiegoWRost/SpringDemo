DROP TABLE IF EXISTS note;
DROP TABLE IF EXISTS "person";

CREATE TABLE IF NOT EXISTS "person" (
    person_id BIGINT GENERATED ALWAYS AS IDENTITY,
    email VARCHAR NOT NULL,
    active BOOLEAN NOT NULL DEFAULT true,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    PRIMARY KEY (person_id)
);

CREATE TABLE note (
  note_id BIGINT GENERATED ALWAYS AS IDENTITY,
  title VARCHAR NOT NULL,
  contents VARCHAR NOT NULL,
  owner BIGINT NOT NULL,
  FOREIGN KEY (owner) REFERENCES "person" (person_id) ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO "person" (email,active,first_name,last_name,password)
    VALUES ('diego@testemail.com', true, 'Diego','Rost', 'password');
INSERT INTO "person" (email,active,first_name,last_name,password)
    VALUES ('someperson@testemail.com', true, 'Some','Person', 'password');

INSERT INTO note (Title, Contents, Owner)
    VALUES ('Note to Self', 'It is being a fun journey learning Spring and REST', 1);
INSERT INTO note (Title, Contents, Owner)
    VALUES ('Another Note', 'Hopefully everyone can learn something from my tutorial', 1);
INSERT INTO note (Title, Contents, Owner)
    VALUES ('Some Note', 'Some note from Some Person', 2);
