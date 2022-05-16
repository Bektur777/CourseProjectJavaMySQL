CREATE TABLE `mydb`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `account` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `mydb`.`allequipment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `serialnumber` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `count` INT NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `mydb`.`orderedproducts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `serialnumber` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `count` INT NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

