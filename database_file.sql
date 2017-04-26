CREATE DATABASE  IF NOT EXISTS `samplecenter` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `samplecenter`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: samplecenter
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `folder`
--

DROP TABLE IF EXISTS `folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder` (
  `idfolder` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fk_folder` int(11) DEFAULT NULL,
  PRIMARY KEY (`idfolder`),
  KEY `fk_folder_folder_idx` (`fk_folder`),
  CONSTRAINT `fk_folder_folder` FOREIGN KEY (`fk_folder`) REFERENCES `folder` (`idfolder`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder`
--

LOCK TABLES `folder` WRITE;
/*!40000 ALTER TABLE `folder` DISABLE KEYS */;
INSERT INTO `folder` VALUES (1,'samples',NULL),(2,'drums',1),(3,'instruments',1),(4,'other',1),(5,'percussions',2),(6,'kicks',2),(7,'snares',2),(8,'cymbals',2),(9,'voices',4),(10,'fx',4),(11,'acoustic',3),(12,'piano',11),(13,'acoustic',6),(14,'bass',3);
/*!40000 ALTER TABLE `folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sample`
--

DROP TABLE IF EXISTS `sample`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sample` (
  `idsample` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tag` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `fk_author` int(11) NOT NULL,
  `fk_folder` int(11) NOT NULL,
  PRIMARY KEY (`idsample`),
  UNIQUE KEY `url_UNIQUE` (`url`),
  KEY `fk_sample_1_idx` (`fk_author`),
  KEY `fk_sample_folder_idx` (`fk_folder`),
  CONSTRAINT `fk_sample_folder` FOREIGN KEY (`fk_folder`) REFERENCES `folder` (`idfolder`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sample_user` FOREIGN KEY (`fk_author`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sample`
--

LOCK TABLES `sample` WRITE;
/*!40000 ALTER TABLE `sample` DISABLE KEYS */;
INSERT INTO `sample` VALUES (1,'shaker','percussion','shaker.wav',1,5),(2,'Acoustic british kick','','acoustic-kick.wav',2,13),(3,'Trance Kick','boum','techno-kick.wav',1,6),(4,'Snare','electro, techno','snare-electro.wav',2,7),(5,'closed hi-hat',NULL,'hihat-closed.wav',1,8),(6,'piano c3',NULL,'c3.wav',2,12),(7,'piano c4',NULL,'c4.wav',1,12),(8,'surprise','comics, toon sound','surprise.wav',2,10),(14,'prout','pâte à prout, rigolo, fun, mdr','prout.wav',5,10),(15,'Dieu','grand, extraterrestres, ancêtre, génétique','dieu.wav',5,9),(23,'clic','gun, hip-hop, east-coast, gangster','EC_GUNCLIC.wav',2,10),(24,'gun fire','gunshot, hip-hop, east-coast, gangster','EC_GUNSHOT.wav',2,10),(25,'hat','hip-hop, east-coast, open','EC_HAT001.wav',2,8),(26,'kick','hip-hop, east-coast','EC_KIK001.wav',2,6),(27,'snare','hip-hop, east-coast','EC_SN001.wav',2,7),(28,'error','video game, error, mr matt','error_buzz.wav',5,10),(29,'flipper','space cadet video game intitialization','flipperinit.WAV',5,10),(30,'ho no','video game ho no murder kill','ho_no.wav',5,9),(31,'hoooooo','fear ','hooo.wav',5,9),(32,'jazz','guitar chord smooth','jazz_guitar.wav',1,3),(33,'oh yeah','victory enthousiasm hourray burst of joy','oh_yeah.wav',5,9),(34,'poulet','extraterrestre vaches','poulets.wav',5,9),(35,'double snare','','snare_3.wav',1,7),(36,'trumpet riff 1','jazz','trumpet_1.wav',1,11),(37,'trumpet riff 2','jazz','trumpet_2.wav',1,11),(38,'trumpet riff 3','jazz','trumpet_3.wav',1,11),(39,'multiple clap','hip hop clap stacked','multiple_clap.wav',2,5),(40,'djembe','djrembe riff african ethnic hip hop','Djemb9.wav',2,5),(41,'tink',NULL,'tink.wav',2,5),(42,'Horn','reggae horn pull up','horn.wav',1,10),(43,'bass loop','lead, electro, analaog','lead_bass_loop.wav',1,14),(44,'bass C','lead','bass_C.wav',2,14);
/*!40000 ALTER TABLE `sample` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sample_style`
--

DROP TABLE IF EXISTS `sample_style`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sample_style` (
  `idsample_style` int(11) NOT NULL AUTO_INCREMENT,
  `fk_sample` int(11) DEFAULT NULL,
  `fk_style` int(11) DEFAULT NULL,
  PRIMARY KEY (`idsample_style`),
  KEY `fk_sample_idx` (`fk_sample`),
  KEY `fk_style_idx` (`fk_style`),
  CONSTRAINT `fk_sample` FOREIGN KEY (`fk_sample`) REFERENCES `sample` (`idsample`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_style` FOREIGN KEY (`fk_style`) REFERENCES `style` (`idstyle`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sample_style`
--

LOCK TABLES `sample_style` WRITE;
/*!40000 ALTER TABLE `sample_style` DISABLE KEYS */;
INSERT INTO `sample_style` VALUES (1,1,3),(2,1,2),(3,2,3),(4,3,2),(5,4,2),(6,5,1),(7,5,2),(8,8,2);
/*!40000 ALTER TABLE `sample_style` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `style`
--

DROP TABLE IF EXISTS `style`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `style` (
  `idstyle` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idstyle`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `style`
--

LOCK TABLES `style` WRITE;
/*!40000 ALTER TABLE `style` DISABLE KEYS */;
INSERT INTO `style` VALUES (2,'electro'),(1,'hip-hop'),(4,'jazz'),(3,'rock');
/*!40000 ALTER TABLE `style` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` char(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `avatarUrl` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'default_avatar.jpg',
  `isAdmin` tinyint(4) NOT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'joaquim','joaquim.perez@he-arc.ch','joaquim','https://avatars0.githubusercontent.com/u/19431331?v=3&s=460',0),(2,'loris','loris.ceschin@he-arc.ch','loris','https://avatars1.githubusercontent.com/u/1257528?v=3&s=400',1),(5,'nicolas','nicolas.gonin@he-arc.ch','nicolas','https://avatars0.githubusercontent.com/u/9034809?v=3&s=400',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-26 17:15:29
