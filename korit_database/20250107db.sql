CREATE DATABASE  IF NOT EXISTS `study` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `study`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: study
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `course_registering_information_tb`
--

DROP TABLE IF EXISTS `course_registering_information_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_registering_information_tb` (
  `course_registering_information_id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NOT NULL,
  `instructor_id` int NOT NULL,
  `day` varchar(45) NOT NULL,
  `course_duration_start` date NOT NULL,
  `course_duration_end` date NOT NULL,
  `major_id` int NOT NULL,
  PRIMARY KEY (`course_registering_information_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_registering_information_tb`
--

LOCK TABLES `course_registering_information_tb` WRITE;
/*!40000 ALTER TABLE `course_registering_information_tb` DISABLE KEYS */;
INSERT INTO `course_registering_information_tb` VALUES (1,1,2,'월','2025-01-07','2025-06-06',2),(2,1,2,'목','2025-01-07','2025-06-06',2),(3,1,3,'화','2025-01-07','2025-06-06',2),(4,1,3,'수','2025-01-07','2025-06-06',2),(5,2,1,'월','2025-03-07','2025-08-06',1),(6,2,1,'수','2025-03-07','2025-08-06',1),(7,3,1,'화','2025-02-07','2025-07-06',1),(8,3,1,'금','2025-02-07','2025-07-06',1),(9,4,3,'수','2025-03-07','2025-08-06',2),(10,4,3,'금','2025-03-07','2025-08-06',2);
/*!40000 ALTER TABLE `course_registering_information_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_registering_tb`
--

DROP TABLE IF EXISTS `course_registering_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_registering_tb` (
  `course_registering_id` int NOT NULL AUTO_INCREMENT,
  `course_registering_information_id` int NOT NULL,
  `student_id` int NOT NULL,
  PRIMARY KEY (`course_registering_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_registering_tb`
--

LOCK TABLES `course_registering_tb` WRITE;
/*!40000 ALTER TABLE `course_registering_tb` DISABLE KEYS */;
INSERT INTO `course_registering_tb` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,2,2),(6,2,4),(7,3,1),(8,3,3),(9,4,1),(10,4,2),(11,5,1),(12,5,4),(13,6,1),(14,6,2),(15,6,3);
/*!40000 ALTER TABLE `course_registering_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_tb`
--

DROP TABLE IF EXISTS `course_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_tb` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(45) NOT NULL,
  PRIMARY KEY (`course_id`),
  UNIQUE KEY `course_name_UNIQUE` (`course_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_tb`
--

LOCK TABLES `course_tb` WRITE;
/*!40000 ALTER TABLE `course_tb` DISABLE KEYS */;
INSERT INTO `course_tb` VALUES (1,'데이터베이스론'),(2,'알고리즘'),(3,'운영체제'),(4,'자료구조');
/*!40000 ALTER TABLE `course_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instructor_tb`
--

DROP TABLE IF EXISTS `instructor_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instructor_tb` (
  `instructor_id` int NOT NULL AUTO_INCREMENT,
  `instructor_name` varchar(45) NOT NULL,
  PRIMARY KEY (`instructor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructor_tb`
--

LOCK TABLES `instructor_tb` WRITE;
/*!40000 ALTER TABLE `instructor_tb` DISABLE KEYS */;
INSERT INTO `instructor_tb` VALUES (1,'김교수'),(2,'박교수'),(3,'이교수');
/*!40000 ALTER TABLE `instructor_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major_tb`
--

DROP TABLE IF EXISTS `major_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `major_tb` (
  `major_id` int NOT NULL AUTO_INCREMENT,
  `major_name` varchar(45) NOT NULL,
  PRIMARY KEY (`major_id`),
  UNIQUE KEY `major_name_UNIQUE` (`major_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major_tb`
--

LOCK TABLES `major_tb` WRITE;
/*!40000 ALTER TABLE `major_tb` DISABLE KEYS */;
INSERT INTO `major_tb` VALUES (2,'소프트웨어공학과'),(1,'컴퓨터공학과');
/*!40000 ALTER TABLE `major_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_tb`
--

DROP TABLE IF EXISTS `student_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_tb` (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `student_name` varchar(45) NOT NULL,
  `student_year` int NOT NULL,
  `gender` varchar(45) NOT NULL,
  `major_id` int NOT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_tb`
--

LOCK TABLES `student_tb` WRITE;
/*!40000 ALTER TABLE `student_tb` DISABLE KEYS */;
INSERT INTO `student_tb` VALUES (1,'최석현',3,'남',1),(2,'백진우',4,'남',1),(3,'박소율',3,'여',2),(4,'정현영',4,'여',1);
/*!40000 ALTER TABLE `student_tb` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-07 17:20:31
