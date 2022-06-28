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
INSERT INTO `ingredients` VALUES (7,1,1,'pound lean ground beef'),(7,2,5,'cloves garlic, crushed'),(7,3,1,'tablespoon freshly grated ginger'),(7,4,2,'teaspoons toasted sesame oil'),(7,5,1,'cup reduced-sodium soy sauce'),(7,6,1,'cup light brown sugar'),(7,7,1,'teaspoon crushed red pepper'),(7,8,6,'green onions, chopped, divided'),(7,9,4,'cups hot cooked brown rice'),(7,10,1,'tablespoon toasted sesame seeds'),(8,1,1,'cup raw peas'),(8,2,1,'lemon, juiced'),(8,3,1,'salt and ground black pepper to taste'),(9,1,6,'hard-cooked eggs'),(9,2,2,'tablespoons mayonnaise'),(9,3,1,'teaspoon white sugar'),(9,4,1,'teaspoon white vinegar'),(9,5,1,'teaspoon prepared mustard'),(9,6,1,'teaspoon salt'),(9,7,1,'tablespoon finely chopped onion'),(9,8,1,'tablespoon finely chopped celery'),(9,9,1,'pinch paprika, or to taste');
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
INSERT INTO `instructions` VALUES (7,1,'Heat a large skillet over medium-high heat.'),(7,2,'Add beef and cook, stirring and crumbling into small pieces until browned, 5 to 7 minutes.'),(7,3,'Drain excess grease.'),(7,4,'Stir in garlic, ginger, and sesame oil and cook until fragrant, about 2 minutes.'),(7,5,'Stir in soy sauce, brown sugar, and red pepper.'),(7,6,'Cook until beef absorbs some sauce, about 7 minutes.'),(7,7,'Add 1/2 of chopped green onions.'),(7,8,'Serve over hot cooked rice; garnish with sesame seeds and remaining green onions.'),(8,1,'Mix peas, lemon juice, salt, and pepper together in a bowl.'),(9,1,'Slice eggs in half lengthwise and remove yolks; set whites aside.'),(9,2,'Mash yolks with a fork in a small bowl.'),(9,3,'Stir in mayonnaise, sugar, vinegar, mustard, salt, onion, and celery; mix well.'),(9,4,'Stuff or pipe egg yolk mixture into egg whites.'),(9,5,'Sprinkle with paprika.'),(9,6,'Refrigerate until serving.');
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
INSERT INTO `recipes` VALUES (7,'Easy Korean Ground Beef Bowl','SEA','/recipeImage/image2.jpg',10,15,1),(8,'Lemon Pea Salad','Others','/recipeImage/image1.jpg',10,10,1),(9,'Simple Deviled Eggs','SA','/recipeImage/image.jpg',15,15,1);
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

-- Dump completed on 2022-06-28 21:14:56
