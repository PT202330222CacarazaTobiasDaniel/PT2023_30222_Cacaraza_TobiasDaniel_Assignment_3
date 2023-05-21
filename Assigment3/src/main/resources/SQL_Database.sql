DROP SCHEMA IF EXISTS `schooldb`;

CREATE SCHEMA `schooldb`;



drop table if exists `schooldb`.`product`;

CREATE TABLE `schooldb`.`product`
(
    `id`    INT NOT NULL AUTO_INCREMENT,
    `stock` INT NULL,
    `name`  VARCHAR(45) NULL,
    `price` FLOAT NULL,
    PRIMARY KEY (`id`)
);


drop table if exists `schooldb`.`client`;

CREATE TABLE `schooldb`.`client`
(
    `id`      INT NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(45) NULL,
    `address` VARCHAR(45) NULL,
    `email`   VARCHAR(45) NULL,
    `age`     INT NULL,
    PRIMARY KEY (`id`)
);

drop table if exists `schooldb`.`order`;

CREATE TABLE `schooldb`.`order`
(
    `id`       INT NOT NULL AUTO_INCREMENT,
    `quantity` INT NULL,
    `Client`   INT NULL,
    `Product`  INT NULL,
    INDEX      `fk_order_client_idx`(`Client` ASC),
    INDEX      `fk_order_product_idx`(`Product` ASC),
    CONSTRAINT `fk_order_client_idx`
        FOREIGN KEY (`Client`)
            REFERENCES `schooldb`.`client` (`id`),
    CONSTRAINT `fk_order_product_idx`
        FOREIGN KEY (`Product`)
            REFERENCES `schooldb`.`product` (`id`),
    PRIMARY KEY (`id`)
);

drop table if exists `schooldb`.`Bill`;

CREATE TABLE `schooldb`.`Bill`
(
    `id`         INT NOT NULL AUTO_INCREMENT,
    `quantity`   INT NULL,
    `pret_total` FLOAT NULL,
    `Client`     INT NULL,
    `Product`    INT NULL,
    INDEX        `fk_bill_client_idx`(`Client` ASC),
    INDEX        `fk_bill_product_idx`(`Product` ASC),
    CONSTRAINT `fk_bill_client_idx`
        FOREIGN KEY (`Client`)
            REFERENCES `schooldb`.`client` (`id`),
    CONSTRAINT `fk_bill_product_idx`
        FOREIGN KEY (`Product`)
            REFERENCES `schooldb`.`product` (`id`),
    PRIMARY KEY (`id`)
);


INSERT INTO `schooldb`.`client` (`name`, `address`, `email`, `age`)
VALUES ('andr', '098', 'bjg798@yahoo.com', '51'),
       ('Mary', '123', 'aMary@yahoo.com', '15');
INSERT INTO `schooldb`.`product` (`name`, `stock`, `price`)
VALUES ('lapte', 123, 098),
       ('sare', 321, 123);
INSERT INTO `schooldb`.`order` (`quantity`, `Client`, `Product`)
VALUES ('4', '2', 1);
INSERT INTO `schooldb`.`bill` (`quantity`, `pret_total`, `Client`, `Product`)
VALUES ('4', '392', '2', '1');

-- INSERT INTO Client ( `nameclientclient`, `address`, `email`, `age`) VALUES  (  'asd', '1234', 'asd@yahoo.com', '14');
-- UPDATE `schooldb`.`Client` SET `address` = '14' WHERE (`id` = '2');
-- DELETE FROM  `schooldb`.Client cl WHERE cl.id = 1;