DROP DATABASE IF EXISTS FlashCards;

CREATE DATABASE FlashCards;

USE FlashCards;

CREATE TABLE IF NOT EXISTS Category
(CategoryID SMALLINT UNSIGNED AUTO_INCREMENT,
CategoryName VARCHAR(30) NOT NULL,
CategoryDesc VARCHAR(100),
PRIMARY KEY(CategoryID)
);

CREATE TABLE IF NOT EXISTS Card
(CardID MEDIUMINT UNSIGNED AUTO_INCREMENT,
CardName VARCHAR(30) NOT NULL,
CardChallenge VARCHAR(30) NOT NULL,
CardAnswer VARCHAR(500) NOT NULL,
PRIMARY KEY(CardID)
);

CREATE TABLE IF NOT EXISTS CardCategory
(CardID MEDIUMINT UNSIGNED,
CategoryID SMALLINT UNSIGNED,
PRIMARY KEY(CardID, CategoryID),
CONSTRAINT fk_CardCategoryCardID FOREIGN KEY (CardID) REFERENCES Card(CardID),
CONSTRAINT fk_CategoryID FOREIGN KEY (CategoryID) REFERENCES Category(CategoryID),
INDEX (CardID),
INDEX (CategoryID)
);

CREATE TABLE IF NOT EXISTS CardQueue
(CardID MEDIUMINT UNSIGNED AUTO_INCREMENT,
CardName VARCHAR(30) NOT NULL,
CardChallenge VARCHAR(30) NOT NULL,
CardAnswer VARCHAR(500) NOT NULL,
PRIMARY KEY(CardID)
);

CREATE TABLE IF NOT EXISTS CardCategoryQueue
(CardID MEDIUMINT UNSIGNED,
CategoryID SMALLINT UNSIGNED,
PRIMARY KEY(CardID, CategoryID),
CONSTRAINT fk_CardCategoryQCardID FOREIGN KEY (CardID) REFERENCES CardQueue(CardID),
CONSTRAINT fk_CardCategoryQCategoryID FOREIGN KEY (CategoryID) REFERENCES Category(CategoryID),
INDEX (CardID),
INDEX (CategoryID)
);

CREATE TABLE IF NOT EXISTS `User`
(UserID INT UNSIGNED AUTO_INCREMENT,
UserName VARCHAR(25) NOT NULL, -- IF WE'RE GOING TO USE EMAIL IT NEEDS TO BE MORE LIKE 50
`Password` VARCHAR(25) NOT NULL,
Active BOOLEAN NOT NULL DEFAULT 1,
PRIMARY KEY(UserID)
);

CREATE TABLE IF NOT EXISTS UserCard
(UserID INT UNSIGNED NOT NULL,
CardID MEDIUMINT UNSIGNED NOT NULL,
PRIMARY KEY(UserID, CardID),
CONSTRAINT fk_UserCardUserID FOREIGN KEY (UserID) REFERENCES `User`(UserID),
CONSTRAINT fk_UserCardCardID FOREIGN KEY (CardID) REFERENCES Card(CardID),
INDEX (UserID),
INDEX (CardID)
);

CREATE TABLE IF NOT EXISTS Role
(RoleID TINYINT UNSIGNED AUTO_INCREMENT,
RoleName VARCHAR(30) NOT NULL,
RoleDesc VARCHAR(50) NOT NULL,
PRIMARY KEY(RoleID)
);

CREATE TABLE IF NOT EXISTS UserRole
(UserID INT UNSIGNED NOT NULL,
RoleID TINYINT UNSIGNED NOT NULL,
PRIMARY KEY(UserID, RoleID),
CONSTRAINT fk_UserRoleUserID FOREIGN KEY (UserID) REFERENCES `User` (UserID),
CONSTRAINT fk_RoleID FOREIGN KEY (RoleID) REFERENCES Role (RoleID),
INDEX (UserID),
INDEX (RoleID)
);

CREATE TABLE IF NOT EXISTS Deck
(DeckID SMALLINT UNSIGNED AUTO_INCREMENT,
DeckName VARCHAR(30) NOT NULL,
DeckDesc VARCHAR(100),
PRIMARY KEY(DeckID)
);

CREATE TABLE IF NOT EXISTS UserDeck
(UserID INT UNSIGNED NOT NULL,
DeckID SMALLINT UNSIGNED NOT NULL,
PRIMARY KEY(UserID, DeckID),
CONSTRAINT fk_UserDeckUserID FOREIGN KEY (UserID) REFERENCES `User` (UserID),
CONSTRAINT fk_UserDeckDeckID FOREIGN KEY (DeckID) REFERENCES Deck (DeckID),
INDEX (UserID),
INDEX (DeckID)
);

CREATE TABLE IF NOT EXISTS DeckCard
(DeckID SMALLINT UNSIGNED NOT NULL,
CardID MEDIUMINT UNSIGNED NOT NULL,
PRIMARY KEY(DeckID, CardID),
CONSTRAINT fk_DeckCardDeckID FOREIGN KEY (DeckID) REFERENCES Deck (DeckID),
CONSTRAINT fk_DeckCardCardID FOREIGN KEY (CardID) REFERENCES Card (CardID),
INDEX (DeckID),
INDEX (CardID)
);

CREATE TABLE IF NOT EXISTS Folder
(FolderID SMALLINT UNSIGNED AUTO_INCREMENT,
FolderName VARCHAR(30) NOT NULL,
PRIMARY KEY(FolderID)
);

CREATE TABLE IF NOT EXISTS DeckFolder
(DeckID SMALLINT UNSIGNED NOT NULL,
FolderID SMALLINT UNSIGNED NOT NULL,
PRIMARY KEY(DeckID, FolderID),
CONSTRAINT fk_DeckFolderDeckID FOREIGN KEY (DeckID) REFERENCES Deck (DeckID),
CONSTRAINT fk_FolderID FOREIGN KEY (FolderID) REFERENCES Folder (FolderID),
INDEX (DeckID),
INDEX (FolderID)
);