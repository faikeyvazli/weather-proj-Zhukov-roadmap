CREATE TABLE users
(
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    login VARCHAR(40),
    password VARCHAR(255)
);

CREATE TABLE locations
(
    id         INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name       VARCHAR(40),
    userId     INT,
    latitude   DECIMAL,
    longitude DECIMAL,
    FOREIGN KEY (userId) REFERENCES users (id)
);

CREATE TABLE sessions
(
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  userId INT,
  expiresAt DATE,
  FOREIGN KEY (userId) REFERENCES users(id)
);