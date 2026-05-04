--   tabla user (id INT, nombre VARCHAR)
--   tabla bets (id BIGINT, user_id INT)
--   tabla bet_numbers (bet_id BIGINT, position INT, number INT)

INSERT INTO user (id, nombre) VALUES (1, 'Francisco Sánchez Gómez');
INSERT INTO user (id, nombre) VALUES (2, 'Ana López Martín');
INSERT INTO user (id, nombre) VALUES (3, 'Carlos Ruiz Pérez');

-- Apuestas de Francisco (id=1)
INSERT INTO bets (id, user_id) VALUES (1, 1);
INSERT INTO bet_numbers (bet_id, position, number) VALUES
    (1, 0, 23), (1, 1, 45), (1, 2, 2), (1, 3, 10), (1, 4, 11), (1, 5, 43);

INSERT INTO bets (id, user_id) VALUES (2, 1);
INSERT INTO bet_numbers (bet_id, position, number) VALUES
    (2, 0, 34), (2, 1, 21), (2, 2, 7), (2, 3, 3), (2, 4, 9), (2, 5, 8);

-- Apuesta de Ana (id=2)
INSERT INTO bets (id, user_id) VALUES (3, 2);
INSERT INTO bet_numbers (bet_id, position, number) VALUES
    (3, 0, 1), (3, 1, 13), (3, 2, 17), (3, 3, 25), (3, 4, 36), (3, 5, 49);

-- con los ids ya usados.
ALTER TABLE user ALTER COLUMN id RESTART WITH 100;
ALTER TABLE bets ALTER COLUMN id RESTART WITH 100;
