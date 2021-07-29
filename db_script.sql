-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema s7gieufzb379zhg8
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema s7gieufzb379zhg8
-- -----------------------------------------------------
-- CREATE SCHEMA IF NOT EXISTS `s7gieufzb379zhg8` DEFAULT CHARACTER SET utf8 ;
USE `s7gieufzb379zhg8` ;

-- -----------------------------------------------------
-- Table `s7gieufzb379zhg8`.`pauta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `s7gieufzb379zhg8`.`pauta` ;

CREATE TABLE IF NOT EXISTS `s7gieufzb379zhg8`.`pauta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `dataCriacao` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `s7gieufzb379zhg8`.`sessaoVotacao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `s7gieufzb379zhg8`.`sessaoVotacao` ;

CREATE TABLE IF NOT EXISTS `s7gieufzb379zhg8`.`sessaoVotacao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `aberta` TINYINT NOT NULL DEFAULT 0,
  `dataAbertura` TIMESTAMP NOT NULL,
  `dataFechamento` TIMESTAMP NOT NULL,
  `pauta_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sessaoVotacao_pauta_idx` (`pauta_id` ASC) VISIBLE,
  CONSTRAINT `fk_sessaoVotacao_pauta`
    FOREIGN KEY (`pauta_id`)
    REFERENCES `s7gieufzb379zhg8`.`pauta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `s7gieufzb379zhg8`.`Voto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `s7gieufzb379zhg8`.`Voto` ;

CREATE TABLE IF NOT EXISTS `s7gieufzb379zhg8`.`Voto` (
  `cpf` VARCHAR(11) NOT NULL,
  `voto` ENUM('SIM', 'NÃO') NOT NULL,
  `sessaoVotacao_id` INT NOT NULL,
  PRIMARY KEY (`cpf`),
  INDEX `fk_Voto_sessaoVotacao1_idx` (`sessaoVotacao_id` ASC) VISIBLE,
  CONSTRAINT `fk_Voto_sessaoVotacao1`
    FOREIGN KEY (`sessaoVotacao_id`)
    REFERENCES `s7gieufzb379zhg8`.`sessaoVotacao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
