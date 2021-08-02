-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema s7gieufzb379zhg8
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema s7gieufzb379zhg8
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `s7gieufzb379zhg8` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `s7gieufzb379zhg8` ;

-- -----------------------------------------------------
-- Table `s7gieufzb379zhg8`.`pauta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `s7gieufzb379zhg8`.`pauta` ;

CREATE TABLE IF NOT EXISTS `s7gieufzb379zhg8`.`pauta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `dataCriacao` TIMESTAMP NOT NULL,
  `dataAbertura` TIMESTAMP NULL DEFAULT NULL,
  `dataFechamento` TIMESTAMP NULL DEFAULT NULL,
  `qtdVotosNao` INT NULL DEFAULT NULL,
  `qtdVotosSim` INT NULL DEFAULT NULL,
  `resultado` VARCHAR(6) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `s7gieufzb379zhg8`.`voto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `s7gieufzb379zhg8`.`voto` ;

CREATE TABLE IF NOT EXISTS `s7gieufzb379zhg8`.`voto` (
  `cpf` VARCHAR(11) NOT NULL,
  `voto` ENUM('SIM', 'NÃO') NOT NULL,
  `pauta_id` INT NOT NULL,
  PRIMARY KEY USING BTREE (`cpf`, `pauta_id`),
  INDEX `fk_Voto_pauta_idx` (`pauta_id` ASC) VISIBLE,
  CONSTRAINT `fk_Voto_pauta`
    FOREIGN KEY (`pauta_id`)
    REFERENCES `s7gieufzb379zhg8`.`pauta` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
