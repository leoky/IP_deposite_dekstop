-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.24


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema ipi_dekstop
--

CREATE DATABASE IF NOT EXISTS ipi_dekstop;
USE ipi_dekstop;

--
-- Definition of table `deposito`
--

DROP TABLE IF EXISTS `deposito`;
CREATE TABLE `deposito` (
  `dep_id` int(11) NOT NULL AUTO_INCREMENT,
  `dep_bank` varchar(45) NOT NULL,
  `dep_tgl_jto` date DEFAULT NULL,
  `dep_tgl_perpanjangan` date DEFAULT NULL,
  `dep_company` varchar(12) NOT NULL,
  `dep_no_bilyet_1` varchar(12) NOT NULL,
  `dep_no_bilyet_2` varchar(12) NOT NULL,
  `dep_type` varchar(20) NOT NULL,
  `dep_nilai` double NOT NULL,
  `dep_bunga` double NOT NULL,
  `dep_hari` double NOT NULL,
  `dep_gross` double NOT NULL,
  `dep_tax` int(10) unsigned NOT NULL,
  `dep_nett` double NOT NULL,
  `dep_pokokdanBunga` double NOT NULL,
  `dep_nama` varchar(45) NOT NULL,
  `dep_pencairan` tinyint(1) NOT NULL,
  PRIMARY KEY (`dep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `deposito`
--

/*!40000 ALTER TABLE `deposito` DISABLE KEYS */;
INSERT INTO `deposito` (`dep_id`,`dep_bank`,`dep_tgl_jto`,`dep_tgl_perpanjangan`,`dep_company`,`dep_no_bilyet_1`,`dep_no_bilyet_2`,`dep_type`,`dep_nilai`,`dep_bunga`,`dep_hari`,`dep_gross`,`dep_tax`,`dep_nett`,`dep_pokokdanBunga`,`dep_nama`,`dep_pencairan`) VALUES 
 (1,'bca','2017-11-04','2017-11-30','PRIBADI','12345678','87654321','ARO',220000000,6,26,940273.9726027397,94027,846246.5753424657,220846246.57534248,'leo',1),
 (2,'bca','2017-11-30','2017-12-30','PRIBADI','12345678','87654321','ARO',220846246.58,6,30,1089104.7776547945,108910,980194.299889315,221826440.87988934,'leo',1),
 (3,'bca','2017-12-26','2018-01-26','PRIBADI','12345678','87654321','ARO',221826440.88,6,31,1130403.2329775342,113040,1017362.9096797807,222843803.78967977,'leo',1),
 (4,'bca','2017-11-24','2017-12-24','PRIBADI','12345678','87654321','ARO',222843803.79,6,29,1062323.8865605479,106232,956091.497904493,223799895.28790447,'kjjhkkjh',1),
 (5,'bca','2017-11-10','2017-12-10','PRIBADI','12345678','87654321','TT',222843803.79,6,29,1062323.8865605479,106232,956091.497904493,0,'sdsadasdsakjjhkkjh',1);
/*!40000 ALTER TABLE `deposito` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `u_username` varchar(45) NOT NULL,
  `u_password` varchar(45) NOT NULL,
  PRIMARY KEY (`u_username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`u_username`,`u_password`) VALUES 
 ('ipi','ipi');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
