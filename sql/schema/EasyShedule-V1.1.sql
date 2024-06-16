-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: db_easyshedule
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `locacaoquadra`
--

DROP TABLE IF EXISTS `locacaoquadra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locacaoquadra` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_QuadraEsportiva` int NOT NULL,
  `id_Locatario` int NOT NULL,
  `dataHorarioInicio` datetime NOT NULL,
  `dataHorarioFim` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_quadraEsportiva_locacaoQuadra_idx` (`id_QuadraEsportiva`),
  KEY `id_locatario_locacaoQuadra_idx` (`id_Locatario`),
  CONSTRAINT `id_locatario_locacaoQuadra` FOREIGN KEY (`id_Locatario`) REFERENCES `locatario` (`id`),
  CONSTRAINT `id_quadraEsportiva_locacaoQuadra` FOREIGN KEY (`id_QuadraEsportiva`) REFERENCES `quadraesportiva` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `locador`
--

DROP TABLE IF EXISTS `locador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locador` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_Usuario` int NOT NULL,
  `CNPJ` char(14) NOT NULL,
  `nQuadras` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `CNPJ_UNIQUE` (`CNPJ`),
  KEY `id_usuario_locador_idx` (`id_Usuario`),
  CONSTRAINT `id_usuario_locador` FOREIGN KEY (`id_Usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `locatario`
--

DROP TABLE IF EXISTS `locatario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locatario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_Usuario` int NOT NULL,
  `CPF` char(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `CPF_UNIQUE` (`CPF`),
  KEY `id_usuario_locatario_idx` (`id_Usuario`),
  CONSTRAINT `id_usuario_locatario` FOREIGN KEY (`id_Usuario`) REFERENCES `usuario` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `quadraesportiva`
--

DROP TABLE IF EXISTS `quadraesportiva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quadraesportiva` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `precoPorHora` double NOT NULL,
  `disponivel` tinyint DEFAULT NULL,
  `id_Locador` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_locador_quadraEsportiva_idx` (`id_Locador`),
  CONSTRAINT `id_locador_quadraEsportiva` FOREIGN KEY (`id_Locador`) REFERENCES `locador` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `tipoUsuario` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-16 13:54:24
