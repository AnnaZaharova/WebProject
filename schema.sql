SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `tour_agency` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `tour_agency` ;

-- -----------------------------------------------------
-- Table `tour_agency`.`role`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tour_agency`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tour_agency`.`user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tour_agency`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(32) NOT NULL ,
  `role_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) ,
  INDEX `FK_ROLE_ID_idx` (`role_id` ASC) ,
  CONSTRAINT `FK_ROLE_ID`
    FOREIGN KEY (`role_id` )
    REFERENCES `tour_agency`.`role` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tour_agency`.`tour`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tour_agency`.`tour` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `tourname` VARCHAR(255) NOT NULL ,
  `type` INT(1) NOT NULL ,
  `price` INT NOT NULL ,
  `hot` INT(1) NULL DEFAULT 0 ,
  `rugular_discount` INT(2) NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tour_agency`.`tour_purchase`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tour_agency`.`tour_purchase` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `tour_id` INT NOT NULL ,
  `client_id` INT NOT NULL ,
  `price` INT NOT NULL ,
  `purchase_date` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_TOUR_idx` (`tour_id` ASC) ,
  INDEX `FK_CLIENT_idx` (`client_id` ASC) ,
  CONSTRAINT `FK_TOUR`
    FOREIGN KEY (`tour_id` )
    REFERENCES `tour_agency`.`tour` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CLIENT`
    FOREIGN KEY (`client_id` )
    REFERENCES `tour_agency`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
