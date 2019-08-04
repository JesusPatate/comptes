CREATE TABLE account (
    id serial PRIMARY KEY,
    name text NOT NULL,
    type text NOT NULL,
    initial_balance numeric DEFAULT 0.0,
    parent integer REFERENCES account (id)
);

CREATE TABLE operation (
    id serial PRIMARY KEY,
    description text NOT NULL,
    date date NOT NULL,
    amount numeric NOT NULL,
    from_account integer NOT NULL REFERENCES account (id),
    to_account integer NOT NULL REFERENCES account (id)
);