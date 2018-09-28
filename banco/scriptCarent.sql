-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema carent
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema carent
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `carent` DEFAULT CHARACTER SET utf8 ;
USE `carent` ;

-- -----------------------------------------------------
-- Table `carent`.`estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carent`.`estado` (
  `sigla` VARCHAR(2) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`sigla`),
  UNIQUE INDEX `sigla_UNIQUE` (`sigla` ASC),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carent`.`cidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carent`.`cidade` (
  `id` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `estado_sigla` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cidade_estado1_idx` (`estado_sigla` ASC),
  CONSTRAINT `fk_cidade_estado1`
    FOREIGN KEY (`estado_sigla`)
    REFERENCES `carent`.`estado` (`sigla`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carent`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carent`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(60) NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `endereco` VARCHAR(200) NOT NULL,
  `cidade_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC),
  INDEX `fk_usuario_cidade1_idx` (`cidade_id` ASC),
  CONSTRAINT `fk_usuario_cidade1`
    FOREIGN KEY (`cidade_id`)
    REFERENCES `carent`.`cidade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carent`.`cor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carent`.`cor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carent`.`marca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carent`.`marca` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(10) NOT NULL,
  `titulo` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carent`.`modelo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carent`.`modelo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(20) NOT NULL,
  `titulo` VARCHAR(20) NOT NULL,
  `marca_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_modelo_marca_idx` (`marca_id` ASC),
  CONSTRAINT `fk_modelo_marca`
    FOREIGN KEY (`marca_id`)
    REFERENCES `carent`.`marca` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carent`.`cor_has_modelo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carent`.`cor_has_modelo` (
  `cor_id` INT NOT NULL,
  `modelo_id` INT NOT NULL,
  PRIMARY KEY (`cor_id`, `modelo_id`),
  INDEX `fk_cor_has_modelo_modelo1_idx` (`modelo_id` ASC),
  INDEX `fk_cor_has_modelo_cor1_idx` (`cor_id` ASC),
  CONSTRAINT `fk_cor_has_modelo_cor1`
    FOREIGN KEY (`cor_id`)
    REFERENCES `carent`.`cor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cor_has_modelo_modelo1`
    FOREIGN KEY (`modelo_id`)
    REFERENCES `carent`.`modelo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carent`.`filial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carent`.`filial` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `endereco` VARCHAR(200) NOT NULL,
  `cnpj` VARCHAR(14) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `cidade_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cnpj_UNIQUE` (`cnpj` ASC),
  INDEX `fk_filial_cidade1_idx` (`cidade_id` ASC),
  CONSTRAINT `fk_filial_cidade1`
    FOREIGN KEY (`cidade_id`)
    REFERENCES `carent`.`cidade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carent`.`avaliacao_carro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carent`.`avaliacao_carro` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `mensagem` VARCHAR(200) NOT NULL,
  `nota` INT(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carent`.`carro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carent`.`carro` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `disponivel` TINYINT NOT NULL,
  `placa` CHAR(6) NULL,
  `filial_id` INT NOT NULL,
  `modelo_id` INT NOT NULL,
  `motor` VARCHAR(45) NOT NULL,
  `combustivel` VARCHAR(15) NOT NULL,
  `avaliacao_carro_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `placa_UNIQUE` (`placa` ASC),
  INDEX `fk_carro_filial1_idx` (`filial_id` ASC),
  INDEX `fk_carro_modelo1_idx` (`modelo_id` ASC),
  INDEX `fk_carro_avaliacao_carro1_idx` (`avaliacao_carro_id` ASC),
  CONSTRAINT `fk_carro_filial1`
    FOREIGN KEY (`filial_id`)
    REFERENCES `carent`.`filial` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_carro_modelo1`
    FOREIGN KEY (`modelo_id`)
    REFERENCES `carent`.`modelo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_carro_avaliacao_carro1`
    FOREIGN KEY (`avaliacao_carro_id`)
    REFERENCES `carent`.`avaliacao_carro` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carent`.`acessorio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carent`.`acessorio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carent`.`acessorio_has_carro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carent`.`acessorio_has_carro` (
  `acessorio_id` INT NOT NULL,
  `carro_id` INT NOT NULL,
  PRIMARY KEY (`acessorio_id`, `carro_id`),
  INDEX `fk_acessorio_has_carro_carro1_idx` (`carro_id` ASC),
  INDEX `fk_acessorio_has_carro_acessorio1_idx` (`acessorio_id` ASC),
  CONSTRAINT `fk_acessorio_has_carro_acessorio1`
    FOREIGN KEY (`acessorio_id`)
    REFERENCES `carent`.`acessorio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_acessorio_has_carro_carro1`
    FOREIGN KEY (`carro_id`)
    REFERENCES `carent`.`carro` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carent`.`aluguel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carent`.`aluguel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` INT NOT NULL,
  `valor_total` DATE NOT NULL,
  `data_fim` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carent`.`aluguel_carro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carent`.`aluguel_carro` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `aluguel_id` INT NOT NULL,
  `carro_id` INT NOT NULL,
  `data_inicio` DATE NOT NULL,
  `data_fim` DATE NOT NULL,
  `kilometragem` VARCHAR(45) NOT NULL,
  `usuario_id` INT NOT NULL,
  PRIMARY KEY (`id`, `aluguel_id`, `carro_id`),
  INDEX `fk_aluguel_has_carro_carro1_idx` (`carro_id` ASC),
  INDEX `fk_aluguel_has_carro_aluguel1_idx` (`aluguel_id` ASC),
  INDEX `fk_aluguel_carro_usuario1_idx` (`usuario_id` ASC),
  CONSTRAINT `fk_aluguel_has_carro_aluguel1`
    FOREIGN KEY (`aluguel_id`)
    REFERENCES `carent`.`aluguel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aluguel_has_carro_carro1`
    FOREIGN KEY (`carro_id`)
    REFERENCES `carent`.`carro` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aluguel_carro_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `carent`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carent`.` avaliar_aluguel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carent`.` avaliar_aluguel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `mensagem` VARCHAR(45) NOT NULL,
  `nota` INT(1) NOT NULL,
  `aluguel_carro_id` INT NOT NULL,
  `aluguel_carro_aluguel_id` INT NOT NULL,
  `aluguel_carro_carro_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ avaliar_aluguel_aluguel_carro1_idx` (`aluguel_carro_id` ASC, `aluguel_carro_aluguel_id` ASC, `aluguel_carro_carro_id` ASC),
  CONSTRAINT `fk_ avaliar_aluguel_aluguel_carro1`
    FOREIGN KEY (`aluguel_carro_id` , `aluguel_carro_aluguel_id` , `aluguel_carro_carro_id`)
    REFERENCES `carent`.`aluguel_carro` (`id` , `aluguel_id` , `carro_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carent`.`manutencao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carent`.`manutencao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `setor` VARCHAR(45) NOT NULL COMMENT 'setor seria qual parte do carro e a manutenção , suspeçao, eletrica, cambio etc',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carent`.`manutencao_carro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carent`.`manutencao_carro` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `manutencao_id` INT NOT NULL,
  `carro_id` INT NOT NULL,
  `data` DATE NOT NULL,
  `kilometgragem` INT NOT NULL,
  PRIMARY KEY (`id`, `manutencao_id`, `carro_id`),
  INDEX `fk_manutencao_has_carro_carro1_idx` (`carro_id` ASC),
  INDEX `fk_manutencao_has_carro_manutencao1_idx` (`manutencao_id` ASC),
  CONSTRAINT `fk_manutencao_has_carro_manutencao1`
    FOREIGN KEY (`manutencao_id`)
    REFERENCES `carent`.`manutencao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_manutencao_has_carro_carro1`
    FOREIGN KEY (`carro_id`)
    REFERENCES `carent`.`carro` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
