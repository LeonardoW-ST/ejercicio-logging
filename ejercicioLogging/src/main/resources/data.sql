INSERT INTO users (id, name) VALUES ('USR001', 'Francisco Sánchez Gómez');
INSERT INTO users (id, name) VALUES ('USR002', 'Ana López Martín');
INSERT INTO users (id, name) VALUES ('USR003', 'Carlos Ruiz Pérez');

-- Apuestas de USR001
INSERT INTO bets (id, user_id) VALUES (1, 'USR001');
INSERT INTO bet_numbers (bet_id, position, number) VALUES
                                                       (1, 0, 23), (1, 1, 45), (1, 2, 2), (1, 3, 10), (1, 4, 11), (1, 5, 43);

INSERT INTO bets (id, user_id) VALUES (2, 'USR001');
INSERT INTO bet_numbers (bet_id, position, number) VALUES
                                                       (2, 0, 34), (2, 1, 21), (2, 2, 7), (2, 3, 3), (2, 4, 9), (2, 5, 8);

-- Apuesta de USR002
INSERT INTO bets (id, user_id) VALUES (3, 'USR002');
INSERT INTO bet_numbers (bet_id, position, number) VALUES
                                                       (3, 0, 1), (3, 1, 13), (3, 2, 17), (3, 3, 25), (3, 4, 36), (3, 5, 49);

-- con los ids ya usados.
ALTER TABLE bets ALTER COLUMN id RESTART WITH 100;
