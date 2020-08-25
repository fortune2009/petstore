SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE pet_animal;

INSERT INTO pet_animal(`id`, `name`, `breed`, `types`, `sex`, `age`)
VALUES(200, 'Bobby', 'German Shephard', 'FISH', 'MALE', 15),
    (201, 'Bobby', 'German Shephard', 'RABBIT', 'FEMALE', 15),
    (202, 'Bobby', 'German Shephard', 'DOG', 'MALE', 15),
    (203, 'Bobby', 'German Shephard', 'HAMSTERS', 'MALE', 15),
    (204, 'Bobby', 'German Shephard', 'GOAT', 'MALE', 15),
    (205, 'Bobby', 'German Shephard', 'RAT', 'FEMALE', 15),
    (206, 'Bobby', 'German Shephard', 'CAT', 'MALE', 15),
    (207, 'Bobby', 'German Shephard', 'SNAKES', 'MALE', 15);



SET FOREIGN_KEY_CHECKS = 1;

