-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema BarSpring
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema BarSpring
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BarSpring` ;
USE `BarSpring` ;

-- -----------------------------------------------------
-- Table `BarSpring`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BarSpring`.`Cliente` (
  `cpf` INT NOT NULL,
  `nome` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`cpf`),
  UNIQUE INDEX `CPF_UNIQUE` (`cpf` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BarSpring`.`Conta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BarSpring`.`Conta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cliente_cpf` INT NOT NULL,
  `gorjeta` DOUBLE NULL,
  `aberta` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idMesa_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_Conta_Cliente_idx` (`cliente_cpf` ASC) VISIBLE,
  UNIQUE INDEX `Cliente_CPF_UNIQUE` (`cliente_cpf` ASC) VISIBLE,
  CONSTRAINT `fk_Conta_Cliente`
    FOREIGN KEY (`cliente_cpf`)
    REFERENCES `BarSpring`.`Cliente` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BarSpring`.`Pagamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BarSpring`.`Pagamento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `conta_id` INT NOT NULL,
  `valor` DOUBLE NOT NULL,
  `autor` VARCHAR(200) NULL,
  `data` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  INDEX `fk_Pagamento_Conta1_idx` (`conta_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Pagamento_Conta1`
    FOREIGN KEY (`conta_id`)
    REFERENCES `BarSpring`.`Conta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BarSpring`.`Item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BarSpring`.`Item` (
  `num_item` INT NOT NULL,
  `tipo` INT NOT NULL,
  `valor` DOUBLE NOT NULL,
  `nome` VARCHAR(200) NOT NULL,
  `disponivel` TINYINT NOT NULL,
  PRIMARY KEY (`num_item`),
  UNIQUE INDEX `numItem_UNIQUE` (`num_item` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BarSpring`.`Consumo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BarSpring`.`Consumo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `conta_id` INT NOT NULL,
  `num_item` INT NOT NULL,
  `quantidade` INT NOT NULL,
  INDEX `fk_Consumo_Item1_idx` (`num_item` ASC) VISIBLE,
  INDEX `fk_Consumo_Conta1_idx` (`conta_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Consumo_Item1`
    FOREIGN KEY (`num_item`)
    REFERENCES `BarSpring`.`Item` (`num_item`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Consumo_Conta1`
    FOREIGN KEY (`conta_id`)
    REFERENCES `BarSpring`.`Conta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
