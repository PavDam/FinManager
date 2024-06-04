CREATE TABLE `finance-manager`.`user` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(20) NOT NULL,
  `Email` VARCHAR(70) NOT NULL,
  `Password` CHAR(60) NOT NULL,
  `Limit` FLOAT NOT NULL DEFAULT 0.0,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC) VISIBLE,
  UNIQUE INDEX `Username_UNIQUE` (`Username` ASC) VISIBLE,
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) VISIBLE);

CREATE TABLE `finance-manager`.`account` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `UserID` INT NOT NULL,
  `Title` VARCHAR(50) NOT NULL,
  `Amount` FLOAT NOT NULL DEFAULT 0.0,
  `Currency` ENUM('UAH', "USD") NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC) VISIBLE,
  INDEX `user-account_idx` (`UserID` ASC) VISIBLE,
  CONSTRAINT `user-account`
    FOREIGN KEY (`UserID`)
    REFERENCES `finance-manager`.`user` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `finance-manager`.`category` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(40) NOT NULL,
  `Type` ENUM("Витрати", "Отримання") NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `id_UNIQUE` (`ID` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);

CREATE TABLE `finance-manager`.`transaction` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `UserID` INT NOT NULL,
  `AccountID` INT NOT NULL,
  `Date` DATE NOT NULL,
  `Type` ENUM("Витрати", "Отримання") NOT NULL,
  `CategoryID` INT NOT NULL,
  `Amount` FLOAT NOT NULL,
  `Note` TINYTEXT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC) VISIBLE,
  INDEX `user-transaction_idx` (`UserID` ASC) VISIBLE,
  INDEX `account-transaction_idx` (`AccountID` ASC) VISIBLE,
  INDEX `category-transaction_idx` (`CategoryID` ASC) VISIBLE,
  CONSTRAINT `user-transaction`
    FOREIGN KEY (`UserID`)
    REFERENCES `finance-manager`.`user` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `account-transaction`
    FOREIGN KEY (`AccountID`)
    REFERENCES `finance-manager`.`account` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `category-transaction`
    FOREIGN KEY (`CategoryID`)
    REFERENCES `finance-manager`.`category` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);