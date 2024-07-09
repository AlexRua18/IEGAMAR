-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-06-2016 a las 23:05:31
-- Versión del servidor: 10.1.10-MariaDB
-- Versión de PHP: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `iegamar`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `control_llegada_tarde`
--

CREATE TABLE `control_llegada_tarde` (
  `Id_llegada_tarde` int(11) NOT NULL,
  `Fecha` varchar(255) DEFAULT NULL,
  `Hora` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_cuentas_usuario`
--

CREATE TABLE `tbl_cuentas_usuario` (
  `Id_cuenta` int(11) NOT NULL,
  `Login` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Tipo_rol` varchar(255) DEFAULT NULL,
  `Estado` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tbl_cuentas_usuario`
--

INSERT INTO `tbl_cuentas_usuario` (`Id_cuenta`, `Login`, `Password`, `Tipo_rol`, `Estado`) VALUES
(0, 'Admin', 'iegamar', 'Super', 'Habilitado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_dtlls_controlxempleado`
--

CREATE TABLE `tbl_dtlls_controlxempleado` (
  `identificacion` varchar(255) DEFAULT NULL,
  `Id_llegada_tarde` int(11) DEFAULT NULL,
  `Estado` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_dtlls_controlxestudiante`
--

CREATE TABLE `tbl_dtlls_controlxestudiante` (
  `identificacion` varchar(255) DEFAULT NULL,
  `Id_llegada_tarde` int(11) DEFAULT NULL,
  `Estado` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_dtlls_cunetaxprestamo`
--

CREATE TABLE `tbl_dtlls_cunetaxprestamo` (
  `Id_cuenta` int(11) DEFAULT NULL,
  `Id_prestamo` int(11) DEFAULT NULL,
  `Tipo` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_dtlls_empleadosxcuentas`
--

CREATE TABLE `tbl_dtlls_empleadosxcuentas` (
  `Identificacion` varchar(11) DEFAULT NULL,
  `Id_cuenta` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_dtlls_empleadxprestamo`
--

CREATE TABLE `tbl_dtlls_empleadxprestamo` (
  `Identificacion` varchar(11) DEFAULT NULL,
  `Id_prestamo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_dtlls_estudiantesxcuentas`
--

CREATE TABLE `tbl_dtlls_estudiantesxcuentas` (
  `Identificacion` varchar(40) DEFAULT NULL,
  `Id_cuenta` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_dtlls_estudiantexprestamo`
--

CREATE TABLE `tbl_dtlls_estudiantexprestamo` (
  `Identificacion` varchar(225) DEFAULT NULL,
  `Id_prestamo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_dtlls_movimiento`
--

CREATE TABLE `tbl_dtlls_movimiento` (
  `Id_movimientos` int(11) DEFAULT NULL,
  `Seriales` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_dtlls_prestamo`
--

CREATE TABLE `tbl_dtlls_prestamo` (
  `Id_prestamo` int(11) NOT NULL,
  `Seriales` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_dtlls_reserva`
--

CREATE TABLE `tbl_dtlls_reserva` (
  `Id_reserva` int(11) NOT NULL,
  `Seriales` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_elementos`
--

CREATE TABLE `tbl_elementos` (
  `Codigo` int(11) NOT NULL,
  `Nombre_elemento` varchar(100) DEFAULT NULL,
  `Descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_empleado`
--

CREATE TABLE `tbl_empleado` (
  `Identificacion` varchar(11) NOT NULL,
  `Nombre` varchar(255) DEFAULT NULL,
  `Apellido` varchar(255) DEFAULT NULL,
  `Direccion` varchar(255) DEFAULT NULL,
  `Telefono` varchar(255) DEFAULT NULL,
  `Foto` varchar(100) DEFAULT NULL,
  `Fecha_nacimiento` varchar(255) DEFAULT NULL,
  `Estado` varchar(255) DEFAULT NULL,
  `Perfil_profesional` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_estudiante`
--

CREATE TABLE `tbl_estudiante` (
  `Identificacion` varchar(40) NOT NULL,
  `Nombre` varchar(255) DEFAULT NULL,
  `Apellido` varchar(255) DEFAULT NULL,
  `Direccion` varchar(255) DEFAULT NULL,
  `Telefono` varchar(255) DEFAULT NULL,
  `Foto` varchar(100) DEFAULT NULL,
  `Fecha` varchar(255) DEFAULT NULL,
  `Id_grado` int(11) DEFAULT NULL,
  `Estado` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_grado`
--

CREATE TABLE `tbl_grado` (
  `Id_grado` int(11) NOT NULL,
  `Grado` varchar(255) DEFAULT NULL,
  `Estado` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_movimiento_elementos`
--

CREATE TABLE `tbl_movimiento_elementos` (
  `Id_movimiento` int(11) NOT NULL,
  `Tipo_movimiento` varchar(255) DEFAULT NULL,
  `Fecha` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_prestamo`
--

CREATE TABLE `tbl_prestamo` (
  `Id_prestamo` int(11) NOT NULL,
  `Fecha_prestamo` varchar(255) DEFAULT NULL,
  `Hora_prestamo` varchar(255) DEFAULT NULL,
  `Fecha_devolucion` varchar(255) DEFAULT NULL,
  `Hora_devolucion` varchar(255) DEFAULT NULL,
  `Tipo` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_reserva`
--

CREATE TABLE `tbl_reserva` (
  `Id_reserva` int(11) NOT NULL,
  `Fecha_reserva` varchar(255) DEFAULT NULL,
  `Hora_reserva` varchar(255) DEFAULT NULL,
  `Fecha_entrega` varchar(255) DEFAULT NULL,
  `Estado` varchar(255) DEFAULT NULL,
  `Identificacion` varchar(11) DEFAULT NULL,
  `Observaciones` varchar(255) DEFAULT NULL,
  `Id_cuenta_reserva` varchar(255) DEFAULT NULL,
  `Notificacion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_seriales`
--

CREATE TABLE `tbl_seriales` (
  `Seriales` varchar(40) NOT NULL,
  `Estado` varchar(255) DEFAULT NULL,
  `Codigo` int(11) DEFAULT NULL,
  `Anomalia` varchar(255) DEFAULT NULL,
  `Estado_anomalia` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `control_llegada_tarde`
--
ALTER TABLE `control_llegada_tarde`
  ADD PRIMARY KEY (`Id_llegada_tarde`);

--
-- Indices de la tabla `tbl_cuentas_usuario`
--
ALTER TABLE `tbl_cuentas_usuario`
  ADD PRIMARY KEY (`Id_cuenta`),
  ADD UNIQUE KEY `Login` (`Login`);

--
-- Indices de la tabla `tbl_dtlls_controlxempleado`
--
ALTER TABLE `tbl_dtlls_controlxempleado`
  ADD KEY `Id_llegada_tarde` (`Id_llegada_tarde`),
  ADD KEY `forein1` (`identificacion`);

--
-- Indices de la tabla `tbl_dtlls_controlxestudiante`
--
ALTER TABLE `tbl_dtlls_controlxestudiante`
  ADD KEY `for_estxcontrol1` (`Id_llegada_tarde`),
  ADD KEY `for_estxcontrol2` (`identificacion`);

--
-- Indices de la tabla `tbl_dtlls_cunetaxprestamo`
--
ALTER TABLE `tbl_dtlls_cunetaxprestamo`
  ADD KEY `tbl_dtlls_estudiente*prestamo` (`Id_cuenta`),
  ADD KEY `Id_prestamo` (`Id_prestamo`);

--
-- Indices de la tabla `tbl_dtlls_empleadosxcuentas`
--
ALTER TABLE `tbl_dtlls_empleadosxcuentas`
  ADD KEY `tbl_dtlls_empleados*cuentas` (`Identificacion`),
  ADD KEY `Id_cuenta` (`Id_cuenta`);

--
-- Indices de la tabla `tbl_dtlls_empleadxprestamo`
--
ALTER TABLE `tbl_dtlls_empleadxprestamo`
  ADD KEY `tbl_dtlls_empleado*prestamo` (`Identificacion`),
  ADD KEY `Id_prestamo` (`Id_prestamo`);

--
-- Indices de la tabla `tbl_dtlls_estudiantesxcuentas`
--
ALTER TABLE `tbl_dtlls_estudiantesxcuentas`
  ADD KEY `tbl_dtlls_estudiantes*cuentas` (`Id_cuenta`),
  ADD KEY `Identificacion` (`Identificacion`);

--
-- Indices de la tabla `tbl_dtlls_estudiantexprestamo`
--
ALTER TABLE `tbl_dtlls_estudiantexprestamo`
  ADD KEY `tbl_dtlls_empleado*prestamo` (`Identificacion`),
  ADD KEY `Id_prestamo` (`Id_prestamo`);

--
-- Indices de la tabla `tbl_dtlls_movimiento`
--
ALTER TABLE `tbl_dtlls_movimiento`
  ADD KEY `movimiento` (`Id_movimientos`),
  ADD KEY `Seriales` (`Seriales`);

--
-- Indices de la tabla `tbl_dtlls_prestamo`
--
ALTER TABLE `tbl_dtlls_prestamo`
  ADD KEY `Id_prestamo` (`Id_prestamo`),
  ADD KEY `Seriales` (`Seriales`);

--
-- Indices de la tabla `tbl_dtlls_reserva`
--
ALTER TABLE `tbl_dtlls_reserva`
  ADD KEY `Id_reserva` (`Id_reserva`),
  ADD KEY `Seriales` (`Seriales`);

--
-- Indices de la tabla `tbl_elementos`
--
ALTER TABLE `tbl_elementos`
  ADD PRIMARY KEY (`Codigo`);

--
-- Indices de la tabla `tbl_empleado`
--
ALTER TABLE `tbl_empleado`
  ADD PRIMARY KEY (`Identificacion`),
  ADD UNIQUE KEY `IDFOTO` (`Foto`);

--
-- Indices de la tabla `tbl_estudiante`
--
ALTER TABLE `tbl_estudiante`
  ADD PRIMARY KEY (`Identificacion`),
  ADD UNIQUE KEY `IDOC` (`Foto`),
  ADD KEY `tbl_estudiante` (`Id_grado`);

--
-- Indices de la tabla `tbl_grado`
--
ALTER TABLE `tbl_grado`
  ADD PRIMARY KEY (`Id_grado`),
  ADD UNIQUE KEY `UQ_Grado` (`Grado`);

--
-- Indices de la tabla `tbl_movimiento_elementos`
--
ALTER TABLE `tbl_movimiento_elementos`
  ADD PRIMARY KEY (`Id_movimiento`);

--
-- Indices de la tabla `tbl_prestamo`
--
ALTER TABLE `tbl_prestamo`
  ADD PRIMARY KEY (`Id_prestamo`);

--
-- Indices de la tabla `tbl_reserva`
--
ALTER TABLE `tbl_reserva`
  ADD PRIMARY KEY (`Id_reserva`),
  ADD KEY `Identificacion` (`Identificacion`),
  ADD KEY `Id_cuenta_reserva` (`Id_cuenta_reserva`);

--
-- Indices de la tabla `tbl_seriales`
--
ALTER TABLE `tbl_seriales`
  ADD PRIMARY KEY (`Seriales`),
  ADD KEY `Codigo` (`Codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `control_llegada_tarde`
--
ALTER TABLE `control_llegada_tarde`
  MODIFY `Id_llegada_tarde` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `tbl_cuentas_usuario`
--
ALTER TABLE `tbl_cuentas_usuario`
  MODIFY `Id_cuenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de la tabla `tbl_elementos`
--
ALTER TABLE `tbl_elementos`
  MODIFY `Codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `tbl_grado`
--
ALTER TABLE `tbl_grado`
  MODIFY `Id_grado` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `tbl_prestamo`
--
ALTER TABLE `tbl_prestamo`
  MODIFY `Id_prestamo` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `tbl_reserva`
--
ALTER TABLE `tbl_reserva`
  MODIFY `Id_reserva` int(11) NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tbl_dtlls_controlxempleado`
--
ALTER TABLE `tbl_dtlls_controlxempleado`
  ADD CONSTRAINT `fer` FOREIGN KEY (`Id_llegada_tarde`) REFERENCES `control_llegada_tarde` (`Id_llegada_tarde`),
  ADD CONSTRAINT `forein1` FOREIGN KEY (`identificacion`) REFERENCES `tbl_empleado` (`Identificacion`);

--
-- Filtros para la tabla `tbl_dtlls_controlxestudiante`
--
ALTER TABLE `tbl_dtlls_controlxestudiante`
  ADD CONSTRAINT `for_estxcontrol1` FOREIGN KEY (`Id_llegada_tarde`) REFERENCES `control_llegada_tarde` (`Id_llegada_tarde`),
  ADD CONSTRAINT `for_estxcontrol2` FOREIGN KEY (`identificacion`) REFERENCES `tbl_estudiante` (`Identificacion`);

--
-- Filtros para la tabla `tbl_dtlls_cunetaxprestamo`
--
ALTER TABLE `tbl_dtlls_cunetaxprestamo`
  ADD CONSTRAINT `tbl_dtlls_cunetaxprestamo_ibfk_1` FOREIGN KEY (`Id_cuenta`) REFERENCES `tbl_cuentas_usuario` (`Id_cuenta`),
  ADD CONSTRAINT `tbl_dtlls_cunetaxprestamo_ibfk_2` FOREIGN KEY (`Id_prestamo`) REFERENCES `tbl_prestamo` (`Id_prestamo`);

--
-- Filtros para la tabla `tbl_dtlls_empleadosxcuentas`
--
ALTER TABLE `tbl_dtlls_empleadosxcuentas`
  ADD CONSTRAINT `aa` FOREIGN KEY (`Identificacion`) REFERENCES `tbl_empleado` (`Identificacion`),
  ADD CONSTRAINT `tbl_dtlls_empleadosxcuentas_ibfk_1` FOREIGN KEY (`Id_cuenta`) REFERENCES `tbl_cuentas_usuario` (`Id_cuenta`);

--
-- Filtros para la tabla `tbl_dtlls_empleadxprestamo`
--
ALTER TABLE `tbl_dtlls_empleadxprestamo`
  ADD CONSTRAINT `tbl_dtlls_empleadxprestamo_ibfk_1` FOREIGN KEY (`Identificacion`) REFERENCES `tbl_empleado` (`Identificacion`),
  ADD CONSTRAINT `tbl_dtlls_empleadxprestamo_ibfk_2` FOREIGN KEY (`Id_prestamo`) REFERENCES `tbl_prestamo` (`Id_prestamo`);

--
-- Filtros para la tabla `tbl_dtlls_estudiantesxcuentas`
--
ALTER TABLE `tbl_dtlls_estudiantesxcuentas`
  ADD CONSTRAINT `tbl_dtlls_estudiantes*cuentas` FOREIGN KEY (`Identificacion`) REFERENCES `tbl_estudiante` (`Identificacion`),
  ADD CONSTRAINT `tbl_dtlls_estudiantesxcuentas_ibfk_1` FOREIGN KEY (`Id_cuenta`) REFERENCES `tbl_cuentas_usuario` (`Id_cuenta`);

--
-- Filtros para la tabla `tbl_dtlls_estudiantexprestamo`
--
ALTER TABLE `tbl_dtlls_estudiantexprestamo`
  ADD CONSTRAINT `tbl_dtlls_estudiantexprestamo_ibfk_1` FOREIGN KEY (`Identificacion`) REFERENCES `tbl_estudiante` (`Identificacion`),
  ADD CONSTRAINT `tbl_dtlls_estudiantexprestamo_ibfk_2` FOREIGN KEY (`Id_prestamo`) REFERENCES `tbl_prestamo` (`Id_prestamo`);

--
-- Filtros para la tabla `tbl_dtlls_movimiento`
--
ALTER TABLE `tbl_dtlls_movimiento`
  ADD CONSTRAINT `movimiento` FOREIGN KEY (`Id_movimientos`) REFERENCES `tbl_movimiento_elementos` (`Id_movimiento`),
  ADD CONSTRAINT `tbl_dtlls_movimiento_ibfk_1` FOREIGN KEY (`Seriales`) REFERENCES `tbl_seriales` (`Seriales`);

--
-- Filtros para la tabla `tbl_dtlls_prestamo`
--
ALTER TABLE `tbl_dtlls_prestamo`
  ADD CONSTRAINT `tbl_dtlls_prestamo_ibfk_3` FOREIGN KEY (`Seriales`) REFERENCES `tbl_seriales` (`Seriales`),
  ADD CONSTRAINT `tbl_dtlls_prestamo_ibfk_4` FOREIGN KEY (`Id_prestamo`) REFERENCES `tbl_prestamo` (`Id_prestamo`);

--
-- Filtros para la tabla `tbl_dtlls_reserva`
--
ALTER TABLE `tbl_dtlls_reserva`
  ADD CONSTRAINT `tbl_dtlls_reserva_ibfk_1` FOREIGN KEY (`Seriales`) REFERENCES `tbl_seriales` (`Seriales`),
  ADD CONSTRAINT `tbl_dtlls_reserva_ibfk_2` FOREIGN KEY (`Id_reserva`) REFERENCES `tbl_reserva` (`Id_reserva`);

--
-- Filtros para la tabla `tbl_estudiante`
--
ALTER TABLE `tbl_estudiante`
  ADD CONSTRAINT `tbl_estudiante` FOREIGN KEY (`Id_grado`) REFERENCES `tbl_grado` (`Id_grado`);

--
-- Filtros para la tabla `tbl_reserva`
--
ALTER TABLE `tbl_reserva`
  ADD CONSTRAINT `tbl_reserva_ibfk_1` FOREIGN KEY (`Identificacion`) REFERENCES `tbl_empleado` (`Identificacion`);

--
-- Filtros para la tabla `tbl_seriales`
--
ALTER TABLE `tbl_seriales`
  ADD CONSTRAINT `tbl_seriales_ibfk_1` FOREIGN KEY (`Codigo`) REFERENCES `tbl_elementos` (`Codigo`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
