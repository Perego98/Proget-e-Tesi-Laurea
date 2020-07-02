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
-- Table structure for table `schedavalutazione`
--

DROP TABLE IF EXISTS `schedavalutazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedavalutazione` (
  `dataColloquio` date DEFAULT NULL,
  `CVAllegato` enum('si','no') DEFAULT 'no',
  `periodoPreavviso` int DEFAULT '10',
  `inquadramentoRichiesto` varchar(75) DEFAULT NULL,
  `inquadramentoAttuale` varchar(75) DEFAULT NULL,
  `retribuzioneRichiesta` float DEFAULT NULL,
  `retribuzioneAttuale` float DEFAULT '0',
  `note` mediumtext,
  `competenza` varchar(75) DEFAULT NULL,
  `lingue` varchar(200) DEFAULT NULL,
  `noteSpostamenti` varchar(145) DEFAULT NULL,
  `motivazioneProfessionale` varchar(145) DEFAULT NULL,
  `motivazioneCambiamento` varchar(145) DEFAULT NULL,
  `esperienzeGenerali` varchar(245) DEFAULT NULL,
  `esperienzePosizione` varchar(245) DEFAULT NULL,
  `presenza` enum('insuff','suff','discreto','buono','ottimo') DEFAULT NULL,
  `comunicatività` enum('insuff','suff','discreto','buono','ottimo') DEFAULT NULL,
  `dinamicità` enum('insuff','suff','discreto','buono','ottimo') DEFAULT NULL,
  `disponibilitàSpostamentiTrasferimenti` enum('si','no') DEFAULT NULL,
  `sede_preferita` int DEFAULT NULL,
  `utente_relatore` varchar(45) NOT NULL,
  `candidato_relativo` varchar(16) NOT NULL,
  PRIMARY KEY (`utente_relatore`,`candidato_relativo`),
  KEY `fk_candidato_idx` (`candidato_relativo`),
  CONSTRAINT `fk_candidato` FOREIGN KEY (`candidato_relativo`) REFERENCES `candidato` (`codiceFiscale`) ON UPDATE CASCADE,
  CONSTRAINT `fk_utente_relatore` FOREIGN KEY (`utente_relatore`) REFERENCES `user` (`username`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedavalutazione`
--

LOCK TABLES `schedavalutazione` WRITE;
/*!40000 ALTER TABLE `schedavalutazione` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedavalutazione` ENABLE KEYS */;
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
