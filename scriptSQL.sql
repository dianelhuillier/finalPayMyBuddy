-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           8.0.2-dmr-log - MySQL Community Server (GPL)
-- SE du serveur:                Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Listage de la structure de la base pour paymybuddy
CREATE DATABASE IF NOT EXISTS `paymybuddy` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `paymybuddy`;

-- Listage de la structure de la table paymybuddy. connection
CREATE TABLE IF NOT EXISTS `connection` (
  `connection_id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`connection_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- Listage des données de la table paymybuddy.connection : -1 rows
/*!40000 ALTER TABLE `connection` DISABLE KEYS */;
INSERT INTO `connection` (`connection_id`, `email`) VALUES
	(135, 'test@save.fr'),
	(121, 'test@save.fr'),
	(120, 'test@save.fr'),
	(117, 'test@save.fr'),
	(116, 'test@save.fr'),
	(113, 'test@save.fr'),
	(112, 'test@save.fr'),
	(109, 'test@save.fr'),
	(108, 'test@save.fr'),
	(106, 'diane@diane.fr'),
	(101, 'test@save.fr'),
	(100, 'test@save.fr'),
	(97, 'test@save.fr'),
	(96, 'test@save.fr'),
	(93, 'test@save.fr'),
	(92, 'test@save.fr'),
	(80, 'test@save.fr'),
	(79, 'test@save.fr'),
	(76, 'test@save.fr'),
	(77, 'test@save.fr'),
	(3, 'email@email.fr'),
	(2, 'test@test.fr'),
	(4, 'user@user.fr'),
	(1, 'diane@admin.fr'),
	(136, 'test@save.fr');
/*!40000 ALTER TABLE `connection` ENABLE KEYS */;

-- Listage de la structure de la table paymybuddy. hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- Listage des données de la table paymybuddy.hibernate_sequence : -1 rows
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(139);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Listage de la structure de la table paymybuddy. message_model
CREATE TABLE IF NOT EXISTS `message_model` (
  `id` bigint(20) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `text_msg` varchar(255) DEFAULT NULL,
  `time_stamp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- Listage des données de la table paymybuddy.message_model : -1 rows
/*!40000 ALTER TABLE `message_model` DISABLE KEYS */;
INSERT INTO `message_model` (`id`, `author`, `subject`, `text_msg`, `time_stamp`) VALUES
	(65, 'diane', 'test', 'description\r\n', 'Thu Jul 01 17:00:33 CEST 2021'),
	(66, 'test', 'msg', 'message', 'Thu Jul 01 17:02:31 CEST 2021');
/*!40000 ALTER TABLE `message_model` ENABLE KEYS */;

-- Listage de la structure de la table paymybuddy. role
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` int(11) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- Listage des données de la table paymybuddy.role : -1 rows
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`role_id`, `role`) VALUES
	(1, 'ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Listage de la structure de la table paymybuddy. transaction_pay_my_buddy
CREATE TABLE IF NOT EXISTS `transaction_pay_my_buddy` (
  `id` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `email_connection` varchar(255) DEFAULT NULL,
  `email_user` varchar(255) DEFAULT NULL,
  `time_stamp` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- Listage des données de la table paymybuddy.transaction_pay_my_buddy : -1 rows
/*!40000 ALTER TABLE `transaction_pay_my_buddy` DISABLE KEYS */;
INSERT INTO `transaction_pay_my_buddy` (`id`, `amount`, `email_connection`, `email_user`, `time_stamp`, `description`) VALUES
	(5, 150, 'admin', 'user', NULL, 'test'),
	(6, 5, 'admin', 'user', NULL, 'testestsetestesetsetestest'),
	(9, 25, 'admin', 'new@user', NULL, 'test'),
	(10, 10, 'user', 'new@user', NULL, 'lion'),
	(11, 10, 'user', 'new@user', NULL, 'test'),
	(12, 10, 'user', 'new@user', NULL, 'test'),
	(13, 10, 'admin', 'new@user', NULL, 'test'),
	(16, 10, 'admin', 'new@user', NULL, 'test'),
	(17, 10, 'user', 'new@user', NULL, 'test'),
	(18, 10, 'user', 'new@user', NULL, 'test'),
	(19, 10, 'user', 'new@user', NULL, 'test'),
	(20, 10, 'user', 'new@user', NULL, 'test'),
	(21, 10, 'user', 'new@user', NULL, 'test'),
	(22, 10, 'user', 'new@user', NULL, ''),
	(23, 100, 'user', 'new@user', NULL, 'test'),
	(24, 50, 'user', 'new@user', NULL, ''),
	(25, 100, 'user', 'new@user', NULL, ''),
	(26, 50, 'user', 'new@user', NULL, 'test'),
	(69, 50, 'user@user.fr', 'test@test.fr', NULL, 'test'),
	(82, 10, 'test@test.fr', 'diane@admin.fr', NULL, NULL),
	(83, 10, 'test@test.fr', 'diane@admin.fr', NULL, NULL),
	(84, 10, 'test@test.fr', 'diane@admin.fr', NULL, NULL),
	(85, 10, 'test@test.fr', 'diane@admin.fr', NULL, NULL),
	(86, 10, 'test@test.fr', 'diane@admin.fr', NULL, NULL),
	(87, 10, 'test@test.fr', 'diane@admin.fr', NULL, NULL),
	(88, 10, 'test@test.fr', 'diane@admin.fr', NULL, NULL),
	(89, 10, 'test@test.fr', 'diane@admin.fr', NULL, NULL),
	(90, 10, 'test@test.fr', 'diane@admin.fr', NULL, NULL),
	(91, 10, 'test@test.fr', 'diane@admin.fr', NULL, NULL),
	(95, 10, 'test@test.fr', 'diane@admin.fr', NULL, NULL),
	(99, 10, 'test@test.fr', 'diane@admin.fr', NULL, NULL),
	(103, 10, 'test@test.fr', 'diane@admin.fr', NULL, NULL),
	(104, 10, 'test@test.fr', 'diane@admin.fr', NULL, NULL),
	(105, 10, 'test@test.fr', 'diane@admin.fr', NULL, NULL),
	(111, 10, 'test@test.fr', 'diane@admin.fr', NULL, NULL),
	(115, 10, 'test@test.fr', 'diane@admin.fr', NULL, NULL),
	(119, 10, 'test@test.fr', 'diane@admin.fr', NULL, NULL),
	(123, 10, 'test@test.fr', 'diane@admin.fr', NULL, NULL),
	(124, 1, 'diane@admin.fr', 'user@user.fr', NULL, 'test'),
	(125, 1, 'diane@admin.fr', 'user@user.fr', NULL, ''),
	(126, 1, 'diane@admin.fr', 'user@user.fr', NULL, ''),
	(127, 10, 'diane@admin.fr', 'user@user.fr', NULL, ''),
	(128, 50, 'diane@admin.fr', 'user@user.fr', NULL, ''),
	(129, 5, 'diane@admin.fr', 'user@user.fr', NULL, ''),
	(130, 6, 'diane@admin.fr', 'user@user.fr', NULL, ''),
	(131, 3, 'diane@admin.fr', 'user@user.fr', NULL, ''),
	(132, 4, 'diane@admin.fr', 'user@user.fr', NULL, ''),
	(133, 4, 'diane@admin.fr', 'user@user.fr', NULL, ''),
	(134, 6, 'diane@admin.fr', 'user@user.fr', NULL, ''),
	(138, 10, 'test@test.fr', 'diane@admin.fr', NULL, NULL);
/*!40000 ALTER TABLE `transaction_pay_my_buddy` ENABLE KEYS */;

-- Listage de la structure de la table paymybuddy. user
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` bigint(20) NOT NULL,
  `active` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sold` double NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- Listage des données de la table paymybuddy.user : -1 rows
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `active`, `email`, `name`, `last_name`, `password`, `sold`) VALUES
	(2, 1, 'test@test.fr', 'test', 'test', '$2a$10$/NfruUulbWmclrqT3gL5fuBXOV.05/USY1/zlOojOnJtdaYgZsTj.', 248.9999999999999),
	(4, 1, 'user@user.fr', 'user', 'user', '$2a$10$n/xrZQkj5U2OQw6r9sSeHOoem7QeB/cTI21iZhVjdcnt.yeIzgXb2', 909),
	(1, 1, 'diane@admin.fr', 'diane', 'admin', '$2a$10$OdfPpytZo.6lPPgVOiEk0OQzbBSH6uJ2W60.wkWP.G0W.YXritU/C', 99891.54500000004),
	(3, 1, 'email@email.fr', 'email', 'email', '$2a$10$UnosivUzNll//nJ00ZEscOk623QXJoP/YIdPH/R4BxQa/HEGrd2Y.', 0),
	(137, 1, 'test@save.fr', 'test', 'test', '$2a$10$fybyE0bFj0.zYIvrBY8dYuemQz7ZlzOZl0teFTkvVBRx4WbbFQcu.', 0),
	(122, 1, 'test@save.fr', 'test', 'test', '$2a$10$5iDzlkesv7aSesyNDcQ9keUj/fhu/al7K9W7w6IT/ZZX.9nSLYuUK', 0),
	(118, 1, 'test@save.fr', 'test', 'test', '$2a$10$Y/cxaABFUOnPqG50DQ4RmOsGTuPD5sRCQoqvKr7Lg7Q7wus5pBZhG', 0),
	(110, 1, 'test@save.fr', 'test', 'test', '$2a$10$MU51bngcrYChpJ4xhKsJfu8qag8JlCICXaDrxfXEBzlwqrngx/rX6', 0),
	(114, 1, 'test@save.fr', 'test', 'test', '$2a$10$KN78./7sU4iuX4o7pyhTFu8BeqgYiD07lMA3d0zJtU/H9WuXJ/WDi', 0),
	(107, 1, 'diane@diane.fr', 'diane', 'diane', '$2a$10$0D3ZhFb5XJCR0yQzWP95ReUJGxoNIXGT152wBeVz0MEOiD4FbJJRm', 0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Listage de la structure de la table paymybuddy. user_connection
CREATE TABLE IF NOT EXISTS `user_connection` (
  `user_id` bigint(20) NOT NULL,
  `connection_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`connection_id`),
  KEY `FKg2v4e5n6gispc2qi3h0091k2q` (`connection_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- Listage des données de la table paymybuddy.user_connection : -1 rows
/*!40000 ALTER TABLE `user_connection` DISABLE KEYS */;
INSERT INTO `user_connection` (`user_id`, `connection_id`) VALUES
	(1, 2),
	(2, 1),
	(2, 3),
	(2, 4),
	(4, 1),
	(4, 2),
	(4, 4);
/*!40000 ALTER TABLE `user_connection` ENABLE KEYS */;

-- Listage de la structure de la table paymybuddy. user_model
CREATE TABLE IF NOT EXISTS `user_model` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- Listage des données de la table paymybuddy.user_model : -1 rows
/*!40000 ALTER TABLE `user_model` DISABLE KEYS */;
INSERT INTO `user_model` (`id`, `email`, `first_name`, `last_name`, `password`) VALUES
	(4, 'your email', 'your first name', 'your last name', 'your password');
/*!40000 ALTER TABLE `user_model` ENABLE KEYS */;

-- Listage de la structure de la table paymybuddy. user_role
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- Listage des données de la table paymybuddy.user_role : -1 rows
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
	(2, 1),
	(4, 1),
	(8, 1),
	(15, 1),
	(36, 1),
	(41, 1),
	(44, 1),
	(47, 1),
	(50, 1),
	(53, 1),
	(56, 1),
	(58, 1),
	(60, 1),
	(62, 1),
	(64, 1),
	(68, 1),
	(71, 1),
	(73, 1),
	(75, 1),
	(78, 1),
	(81, 1),
	(94, 1),
	(98, 1),
	(102, 1),
	(107, 1),
	(110, 1),
	(114, 1),
	(118, 1),
	(122, 1),
	(137, 1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

-- Listage de la structure de la table paymybuddy. user_service
CREATE TABLE IF NOT EXISTS `user_service` (
  `id` bigint(20) NOT NULL,
  `confirm_password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- Listage des données de la table paymybuddy.user_service : -1 rows
/*!40000 ALTER TABLE `user_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_service` ENABLE KEYS */;

-- Listage de la structure de la table paymybuddy. user_transaction
CREATE TABLE IF NOT EXISTS `user_transaction` (
  `user_id` bigint(20) NOT NULL,
  `transaction_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`transaction_id`),
  KEY `FKhxuhwahnjbj0qmvnldg2ke796` (`transaction_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- Listage des données de la table paymybuddy.user_transaction : -1 rows
/*!40000 ALTER TABLE `user_transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_transaction` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
