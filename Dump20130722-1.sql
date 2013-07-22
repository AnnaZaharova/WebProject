CREATE DATABASE  IF NOT EXISTS `tour_agency` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tour_agency`;
-- MySQL dump 10.13  Distrib 5.5.31, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: tour_agency
-- ------------------------------------------------------
-- Server version	5.5.31-0ubuntu0.13.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tour`
--

DROP TABLE IF EXISTS `tour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tour` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tourname` varchar(255) NOT NULL,
  `type` int(1) NOT NULL,
  `details` text NOT NULL,
  `price` int(11) NOT NULL,
  `hot` int(1) DEFAULT '0',
  `regular_discount` int(2) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour`
--

LOCK TABLES `tour` WRITE;
/*!40000 ALTER TABLE `tour` DISABLE KEYS */;
INSERT INTO `tour` VALUES (1,'Горящий тур в Дубай на 7 дней',1,'Дубай – второй по величине эмират с одноименной столицей – растянулся вдоль берега Персидского Залива на 72 км. Археологические раскопки свидетельствуют о том, что рыбацкие поселения существовали на месте города Дубай более 4000 лет назад. В прошлом – небольшая прибрежная деревня, на сегодняшний день Дубай – это современный город, имеющий свой неповторимый облик. Расположившийся на берегах залива Крик, город разделен на две части: восточную Дейру и западный район – Бар Дубай. Иногда Дубай называют «Парижем Персидского Залива», ведь это главный деловой центр Ближнего Востока, один из крупнейших центров международной торговли и туризма, сердце коммерческой жизни государства',830,1,5),(2,'Шопинг тур в Милан',2,'<p>     Милан — деловой и промышленный центр Италии, а также законодатель европейской моды — это еще одно лицо многоликой Италии.\n\nСердце Милана — Пьяцца Дуомо. На этой огромной площади миланцы вместе встречают Рождество, Новый год и прочие шумные праздники. Главным сооружением на площади является Миланский Собор (Собор Дуомо), устремляющий ввысь легкие ажурные кружева готики. Неподалеку находится знаменитый оперный театр \"Ла Скала\", место испытания и посвящения в мир музыки композиторов, певцов и дирижеров с мировыми именами. Здание театра столь скромно и неприметно, что незадачливые туристы часто принимают за мировую оперную сцену галерею Витторио Эмануеле, расположенную напротив.\n\n     Круглый год в Милане проводятся демонстрации новых модных коллекций; составляющие конкуренцию Парижу. На центральных улицах via Montenapole, via Santo Spirito, via Gesu и via Sant Andrea находятся изысканные и элегантные бутики. В Выставочном Центре Fiera Milano периодически проходят торгово-промышленные выставки, где собирается деловая общественность как Италии, так и многих других стран, включая Казахстан и Россию.</p>',0,0,0);
/*!40000 ALTER TABLE `tour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_info`
--

DROP TABLE IF EXISTS `client_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_info` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `is_regular` int(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  KEY `fk_client_info_1` (`user_id`),
  CONSTRAINT `fk_client_info_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_info`
--

LOCK TABLES `client_info` WRITE;
/*!40000 ALTER TABLE `client_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `client_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`rolename`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (2,'admin'),(1,'client');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(32) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `FK_ROLE_ID_idx` (`role_id`),
  CONSTRAINT `FK_ROLE_ID` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'admin','21232f297a57a5a743894a0e4a801fc3',2),(3,'client','62608e08adc29a8d6dbc9754e659f125',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_purchase`
--

DROP TABLE IF EXISTS `tour_purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tour_purchase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tour_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `purchase_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_TOUR_idx` (`tour_id`),
  KEY `FK_CLIENT_idx` (`client_id`),
  CONSTRAINT `FK_TOUR` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_CLIENT` FOREIGN KEY (`client_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_purchase`
--

LOCK TABLES `tour_purchase` WRITE;
/*!40000 ALTER TABLE `tour_purchase` DISABLE KEYS */;
/*!40000 ALTER TABLE `tour_purchase` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-07-22 17:02:30
