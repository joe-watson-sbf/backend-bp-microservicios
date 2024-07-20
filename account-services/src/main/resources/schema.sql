CREATE SEQUENCE cuenta_sequence START WITH 1000 INCREMENT BY 1;
CREATE SEQUENCE movimiento_sequence START WITH 1000 INCREMENT BY 1;


-- Table for 'cuentas'
CREATE TABLE IF NOT EXISTS cuentas
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero INT NOT NULL,
    tipo VARCHAR(255) NOT NULL,
    saldo_inicial DECIMAL(19, 2) NOT NULL,
    estado BOOLEAN NOT NULL,
    cliente_id BIGINT NOT NULL
);

-- Table for 'movimientos'
CREATE TABLE IF NOT EXISTS movimientos
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    tipo VARCHAR(255) NOT NULL,
    valor DECIMAL(19, 2) NOT NULL,
    saldo DECIMAL(19, 2) NOT NULL,
    saldo_anterior DECIMAL(19, 2) NOT NULL,
    cuenta_id BIGINT,
    FOREIGN KEY (cuenta_id) REFERENCES cuentas(id)
);

-- Insert data into 'cuentas'
INSERT INTO cuentas (numero, tipo, saldo_inicial, estado, cliente_id) VALUES
((FLOOR(RAND() * 1000000)), 'AHORROS', 1000.00, TRUE, 1),
((FLOOR(RAND() * 1000000)), 'CORRIENTE', 2000.00, TRUE, 2),
((FLOOR(RAND() * 1000000)), 'AHORROS', 1500.00, TRUE, 3),
((FLOOR(RAND() * 1000000)), 'CORRIENTE', 3000.00, TRUE, 4),
((FLOOR(RAND() * 1000000)), 'AHORROS', 2500.00, TRUE, 5),
((FLOOR(RAND() * 1000000)), 'CORRIENTE', 4000.00, TRUE, 6),
((FLOOR(RAND() * 1000000)), 'AHORROS', 3500.00, TRUE, 7),
((FLOOR(RAND() * 1000000)), 'CORRIENTE', 5000.00, TRUE, 8),
((FLOOR(RAND() * 1000000)), 'AHORROS', 4500.00, TRUE, 9),
((FLOOR(RAND() * 1000000)), 'CORRIENTE', 6000.00, TRUE, 10);


-- Insert data into 'movimientos'
INSERT INTO movimientos (fecha, tipo, valor, saldo, saldo_anterior, cuenta_id) VALUES
('2021-01-01', 'DEPOSITO', 1000.00, 1000.00, 0, 1),
('2021-04-03', 'RETIRO', 200.00, 800.00, 1000.0, 1),
('2021-07-03', 'DEPOSITO', 500.00, 1300.00, 800.0, 2),
('2022-01-08', 'RETIRO', 300.00, 1000.00, 1300.0, 2),
('2022-07-09', 'DEPOSITO', 700.00, 1700.00, 1000.00, 3);

