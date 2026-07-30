-- Seed de datos demo para defensa de tesis.
-- Borra todo lo existente y carga 2 nutricionistas con pacientes, consultas, planes y turnos.
-- Password para AMBOS nutricionistas: demo1234

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE turnos;
TRUNCATE TABLE planes_nutricionales;
TRUNCATE TABLE consultas;
TRUNCATE TABLE pacientes;
TRUNCATE TABLE nutricionistas;
SET FOREIGN_KEY_CHECKS = 1;

-- ═══════════════════════════ NUTRICIONISTAS ═══════════════════════════
-- password para ambos: demo1234
INSERT INTO nutricionistas (id, username, password, email, nombre) VALUES
(1, 'agomez', '$2a$10$q3cH86VOyw.TQRMINy1NRuRFDP629aWJoSZV8VX/udSQ6QPvsukXy', 'ana.gomez@utnutri.com', 'Ana Gómez'),
(2, 'cruiz',  '$2a$10$q3cH86VOyw.TQRMINy1NRuRFDP629aWJoSZV8VX/udSQ6QPvsukXy', 'carlos.ruiz@utnutri.com', 'Carlos Ruiz');

-- ═══════════════════════════ PACIENTES ═══════════════════════════
-- Nutri 1: Ana Gómez (ids 1-5)
INSERT INTO pacientes (id, nutricionista_id, nombre, genero, fecha_nacimiento, correo, telefono) VALUES
(1, 1, 'Juan Pérez',         'Masculino', '1990-05-14', 'juan.perez@mail.com',       '1122334455'),
(2, 1, 'Lucía Fernández',    'Femenino',  '1985-11-02', 'lucia.fernandez@mail.com',  '1133445566'),
(3, 1, 'Martín Sosa',        'Masculino', '1998-03-22', 'martin.sosa@mail.com',      '1144556677'),
(4, 1, 'Camila Rodríguez',   'Femenino',  '2001-07-09', 'camila.rodriguez@mail.com', '1155667788'),
(5, 1, 'Diego Álvarez',      'Masculino', '1978-12-30', 'diego.alvarez@mail.com',    '1166778899');

-- Nutri 2: Carlos Ruiz (ids 6-10)
INSERT INTO pacientes (id, nutricionista_id, nombre, genero, fecha_nacimiento, correo, telefono) VALUES
(6,  2, 'Valentina Torres',  'Femenino',  '1993-02-17', 'valentina.torres@mail.com', '1177889900'),
(7,  2, 'Nicolás Medina',    'Masculino', '1988-09-05', 'nicolas.medina@mail.com',   '1188990011'),
(8,  2, 'Sofía Herrera',     'Femenino',  '1995-06-25', 'sofia.herrera@mail.com',    '1199001122'),
(9,  2, 'Federico Castro',   'Masculino', '1982-01-11', 'federico.castro@mail.com',  '1100112233'),
(10, 2, 'Agustina Romero',   'Femenino',  '2000-10-08', 'agustina.romero@mail.com',  '1111223344');

-- ═══════════════════════════ CONSULTAS ═══════════════════════════
-- 3 consultas por paciente, con progresión, para poblar los gráficos de evolución.
-- Paciente 1 (Juan) tiene DOS consultas el mismo día (2026-07-25) con distinto peso,
-- a propósito, para mostrar que "peso actual" toma la última cargada (mayor id), no la primera.
INSERT INTO consultas (paciente_id, fecha, peso, altura, grasa, masa, observaciones) VALUES
(1, '2026-05-10', 82.50, 178, 24.0, 38.0, 'Primera consulta, inicia plan de descenso'),
(1, '2026-06-15', 80.10, 178, 22.5, 38.5, 'Buena adherencia al plan'),
(1, '2026-07-25', 78.90, 178, 21.8, 39.0, 'Control de rutina'),
(1, '2026-07-25', 78.40, 178, 21.5, 39.2, 'Segunda medición del mismo día, balanza calibrada'),

(2, '2026-05-05', 65.00, 165, 28.0, 30.0, 'Consulta inicial'),
(2, '2026-06-10', 63.80, 165, 26.5, 30.8, 'Mejora sostenida'),
(2, '2026-07-20', 62.90, 165, 25.9, 31.2, 'Objetivo casi alcanzado'),

(3, '2026-05-12', 90.00, 182, 20.0, 42.0, 'Paciente deportista, foco en definición'),
(3, '2026-06-18', 89.20, 182, 19.2, 42.6, 'Progreso constante'),
(3, '2026-07-22', 88.50, 182, 18.6, 43.1, 'Buen resultado'),

(4, '2026-06-01', 58.00, 160, 25.0, 28.5, 'Consulta inicial, paciente joven'),
(4, '2026-06-28', 57.50, 160, 24.3, 28.9, 'Sin cambios mayores'),
(4, '2026-07-26', 57.10, 160, 24.0, 29.0, 'Estable'),

(5, '2026-05-20', 95.00, 175, 30.0, 35.0, 'Paciente con indicación médica de bajar de peso'),
(5, '2026-06-24', 92.80, 175, 28.7, 35.6, 'Buena baja inicial'),
(5, '2026-07-28', 90.50, 175, 27.5, 36.0, 'Sigue en descenso sostenido'),

(6, '2026-05-08', 60.00, 163, 26.0, 29.0, 'Consulta inicial'),
(6, '2026-06-14', 59.20, 163, 25.1, 29.5, 'Progreso leve'),
(6, '2026-07-24', 58.60, 163, 24.5, 29.9, 'Continua bien'),

(7, '2026-05-15', 85.00, 180, 22.0, 40.0, 'Consulta inicial'),
(7, '2026-06-20', 83.90, 180, 21.0, 40.5, 'Progreso normal'),
(7, '2026-07-18', 82.70, 180, 20.2, 41.0, 'Buen avance'),

(8, '2026-05-22', 55.00, 158, 27.0, 27.0, 'Consulta inicial'),
(8, '2026-06-26', 54.60, 158, 26.4, 27.3, 'Leve mejora'),
(8, '2026-07-27', 54.10, 158, 25.8, 27.8, 'Estable con tendencia positiva'),

(9, '2026-05-18', 98.00, 176, 31.0, 34.0, 'Consulta inicial, riesgo cardiovascular'),
(9, '2026-06-22', 95.40, 176, 29.5, 34.8, 'Buena respuesta al plan'),
(9, '2026-07-26', 93.10, 176, 28.0, 35.5, 'Sigue en descenso'),

(10, '2026-06-05', 62.00, 167, 24.0, 30.0, 'Consulta inicial'),
(10, '2026-06-30', 61.50, 167, 23.5, 30.4, 'Sin cambios significativos'),
(10, '2026-07-25', 61.00, 167, 23.0, 30.8, 'Estable');

-- ═══════════════════════════ PLANES NUTRICIONALES ═══════════════════════════
-- 7 de 10 pacientes con plan cargado (los otros 3 quedan sin plan a propósito,
-- para mostrar el estado vacío en el detalle de plan).
INSERT INTO planes_nutricionales (paciente_id, desayuno, almuerzo, merienda, cena, snacks, notas) VALUES
(1, 'Avena con fruta y yogur natural', 'Pechuga de pollo + ensalada + arroz integral', 'Yogur con nueces', 'Pescado al horno + verduras al vapor', 'Frutos secos, fruta', 'Reducir sodio, tomar 2L de agua al día'),
(2, 'Tostadas integrales con palta', 'Ensalada de quinoa con atún', 'Fruta + té verde', 'Sopa de verduras + tofu', 'Barritas de cereal', 'Evitar azúcares refinados'),
(3, 'Batido proteico + banana', 'Carne magra + batata + ensalada', 'Yogur griego', 'Pollo a la plancha + vegetales', 'Frutos secos', 'Aumentar ingesta proteica por entrenamiento'),
(4, 'Yogur con granola', 'Milanesa al horno + puré de calabaza', 'Fruta', 'Tarta de verduras', 'Gelatina light', 'Sin restricciones mayores, controlar porciones'),
(6, 'Huevos revueltos + tostada integral', 'Pescado + ensalada mixta', 'Licuado de frutas', 'Sopa + pan integral', 'Yogur', 'Reducir consumo de frituras'),
(7, 'Avena + fruta', 'Pollo + arroz integral + vegetales', 'Frutos secos', 'Ensalada completa con huevo', 'Yogur natural', 'Mantener actividad física 3 veces por semana'),
(9, 'Licuado de avena y fruta', 'Carne magra + ensalada + legumbres', 'Yogur descremado', 'Pescado + vegetales al vapor', 'Fruta', 'Control de presión arterial, reducir sal');

-- ═══════════════════════════ TURNOS ═══════════════════════════
-- Variedad de estados para mostrar los botones agregados (Aceptar/Reprogramar/Cancelar)
-- y el badge "Vencido" en turnos pendientes cuya fecha ya pasó.
INSERT INTO turnos (paciente_id, fecha_hora, observaciones, estado) VALUES
-- Juan (1): un turno futuro pendiente + uno vencido pendiente (para ver el badge y "Aceptar")
(1, '2026-08-05 10:00:00', 'Control mensual', 'PENDIENTE'),
(1, '2026-07-20 11:00:00', 'Revisión de plan, quedó sin marcar', 'PENDIENTE'),

-- Lucía (2): turno ya realizado
(2, '2026-07-10 09:00:00', 'Consulta de seguimiento', 'REALIZADO'),

-- Martín (3): turno cancelado
(3, '2026-07-15 16:00:00', 'Canceló por viaje', 'CANCELADO'),

-- Camila (4): turno futuro pendiente
(4, '2026-08-12 14:30:00', 'Primera revisión del mes', 'PENDIENTE'),

-- Diego (5): sin turnos (queda vacío a propósito)

-- Valentina (6): futuro pendiente + vencido pendiente
(6, '2026-08-03 09:30:00', 'Control de rutina', 'PENDIENTE'),
(6, '2026-07-22 15:00:00', 'Seguimiento de plan', 'PENDIENTE'),

-- Nicolás (7): realizado
(7, '2026-07-05 10:00:00', 'Consulta de control', 'REALIZADO'),

-- Sofía (8): cancelado
(8, '2026-07-18 11:30:00', 'Reprogramar pendiente', 'CANCELADO'),

-- Federico (9): futuro pendiente
(9, '2026-08-08 17:00:00', 'Control de presión y plan', 'PENDIENTE');

-- Agustina (10): sin turnos (queda vacío a propósito)
