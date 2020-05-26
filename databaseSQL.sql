-- MySQL Script generated by MySQL Workbench
-- Thu Apr 30 10:50:47 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema candidature
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `candidature` ;

-- -----------------------------------------------------
-- Schema candidature
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `candidature` DEFAULT CHARACTER SET utf8 ;
USE `candidature` ;

-- -----------------------------------------------------
-- Table `candidature`.`sede`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `candidature`.`sede` ;

CREATE TABLE IF NOT EXISTS `candidature`.`sede` (
  `id_città` INT NOT NULL AUTO_INCREMENT,
  `nomeCittà` VARCHAR(45) NOT NULL,
  `via` VARCHAR(55) NOT NULL,
  `cap` INT(5) NOT NULL,
  `numeroCivico` INT(3) NOT NULL,
  PRIMARY KEY (`id_città`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `candidature`.`utente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `candidature`.`utente` ;

CREATE TABLE IF NOT EXISTS `candidature`.`utente` (
  `username` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `cognome` VARCHAR(45) NOT NULL,
  `numeroTel` CHAR(12) NOT NULL,
  `mail` VARCHAR(55) NOT NULL,
  `ruolo` ENUM('hr', 'manager', 'admin') NOT NULL DEFAULT 'not_set',
  `sedeAssegnamento` INT NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `NumeroTel_UNIQUE` (`numeroTel` ASC) VISIBLE,
  INDEX `fk_sedeAssegnamento_idx` (`sedeAssegnamento` ASC) VISIBLE,
  CONSTRAINT `fk_sedeAssegnamento`
    FOREIGN KEY (`sedeAssegnamento`)
    REFERENCES `candidature`.`sede` (`id_città`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `candidature`.`Candidato`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `candidature`.`Candidato` ;

CREATE TABLE IF NOT EXISTS `candidature`.`Candidato` (
  `codiceFiscale` VARCHAR(16) NOT NULL,
  `sato_candidatura` ENUM('new', 'assegnato_hr', 'assegnato_manager', 'in_valutazione', 'assunto', 'rigettato') NOT NULL DEFAULT 'new',
  `nome` VARCHAR(45) NOT NULL,
  `cognome` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(12) NOT NULL,
  `email` VARCHAR(55) NOT NULL,
  `dataNascita` DATE NOT NULL,
  `tipoContratto` ENUM('tempo_indeterminato', 'tempo_determinato', 'somministrazione', 'a_chiamata', 'a_progetto', 'accessorio', 'apprendistato', 'tirocinio_formativo_e_orientamento', 'part-time') NOT NULL DEFAULT 'not_set',
  `ral` FLOAT NOT NULL DEFAULT 0,
  `tempo_preavviso_giorni` INT NOT NULL DEFAULT 10 COMMENT 'Numero di giorni preavviso',
  `tipoOfferta` ENUM('lavoro', 'stage', 'candidatura_spontanea') NOT NULL,
  `canale_provenienza` VARCHAR(150) NOT NULL,
  `aspettative` VARCHAR(100) NULL,
  `note` MEDIUMTEXT NULL,
  `curriculum` BLOB NOT NULL COMMENT 'curriculum o caricato direttamente o si potrebbe salvare il percorso del file',
  `supervisore` VARCHAR(45) NOT NULL,
  INDEX `fk_Candidato_utente1_idx` (`supervisore` ASC) VISIBLE,
  PRIMARY KEY (`codiceFiscale`),
  CONSTRAINT `fk_Candidato_utente1`
    FOREIGN KEY (`supervisore`)
    REFERENCES `candidature`.`utente` (`username`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `candidature`.`schedaValutazione`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `candidature`.`schedaValutazione` ;

CREATE TABLE IF NOT EXISTS `candidature`.`schedaValutazione` (
  `dataColloquio` DATE NULL,
  `CVAllegato` ENUM('si', 'no') NULL DEFAULT 'no',
  `periodoPreavviso` INT NULL DEFAULT 10,
  `inquadramentoRichiesto` VARCHAR(75) NULL,
  `inquadramentoAttuale` VARCHAR(75) NULL,
  `retribuzioneRichiesta` FLOAT NULL,
  `retribuzioneAttuale` FLOAT NULL DEFAULT 0,
  `note` MEDIUMTEXT NULL,
  `competenza` VARCHAR(75) NULL,
  `lingue` VARCHAR(200) NULL,
  `noteSpostamenti` VARCHAR(145) NULL,
  `motivazioneProfessionale` VARCHAR(145) NULL,
  `motivazioneCambiamento` VARCHAR(145) NULL,
  `esperienzeGenerali` VARCHAR(245) NULL,
  `esperienzePosizione` VARCHAR(245) NULL,
  `presenza` ENUM('insuff', 'suff', 'discreto', 'buono', 'ottimo') NULL,
  `comunicatività` ENUM('insuff', 'suff', 'discreto', 'buono', 'ottimo') NULL,
  `dinamicità` ENUM('insuff', 'suff', 'discreto', 'buono', 'ottimo') NULL,
  `disponibilitàSpostamentiTrasferimenti` ENUM('si', 'no') NULL,
  `sede_preferita` INT NULL,
  `utente_relatore` VARCHAR(45) NOT NULL,
  `candidato_relativo` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`utente_relatore`, `candidato_relativo`),
  INDEX `fk_candidato_idx` (`candidato_relativo` ASC) VISIBLE,
  CONSTRAINT `fk_utente_relatore`
    FOREIGN KEY (`utente_relatore`)
    REFERENCES `candidature`.`utente` (`username`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_candidato`
    FOREIGN KEY (`candidato_relativo`)
    REFERENCES `candidature`.`Candidato` (`codiceFiscale`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;