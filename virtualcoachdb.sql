-- phpMyAdmin SQL Dump
-- version 4.0.10.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 20, 2015 at 09:32 AM
-- Server version: 5.5.42-log
-- PHP Version: 5.4.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `virtualcoachdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `activitygoal`
--

CREATE TABLE IF NOT EXISTS `activitygoal` (
  `pid` int(11) NOT NULL,
  `agmeasurement` varchar(30) CHARACTER SET latin1 NOT NULL,
  `agvalue` float NOT NULL,
  `agstartdate` date NOT NULL,
  `agenddate` date NOT NULL,
  `agperday` tinyint(1) NOT NULL,
  PRIMARY KEY (`pid`,`agmeasurement`,`agstartdate`),
  KEY `FK_activitygoal_AGMEASUREMENT` (`agmeasurement`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `activitygoal`
--

INSERT INTO `activitygoal` (`pid`, `agmeasurement`, `agvalue`, `agstartdate`, `agenddate`, `agperday`) VALUES
(1, 'run', 30, '2015-06-01', '2015-07-03', 1),
(1, 'run', 30, '2015-07-01', '2015-07-03', 1),
(1, 'walk', 70, '2015-03-24', '2015-03-24', 1),
(1, 'walk', 30, '2015-04-01', '2015-04-03', 1),
(1, 'walk', 30, '2015-05-01', '2015-07-03', 1),
(2, 'walk', 30, '2015-03-01', '2015-03-03', 1);

-- --------------------------------------------------------

--
-- Table structure for table `healthprofile`
--

CREATE TABLE IF NOT EXISTS `healthprofile` (
  `pid` int(11) NOT NULL,
  `pdate` date NOT NULL,
  `pmeasurement` varchar(30) NOT NULL,
  `pvalue` float NOT NULL,
  PRIMARY KEY (`pid`,`pmeasurement`),
  KEY `pmeasurement` (`pmeasurement`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `healthprofile`
--

INSERT INTO `healthprofile` (`pid`, `pdate`, `pmeasurement`, `pvalue`) VALUES
(1, '2015-01-04', 'dpb', 82),
(1, '2015-06-09', 'height', 1.7),
(1, '2015-03-04', 'weight', 80),
(2, '2015-01-04', 'height', 1.91),
(2, '2015-01-04', 'weight', 91),
(4, '2015-03-04', 'weight', 60),
(6, '2015-01-04', 'height', 1.72),
(6, '2015-01-04', 'weight', 72);

-- --------------------------------------------------------

--
-- Table structure for table `healthprofilegoal`
--

CREATE TABLE IF NOT EXISTS `healthprofilegoal` (
  `pid` int(11) NOT NULL,
  `gmeasurement` varchar(30) NOT NULL,
  `goalvalue` float NOT NULL,
  `goaldate` date NOT NULL,
  PRIMARY KEY (`pid`,`gmeasurement`,`goaldate`),
  KEY `gmeasurement` (`gmeasurement`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `healthprofilegoal`
--

INSERT INTO `healthprofilegoal` (`pid`, `gmeasurement`, `goalvalue`, `goaldate`) VALUES
(1, 'weight', 75, '2015-07-19');

-- --------------------------------------------------------

--
-- Table structure for table `healthymeasure`
--

CREATE TABLE IF NOT EXISTS `healthymeasure` (
  `nmeasurement` varchar(30) NOT NULL,
  `nminvalue` float NOT NULL,
  `nmaxvalue` float NOT NULL,
  PRIMARY KEY (`nmeasurement`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `healthymeasure`
--

INSERT INTO `healthymeasure` (`nmeasurement`, `nminvalue`, `nmaxvalue`) VALUES
('bmi', 18.5, 25),
('dpb', 80, 80),
('sbp', 120, 120);

-- --------------------------------------------------------

--
-- Table structure for table `hhealthprofile`
--

CREATE TABLE IF NOT EXISTS `hhealthprofile` (
  `pid` int(11) NOT NULL,
  `hdate` date NOT NULL,
  `hmeasurement` varchar(30) NOT NULL,
  `hvale` float NOT NULL,
  PRIMARY KEY (`pid`,`hdate`,`hmeasurement`,`hvale`),
  KEY `hmeasurement` (`hmeasurement`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `measurementdefinition`
--

CREATE TABLE IF NOT EXISTS `measurementdefinition` (
  `measurement` varchar(30) NOT NULL,
  `type` varchar(30) NOT NULL,
  PRIMARY KEY (`measurement`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `measurementdefinition`
--

INSERT INTO `measurementdefinition` (`measurement`, `type`) VALUES
('bmi', 'float'),
('dpb', 'float'),
('height', 'meters'),
('run', 'minuts'),
('sbp', 'float'),
('walk', 'minutes '),
('weight', 'kg');

-- --------------------------------------------------------

--
-- Table structure for table `motivationalphrases`
--

CREATE TABLE IF NOT EXISTS `motivationalphrases` (
  `mid` int(11) NOT NULL,
  `motivationph` varchar(400) NOT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `motivationalphrases`
--

INSERT INTO `motivationalphrases` (`mid`, `motivationph`) VALUES
(1, '“Age is no barrier. It’s a limitation you put on your mind.” – Jackie Joyner-Kersee'),
(2, '“Most people give up just when they’re about to achieve success. They quit on the one yard line. They give up at the last minute of the game one foot from a winning touchdown.” – Ross Perot'),
(3, '“There may be people that have more talent than you, but theres no excuse for anyone to work harder than you do.” – Derek Jeter'),
(4, '“The five S’s of sports training are: stamina, speed, strength, skill, and spirit; but the greatest of these is spirit.” – Ken Doherty'),
(5, '“Never give up, never give in, and when the upper hand is ours, may we have the ability to handle the win with the dignity that we absorbed the loss.” – Doug Williams'),
(6, '“Persistence can change failure into extraordinary achievement.” – Marv Levy'),
(7, '“I’ve learned that something constructive comes from every defeat.” – Tom Landry'),
(8, '“Set your goals high, and don’t stop till you get there.” – Bo Jackson'),
(9, '“Most people never run far enough on their first wind to find out they’ve got a second.” – William James'),
(10, '“If at first you don’t succeed, you are running about average.” – M.H. Alderson');

-- --------------------------------------------------------

--
-- Table structure for table `personalactivities`
--

CREATE TABLE IF NOT EXISTS `personalactivities` (
  `pid` int(11) NOT NULL,
  `pameasurement` varchar(30) CHARACTER SET latin1 NOT NULL,
  `pavalue` int(11) NOT NULL,
  `padate` date NOT NULL,
  PRIMARY KEY (`pid`,`pameasurement`,`padate`),
  KEY `FK_personalactivities_PAMEASUREMENT` (`pameasurement`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `personalactivities`
--

INSERT INTO `personalactivities` (`pid`, `pameasurement`, `pavalue`, `padate`) VALUES
(1, 'walk', 40, '2015-03-03'),
(1, 'walk', 30, '2015-04-03'),
(1, 'walk', 40, '2015-06-17'),
(1, 'walk', 40, '2015-06-18'),
(1, 'walk', 40, '2015-06-19');

-- --------------------------------------------------------

--
-- Table structure for table `personprofile`
--

CREATE TABLE IF NOT EXISTS `personprofile` (
  `pid` int(11) NOT NULL COMMENT 'auto increment',
  `fname` varchar(30) NOT NULL,
  `lname` varchar(30) NOT NULL,
  `dofb` date NOT NULL,
  `sex` varchar(1) NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `personprofile`
--

INSERT INTO `personprofile` (`pid`, `fname`, `lname`, `dofb`, `sex`) VALUES
(1, 'Hena B', 'Beke', '2005-04-10', 'M'),
(2, 'b', 'Henok Ze man', '2005-03-10', 'M'),
(4, 'Hirut Bekele', 'Eshete', '2005-04-10', 'M'),
(5, 'Daniel', 'Bekele', '2005-03-10', 'M'),
(6, 'Kda12', 'A', '2005-04-10', 'f');

-- --------------------------------------------------------

--
-- Table structure for table `reminders`
--

CREATE TABLE IF NOT EXISTS `reminders` (
  `pid` int(11) NOT NULL,
  `numberofremdperday` int(11) NOT NULL,
  `toberemind` varchar(400) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`pid`,`numberofremdperday`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `reminders`
--

INSERT INTO `reminders` (`pid`, `numberofremdperday`, `toberemind`, `date`) VALUES
(1, 1, 'go to doctor', '2015-06-01 03:00:00'),
(1, 2, 'go to museum', '2015-06-23 04:00:00'),
(1, 4, 'go to museum', '2015-06-23 00:00:00'),
(1, 5, 'go to museum', '2015-06-27 00:00:00'),
(1, 9, 'go to museum', '2015-06-02 00:00:00'),
(1, 10, 'go to museum', '2015-06-17 00:00:00'),
(1, 11, 'go to doctor', '2015-06-18 00:00:00'),
(1, 19, 'go to doctor', '2015-06-19 00:00:00');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `activitygoal`
--
ALTER TABLE `activitygoal`
  ADD CONSTRAINT `activitygoal_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `personprofile` (`pid`),
  ADD CONSTRAINT `activitygoal_ibfk_2` FOREIGN KEY (`agmeasurement`) REFERENCES `measurementdefinition` (`measurement`),
  ADD CONSTRAINT `FK_activitygoal_AGMEASUREMENT` FOREIGN KEY (`agmeasurement`) REFERENCES `measurementdefinition` (`measurement`),
  ADD CONSTRAINT `FK_activitygoal_PID` FOREIGN KEY (`pid`) REFERENCES `personprofile` (`pid`);

--
-- Constraints for table `healthprofile`
--
ALTER TABLE `healthprofile`
  ADD CONSTRAINT `FK_healthprofile_PID` FOREIGN KEY (`pid`) REFERENCES `personprofile` (`pid`),
  ADD CONSTRAINT `FK_healthprofile_PMEASUREMENT` FOREIGN KEY (`pmeasurement`) REFERENCES `measurementdefinition` (`measurement`),
  ADD CONSTRAINT `healthprofile_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `personprofile` (`pid`),
  ADD CONSTRAINT `healthprofile_ibfk_2` FOREIGN KEY (`pmeasurement`) REFERENCES `measurementdefinition` (`measurement`);

--
-- Constraints for table `healthprofilegoal`
--
ALTER TABLE `healthprofilegoal`
  ADD CONSTRAINT `FK_healthprofilegoal_GMEASUREMENT` FOREIGN KEY (`gmeasurement`) REFERENCES `measurementdefinition` (`measurement`),
  ADD CONSTRAINT `FK_healthprofilegoal_PID` FOREIGN KEY (`pid`) REFERENCES `personprofile` (`pid`),
  ADD CONSTRAINT `healthprofilegoal_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `personprofile` (`pid`),
  ADD CONSTRAINT `healthprofilegoal_ibfk_2` FOREIGN KEY (`gmeasurement`) REFERENCES `measurementdefinition` (`measurement`);

--
-- Constraints for table `healthymeasure`
--
ALTER TABLE `healthymeasure`
  ADD CONSTRAINT `FK_healthymeasure_NMEASUREMENT` FOREIGN KEY (`nmeasurement`) REFERENCES `measurementdefinition` (`measurement`),
  ADD CONSTRAINT `healthymeasure_ibfk_1` FOREIGN KEY (`nmeasurement`) REFERENCES `measurementdefinition` (`measurement`);

--
-- Constraints for table `hhealthprofile`
--
ALTER TABLE `hhealthprofile`
  ADD CONSTRAINT `FK_hhealthprofile_HMEASUREMENT` FOREIGN KEY (`hmeasurement`) REFERENCES `measurementdefinition` (`measurement`),
  ADD CONSTRAINT `FK_hhealthprofile_PID` FOREIGN KEY (`pid`) REFERENCES `personprofile` (`pid`),
  ADD CONSTRAINT `hhealthprofile_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `personprofile` (`pid`),
  ADD CONSTRAINT `hhealthprofile_ibfk_2` FOREIGN KEY (`hmeasurement`) REFERENCES `measurementdefinition` (`measurement`);

--
-- Constraints for table `personalactivities`
--
ALTER TABLE `personalactivities`
  ADD CONSTRAINT `FK_personalactivities_PAMEASUREMENT` FOREIGN KEY (`pameasurement`) REFERENCES `measurementdefinition` (`measurement`),
  ADD CONSTRAINT `FK_personalactivities_PID` FOREIGN KEY (`pid`) REFERENCES `personprofile` (`pid`),
  ADD CONSTRAINT `personalactivities_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `personprofile` (`pid`),
  ADD CONSTRAINT `personalactivities_ibfk_2` FOREIGN KEY (`pameasurement`) REFERENCES `measurementdefinition` (`measurement`);

--
-- Constraints for table `reminders`
--
ALTER TABLE `reminders`
  ADD CONSTRAINT `reminders_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `personprofile` (`pid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
