-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema servicios
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema servicios
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `servicios` DEFAULT CHARACTER SET utf8 ;
USE `servicios` ;

-- -----------------------------------------------------
-- Table `servicios`.`materias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicios`.`materias` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `clave` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `servicios`.`tipo_empleados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicios`.`tipo_empleados` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `servicios`.`empleados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicios`.`empleados` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo_empleado_id` INT NOT NULL,
  `persona_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_Empleados_Tipo_Empleado_idx` (`tipo_empleado_id` ASC),
  INDEX `FK_Empleados_Persona_idx` (`persona_id` ASC),
  CONSTRAINT `FK_Empleados_Tipo_Empleado`
    FOREIGN KEY (`tipo_empleado_id`)
    REFERENCES `servicios`.`tipo_empleados` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Empleados_Persona`
    FOREIGN KEY (`persona_id`)
    REFERENCES `servicios`.`personas` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `servicios`.`personas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicios`.`personas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(65) NOT NULL,
  `apellido_paterno` VARCHAR(45) NOT NULL,
  `apellido_materno` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NULL,
  `empleado_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_Empleados_Persona_idx` (`empleado_id` ASC),
  CONSTRAINT `FK_Persona_Empleado`
    FOREIGN KEY (`empleado_id`)
    REFERENCES `servicios`.`empleados` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `servicios`.`puestos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicios`.`puestos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `servicios`.`tipo_salones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicios`.`tipo_salones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `servicios`.`edificios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicios`.`edificios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `ubicacion_x` FLOAT NULL,
  `ubicacion_y` FLOAT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `servicios`.`salones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicios`.`salones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `capacidad` INT NOT NULL,
  `tipo_salon_id` INT NOT NULL,
  `edificio_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_Salones_Tipo_Salon_idx` (`tipo_salon_id` ASC),
  INDEX `FK_Salon_Edificio_idx` (`edificio_id` ASC),
  CONSTRAINT `FK_Salones_Tipo_Salon`
    FOREIGN KEY (`tipo_salon_id`)
    REFERENCES `servicios`.`tipo_salones` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_Salon_Edificio`
    FOREIGN KEY (`edificio_id`)
    REFERENCES `servicios`.`edificios` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `servicios`.`clases`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicios`.`clases` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `horario` VARCHAR(45) NOT NULL,
  `materia_id` INT NOT NULL,
  `profesor_id` INT NOT NULL,
  `salon_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_Clases_Materia_idx` (`materia_id` ASC),
  INDEX `FK_Clases_Profesor_idx` (`profesor_id` ASC),
  INDEX `FK_Clases_Salon_idx` (`salon_id` ASC),
  CONSTRAINT `FK_Clases_Materia`
    FOREIGN KEY (`materia_id`)
    REFERENCES `servicios`.`materias` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_Clases_Profesor`
    FOREIGN KEY (`profesor_id`)
    REFERENCES `servicios`.`empleados` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_Clases_Salon`
    FOREIGN KEY (`salon_id`)
    REFERENCES `servicios`.`salones` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `servicios`.`departamentos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicios`.`departamentos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `edificio_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_Departamento_Edificio_idx` (`edificio_id` ASC),
  CONSTRAINT `FK_Departamento_Edificio`
    FOREIGN KEY (`edificio_id`)
    REFERENCES `servicios`.`edificios` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `servicios`.`servicios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicios`.`servicios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(255) NULL,
  `departamento_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_Servicio_Departamento_idx` (`departamento_id` ASC),
  CONSTRAINT `FK_Servicio_Departamento`
    FOREIGN KEY (`departamento_id`)
    REFERENCES `servicios`.`departamentos` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `servicios`.`servicios_pesonal_puestos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicios`.`servicios_pesonal_puestos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `servicio_id` INT NOT NULL,
  `empleado_id` INT NOT NULL,
  `puesto_id` INT NOT NULL,
  `horario` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_Horario_Servicio_idx` (`servicio_id` ASC),
  INDEX `FK_Horario_Empleado_idx` (`empleado_id` ASC),
  INDEX `FK_Horario_Puesto_idx` (`puesto_id` ASC),
  CONSTRAINT `FK_Horario_Servicio`
    FOREIGN KEY (`servicio_id`)
    REFERENCES `servicios`.`servicios` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_Horario_Empleado`
    FOREIGN KEY (`empleado_id`)
    REFERENCES `servicios`.`empleados` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_Horario_Puesto`
    FOREIGN KEY (`puesto_id`)
    REFERENCES `servicios`.`puestos` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;