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
INSERT INTO movimientos (fecha, tipo, valor, saldo, cuenta_id) VALUES
('2024-07-01', 'DEPOSITO', 1000.00, 1000.00, 1),
('2024-07-02', 'RETIRO', 200.00, 800.00, 1),
('2024-07-03', 'DEPOSITO', 500.00, 1300.00, 2),
('2024-07-04', 'RETIRO', 300.00, 1000.00, 2),
('2024-07-05', 'DEPOSITO', 700.00, 1700.00, 3),
('2024-07-06', 'RETIRO', 400.00, 1300.00, 3),
('2024-07-07', 'DEPOSITO', 900.00, 2200.00, 4),
('2024-07-08', 'RETIRO', 100.00, 2100.00, 4),
('2024-07-09', 'DEPOSITO', 600.00, 2700.00, 5),
('2024-07-10', 'RETIRO', 500.00, 2200.00, 5);
