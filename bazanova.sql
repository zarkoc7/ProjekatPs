/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 10.4.21-MariaDB : Database - iznajmljivanje_etrotineta
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`iznajmljivanje_etrotineta` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `iznajmljivanje_etrotineta`;

/*Table structure for table `cenovnik` */

DROP TABLE IF EXISTS `cenovnik`;

CREATE TABLE `cenovnik` (
  `cenovnikId` bigint(10) NOT NULL AUTO_INCREMENT,
  `vaziOd` date NOT NULL,
  `vaziDo` date NOT NULL,
  `userId` bigint(10) NOT NULL,
  PRIMARY KEY (`cenovnikId`),
  KEY `c1_s` (`userId`),
  CONSTRAINT `c1_s` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;

/*Data for the table `cenovnik` */

insert  into `cenovnik`(`cenovnikId`,`vaziOd`,`vaziDo`,`userId`) values 
(3,'2024-01-29','2024-02-05',1),
(6,'2024-04-12','2024-04-16',1),
(13,'2027-02-02','2027-02-05',1),
(21,'2024-08-02','2024-08-04',1),
(22,'2026-02-01','2026-05-01',1),
(23,'2024-03-02','2024-03-12',1),
(24,'2024-03-16','2024-03-21',1);

/*Table structure for table `klijent` */

DROP TABLE IF EXISTS `klijent`;

CREATE TABLE `klijent` (
  `klijentId` bigint(10) NOT NULL AUTO_INCREMENT,
  `imeK` varchar(20) NOT NULL,
  `prezimeK` varchar(20) NOT NULL,
  `jmbg` varchar(20) NOT NULL,
  `kontakt` varchar(20) NOT NULL,
  `userId` bigint(10) NOT NULL,
  PRIMARY KEY (`klijentId`),
  KEY `c2n_2` (`userId`),
  CONSTRAINT `c2n_2` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

/*Data for the table `klijent` */

insert  into `klijent`(`klijentId`,`imeK`,`prezimeK`,`jmbg`,`kontakt`,`userId`) values 
(1,'Darko','Muminovic','2534567654234','0631256433',1),
(3,'Milutin','Milankovic','1436543423345','0653265432',1),
(10,'Boris','Malagurski','342256754353','061234553',1);

/*Table structure for table `marka` */

DROP TABLE IF EXISTS `marka`;

CREATE TABLE `marka` (
  `markaId` bigint(10) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(20) NOT NULL,
  `model` varchar(20) NOT NULL,
  PRIMARY KEY (`markaId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `marka` */

insert  into `marka`(`markaId`,`naziv`,`model`) values 
(1,'Apollo','City V3'),
(2,'Kaabo','Wolf king'),
(3,'Apollo','Air V3');

/*Table structure for table `potvrda` */

DROP TABLE IF EXISTS `potvrda`;

CREATE TABLE `potvrda` (
  `potvrdaId` bigint(10) NOT NULL AUTO_INCREMENT,
  `datumOd` date NOT NULL,
  `datumDo` date NOT NULL,
  `cenaP` double NOT NULL,
  `sifraT` varchar(10) NOT NULL,
  `klijentId` bigint(10) NOT NULL,
  `stavkaCenovnikaId` bigint(10) NOT NULL,
  `userId` bigint(10) NOT NULL,
  `cenovnikId` bigint(10) NOT NULL,
  PRIMARY KEY (`potvrdaId`),
  KEY `pnesf_1` (`sifraT`),
  KEY `pness_2` (`klijentId`),
  KEY `pness234` (`userId`),
  KEY `pness5` (`stavkaCenovnikaId`,`cenovnikId`),
  CONSTRAINT `pnesf_1` FOREIGN KEY (`sifraT`) REFERENCES `trotinet` (`sifraT`) ON DELETE CASCADE,
  CONSTRAINT `pness234` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  CONSTRAINT `pness5` FOREIGN KEY (`stavkaCenovnikaId`, `cenovnikId`) REFERENCES `stavkacenovnika` (`stavkaCenovnikaId`, `cenovnikId`),
  CONSTRAINT `pness_2` FOREIGN KEY (`klijentId`) REFERENCES `klijent` (`klijentID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;

/*Data for the table `potvrda` */

insert  into `potvrda`(`potvrdaId`,`datumOd`,`datumDo`,`cenaP`,`sifraT`,`klijentId`,`stavkaCenovnikaId`,`userId`,`cenovnikId`) values 
(16,'2024-04-12','2024-04-16',176,'o44',10,1,1,6),
(17,'2026-01-05','2026-01-10',110,'o22',3,1,1,22),
(20,'2024-02-16','2024-02-21',55,'o444',3,1,1,3);

/*Table structure for table `stavkacenovnika` */

DROP TABLE IF EXISTS `stavkacenovnika`;

CREATE TABLE `stavkacenovnika` (
  `stavkaCenovnikaId` bigint(10) NOT NULL,
  `cenovnikId` bigint(10) NOT NULL,
  `cena` double NOT NULL,
  `valuta` varchar(20) NOT NULL,
  `tipObracuna` varchar(20) NOT NULL,
  PRIMARY KEY (`stavkaCenovnikaId`,`cenovnikId`),
  KEY `csdsadas` (`cenovnikId`),
  CONSTRAINT `csdsadas` FOREIGN KEY (`cenovnikId`) REFERENCES `cenovnik` (`cenovnikId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `stavkacenovnika` */

insert  into `stavkacenovnika`(`stavkaCenovnikaId`,`cenovnikId`,`cena`,`valuta`,`tipObracuna`) values 
(1,3,11,'EURO','GOTOVINA'),
(1,6,44,'EURO','KARTICA'),
(1,22,22,'EURO','GOTOVINA'),
(1,23,24,'EURO','GOTOVINA'),
(1,24,20,'EURO','GOTOVINA'),
(2,13,5000,'RSD','KARTICA'),
(2,23,3000,'RSD','GOTOVINA'),
(2,24,2600,'RSD','KARTICA'),
(3,22,32,'EURO','KARTICA'),
(3,24,22,'EURO','GOTOVINA'),
(9,21,33,'EURO','GOTOVINA'),
(10,21,3333,'RSD','GOTOVINA'),
(11,21,222,'EURO','GOTOVINA');

/*Table structure for table `trotinet` */

DROP TABLE IF EXISTS `trotinet`;

CREATE TABLE `trotinet` (
  `sifraT` varchar(10) NOT NULL,
  `maxSpeed` int(10) NOT NULL,
  `maxRange` int(10) NOT NULL,
  `maxPower` int(10) NOT NULL,
  `maxPayload` int(10) NOT NULL,
  `markaId` bigint(10) NOT NULL,
  PRIMARY KEY (`sifraT`,`maxSpeed`,`maxRange`,`maxPower`,`maxPayload`,`markaId`),
  KEY `trt_id` (`markaId`),
  CONSTRAINT `trt_id` FOREIGN KEY (`MarkaId`) REFERENCES `marka` (`MarkaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `trotinet` */

insert  into `trotinet`(`sifraT`,`maxSpeed`,`maxRange`,`maxPower`,`maxPayload`,`markaId`) values 
('01',511,69,500,120,1),
('02',652,180,4001,150,2),
('o15',102,70,66,120,1),
('o22',222,33,44,55,1),
('o44',22,233,44,55,1),
('o444',222,44,111,33,3),
('o445',55,70,40,120,1),
('s1',39,120,87,111,2);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userId` bigint(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `ime` varchar(20) NOT NULL,
  `prezime` varchar(20) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

insert  into `user`(`userId`,`username`,`password`,`ime`,`prezime`) values 
(1,'zarko','zarko123','Zarko','Cvijanovic'),
(2,'marko','marko123','Marko','Markovic');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
