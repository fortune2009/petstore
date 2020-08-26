SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE store;

INSERT INTO pet_animal(`id`, `store_name`, `store_number`, `address`, `city`, `state`, `country`)
VALUES(500, 'Divine Pet', 125, '12 German street', 'Yaba', 'Lagos', 'Nigeria'),
    (501, 'Good Store Pet', 124, '14 Old guard street', 'Ogio', 'London', 'Uk'),
    (500, 'Divine Pet', 145, '5 Luckman street', 'Ohio', 'Nevade', 'USA');

SET FOREIGN_KEY_CHECKS = 1;
