CREATE DATABASE  IF NOT EXISTS `gestione_candidature` /*!40100 DEFAULT CHARACTER SET latin1 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gestione_candidature`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: gestione_candidature
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `candidato`
--

DROP TABLE IF EXISTS `candidato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidato` (
  `codiceFiscale` varchar(16) NOT NULL,
  `sato_candidatura` enum('new','assegnato_hr','assegnato_manager','in_valutazione','assunto','rigettato') NOT NULL DEFAULT 'new',
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `telefono` varchar(12) NOT NULL,
  `email` varchar(55) NOT NULL,
  `dataNascita` date NOT NULL,
  `tipoContratto` enum('tempo_indeterminato','tempo_determinato','somministrazione','a_chiamata','a_progetto','accessorio','apprendistato','tirocinio_formativo_e_orientamento','part-time','not_set') NOT NULL DEFAULT 'not_set',
  `ral` float NOT NULL DEFAULT '0',
  `tempo_preavviso_giorni` int NOT NULL DEFAULT '10' COMMENT 'Numero di giorni preavviso',
  `tipoOfferta` enum('lavoro','stage','candidatura_spontanea') NOT NULL,
  `canale_provenienza` varchar(150) NOT NULL,
  `aspettative` varchar(100) DEFAULT NULL,
  `note` mediumtext,
  `curriculum` blob NOT NULL COMMENT 'curriculum o caricato direttamente o si potrebbe salvare il percorso del file',
  `supervisore` varchar(45) NOT NULL,
  PRIMARY KEY (`codiceFiscale`),
  KEY `fk_Candidato_utente1_idx` (`supervisore`),
  CONSTRAINT `fk_Candidato_utente1` FOREIGN KEY (`supervisore`) REFERENCES `user` (`username`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidato`
--

LOCK TABLES `candidato` WRITE;
/*!40000 ALTER TABLE `candidato` DISABLE KEYS */;
/*!40000 ALTER TABLE `candidato` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-02 10:41:19
