-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: cookbook
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `ingredients`
--

DROP TABLE IF EXISTS `ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredients` (
  `recipe_id` int unsigned NOT NULL DEFAULT '0',
  `ingredient_id` int NOT NULL,
  `quantity` decimal(10,0) NOT NULL,
  `description` varchar(80) NOT NULL,
  PRIMARY KEY (`recipe_id`,`ingredient_id`),
  KEY `fk_Recipes_idx` (`recipe_id`),
  CONSTRAINT `fk_ingredient_recipe` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`recipe_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredients`
--

LOCK TABLES `ingredients` WRITE;
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
INSERT INTO `ingredients` VALUES (7,1,500,'g Rinderfleisch'),(8,1,500,'g Programmer`s hair'),(9,1,500,'g Rice'),(9,2,1,'Fox');
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instructions`
--

DROP TABLE IF EXISTS `instructions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instructions` (
  `recipe_id` int unsigned NOT NULL,
  `instruction_id` int NOT NULL,
  `description` varchar(255) DEFAULT '',
  PRIMARY KEY (`recipe_id`,`instruction_id`),
  KEY `fk_Recipes_idx` (`recipe_id`),
  CONSTRAINT `fk_preparation_step_recipe` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`recipe_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructions`
--

LOCK TABLES `instructions` WRITE;
/*!40000 ALTER TABLE `instructions` DISABLE KEYS */;
INSERT INTO `instructions` VALUES (7,1,'Grillen Sie der Rinderfleisch'),(7,2,'Dann fiertig'),(8,1,'Programmer`s hair -1'),(8,2,'Programmer`s hair -1'),(8,3,'Programmer`s hair -1'),(9,1,'This is just a bow of Rice');
/*!40000 ALTER TABLE `instructions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipes`
--

DROP TABLE IF EXISTS `recipes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipes` (
  `recipe_id` int unsigned NOT NULL AUTO_INCREMENT,
  `recName` varchar(80) NOT NULL,
  `recRegion` varchar(80) NOT NULL DEFAULT 'Others',
  `imageURL` varchar(80) NOT NULL,
  `prepTime` int NOT NULL,
  `cookTime` int NOT NULL,
  `serve` int DEFAULT '1',
  PRIMARY KEY (`recipe_id`,`recRegion`),
  UNIQUE KEY `name_UNIQUE` (`recipe_id`),
  UNIQUE KEY `recipe_id_UNIQUE` (`recipe_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipes`
--

LOCK TABLES `recipes` WRITE;
/*!40000 ALTER TABLE `recipes` DISABLE KEYS */;
INSERT INTO `recipes` VALUES (7,'Grill','EU','/recipeImage/default.jpg',10,10,1),(8,'Programmer`s hair','Others','/recipeImage/A (16).jpg',10,20,2),(9,'A bow of Rice','SA','/recipeImage/A (16).jpg',1,1,1);
/*!40000 ALTER TABLE `recipes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-23  2:58:51
