CREATE TABLE users (
    id UUID NOT NULL,
    created TIMESTAMP(6),
    email VARCHAR(255) NOT NULL UNIQUE, -- Añadimos la restricción UNIQUE directamente
    is_active BOOLEAN,
    last_login TIMESTAMP(6),
    modified TIMESTAMP(6),
    name VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    token VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE phones (
    id UUID NOT NULL,
    citycode VARCHAR(255),
    countrycode VARCHAR(255),
    number VARCHAR(255),
    user_id UUID NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE phones
    ADD CONSTRAINT FK_phones_to_users
    FOREIGN KEY (user_id)
    REFERENCES users(id);