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
  `stato_candidatura` enum('new','assegnato_hr','assegnato_manager','in_valutazione','assunto','rigettato') NOT NULL DEFAULT 'new',
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
  `note` text,
  `curriculum` longblob COMMENT 'curriculum caricato direttamente da app',
  `supervisore` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`codiceFiscale`),
  KEY `fk_Candidato_utente1_idx` (`supervisore`),
  CONSTRAINT `fk_Candidato_supervisore` FOREIGN KEY (`supervisore`) REFERENCES `user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `comunicativita` enum('insuff','suff','discreto','buono','ottimo') DEFAULT NULL,
  `dinamicita` enum('insuff','suff','discreto','buono','ottimo') DEFAULT NULL,
  `dispSpostamentiTrasferimenti` enum('si','no') DEFAULT NULL,
  `sede_preferita` int DEFAULT NULL,
  `utente_relatore` varchar(50) DEFAULT NULL,
  `candidato_relativo` varchar(16) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_candidato_idx` (`candidato_relativo`),
  KEY `fk_sede_preferita_idx` (`sede_preferita`),
  KEY `fk_utente_relatore_idx` (`utente_relatore`),
  CONSTRAINT `fk_candidato` FOREIGN KEY (`candidato_relativo`) REFERENCES `candidato` (`codiceFiscale`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sede_preferita` FOREIGN KEY (`sede_preferita`) REFERENCES `sede` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_utente_relatore` FOREIGN KEY (`utente_relatore`) REFERENCES `user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sede`
--

DROP TABLE IF EXISTS `sede`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sede` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name_city` varchar(45) NOT NULL,
  `via` varchar(55) NOT NULL,
  `cap` int NOT NULL,
  `civic_number` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `numeroTel` char(12) DEFAULT NULL,
  `email` varchar(55) NOT NULL,
  `sedeAssegnamento` int DEFAULT NULL,
  `qualified` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`username`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `fk_sedeAssegnamento_idx` (`sedeAssegnamento`),
  CONSTRAINT `fk_sedeAssegnamento` FOREIGN KEY (`sedeAssegnamento`) REFERENCES `sede` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_username` varchar(50) NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_username`,`role_id`),
  KEY `FK_id_ruolo_idx` (`role_id`),
  CONSTRAINT `FK_ROLE_ID` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_USER_USERNAME` FOREIGN KEY (`user_username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'gestione_candidature'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-02  8:38:43
