CREATE DATABASE IF NOT EXISTS nutricionistas_db
CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE nutricionistas_db;

CREATE TABLE nutricionistas (
                                id              BIGINT AUTO_INCREMENT PRIMARY KEY,
                                username        VARCHAR(50)  NOT NULL UNIQUE,
                                password        VARCHAR(255) NOT NULL,  -- BCrypt hash (~60 chars)
                                email           VARCHAR(150) NOT NULL UNIQUE,
                                nombre          VARCHAR(150) NOT NULL,
                                created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE pacientes (
                           id                BIGINT AUTO_INCREMENT PRIMARY KEY,
                           nutricionista_id  BIGINT       NOT NULL,
                           nombre            VARCHAR(150) NOT NULL,
                           genero            VARCHAR(20),
                           fecha_nacimiento  DATE,
                           correo            VARCHAR(150),
                           telefono          VARCHAR(30),
                           created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           CONSTRAINT fk_pac_nutri FOREIGN KEY (nutricionista_id)
                               REFERENCES nutricionistas(id) ON DELETE CASCADE,
                           INDEX idx_pac_nutri (nutricionista_id)
);

CREATE TABLE consultas (
                           id              BIGINT AUTO_INCREMENT PRIMARY KEY,
                           paciente_id     BIGINT NOT NULL,
                           fecha           DATE   NOT NULL,
                           peso            DECIMAL(5,2),
                           altura          DECIMAL(5,2),
                           grasa           DECIMAL(4,2),
                           masa            DECIMAL(5,2),
                           observaciones   TEXT,
                           CONSTRAINT fk_cons_pac FOREIGN KEY (paciente_id)
                               REFERENCES pacientes(id) ON DELETE CASCADE,
                           INDEX idx_cons_pac (paciente_id)
);

CREATE TABLE planes_nutricionales (
                                      id           BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      paciente_id  BIGINT NOT NULL UNIQUE,  -- 1:1
                                      desayuno     TEXT,
                                      almuerzo     TEXT,
                                      merienda     TEXT,
                                      cena         TEXT,
                                      snacks       TEXT,
                                      notas        TEXT,
                                      CONSTRAINT fk_plan_pac FOREIGN KEY (paciente_id)
                                          REFERENCES pacientes(id) ON DELETE CASCADE
);

CREATE TABLE turnos (
                        id              BIGINT AUTO_INCREMENT PRIMARY KEY,
                        paciente_id     BIGINT       NOT NULL,
                        fecha_hora      DATETIME     NOT NULL,
                        observaciones   TEXT,
                        estado          ENUM('PENDIENTE','CANCELADO','REALIZADO')
                                                     NOT NULL DEFAULT 'PENDIENTE',
                        created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        CONSTRAINT fk_tur_pac FOREIGN KEY (paciente_id)
                            REFERENCES pacientes(id) ON DELETE CASCADE,
                        INDEX idx_tur_pac (paciente_id),
                        INDEX idx_tur_fecha (fecha_hora)
);