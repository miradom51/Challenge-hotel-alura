-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `huespedes`
--

DROP TABLE IF EXISTS `huespedes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `huespedes` (
  `id_huesped` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(25) NOT NULL,
  `apellido` varchar(25) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `nacionalidad` varchar(35) NOT NULL,
  `telefono` varchar(30) NOT NULL,
  `id_reserva` varchar(36) DEFAULT NULL,
  `dni` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`id_huesped`),
  KEY `id_reserva` (`id_reserva`),
  CONSTRAINT `huespedes_ibfk_1` FOREIGN KEY (`id_reserva`) REFERENCES `reservas` (`id_reserva`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `huespedes`
--

LOCK TABLES `huespedes` WRITE;
/*!40000 ALTER TABLE `huespedes` DISABLE KEYS */;
INSERT INTO `huespedes` VALUES (3,'Ángelica','Flores Diaz','1987-02-17','Mexican – mexicano','33-33-22-44-33','4798a389-132c-4c79-aa67-8569470dcf4a',NULL),(4,'Francisco','Lopez Rodi','1976-09-23','Nicaraguan – nicaraguense','22-11-22-33-44','b2e8ad3b-a131-4179-ac36-36723d086594',NULL),(5,'Ana','Lopez Bautista','1987-06-27','Guatemalan – guatemalteco','22-33-44-43-33','ce477aea-cb4c-4a12-acad-8a5fa6412369',NULL),(16,'Carlos','Moron','1983-04-09','American – americano','45-45-45-45-45','c78006cc-0a12-4bd8-9d03-7eb041237541',NULL),(17,'Eduardo','Lira','1980-04-16','Argentinian – argentino','45-45-45-45-45','3d47758e-a44b-4ed0-b79f-f306140edff0',NULL),(18,'Camila','Ordoñez','2000-04-26','Argentinian – argentino','65-65-56-65-59','3c5a871a-baf6-4311-80b5-b0f91e54f6b1',NULL),(19,'Jmmm','Lnn','2000-04-13','Australian – australiano','86-86-86-86-97','30d085bf-f059-413d-82eb-8dea221ac07f',NULL);
/*!40000 ALTER TABLE `huespedes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-17 14:21:16
