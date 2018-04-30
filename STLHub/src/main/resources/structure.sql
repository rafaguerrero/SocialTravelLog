
-- THIS IS THE STRUCTURE DESIGN, NOT USING THIS FILE FOR ANYTHING

CREATE TABLE users (
  username varchar(50) NOT NULL PRIMARY KEY,
  password varchar(100) NOT NULL,
  enabled BOOLEAN NOT NULL DEFAULT TRUE
);

-- User Roles
CREATE TABLE authorities (
  username varchar(50) NOT NULL,
  authority varchar(50) NOT NULL,
  CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);

CREATE UNIQUE INDEX ix_auth_username on authorities (username, authority);

CREATE TABLE persistent_logins (
       username varchar(100) not null,
       series varchar(64) primary key,
       token varchar(64) not null,
       last_used timestamp not null
);