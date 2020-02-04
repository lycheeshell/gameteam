CREATE DATABASE  IF NOT EXISTS `gameteam` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `gameteam`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: gameteam
-- ------------------------------------------------------
-- Server version	5.7.25-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `adept`
--

DROP TABLE IF EXISTS `adept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adept` (
  `adept_id` varchar(40) NOT NULL,
  `student_id` varchar(40) DEFAULT NULL,
  `game_id` varchar(40) DEFAULT NULL,
  `score` int(3) DEFAULT NULL,
  `block_flag` tinyint(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`adept_id`),
  KEY `student_adept_key_idx` (`student_id`),
  KEY `game_adept_key_idx` (`game_id`),
  CONSTRAINT `game_adept_key` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `student_adept_key` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game` (
  `game_id` varchar(40) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `image_url` varchar(100) DEFAULT NULL,
  `block_flag` tinyint(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manager` (
  `manager_id` varchar(40) NOT NULL,
  `account` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`manager_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `participant`
--

DROP TABLE IF EXISTS `participant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participant` (
  `participant_id` varchar(40) NOT NULL,
  `play_id` varchar(40) NOT NULL,
  `student_id` varchar(40) NOT NULL,
  `block_flag` tinyint(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`participant_id`),
  KEY `student_participant_key_idx` (`student_id`),
  KEY `play_participant_key_idx` (`play_id`),
  CONSTRAINT `play_participant_key` FOREIGN KEY (`play_id`) REFERENCES `play` (`play_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `student_participant_key` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `play`
--

DROP TABLE IF EXISTS `play`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `play` (
  `play_id` varchar(40) NOT NULL,
  `game_id` varchar(40) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `province` varchar(5) DEFAULT NULL,
  `city` varchar(10) DEFAULT NULL,
  `location` varchar(40) DEFAULT NULL,
  `min_person` int(2) DEFAULT NULL,
  `max_person` int(2) DEFAULT NULL,
  `min_adept_score` int(3) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `block_flag` tinyint(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`play_id`),
  KEY `game_play_key_idx` (`game_id`),
  CONSTRAINT `game_play_key` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `question_id` varchar(40) NOT NULL,
  `game_id` varchar(40) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `a` varchar(30) DEFAULT NULL,
  `b` varchar(30) DEFAULT NULL,
  `c` varchar(30) DEFAULT NULL,
  `d` varchar(30) DEFAULT NULL,
  `correct_option` varchar(2) DEFAULT NULL,
  `block_flag` tinyint(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `game_question_key_idx` (`game_id`),
  CONSTRAINT `game_question_key` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `show`
--

DROP TABLE IF EXISTS `show`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `show` (
  `show_id` varchar(40) NOT NULL,
  `play_id` varchar(40) NOT NULL,
  `from_id` varchar(40) NOT NULL,
  `to_id` varchar(40) NOT NULL,
  `block_flag` tinyint(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`show_id`),
  KEY `play_show_key_idx` (`play_id`),
  KEY `from_show_key_idx` (`from_id`),
  KEY `to_show_key_idx` (`to_id`),
  CONSTRAINT `from_show_key` FOREIGN KEY (`from_id`) REFERENCES `student` (`student_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `play_show_key` FOREIGN KEY (`play_id`) REFERENCES `play` (`play_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `to_show_key` FOREIGN KEY (`to_id`) REFERENCES `student` (`student_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `student_id` varchar(40) NOT NULL,
  `account` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `school` varchar(20) DEFAULT NULL,
  `start_year` int(4) DEFAULT NULL,
  `photo_url` varchar(50) DEFAULT NULL,
  `credit` int(3) DEFAULT NULL,
  `openid` varchar(40) DEFAULT NULL,
  `session_key` varchar(40) DEFAULT NULL,
  `block_flag` tinyint(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-04 17:49:40
