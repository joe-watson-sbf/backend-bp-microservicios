-- Create table for 'personas'
CREATE TABLE IF NOT EXISTS personas (
    id BIGSERIAL PRIMARY KEY UNIQUE,
    nombre VARCHAR(255) NOT NULL,
    genero VARCHAR(255) NOT NULL,
    edad INT,
    identificacion VARCHAR(255) UNIQUE NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(255)
);

-- Create table for 'clientes'
CREATE TABLE IF NOT EXISTS clientes (
    id BIGSERIAL PRIMARY KEY,
    contrasena VARCHAR(255) NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (id) REFERENCES personas(id)
);

-- Insert sample data into 'personas'
INSERT INTO personas (nombre, genero, edad, identificacion, direccion, telefono) VALUES
 ('Juan Perez', 'MASCULINO', 30, '12345678A', 'Calle Falsa 123', '123456789'),
 ('Maria Gomez', 'FEMENINO', 25, '87654321B', 'Avenida Siempre Viva 742', '987654321'),
 ('Carlos Rios', 'MASCULINO', 35, '12345679A', 'Calle Falsa 124', '223456789'),
 ('Ana Martinez', 'FEMENINO', 28, '87654322B', 'Avenida Siempre Viva 743', '287654321'),
 ('Luis Fernandez', 'MASCULINO', 40, '12345680A', 'Calle Falsa 125', '323456789'),
 ('Laura Sanchez', 'FEMENINO', 22, '87654323B', 'Avenida Siempre Viva 744', '387654321'),
 ('Pedro Lopez', 'MASCULINO', 32, '12345681A', 'Calle Falsa 126', '423456789'),
 ('Elena Garcia', 'FEMENINO', 27, '87654324B', 'Avenida Siempre Viva 745', '487654321'),
 ('Jose Ramirez', 'MASCULINO', 45, '12345682A', 'Calle Falsa 127', '523456789'),
 ('Sofia Gonzalez', 'FEMENINO', 33, '87654325B', 'Avenida Siempre Viva 746', '587654321');

-- Insert sample data into 'clientes'
INSERT INTO clientes (id, contrasena, estado) VALUES
 (1, 'password123', TRUE),
 (2, 'password456', TRUE),
 (3, 'password789', TRUE),
 (4, 'password101', TRUE),
 (5, 'password102', TRUE),
 (6, 'password103', TRUE),
 (7, 'password104', TRUE),
 (8, 'password105', TRUE),
 (9, 'password106', TRUE),
 (10, 'password107', TRUE);
