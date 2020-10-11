-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema junior_workers
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema junior_workers
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `junior_workers` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `junior_workers` ;

-- -----------------------------------------------------
-- Table `junior_workers`.`education`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `junior_workers`.`education` (
  `id_education` INT(10) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_education`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `junior_workers`.`education_level`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `junior_workers`.`education_level` (
  `id_education_level` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_education_level`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `junior_workers`.`profession`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `junior_workers`.`profession` (
  `id_profession` INT(10) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_profession`))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `junior_workers`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `junior_workers`.`user` (
  `id_user` INT(10) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `firstname` VARCHAR(100) NOT NULL,
  `lastname` VARCHAR(100) NOT NULL,
  `availability` INT(11) NULL DEFAULT '0',
  `title` VARCHAR(100) NULL DEFAULT NULL,
  `bio` VARCHAR(100) NULL DEFAULT NULL,
  `role` VARCHAR(100) NOT NULL DEFAULT 'candidate',
  `image_path` VARCHAR(200) NOT NULL DEFAULT 'default.png',
  `video_path` VARCHAR(200) NULL DEFAULT NULL,
  `resume_path` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id_user`))
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `junior_workers`.`experience`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `junior_workers`.`experience` (
  `id_experience` INT(10) NOT NULL AUTO_INCREMENT,
  `company` VARCHAR(100) NOT NULL,
  `date` VARCHAR(100) NOT NULL,
  `id_profession` INT(10) NOT NULL,
  `id_user` INT(10) NOT NULL,
  PRIMARY KEY (`id_experience`),
  INDEX `fk_experience_profession_idx` (`id_profession` ASC) VISIBLE,
  INDEX `fk_experience_user1_idx` (`id_user` ASC) VISIBLE,
  CONSTRAINT `fk_experience_profession`
    FOREIGN KEY (`id_profession`)
    REFERENCES `junior_workers`.`profession` (`id_profession`),
  CONSTRAINT `fk_experience_user1`
    FOREIGN KEY (`id_user`)
    REFERENCES `junior_workers`.`user` (`id_user`))
ENGINE = InnoDB
AUTO_INCREMENT = 162
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `junior_workers`.`job_post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `junior_workers`.`job_post` (
  `id_job_post` INT(10) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(1000) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `id_user` INT(10) NOT NULL,
  `id_profession` INT(10) NOT NULL,
  PRIMARY KEY (`id_job_post`),
  INDEX `fk_job_post_user1_idx` (`id_user` ASC) VISIBLE,
  INDEX `fk_job_post_profession1_idx` (`id_profession` ASC) VISIBLE,
  CONSTRAINT `fk_job_post_user1`
    FOREIGN KEY (`id_user`)
    REFERENCES `junior_workers`.`user` (`id_user`),
  CONSTRAINT `fk_job_post_profession1`
    FOREIGN KEY (`id_profession`)
    REFERENCES `junior_workers`.`profession` (`id_profession`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `junior_workers`.`language`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `junior_workers`.`language` (
  `id_language` INT(10) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_language`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `junior_workers`.`language_level`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `junior_workers`.`language_level` (
  `id_language_level` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_language_level`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `junior_workers`.`skill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `junior_workers`.`skill` (
  `id_skill` INT(10) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_skill`))
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `junior_workers`.`user_has_education`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `junior_workers`.`user_has_education` (
  `id_user` INT(10) NOT NULL,
  `id_education` INT(10) NOT NULL,
  `id_user_has_education` INT(10) NOT NULL AUTO_INCREMENT,
  `id_education_level` INT(10) NOT NULL,
  PRIMARY KEY (`id_user_has_education`),
  INDEX `fk_user_has_education_education1_idx` (`id_education` ASC) VISIBLE,
  INDEX `fk_user_has_education_user1_idx` (`id_user` ASC) VISIBLE,
  INDEX `fk_user_has_education_education_level1_idx` (`id_education_level` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_education_education1`
    FOREIGN KEY (`id_education`)
    REFERENCES `junior_workers`.`education` (`id_education`),
  CONSTRAINT `fk_user_has_education_education_level1`
    FOREIGN KEY (`id_education_level`)
    REFERENCES `junior_workers`.`education_level` (`id_education_level`),
  CONSTRAINT `fk_user_has_education_user1`
    FOREIGN KEY (`id_user`)
    REFERENCES `junior_workers`.`user` (`id_user`))
ENGINE = InnoDB
AUTO_INCREMENT = 71
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `junior_workers`.`user_has_language`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `junior_workers`.`user_has_language` (
  `id_language` INT(10) NOT NULL,
  `id_user` INT(10) NOT NULL,
  `id_language_level` INT(10) NOT NULL,
  `id_user_has_language` INT(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_user_has_language`),
  INDEX `fk_language_has_user_user1_idx` (`id_user` ASC) VISIBLE,
  INDEX `fk_language_has_user_language1_idx` (`id_language` ASC) VISIBLE,
  INDEX `fk_language_has_user_language_level1_idx` (`id_language_level` ASC) VISIBLE,
  CONSTRAINT `fk_language_has_user_language1`
    FOREIGN KEY (`id_language`)
    REFERENCES `junior_workers`.`language` (`id_language`),
  CONSTRAINT `fk_language_has_user_language_level1`
    FOREIGN KEY (`id_language_level`)
    REFERENCES `junior_workers`.`language_level` (`id_language_level`),
  CONSTRAINT `fk_language_has_user_user1`
    FOREIGN KEY (`id_user`)
    REFERENCES `junior_workers`.`user` (`id_user`))
ENGINE = InnoDB
AUTO_INCREMENT = 103
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `junior_workers`.`user_has_skill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `junior_workers`.`user_has_skill` (
  `id_user` INT(10) NOT NULL,
  `id_skill` INT(10) NOT NULL,
  `id_user_has_skill` INT(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_user_has_skill`),
  INDEX `fk_user_has_skill_skill1_idx` (`id_skill` ASC) VISIBLE,
  INDEX `fk_user_has_skill_user1_idx` (`id_user` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_skill_skill1`
    FOREIGN KEY (`id_skill`)
    REFERENCES `junior_workers`.`skill` (`id_skill`),
  CONSTRAINT `fk_user_has_skill_user1`
    FOREIGN KEY (`id_user`)
    REFERENCES `junior_workers`.`user` (`id_user`))
ENGINE = InnoDB
AUTO_INCREMENT = 151
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;