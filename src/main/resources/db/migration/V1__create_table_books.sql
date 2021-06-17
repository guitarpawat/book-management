CREATE TABLE `Book` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(250) NOT NULL,
    `author` VARCHAR(100) NOT NULL,
    `edition` INT NULL,
    `status` VARCHAR(15) NOT NULL,
    `is_favourite` BOOLEAN NOT NULL DEFAULT 0,
    `created_datetime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC));