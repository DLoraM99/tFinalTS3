SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


--CREAR TABLA
CREATE TABLE `marcadores` (
  `codigo` int(11) NOT NULL,
  `puntaje` int(11) NOT NULL,
  `fecha` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `marcadores`
  ADD PRIMARY KEY (`codigo`);

ALTER TABLE `marcadores`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
COMMIT;


--INSERCION DE DATOS
--INSERTAR SOLO SI ES NECESARIO
INSERT INTO `marcadores` (`codigo`, `puntaje`, `fecha`) VALUES
(1, 120, '22/11/2020'),