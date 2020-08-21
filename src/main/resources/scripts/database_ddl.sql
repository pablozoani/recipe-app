
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lroeo5fvfdeg4hpicn4lw7x9b` (`category_name`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_0900_AI_CI AUTO_INCREMENT=1000;

CREATE TABLE `ingredient` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `amount` DECIMAL(19,2) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `recipe_id` BIGINT DEFAULT NULL,
  `unit_of_measure_id` BIGINT DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj0s4ywmqqqw4h5iommigh5yja` (`recipe_id`),
  KEY `FK15ttfoaomqy1bbpo251fuidxw` (`unit_of_measure_id`),
  CONSTRAINT `FK15ttfoaomqy1bbpo251fuidxw` FOREIGN KEY (`unit_of_measure_id`) REFERENCES `unit_of_measure` (`id`),
  CONSTRAINT `FKj0s4ywmqqqw4h5iommigh5yja` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_0900_AI_CI AUTO_INCREMENT=1000;

CREATE TABLE `notes` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `recipe_notes` LONGTEXT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_0900_AI_CI AUTO_INCREMENT=1000;

CREATE TABLE `recipe` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `cook_time` VARCHAR(255) DEFAULT NULL,
  `description` VARCHAR(255) NOT NULL,
  `difficulty` VARCHAR(255) DEFAULT NULL,
  `directions` LONGTEXT NOT NULL,
  `image` LONGBLOB,
  `preparation_time` VARCHAR(255) NOT NULL,
  `servings` INT DEFAULT NULL,
  `source` VARCHAR(255) DEFAULT NULL,
  `url` VARCHAR(255) DEFAULT NULL,
  `notes_id` BIGINT DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK37al6kcbdasgfnut9xokktie9` (`notes_id`),
  CONSTRAINT `FK37al6kcbdasgfnut9xokktie9` FOREIGN KEY (`notes_id`) REFERENCES `notes` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_0900_AI_CI AUTO_INCREMENT=1000;

CREATE TABLE `recipe_category` (
  `recipe_id` BIGINT NOT NULL,
  `category_id` BIGINT NOT NULL,
  PRIMARY KEY (`recipe_id`,`category_id`),
  KEY `FKqsi87i8d4qqdehlv2eiwvpwb` (`category_id`),
  CONSTRAINT `FKcqlqnvfyarhieewfeayk3v25v` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`),
  CONSTRAINT `FKqsi87i8d4qqdehlv2eiwvpwb` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_0900_AI_CI AUTO_INCREMENT=1000;

CREATE TABLE `unit_of_measure` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `unit_of_measure` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rpnhcgxl12rdgd202c09aavpk` (`unit_of_measure`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_0900_AI_CI AUTO_INCREMENT=1000;

SET FOREIGN_KEY_CHECKS = 1;
