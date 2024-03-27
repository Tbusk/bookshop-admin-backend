-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.3.2-MariaDB-1:11.3.2+maria~ubu2204 - mariadb.org binary distribution
-- Server OS:                    debian-linux-gnu
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for bookshop
CREATE DATABASE IF NOT EXISTS `bookshop` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `bookshop`;

-- Dumping structure for table bookshop.book
CREATE TABLE IF NOT EXISTS `book` (
  `book_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `isbn_10` varchar(16) DEFAULT NULL,
  `isbn_13` varchar(16) DEFAULT NULL,
  `title` varchar(128) NOT NULL,
  `description` varchar(5120) NOT NULL,
  `genre` varchar(64) NOT NULL DEFAULT '0',
  `image` varchar(128) NOT NULL,
  `author` varchar(128) NOT NULL DEFAULT '',
  `publisher` varchar(128) DEFAULT NULL,
  `language` varchar(32) NOT NULL DEFAULT 'English',
  `page_count` smallint(5) unsigned NOT NULL DEFAULT 0,
  `release_date` date NOT NULL DEFAULT current_timestamp(),
  `hardcover_price` double unsigned NOT NULL DEFAULT 0,
  `paperback_price` double unsigned NOT NULL DEFAULT 0,
  `ebook_price` double unsigned NOT NULL DEFAULT 0,
  `audiobook_price` double unsigned NOT NULL DEFAULT 0,
  `ratings` double unsigned NOT NULL DEFAULT 0,
  `ratings_count` int(10) unsigned NOT NULL DEFAULT 0,
  PRIMARY KEY (`book_id`),
  KEY `author_id` (`author`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table bookshop.book: ~5 rows (approximately)
INSERT INTO `book` (`book_id`, `isbn_10`, `isbn_13`, `title`, `description`, `genre`, `image`, `author`, `publisher`, `language`, `page_count`, `release_date`, `hardcover_price`, `paperback_price`, `ebook_price`, `audiobook_price`, `ratings`, `ratings_count`) VALUES
	(14, '', '979-8735581611', 'End Game: A Jack Noble Thriller', 'Marcus Hamilton Thanos is marked for death. And Jack Noble is the man for the job. But when the high-profile target vanishes the day of the assassination attempt, Jack is forced to team up with a female FBI agent who was poised to learn Thanos\'s secret that morning.  Together they are plunged into a frantic race across state lines and international borders in order to solve the mystery, all the while unsure of who they can trust.  And what they discover is that the truth is more chilling and deceptive than either of them could have imagined.  Fans of Tom Clancy\'s Jack Ryan, Lee Child\'s Jack Reacher, Vince Flynn\'s Mitch Rapp, and Robert Ludlum\'s Jason Bourne will enjoy this Jack Noble suspense thriller.', 'Murder Thriller', 'https://m.media-amazon.com/images/I/71xGa2AHGrL._SY466_.jpg', 'L.T. Ryan', 'Independent', 'English', 310, '2023-03-31', 21.99, 14.99, 2.99, 13.96, 4.4, 9737),
	(15, '0062420704', '978-0062420701', 'To Kill a Mockingbird', '"Shoot all the bluejays you want, if you can hit \'em, but remember it\'s a sin to kill a mockingbird."  A lawyer\'s advice to his children as he defends the real mockingbird of Harper Lee\'s classic novel—a black man charged with the rape of a white girl. Through the young eyes of Scout and Jem Finch, Harper Lee explores with rich humor and unswerving honesty the irrationality of adult attitudes toward race and class in the Deep South of the 1930s. The conscience of a town steeped in prejudice, violence, and hypocrisy is pricked by the stamina and quiet heroism of one man\'s struggle for justice—but the weight of history will only tolerate so much.', 'Classic Literature & Fiction', 'https://m.media-amazon.com/images/I/61zxX3+G+tL._SY466_.jpg', 'Harper Lee', 'Harper', 'English', 336, '2004-03-24', 16, 8.89, 7.99, 17.63, 4.7, 134683),
	(17, '9388144295', '978-9388144292', 'The Iliad & the Odyssey', ' Embark on a literary odyssey through ancient Greece with Homer\'s timeless epics, The Iliad and The Odyssey. In The Iliad, witness the ravages of the Trojan War as gods and mortals clash in a tale of honor, heroism, and the consequences of unchecked pride. Then, journey alongside Odysseus in The Odyssey as he battles mythical creatures, evades vengeful gods, ad strives to return home, navigating treacherous seas and testing the limits of human resilience. These masterpieces of ancient literature capture the essence of the human experience, exploring themes of love, loss, destiny, and the indomitable spirit of adventure.', 'Classic Literature & Fiction', 'https://m.media-amazon.com/images/I/810rw5b5WxL._SY466_.jpg', 'Homer', 'Fingerprint! Publishing', 'English', 768, '2024-03-24', 22.49, 0, 1.99, 0, 4.1, 3463),
	(19, '9354403786', '978-9354403781', 'Dracula', 'Experience Bram Stoker\'s timeless masterpiece like never before with the Dracula Deluxe Hardbound Edition. Immerse yourself in the chilling tale of the vampire count and his nocturnal reign of terror. This beautifully crafted edition features a luxurious hardcover binding, making it a perfect addition to any collector\'s library.', 'Classic Literature & Fiction', 'https://m.media-amazon.com/images/I/51V+jAWqSgL._SX342_SY445_.jpg', 'Bram Stoker', 'Fingerprint! Publishing', '', 460, '2024-03-25', 17.99, 11.99, 0.99, 0, 4, 411),
	(20, '0451530063', '978-0451530066', 'Crime and Punishment', 'Dostoyevsky’s epic masterpiece, unabridged, with an afterword by Robin Feuer Miller  One of the world’s greatest novels, Crime and Punishment is the story of a murder and its consequences—an unparalleled tale of suspense set in the midst of nineteenth-century Russia’s troubled transition to the modern age.   In the slums of czarist St. Petersburg lives young Raskolnikov, a sensitive, intellectual student. The poverty he has always known drives him to believe that he is exempt from moral law. But when he puts this belief to the test, he suffers unbearably. Crime and punishment, the novel reminds us, grow from the same seed. ', 'Literary Fiction', 'https://m.media-amazon.com/images/I/71O2XIytdqL._SY466_.jpg', 'Fyodor Dostoyevsky', 'Signet', 'English', 560, '2024-03-25', 0, 7.95, 4.99, 0, 4.3, 478);

-- Dumping structure for table bookshop.user
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(64) NOT NULL,
  `first_name` varchar(64) DEFAULT NULL,
  `last_name` varchar(64) DEFAULT NULL,
  `email_address` varchar(64) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `profile_picture` varchar(128) NOT NULL DEFAULT 'https://icons.getbootstrap.com/assets/icons/person-circle.svg',
  `role` varchar(50) NOT NULL DEFAULT 'USER',
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  `locked` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  KEY `username_password` (`username`,`password`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table bookshop.user: ~2 rows (approximately)
INSERT INTO `user` (`user_id`, `username`, `password`, `first_name`, `last_name`, `email_address`, `phone_number`, `profile_picture`, `role`, `enabled`, `locked`) VALUES
	(1, 'tjames', '$2a$10$kDf8CANThWyHu45k5Sj1Iug3ZzoMWWN2ly/CDgBc6KUHiZANNyXz6', 'Thomas', 'James', 'tjames@bookshop.com', '1-123-4567', 'https://icons.getbootstrap.com/assets/icons/person-circle.svg', 'USER', b'1', b'0'),
	(2, 'john123', '$2a$10$kDf8CANThWyHu45k5Sj1Iug3ZzoMWWN2ly/CDgBc6KUHiZANNyXz6', 'John', 'Doe', 'john123@bookshop.com', '1-234-5678', 'https://icons.getbootstrap.com/assets/icons/person-circle.svg', 'USER', b'1', b'0');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
