-- ============================================================================
-- DATOS DE EJEMPLO PARA SISTEMA DE GESTIÓN DE PERSONAL Y PROVEEDORES
-- Sector: Construcción e Ingeniería Civil
-- ============================================================================

-- Insertar empresa principal
INSERT INTO CIA VALUES (1, 'CONSTRUCTORA INGENIEROS ASOCIADOS S.A.C.', 'CIA-SAC', '1');

-- Insertar catálogos de configuración básicos (ya incluidos en el esquema)
-- Las tablas TABS y ELEMENTOS ya tienen datos básicos

-- ============================================================================
-- DATOS DE ÁREAS ORGANIZACIONALES
-- ============================================================================

-- Insertar áreas de construcción
INSERT INTO areas (CodCIA, CodArea, nombre, descripcion, estado) VALUES 
(1, 1, 'Gerencia General', 'Área de dirección y administración general', 'A');
INSERT INTO areas (CodCIA, CodArea, nombre, descripcion, estado) VALUES 
(1, 2, 'Ingeniería y Proyectos', 'Diseño, planificación y supervisión técnica', 'A');
INSERT INTO areas (CodCIA, CodArea, nombre, descripcion, estado) VALUES 
(1, 3, 'Construcción y Obras', 'Ejecución de obras civiles y construcción', 'A');
INSERT INTO areas (CodCIA, CodArea, nombre, descripcion, estado) VALUES 
(1, 4, 'Administración y Finanzas', 'Gestión administrativa y financiera', 'A');
INSERT INTO areas (CodCIA, CodArea, nombre, descripcion, estado) VALUES 
(1, 5, 'Recursos Humanos', 'Gestión del talento humano', 'A');
INSERT INTO areas (CodCIA, CodArea, nombre, descripcion, estado) VALUES 
(1, 6, 'Seguridad y Medio Ambiente', 'Seguridad ocupacional y gestión ambiental', 'A');

-- Insertar cargos por área
-- Gerencia General
INSERT INTO cargos_areas (CodCargo, CodArea, CodCIA, nombre_cargo) VALUES 
(1, 1, 1, 'Gerente General');
INSERT INTO cargos_areas (CodCargo, CodArea, CodCIA, nombre_cargo) VALUES 
(2, 1, 1, 'Asistente de Gerencia');

-- Ingeniería y Proyectos
INSERT INTO cargos_areas (CodCargo, CodArea, CodCIA, nombre_cargo) VALUES 
(3, 2, 1, 'Jefe de Proyectos');
INSERT INTO cargos_areas (CodCargo, CodArea, CodCIA, nombre_cargo) VALUES 
(4, 2, 1, 'Ingeniero Civil Senior');
INSERT INTO cargos_areas (CodCargo, CodArea, CodCIA, nombre_cargo) VALUES 
(5, 2, 1, 'Ingeniero Civil Junior');
INSERT INTO cargos_areas (CodCargo, CodArea, CodCIA, nombre_cargo) VALUES 
(6, 2, 1, 'Arquitecto');
INSERT INTO cargos_areas (CodCargo, CodArea, CodCIA, nombre_cargo) VALUES 
(7, 2, 1, 'Dibujante Técnico');

-- Construcción y Obras
INSERT INTO cargos_areas (CodCargo, CodArea, CodCIA, nombre_cargo) VALUES 
(8, 3, 1, 'Jefe de Obra');
INSERT INTO cargos_areas (CodCargo, CodArea, CodCIA, nombre_cargo) VALUES 
(9, 3, 1, 'Supervisor de Obra');
INSERT INTO cargos_areas (CodCargo, CodArea, CodCIA, nombre_cargo) VALUES 
(10, 3, 1, 'Maestro de Obra');
INSERT INTO cargos_areas (CodCargo, CodArea, CodCIA, nombre_cargo) VALUES 
(11, 3, 1, 'Operario Especializado');
INSERT INTO cargos_areas (CodCargo, CodArea, CodCIA, nombre_cargo) VALUES 
(12, 3, 1, 'Peón');

-- Administración y Finanzas
INSERT INTO cargos_areas (CodCargo, CodArea, CodCIA, nombre_cargo) VALUES 
(13, 4, 1, 'Jefe Administrativo');
INSERT INTO cargos_areas (CodCargo, CodArea, CodCIA, nombre_cargo) VALUES 
(14, 4, 1, 'Contador');
INSERT INTO cargos_areas (CodCargo, CodArea, CodCIA, nombre_cargo) VALUES 
(15, 4, 1, 'Asistente Contable');

-- Recursos Humanos
INSERT INTO cargos_areas (CodCargo, CodArea, CodCIA, nombre_cargo) VALUES 
(16, 5, 1, 'Jefe de RRHH');
INSERT INTO cargos_areas (CodCargo, CodArea, CodCIA, nombre_cargo) VALUES 
(17, 5, 1, 'Especialista en RRHH');

-- Seguridad y Medio Ambiente
INSERT INTO cargos_areas (CodCargo, CodArea, CodCIA, nombre_cargo) VALUES 
(18, 6, 1, 'Jefe de Seguridad');
INSERT INTO cargos_areas (CodCargo, CodArea, CodCIA, nombre_cargo) VALUES 
(19, 6, 1, 'Prevencionista');

-- ============================================================================
-- USUARIOS DEL SISTEMA
-- ============================================================================

INSERT INTO usuarios (CodCIA, CodUsuario, username, password_hash, tipo_usuario) VALUES 
(1, 1, 'admin', '$2a$10$N9qo8uLOickgx2ZMRZoMye1kdQTZvKt5Z8vLfzNL5mjH5dY8CJQK6', 'ADMINISTRADOR');
INSERT INTO usuarios (CodCIA, CodUsuario, username, password_hash, tipo_usuario) VALUES 
(1, 2, 'secretaria', '$2a$10$M8fO7tPFnvPtKQi6xLKOjOVCdYeL8uRMF5nQ8xJqLiVQ8pO9lKjQ2', 'SECRETARIA');

-- ============================================================================
-- PERSONAS BASE (EMPLEADOS, CLIENTES, PROVEEDORES)
-- ============================================================================

-- Empleados
INSERT INTO PERSONA VALUES (1, 1, 'E', 'RODRIGUEZ MARTINEZ CARLOS ALBERTO', 'C.RODRIGUEZ', 'ING. CARLOS RODRIGUEZ', 'ING.CARLOS', '1');
INSERT INTO PERSONA VALUES (1, 2, 'E', 'GARCIA LOPEZ MARIA ELENA', 'M.GARCIA', 'ARQ. MARIA GARCIA', 'ARQ.MARIA', '1');
INSERT INTO PERSONA VALUES (1, 3, 'E', 'FERNANDEZ SILVA JUAN CARLOS', 'J.FERNANDEZ', 'ING. JUAN FERNANDEZ', 'ING.JUAN', '1');
INSERT INTO PERSONA VALUES (1, 4, 'E', 'TORRES MENDOZA ANA LUCIA', 'A.TORRES', 'ING. ANA TORRES', 'ING.ANA', '1');
INSERT INTO PERSONA VALUES (1, 5, 'E', 'CHAVEZ ROJAS PEDRO MIGUEL', 'P.CHAVEZ', 'PEDRO CHAVEZ', 'P.CHAVEZ', '1');
INSERT INTO PERSONA VALUES (1, 6, 'E', 'MORALES CASTRO LUIS ALBERTO', 'L.MORALES', 'LUIS MORALES', 'L.MORALES', '1');
INSERT INTO PERSONA VALUES (1, 7, 'E', 'HERRERA JIMENEZ ROSA MARIA', 'R.HERRERA', 'ROSA HERRERA', 'R.HERRERA', '1');
INSERT INTO PERSONA VALUES (1, 8, 'E', 'VARGAS FLORES MIGUEL ANGEL', 'M.VARGAS', 'MIGUEL VARGAS', 'M.VARGAS', '1');
INSERT INTO PERSONA VALUES (1, 9, 'E', 'CASTILLO RAMOS SERGIO ANTONIO', 'S.CASTILLO', 'SERGIO CASTILLO', 'S.CASTILLO', '1');
INSERT INTO PERSONA VALUES (1, 10, 'E', 'ROMERO VALDEZ PATRICIA ELENA', 'P.ROMERO', 'PATRICIA ROMERO', 'P.ROMERO', '1');

-- Clientes
INSERT INTO PERSONA VALUES (1, 101, 'C', 'MUNICIPALIDAD PROVINCIAL DE LIMA', 'MUNI.LIMA', 'MUNICIPALIDAD DE LIMA', 'MUNI.LIMA', '1');
INSERT INTO PERSONA VALUES (1, 102, 'C', 'MINISTERIO DE VIVIENDA Y CONSTRUCCION', 'MIN.VIVIENDA', 'MINISTERIO VIVIENDA', 'MIN.VIV', '1');
INSERT INTO PERSONA VALUES (1, 103, 'C', 'EMPRESA INMOBILIARIA TORRES DEL SOL S.A.', 'TORRES.SOL', 'TORRES DEL SOL', 'T.SOL', '1');
INSERT INTO PERSONA VALUES (1, 104, 'C', 'CONSORCIO CONSTRUCTOR DEL NORTE S.A.C.', 'CONSORCIO.N', 'CONSORCIO NORTE', 'CONS.N', '1');

-- Proveedores
INSERT INTO PERSONA VALUES (1, 201, 'P', 'ACEROS AREQUIPA S.A.', 'ACEROS.AQP', 'ACEROS AREQUIPA', 'ACEROS', '1');
INSERT INTO PERSONA VALUES (1, 202, 'P', 'CEMENTO SOL S.A.', 'CEMENTO.SOL', 'CEMENTO SOL', 'CEM.SOL', '1');
INSERT INTO PERSONA VALUES (1, 203, 'P', 'FERREYROS S.A.A.', 'FERREYROS', 'FERREYROS', 'FERREY', '1');
INSERT INTO PERSONA VALUES (1, 204, 'P', 'CONSTRUCCIONES Y SERVICIOS GENERALES E.I.R.L.', 'CONST.SERV', 'CONSTRUCCIONES GRALES', 'CONST.G', '1');
INSERT INTO PERSONA VALUES (1, 205, 'P', 'TRANSPORTES RODRIGUEZ S.A.C.', 'TRANSP.ROD', 'TRANSPORTES RODRIGUEZ', 'TRANSP.R', '1');
INSERT INTO PERSONA VALUES (1, 206, 'P', 'AGREGADOS Y CANTERAS DEL CENTRO S.R.L.', 'AGREG.CANT', 'AGREGADOS CENTRO', 'AGREG.C', '1');

-- ============================================================================
-- EMPLEADOS DETALLADOS
-- ============================================================================

INSERT INTO EMPLEADO VALUES (1, 1, 'Av. Arequipa 1234, Miraflores', '999123456', 'Lectura, Ciclismo, Ajedrez', NULL, TO_DATE('15/03/1975', 'DD/MM/YYYY'), '12345678', 'CIP12345', TO_DATE('15/03/2025', 'DD/MM/YYYY'), '1', '1', 'Ingeniero Civil con 20 años de experiencia', 3, 'carlos.rodriguez@cia.com', '1');
INSERT INTO EMPLEADO VALUES (1, 2, 'Calle Los Olivos 567, San Isidro', '999234567', 'Pintura, Arquitectura, Yoga', NULL, TO_DATE('22/07/1980', 'DD/MM/YYYY'), '23456789', 'CAP23456', TO_DATE('22/07/2025', 'DD/MM/YYYY'), '1', '1', 'Arquitecta especializada en diseño urbano', 6, 'maria.garcia@cia.com', '1');
INSERT INTO EMPLEADO VALUES (1, 3, 'Jr. Lima 890, Pueblo Libre', '999345678', 'Fútbol, Tecnología', NULL, TO_DATE('10/11/1985', 'DD/MM/YYYY'), '34567890', 'CIP34567', TO_DATE('10/11/2025', 'DD/MM/YYYY'), '1', '1', 'Especialista en estructuras y cálculo', 4, 'juan.fernandez@cia.com', '1');
INSERT INTO EMPLEADO VALUES (1, 4, 'Av. Brasil 456, Breña', '999456789', 'Natación, Lectura', NULL, TO_DATE('05/09/1988', 'DD/MM/YYYY'), '45678901', 'CIP45678', TO_DATE('05/09/2025', 'DD/MM/YYYY'), '1', '1', 'Ingeniera civil junior con especialización en hidráulica', 5, 'ana.torres@cia.com', '1');
INSERT INTO EMPLEADO VALUES (1, 5, 'Calle Real 123, Callao', '999567890', 'Carpintería, Mecánica', NULL, TO_DATE('18/12/1970', 'DD/MM/YYYY'), '56789012', 'N/A', TO_DATE('01/01/1900', 'DD/MM/YYYY'), '1', '0', 'Maestro de obra con 25 años de experiencia', 10, 'pedro.chavez@cia.com', '1');
INSERT INTO EMPLEADO VALUES (1, 6, 'Av. Universitaria 789, Los Olivos', '999678901', 'Soldadura, Deportes', NULL, TO_DATE('25/04/1982', 'DD/MM/YYYY'), '67890123', 'N/A', TO_DATE('01/01/1900', 'DD/MM/YYYY'), '1', '0', 'Operario especializado en estructuras metálicas', 11, 'luis.morales@cia.com', '1');
INSERT INTO EMPLEADO VALUES (1, 7, 'Jr. Huancayo 321, La Victoria', '999789012', 'Contabilidad, Baile', NULL, TO_DATE('30/06/1983', 'DD/MM/YYYY'), '78901234', 'N/A', TO_DATE('01/01/1900', 'DD/MM/YYYY'), '0', '1', 'Contadora pública colegiada', 14, 'rosa.herrera@cia.com', '1');
INSERT INTO EMPLEADO VALUES (1, 8, 'Calle Tacna 654, Cercado de Lima', '999890123', 'Seguridad Industrial, Montañismo', NULL, TO_DATE('12/01/1979', 'DD/MM/YYYY'), '89012345', 'CSST8901', TO_DATE('12/01/2025', 'DD/MM/YYYY'), '1', '1', 'Especialista en seguridad y salud ocupacional', 18, 'miguel.vargas@cia.com', '1');
INSERT INTO EMPLEADO VALUES (1, 9, 'Av. Benavides 987, Surco', '999901234', 'Supervisión, Fútbol', NULL, TO_DATE('08/08/1976', 'DD/MM/YYYY'), '90123456', 'CIP90123', TO_DATE('08/08/2025', 'DD/MM/YYYY'), '1', '1', 'Supervisor de obra con experiencia en edificaciones', 9, 'sergio.castillo@cia.com', '1');
INSERT INTO EMPLEADO VALUES (1, 10, 'Calle Cusco 147, Miraflores', '999012345', 'Gestión RRHH, Lectura', NULL, TO_DATE('14/02/1981', 'DD/MM/YYYY'), '01234567', 'N/A', TO_DATE('01/01/1900', 'DD/MM/YYYY'), '1', '1', 'Especialista en recursos humanos', 16, 'patricia.romero@cia.com', '1');

-- ============================================================================
-- CLIENTES
-- ============================================================================

INSERT INTO CLIENTE VALUES (1, 101, '20131367814', '1');
INSERT INTO CLIENTE VALUES (1, 102, '20087697749', '1');
INSERT INTO CLIENTE VALUES (1, 103, '20567891234', '1');
INSERT INTO CLIENTE VALUES (1, 104, '20678901234', '1');

-- ============================================================================
-- PROVEEDORES
-- ============================================================================

INSERT INTO PROVEEDOR VALUES (1, 201, '20100128218', '1');
INSERT INTO PROVEEDOR VALUES (1, 202, '20131312955', '1');
INSERT INTO PROVEEDOR VALUES (1, 203, '20100047218', '1');
INSERT INTO PROVEEDOR VALUES (1, 204, '20789012345', '1');
INSERT INTO PROVEEDOR VALUES (1, 205, '20890123456', '1');
INSERT INTO PROVEEDOR VALUES (1, 206, '20901234567', '1');

-- ============================================================================
-- PROYECTOS DE CONSTRUCCIÓN
-- ============================================================================

INSERT INTO PROYECTO VALUES (1, 1, 'CONSTRUCCION DE PUENTE VEHICULAR SOBRE RIO RIMAC - SECTOR HUACHIPA', 1, 1, 101, 1001, 101, '0', 'SNP001', TO_DATE('15/01/2024', 'DD/MM/YYYY'), 2, 3, 'TRAN', 15, 1, 0, 20, TO_DATE('15/01/2024', 'DD/MM/YYYY'), 2500000.00, 2000000.00, 200000.00, 100000.00, 200000.00, 2500000.00, 450000.00, 2950000.00, 0.00, '15', '01', '15', TO_DATE('01/12/2023', 'DD/MM/YYYY'), '/docs/proy001/', 2024, 2025, 5, NULL, '006', '001', 'Proyecto de infraestructura vial mayor', '1');

INSERT INTO PROYECTO VALUES (1, 2, 'EDIFICACION DE COMPLEJO HABITACIONAL LAS FLORES - 120 DEPARTAMENTOS', 2, 1, 103, 1002, 103, '0', 'SNP002', TO_DATE('20/02/2024', 'DD/MM/YYYY'), 1, 2, 'VIVI', 10, 1, 0, 15, TO_DATE('20/02/2024', 'DD/MM/YYYY'), 8500000.00, 7000000.00, 700000.00, 350000.00, 450000.00, 8500000.00, 1530000.00, 10030000.00, 0.00, '15', '01', '01', TO_DATE('10/01/2024', 'DD/MM/YYYY'), '/docs/proy002/', 2024, 2026, 3, NULL, '006', '001', 'Proyecto habitacional de interés social', '1');

INSERT INTO PROYECTO VALUES (1, 3, 'MEJORAMIENTO DE INFRAESTRUCTURA EDUCATIVA I.E. SAN MARTIN DE PORRES', 3, 1, 101, 1003, 101, '0', 'SNP003', TO_DATE('05/03/2024', 'DD/MM/YYYY'), 1, 1, 'EDUC', 8, 1, 0, 10, TO_DATE('05/03/2024', 'DD/MM/YYYY'), 1200000.00, 950000.00, 95000.00, 48000.00, 107000.00, 1200000.00, 216000.00, 1416000.00, 0.00, '15', '01', '32', TO_DATE('15/02/2024', 'DD/MM/YYYY'), '/docs/proy003/', 2024, 2024, 2, NULL, '006', '001', 'Mejoramiento de infraestructura educativa', '1');

-- ============================================================================
-- ESPECIALIDADES DEL PERSONAL
-- ============================================================================

-- Para especialidades_personal: CodCIA, CodEspecialidad, CodEmpleado, especialidad, certificado, institucion, fecha_obtencion, horas_capacitacion
INSERT INTO especialidades_personal (CodCIA, CodEspecialidad, CodEmpleado, especialidad, certificado, institucion, fecha_obtencion, horas_capacitacion) VALUES 
(1, 1, 1, 'Gestión de Proyectos de Construcción', NULL, 'PMI - Project Management Institute', TO_DATE('15/06/2020', 'DD/MM/YYYY'), 120);
INSERT INTO especialidades_personal (CodCIA, CodEspecialidad, CodEmpleado, especialidad, certificado, institucion, fecha_obtencion, horas_capacitacion) VALUES 
(1, 2, 1, 'Evaluación de Impacto Ambiental', NULL, 'CONAM', TO_DATE('20/08/2019', 'DD/MM/YYYY'), 80);
INSERT INTO especialidades_personal (CodCIA, CodEspecialidad, CodEmpleado, especialidad, certificado, institucion, fecha_obtencion, horas_capacitacion) VALUES 
(1, 3, 2, 'Diseño Arquitectónico Sostenible', NULL, 'Green Building Council', TO_DATE('10/09/2021', 'DD/MM/YYYY'), 100);
INSERT INTO especialidades_personal (CodCIA, CodEspecialidad, CodEmpleado, especialidad, certificado, institucion, fecha_obtencion, horas_capacitacion) VALUES 
(1, 4, 3, 'Análisis Estructural Avanzado', NULL, 'Colegio de Ingenieros del Perú', TO_DATE('25/04/2022', 'DD/MM/YYYY'), 150);
INSERT INTO especialidades_personal (CodCIA, CodEspecialidad, CodEmpleado, especialidad, certificado, institucion, fecha_obtencion, horas_capacitacion) VALUES 
(1, 5, 4, 'Hidráulica y Saneamiento', NULL, 'Universidad Nacional de Ingeniería', TO_DATE('12/11/2023', 'DD/MM/YYYY'), 90);
INSERT INTO especialidades_personal (CodCIA, CodEspecialidad, CodEmpleado, especialidad, certificado, institucion, fecha_obtencion, horas_capacitacion) VALUES 
(1, 6, 8, 'Seguridad y Salud Ocupacional', NULL, 'TECSUP', TO_DATE('05/07/2022', 'DD/MM/YYYY'), 200);

-- ============================================================================
-- GRADOS ACADÉMICOS
-- ============================================================================

-- Para grados_academicos: CodCIA, CodGrado, CodEmpleado, tipo_grado, carrera, titulo, institucion, fecha_obtencion, documento
INSERT INTO grados_academicos (CodCIA, CodGrado, CodEmpleado, tipo_grado, carrera, titulo, institucion, fecha_obtencion, documento) VALUES 
(1, 1, 1, 'LICENCIATURA', 'Ingeniería Civil', 'Ingeniero Civil', 'Universidad Nacional de Ingeniería', TO_DATE('20/12/1998', 'DD/MM/YYYY'), NULL);
INSERT INTO grados_academicos (CodCIA, CodGrado, CodEmpleado, tipo_grado, carrera, titulo, institucion, fecha_obtencion, documento) VALUES 
(1, 2, 1, 'MAESTRIA', 'Gestión de la Construcción', 'Magister en Gestión de la Construcción', 'Universidad Nacional de Ingeniería', TO_DATE('15/07/2005', 'DD/MM/YYYY'), NULL);
INSERT INTO grados_academicos (CodCIA, CodGrado, CodEmpleado, tipo_grado, carrera, titulo, institucion, fecha_obtencion, documento) VALUES 
(1, 3, 2, 'LICENCIATURA', 'Arquitectura', 'Arquitecta', 'Universidad Ricardo Palma', TO_DATE('18/11/2003', 'DD/MM/YYYY'), NULL);
INSERT INTO grados_academicos (CodCIA, CodGrado, CodEmpleado, tipo_grado, carrera, titulo, institucion, fecha_obtencion, documento) VALUES 
(1, 4, 3, 'LICENCIATURA', 'Ingeniería Civil', 'Ingeniero Civil', 'Pontificia Universidad Católica del Perú', TO_DATE('22/06/2008', 'DD/MM/YYYY'), NULL);
INSERT INTO grados_academicos (CodCIA, CodGrado, CodEmpleado, tipo_grado, carrera, titulo, institucion, fecha_obtencion, documento) VALUES 
(1, 5, 4, 'LICENCIATURA', 'Ingeniería Civil', 'Ingeniera Civil', 'Universidad Nacional de Ingeniería', TO_DATE('10/12/2011', 'DD/MM/YYYY'), NULL);
-- Los siguientes empleados no se insertaron correctamente, comentado temporalmente
-- INSERT INTO grados_academicos (CodCIA, CodEmpleado, tipo_grado, carrera, titulo, institucion, fecha_obtencion) VALUES 
-- (1, 5, 'TECNICO', 'Construcción Civil', 'Técnico en Construcción Civil', 'TECSUP', TO_DATE('25/03/1992', 'DD/MM/YYYY'));
-- INSERT INTO grados_academicos (CodCIA, CodEmpleado, tipo_grado, carrera, titulo, institucion, fecha_obtencion) VALUES 
-- (1, 7, 'LICENCIATURA', 'Contabilidad', 'Contadora Pública', 'Universidad San Martín de Porres', TO_DATE('15/07/2006', 'DD/MM/YYYY'));

-- ============================================================================
-- EXPERIENCIA LABORAL
-- ============================================================================

-- Para experiencia_laboral: CodCIA, CodExperiencia, CodEmpleado, empresa, especialidad, fecha_inicio, fecha_fin, certificado
INSERT INTO experiencia_laboral (CodCIA, CodExperiencia, CodEmpleado, empresa, especialidad, fecha_inicio, fecha_fin, certificado) VALUES 
(1, 1, 1, 'Constructora Jiménez S.A.', 'Jefe de Proyectos', TO_DATE('01/01/2000', 'DD/MM/YYYY'), TO_DATE('31/12/2010', 'DD/MM/YYYY'), NULL);
INSERT INTO experiencia_laboral (CodCIA, CodExperiencia, CodEmpleado, empresa, especialidad, fecha_inicio, fecha_fin, certificado) VALUES 
(1, 2, 1, 'Ingenieros Civiles Asociados', 'Gerente Técnico', TO_DATE('01/01/2011', 'DD/MM/YYYY'), TO_DATE('31/12/2020', 'DD/MM/YYYY'), NULL);
INSERT INTO experiencia_laboral (CodCIA, CodExperiencia, CodEmpleado, empresa, especialidad, fecha_inicio, fecha_fin, certificado) VALUES 
(1, 3, 2, 'Estudio de Arquitectura Moderna', 'Arquitecta Proyectista', TO_DATE('01/06/2004', 'DD/MM/YYYY'), TO_DATE('30/11/2015', 'DD/MM/YYYY'), NULL);
INSERT INTO experiencia_laboral (CodCIA, CodExperiencia, CodEmpleado, empresa, especialidad, fecha_inicio, fecha_fin, certificado) VALUES 
(1, 4, 3, 'Consultora Estructural del Perú', 'Ingeniero de Estructuras', TO_DATE('15/01/2009', 'DD/MM/YYYY'), TO_DATE('31/05/2018', 'DD/MM/YYYY'), NULL);
-- Empleado 5 no se insertó correctamente, comentado temporalmente
-- INSERT INTO experiencia_laboral (CodCIA, CodEmpleado, empresa, especialidad, fecha_inicio, fecha_fin) VALUES 
-- (1, 5, 'Constructora Los Andes', 'Maestro de Obra', TO_DATE('01/04/1995', 'DD/MM/YYYY'), TO_DATE('31/12/2020', 'DD/MM/YYYY'));

-- ============================================================================
-- ASIGNACIÓN DE PERSONAL A PROYECTOS
-- ============================================================================

-- Para personal_proyectos, el esquema requiere: CodCIA, CodPersonalProyecto, CodEmpleado, CodPyto, CodCargo, CodArea, fecha_asignacion, fecha_desasignacion, horas_asignadas
INSERT INTO personal_proyectos (CodCIA, CodPersonalProyecto, CodEmpleado, CodPyto, CodCargo, CodArea, fecha_asignacion, horas_asignadas) VALUES 
(1, 1, 1, 1, 3, 2, TO_DATE('15/01/2024', 'DD/MM/YYYY'), 40.00);
INSERT INTO personal_proyectos (CodCIA, CodPersonalProyecto, CodEmpleado, CodPyto, CodCargo, CodArea, fecha_asignacion, horas_asignadas) VALUES 
(1, 2, 3, 1, 4, 2, TO_DATE('15/01/2024', 'DD/MM/YYYY'), 35.00);
INSERT INTO personal_proyectos (CodCIA, CodPersonalProyecto, CodEmpleado, CodPyto, CodCargo, CodArea, fecha_asignacion, horas_asignadas) VALUES 
(1, 3, 9, 1, 9, 3, TO_DATE('20/01/2024', 'DD/MM/YYYY'), 42.00);
INSERT INTO personal_proyectos (CodCIA, CodPersonalProyecto, CodEmpleado, CodPyto, CodCargo, CodArea, fecha_asignacion, horas_asignadas) VALUES 
(1, 4, 2, 2, 6, 2, TO_DATE('20/02/2024', 'DD/MM/YYYY'), 38.00);
INSERT INTO personal_proyectos (CodCIA, CodPersonalProyecto, CodEmpleado, CodPyto, CodCargo, CodArea, fecha_asignacion, horas_asignadas) VALUES 
(1, 5, 4, 2, 5, 2, TO_DATE('20/02/2024', 'DD/MM/YYYY'), 30.00);
INSERT INTO personal_proyectos (CodCIA, CodPersonalProyecto, CodEmpleado, CodPyto, CodCargo, CodArea, fecha_asignacion, horas_asignadas) VALUES 
(1, 6, 3, 3, 3, 2, TO_DATE('05/03/2024', 'DD/MM/YYYY'), 35.00);
INSERT INTO personal_proyectos (CodCIA, CodPersonalProyecto, CodEmpleado, CodPyto, CodCargo, CodArea, fecha_asignacion, horas_asignadas) VALUES 
(1, 7, 8, 3, 18, 6, TO_DATE('10/03/2024', 'DD/MM/YYYY'), 20.00);

-- ============================================================================
-- TAREAS DEL PERSONAL
-- ============================================================================

-- Para tareas_personal: CodCIA, CodTarea, CodPyto, CodEmpleado, nombre, descripcion, fecha_inicio, fecha_fin, estado
INSERT INTO tareas_personal (CodCIA, CodTarea, CodPyto, CodEmpleado, nombre, descripcion, fecha_inicio, fecha_fin, estado) VALUES 
(1, 1, 1, 1, 'Elaboración de expediente técnico', 'Desarrollo completo del expediente técnico del puente vehicular', TO_DATE('15/01/2024', 'DD/MM/YYYY'), TO_DATE('28/02/2024', 'DD/MM/YYYY'), 'COMPLETADA');
INSERT INTO tareas_personal (CodCIA, CodTarea, CodPyto, CodEmpleado, nombre, descripcion, fecha_inicio, fecha_fin, estado) VALUES 
(1, 2, 1, 3, 'Cálculo estructural del puente', 'Análisis y diseño estructural de la superestructura', TO_DATE('01/02/2024', 'DD/MM/YYYY'), TO_DATE('15/03/2024', 'DD/MM/YYYY'), 'COMPLETADA');
INSERT INTO tareas_personal (CodCIA, CodTarea, CodPyto, CodEmpleado, nombre, descripcion, fecha_inicio, fecha_fin, estado) VALUES 
(1, 3, 2, 2, 'Diseño arquitectónico del complejo', 'Planos arquitectónicos y distribución de departamentos', TO_DATE('20/02/2024', 'DD/MM/YYYY'), TO_DATE('20/04/2024', 'DD/MM/YYYY'), 'COMPLETADA');
INSERT INTO tareas_personal (CodCIA, CodTarea, CodPyto, CodEmpleado, nombre, descripcion, fecha_inicio, fecha_fin, estado) VALUES 
(1, 4, 2, 4, 'Diseño de instalaciones sanitarias', 'Sistema de agua potable y desagüe del complejo', TO_DATE('01/03/2024', 'DD/MM/YYYY'), TO_DATE('30/04/2024', 'DD/MM/YYYY'), 'EN_PROGRESO');

-- ============================================================================
-- EVALUACIONES DE DESEMPEÑO
-- ============================================================================

-- Para evaluaciones_desempeno: CodCIA, CodEvaluacion, CodEmpleado, CodPyto, evaluador_id, puntuacion_total, competencias_tecnicas, competencias_blandas, cumplimiento_objetivos
INSERT INTO evaluaciones_desempeno (CodCIA, CodEvaluacion, CodEmpleado, CodPyto, evaluador_id, puntuacion_total, competencias_tecnicas, competencias_blandas, cumplimiento_objetivos) VALUES 
(1, 1, 1, 1, 1, 92.50, 95.00, 90.00, 92.50);
INSERT INTO evaluaciones_desempeno (CodCIA, CodEvaluacion, CodEmpleado, CodPyto, evaluador_id, puntuacion_total, competencias_tecnicas, competencias_blandas, cumplimiento_objetivos) VALUES 
(1, 2, 2, 2, 1, 88.75, 90.00, 87.50, 88.75);
INSERT INTO evaluaciones_desempeno (CodCIA, CodEvaluacion, CodEmpleado, CodPyto, evaluador_id, puntuacion_total, competencias_tecnicas, competencias_blandas, cumplimiento_objetivos) VALUES 
(1, 3, 3, 1, 1, 89.25, 92.50, 86.00, 89.25);
-- Empleado 5 no se insertó correctamente, comentado temporalmente
-- INSERT INTO evaluaciones_desempeno (CodCIA, CodEmpleado, CodPyto, evaluador_id, puntuacion_total, competencias_tecnicas, competencias_blandas, cumplimiento_objetivos) VALUES 
-- (1, 5, 1, 9, 85.50, 88.00, 83.00, 85.50);

-- ============================================================================
-- SERVICIOS DE PROVEEDORES
-- ============================================================================

-- Para servicios_proveedor: CodCIA, CodServicio, CodProveedor, nombre_servicio, descripcion, documento_servicio
INSERT INTO servicios_proveedor (CodCIA, CodServicio, CodProveedor, nombre_servicio, descripcion, documento_servicio) VALUES 
(1, 1, 201, 'Suministro de Acero de Construcción', 'Varillas de acero corrugado, mallas electrosoldadas, perfiles estructurales', NULL);
INSERT INTO servicios_proveedor (CodCIA, CodServicio, CodProveedor, nombre_servicio, descripcion, documento_servicio) VALUES 
(1, 2, 202, 'Suministro de Cemento Portland', 'Cemento tipo I, tipo II, cemento blanco para acabados', NULL);
INSERT INTO servicios_proveedor (CodCIA, CodServicio, CodProveedor, nombre_servicio, descripcion, documento_servicio) VALUES 
(1, 3, 203, 'Alquiler de Maquinaria Pesada', 'Excavadoras, cargadores frontales, compactadoras, grúas', NULL);
INSERT INTO servicios_proveedor (CodCIA, CodServicio, CodProveedor, nombre_servicio, descripcion, documento_servicio) VALUES 
(1, 4, 204, 'Servicios de Construcción Especializada', 'Trabajos de albañilería, instalaciones eléctricas y sanitarias', NULL);
INSERT INTO servicios_proveedor (CodCIA, CodServicio, CodProveedor, nombre_servicio, descripcion, documento_servicio) VALUES 
(1, 5, 205, 'Transporte de Materiales', 'Transporte de agregados, concreto premezclado, materiales varios', NULL);
INSERT INTO servicios_proveedor (CodCIA, CodServicio, CodProveedor, nombre_servicio, descripcion, documento_servicio) VALUES 
(1, 6, 206, 'Suministro de Agregados', 'Arena gruesa, piedra chancada, hormigón, afirmado', NULL);

-- ============================================================================
-- CONTRATOS CON PROVEEDORES
-- ============================================================================

-- Para contratos_proveedor: CodCIA, CodContrato, CodProveedor, CodPyto, numero_contrato, tipo_contrato, fecha_inicio, fecha_fin, monto_total, moneda, documento_contrato
INSERT INTO contratos_proveedor (CodCIA, CodContrato, CodProveedor, CodPyto, numero_contrato, tipo_contrato, fecha_inicio, fecha_fin, monto_total, moneda, documento_contrato) VALUES 
(1, 1, 201, 1, 'CONT-001-2024', 'SUMINISTROS', TO_DATE('01/02/2024', 'DD/MM/YYYY'), TO_DATE('31/12/2024', 'DD/MM/YYYY'), 350000.00, 'PEN', NULL);
INSERT INTO contratos_proveedor (CodCIA, CodContrato, CodProveedor, CodPyto, numero_contrato, tipo_contrato, fecha_inicio, fecha_fin, monto_total, moneda, documento_contrato) VALUES 
(1, 2, 202, 1, 'CONT-002-2024', 'SUMINISTROS', TO_DATE('15/02/2024', 'DD/MM/YYYY'), TO_DATE('31/10/2024', 'DD/MM/YYYY'), 180000.00, 'PEN', NULL);
INSERT INTO contratos_proveedor (CodCIA, CodContrato, CodProveedor, CodPyto, numero_contrato, tipo_contrato, fecha_inicio, fecha_fin, monto_total, moneda, documento_contrato) VALUES 
(1, 3, 203, 1, 'CONT-003-2024', 'SERVICIOS', TO_DATE('01/03/2024', 'DD/MM/YYYY'), TO_DATE('30/11/2024', 'DD/MM/YYYY'), 250000.00, 'PEN', NULL);
INSERT INTO contratos_proveedor (CodCIA, CodContrato, CodProveedor, CodPyto, numero_contrato, tipo_contrato, fecha_inicio, fecha_fin, monto_total, moneda, documento_contrato) VALUES 
(1, 4, 205, 2, 'CONT-004-2024', 'SERVICIOS', TO_DATE('01/03/2024', 'DD/MM/YYYY'), TO_DATE('31/12/2025', 'DD/MM/YYYY'), 420000.00, 'PEN', NULL);
INSERT INTO contratos_proveedor (CodCIA, CodContrato, CodProveedor, CodPyto, numero_contrato, tipo_contrato, fecha_inicio, fecha_fin, monto_total, moneda, documento_contrato) VALUES 
(1, 5, 206, 3, 'CONT-005-2024', 'SUMINISTROS', TO_DATE('10/03/2024', 'DD/MM/YYYY'), TO_DATE('30/09/2024', 'DD/MM/YYYY'), 85000.00, 'PEN', NULL);

-- ============================================================================
-- ACTIVIDADES DE PROVEEDORES
-- ============================================================================

-- Para actividades_proveedor: CodCIA, CodActividad, CodProveedor, CodPyto, CodContrato, descripcion, fecha_actividad, monto, estado, documento, observaciones
INSERT INTO actividades_proveedor (CodCIA, CodActividad, CodProveedor, CodPyto, CodContrato, descripcion, fecha_actividad, monto, estado, documento, observaciones) VALUES 
(1, 1, 201, 1, 1, 'Entrega de varillas de acero 3/8" - Primera entrega', TO_DATE('05/03/2024', 'DD/MM/YYYY'), 45000.00, 'COMPLETADA', NULL, 'Entrega conforme según especificaciones técnicas');
INSERT INTO actividades_proveedor (CodCIA, CodActividad, CodProveedor, CodPyto, CodContrato, descripcion, fecha_actividad, monto, estado, documento, observaciones) VALUES 
(1, 2, 202, 1, 2, 'Suministro cemento Portland Tipo I - 500 bolsas', TO_DATE('20/02/2024', 'DD/MM/YYYY'), 28000.00, 'COMPLETADA', NULL, 'Cemento en perfecto estado, almacenado correctamente');
INSERT INTO actividades_proveedor (CodCIA, CodActividad, CodProveedor, CodPyto, CodContrato, descripcion, fecha_actividad, monto, estado, documento, observaciones) VALUES 
(1, 3, 203, 1, 3, 'Alquiler excavadora CAT 320D - 15 días', TO_DATE('01/04/2024', 'DD/MM/YYYY'), 22500.00, 'EN_PROGRESO', NULL, 'Máquina operando en excavación de cimentación');
INSERT INTO actividades_proveedor (CodCIA, CodActividad, CodProveedor, CodPyto, CodContrato, descripcion, fecha_actividad, monto, estado, documento, observaciones) VALUES 
(1, 4, 205, 2, 4, 'Transporte de materiales - Semana 1', TO_DATE('25/02/2024', 'DD/MM/YYYY'), 8500.00, 'COMPLETADA', NULL, 'Transporte de agregados para inicio de obra');

-- ============================================================================
-- DOCUMENTOS DE PROVEEDORES
-- ============================================================================

-- Para documentos_proveedor: CodCIA, CodDocumento, CodProveedor, tipo_documento, numero_documento, archivo, tipo_archivo, fecha_emision, fecha_vencimiento
INSERT INTO documentos_proveedor (CodCIA, CodDocumento, CodProveedor, tipo_documento, numero_documento, archivo, tipo_archivo, fecha_emision, fecha_vencimiento) VALUES 
(1, 1, 201, 'RUC', '20100128218', EMPTY_BLOB(), 'PDF', TO_DATE('15/01/2020', 'DD/MM/YYYY'), NULL);
INSERT INTO documentos_proveedor (CodCIA, CodDocumento, CodProveedor, tipo_documento, numero_documento, archivo, tipo_archivo, fecha_emision, fecha_vencimiento) VALUES 
(1, 2, 201, 'LICENCIA', 'LIC-CONST-001', EMPTY_BLOB(), 'PDF', TO_DATE('10/06/2023', 'DD/MM/YYYY'), TO_DATE('10/06/2025', 'DD/MM/YYYY'));
INSERT INTO documentos_proveedor (CodCIA, CodDocumento, CodProveedor, tipo_documento, numero_documento, archivo, tipo_archivo, fecha_emision, fecha_vencimiento) VALUES 
(1, 3, 203, 'CERTIFICACION', 'CERT-ISO-9001', EMPTY_BLOB(), 'PDF', TO_DATE('20/03/2023', 'DD/MM/YYYY'), TO_DATE('20/03/2026', 'DD/MM/YYYY'));
INSERT INTO documentos_proveedor (CodCIA, CodDocumento, CodProveedor, tipo_documento, numero_documento, archivo, tipo_archivo, fecha_emision, fecha_vencimiento) VALUES 
(1, 4, 205, 'SEGURO', 'POL-SCTR-2024', EMPTY_BLOB(), 'PDF', TO_DATE('01/01/2024', 'DD/MM/YYYY'), TO_DATE('31/12/2024', 'DD/MM/YYYY'));


INSERT INTO COMP_PAGOCAB VALUES (1, 201, 'F001-0001', 1, 1, '004', '001', TO_DATE('05/03/2024', 'DD/MM/YYYY'), '002', '001', 1.0000, 45000.00, 45000.00, 8100.00, 53100.00, 'fact_001.jpg', 'voucher_001.jpg', TO_DATE('15/03/2024', 'DD/MM/YYYY'), 'Pago por suministro de acero primera entrega', 1, '006', '001');
INSERT INTO COMP_PAGOCAB VALUES (1, 202, 'F002-0001', 1, 1, '004', '001', TO_DATE('20/02/2024', 'DD/MM/YYYY'), '002', '001', 1.0000, 28000.00, 28000.00, 5040.00, 33040.00, 'fact_002.jpg', 'voucher_002.jpg', TO_DATE('05/03/2024', 'DD/MM/YYYY'), 'Pago por cemento Portland primera entrega', 1, '006', '001');
INSERT INTO COMP_PAGOCAB VALUES (1, 203, 'F003-0001', 1, 1, '004', '001', TO_DATE('01/04/2024', 'DD/MM/YYYY'), '002', '001', 1.0000, 22500.00, 22500.00, 4050.00, 26550.00, 'fact_003.jpg', 'pendiente.jpg', TO_DATE('01/01/1900', 'DD/MM/YYYY'), 'Factura por alquiler de excavadora - Pendiente de pago', 1, '006', '002');
-- Para COMP_PAGOEMPLEADO: CodCIA, CodEmpleado, NroCP, CodPyto, NroPago, TCompPago, ECompPago, FecCP, TMoneda, EMoneda, TipCambio, ImpMO, ImpNetoMN, ImpIGVMN, ImpTotalMn, FotoCP, FotoAbono, FecAbono, DesAbono, Semilla, TabEstado, CodEstado
INSERT INTO COMP_PAGOEMPLEADO VALUES (1, 1, 'E001-0001', 1, 1, '004', '001', TO_DATE('10/03/2024', 'DD/MM/YYYY'), '002', '001', 1.0000, 5000.00, 5000.00, 900.00, 5900.00, 'recibo_empl_001.jpg', 'abono_empl_001.jpg', TO_DATE('15/03/2024', 'DD/MM/YYYY'), 'Pago de sueldo marzo 2024', 1, '006', '001');
INSERT INTO COMP_PAGOEMPLEADO VALUES (1, 2, 'E002-0001', 2, 1, '004', '001', TO_DATE('10/03/2024', 'DD/MM/YYYY'), '002', '001', 1.0000, 4800.00, 4800.00, 864.00, 5664.00, 'recibo_empl_002.jpg', 'abono_empl_002.jpg', TO_DATE('15/03/2024', 'DD/MM/YYYY'), 'Pago de sueldo marzo 2024', 1, '006', '001');
INSERT INTO COMP_PAGOEMPLEADO VALUES (1, 3, 'E003-0001', 1, 1, '004', '001', TO_DATE('10/03/2024', 'DD/MM/YYYY'), '002', '001', 1.0000, 5200.00, 5200.00, 936.00, 6136.00, 'recibo_empl_003.jpg', 'abono_empl_003.jpg', TO_DATE('15/03/2024', 'DD/MM/YYYY'), 'Pago de sueldo marzo 2024', 1, '006', '001');
INSERT INTO COMP_PAGOEMPLEADO VALUES (1, 4, 'E004-0001', 2, 1, '004', '001', TO_DATE('10/03/2024', 'DD/MM/YYYY'), '002', '001', 1.0000, 4700.00, 4700.00, 846.00, 5546.00, 'recibo_empl_004.jpg', 'abono_empl_004.jpg', TO_DATE('15/03/2024', 'DD/MM/YYYY'), 'Pago de sueldo marzo 2024', 1, '006', '001');
-- ============================================================================

COMMIT;

-- Mostrar resumen de datos insertados
SELECT 'DATOS DE EJEMPLO INSERTADOS EXITOSAMENTE' as MENSAJE FROM DUAL;
SELECT 'Empleados: ' || COUNT(*) as TOTAL FROM EMPLEADO;
SELECT 'Proyectos: ' || COUNT(*) as TOTAL FROM PROYECTO;
SELECT 'Proveedores: ' || COUNT(*) as TOTAL FROM PROVEEDOR;
SELECT 'Clientes: ' || COUNT(*) as TOTAL FROM CLIENTE;
SELECT 'Contratos: ' || COUNT(*) as TOTAL FROM contratos_proveedor;

-- ============================================================================
-- FIN DEL SCRIPT DE DATOS
-- ============================================================================