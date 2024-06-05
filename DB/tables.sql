CREATE TABLE `user` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(20) NOT NULL,
  `Email` VARCHAR(70) NOT NULL,
  `Password` CHAR(60) NOT NULL,
  `Limit` FLOAT NOT NULL DEFAULT 0.0,
  PRIMARY KEY (`ID`),
  UNIQUE (`Username`),
  UNIQUE (`Email`)
);

CREATE TABLE `account` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `UserID` INT NOT NULL,
  `Title` VARCHAR(50) NOT NULL,
  `Amount` FLOAT NOT NULL DEFAULT 0.0,
  `Currency` ENUM('UAH', 'USD') NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX (`UserID`),
  FOREIGN KEY (`UserID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `category` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `UserID` INT,
  `Name` VARCHAR(40) NOT NULL,
  `Type` ENUM('Витрати', 'Отримання') NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX (`UserID`),
  FOREIGN KEY (`UserID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `transaction` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `UserID` INT NOT NULL,
  `AccountID` INT NOT NULL,
  `Date` DATE NOT NULL,
  `CategoryID` INT NOT NULL,
  `Amount` FLOAT NOT NULL,
  `Note` TINYTEXT,
  PRIMARY KEY (`ID`),
  INDEX (`UserID`),
  INDEX (`AccountID`),
  INDEX (`CategoryID`),
  FOREIGN KEY (`UserID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`AccountID`) REFERENCES `account` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`CategoryID`) REFERENCES `category` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
);