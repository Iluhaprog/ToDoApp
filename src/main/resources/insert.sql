USE ToDoDB;

SET NAMES 'utf8';

INSERT INTO Users(name, password) VALUES
    ("Ilya", "123456");

INSERT INTO Doings(doingText, userId) VALUES
    ("Сходить в магазин", 1);