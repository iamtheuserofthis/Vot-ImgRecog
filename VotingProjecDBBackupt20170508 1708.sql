-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.51b-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema voting_project
--

CREATE DATABASE IF NOT EXISTS voting_project;
USE voting_project;

--
-- Definition of table `castavote`
--

DROP TABLE IF EXISTS `castavote`;
CREATE TABLE `castavote` (
  `VoterID` int(10) unsigned NOT NULL,
  `Party1` tinyint(1) unsigned NOT NULL default '0',
  `Party2` tinyint(1) unsigned NOT NULL default '0',
  `Party3` tinyint(1) unsigned NOT NULL default '0',
  `Party4` tinyint(1) unsigned NOT NULL default '0',
  `Party5` tinyint(1) unsigned NOT NULL default '0',
  `DOV` date default NULL,
  `TOV` time default NULL,
  PRIMARY KEY  (`VoterID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `castavote`
--

/*!40000 ALTER TABLE `castavote` DISABLE KEYS */;
INSERT INTO `castavote` (`VoterID`,`Party1`,`Party2`,`Party3`,`Party4`,`Party5`,`DOV`,`TOV`) VALUES 
 (1001,0,1,0,0,0,'2017-05-07','22:13:50'),
 (1002,0,0,0,0,1,'2017-04-06','11:44:18'),
 (1003,0,1,0,0,0,'2017-05-07','22:13:50'),
 (1004,0,0,1,0,0,'2017-04-15','10:22:37'),
 (1005,0,1,0,0,0,'2017-04-15','14:36:09'),
 (1006,0,0,0,0,0,NULL,NULL),
 (1007,0,0,1,0,0,'2017-03-28','18:02:31'),
 (1008,0,0,1,0,0,'2017-05-07','22:13:50'),
 (1009,0,0,0,1,0,'2017-04-06','12:07:57');
/*!40000 ALTER TABLE `castavote` ENABLE KEYS */;


--
-- Definition of table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `VoterID` int(10) unsigned NOT NULL,
  `DOB` date NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Gender` varchar(45) NOT NULL,
  PRIMARY KEY  (`VoterID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` (`VoterID`,`DOB`,`Name`,`Gender`) VALUES 
 (1001,'1995-01-01','Abhishek Purohit','male'),
 (1002,'1995-02-02','Bijendra Panwar','male'),
 (1003,'1995-03-03','Gaurav Negi','male'),
 (1004,'1995-04-04','Praveen Manola','male'),
 (1005,'1995-05-05','Chamani Singh','female'),
 (1006,'1995-06-06','Emani Singh','female'),
 (1007,'1995-07-07','Eman Singh','male'),
 (1008,'1995-08-08','Gham Singh','male'),
 (1009,'1995-09-09','Ghami Singh','female');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;


--
-- Definition of table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `VoterID` int(10) unsigned NOT NULL,
  `Username` varchar(45) NOT NULL,
  `Pass` varchar(45) NOT NULL,
  `Pichead` int(10) unsigned default NULL,
  `RoleName` varchar(45) NOT NULL,
  PRIMARY KEY  (`VoterID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`VoterID`,`Username`,`Pass`,`Pichead`,`RoleName`) VALUES 
 (1001,'Abhishek Purohit','*839212EE31F4EEFCE55F1D0920F9D26FB313C6A7',2,'user'),
 (1002,'Bijendra Panwar','*8D90EAE1157FCC3E7ABED73DC8F13987834A9F45',3,'user'),
 (1003,'Gaurav Negi','*D4210A157927EC7FF20200BEBACEA3DE3E16E131',1,'admin'),
 (1004,'Praveen Manola','*2C36AD14E5CB619924355546E7CD8405D3D01CCE',4,'user');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
