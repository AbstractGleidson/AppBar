-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
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
  `CPF` INT NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`CPF`),
  UNIQUE INDEX `CPF_UNIQUE` (`CPF` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BarSpring`.`Conta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BarSpring`.`Conta` (
  `idConta` INT NOT NULL AUTO_INCREMENT,
  `Cliente_CPF` INT NOT NULL,
  `gorjeta` DOUBLE NULL,
  PRIMARY KEY (`idConta`),
  UNIQUE INDEX `idMesa_UNIQUE` (`idConta` ASC) VISIBLE,
  INDEX `fk_Conta_Cliente_idx` (`Cliente_CPF` ASC) VISIBLE,
  UNIQUE INDEX `Cliente_CPF_UNIQUE` (`Cliente_CPF` ASC) VISIBLE,
  CONSTRAINT `fk_Conta_Cliente`
    FOREIGN KEY (`Cliente_CPF`)
    REFERENCES `BarSpring`.`Cliente` (`CPF`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BarSpring`.`Pagamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BarSpring`.`Pagamento` (
  `Conta_idConta` INT NOT NULL,
  `autor` VARCHAR(100) NULL,
  `valor` DOUBLE NOT NULL,
  `data` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  INDEX `fk_Pagamento_Conta1_idx` (`Conta_idConta` ASC) VISIBLE,
  CONSTRAINT `fk_Pagamento_Conta1`
    FOREIGN KEY (`Conta_idConta`)
    REFERENCES `BarSpring`.`Conta` (`idConta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BarSpring`.`Item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BarSpring`.`Item` (
  `numItem` INT NOT NULL,
  `tipo` INT NOT NULL,
  `valor` DOUBLE NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`numItem`),
  UNIQUE INDEX `numItem_UNIQUE` (`numItem` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BarSpring`.`Consumo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BarSpring`.`Consumo` (
  `quantidade` INT NOT NULL,
  `Item_numItem` INT NOT NULL,
  `Conta_idConta` INT NOT NULL,
  INDEX `fk_Consumo_Item1_idx` (`Item_numItem` ASC) VISIBLE,
  INDEX `fk_Consumo_Conta1_idx` (`Conta_idConta` ASC) VISIBLE,
  CONSTRAINT `fk_Consumo_Item1`
    FOREIGN KEY (`Item_numItem`)
    REFERENCES `BarSpring`.`Item` (`numItem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Consumo_Conta1`
    FOREIGN KEY (`Conta_idConta`)
    REFERENCES `BarSpring`.`Conta` (`idConta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
