-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gestione_candidature
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
  `note` text,
  `curriculum` blob NOT NULL COMMENT 'curriculum o caricato direttamente o si potrebbe salvare il percorso del file',
  `supervisore` int NOT NULL,
  PRIMARY KEY (`codiceFiscale`),
  KEY `fk_Candidato_utente1_idx` (`supervisore`),
  CONSTRAINT `fk_Candidato_utente1` FOREIGN KEY (`supervisore`) REFERENCES `user` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidato`
--

LOCK TABLES `candidato` WRITE;
/*!40000 ALTER TABLE `candidato` DISABLE KEYS */;
/*!40000 ALTER TABLE `candidato` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_HR'),(3,'ROLE_MANAGER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

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
  `utente_relatore` int NOT NULL,
  `candidato_relativo` varchar(16) NOT NULL,
  KEY `fk_candidato_idx` (`candidato_relativo`),
  KEY `fk_sede_preferita_idx` (`sede_preferita`),
  KEY `fk_utente_relatore_idx` (`utente_relatore`),
  CONSTRAINT `fk_candidato` FOREIGN KEY (`candidato_relativo`) REFERENCES `candidato` (`codiceFiscale`) ON UPDATE CASCADE,
  CONSTRAINT `fk_sede_preferita` FOREIGN KEY (`sede_preferita`) REFERENCES `sede` (`id`),
  CONSTRAINT `fk_utente_relatore` FOREIGN KEY (`utente_relatore`) REFERENCES `user` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedavalutazione`
--

LOCK TABLES `schedavalutazione` WRITE;
/*!40000 ALTER TABLE `schedavalutazione` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedavalutazione` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `sede`
--

LOCK TABLES `sede` WRITE;
/*!40000 ALTER TABLE `sede` DISABLE KEYS */;
INSERT INTO `sede` VALUES (3,'Napoli','Via G. Porzio',80143,8),(4,'Roma','Largo Carlo Salinari',142,18),(5,'Padova','Galleria Spagna',35127,28),(6,'Verona','Via Albere',37138,19),(7,'Milano','Via Giovanni Durando',20158,38);
/*!40000 ALTER TABLE `sede` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `numeroTel` char(12) DEFAULT NULL,
  `email` varchar(55) NOT NULL,
  `sedeAssegnamento` int DEFAULT NULL,
  `qualified` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `fk_sedeAssegnamento_idx` (`sedeAssegnamento`),
  CONSTRAINT `fk_sedeAssegnamento` FOREIGN KEY (`sedeAssegnamento`) REFERENCES `sede` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (8,'admin','$2a$10$gRrRBwskYrqBFX5eXIPpFOLSYmilkR9cLw9BXhumXuDo97tO6jp72','admin','admin','1234567890','admin@gmail.com',NULL,1),(9,'hr','$2a$10$kPRAotY9YY7CBwjyLC9Mru5OX3EjZdg1IhaBYU7tHdVYzsJF5iM.K','hr','hr','1234567890','hr@gmail.com',NULL,1),(10,'manager','$2a$10$lckRmWVTvOfSNUYv4V/v.ubRhlIMQEnTsMFFkOVuPH39UQMjEOVAW','manager','manager','1234567890','manager@gmail.com',NULL,1),(11,'test','$2a$10$gYSU55usADM4apYxJOXhZ.Afsd4dU4xzlTGpnsGlAbxNUgal1vywK','manager','test','12345678980','test@gmail.com',NULL,1),(12,'sede','$2a$10$Db49Wn9Da.p16H04ttdQ9.NBT3NF6SAN/J9W4ISD2mlDfAfC6g1Yi','sede','sede','1234567890','sede@gmail.com',NULL,1),(13,'sede2','$2a$10$Ct0ms96jMSxdY2sZCTmKdOw6c.cCB.dyBSLi73saUHgzFi5Dlp.ku','sede2','sede2','1234567890','sede2@gmail.com',4,1),(14,'test2','$2a$10$.GMSZLQCm.dB.mtb/h0aE.h/v/LPnpXla9wvoj/EoaLG6Qcx/nb.q','test2','test2','1234567890','test2@gmail.com',5,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_id_ruolo_idx` (`role_id`),
  CONSTRAINT `FK_ROLE_ID` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_USER_ID` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (8,1),(14,1),(9,2),(10,3),(11,3),(12,3),(13,3);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;

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

-- Dump completed on 2020-07-06 13:06:55
