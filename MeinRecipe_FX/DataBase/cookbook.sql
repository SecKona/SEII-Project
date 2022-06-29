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
  `quantity` int NOT NULL,
  `description` text NOT NULL,
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
INSERT INTO `ingredients` VALUES (2,1,1,'tablespoon vegetable oil'),(2,2,500,'g butternut squash (about 1 small squash), peeled and chopped into bite-sized chunks (or buy a pack of ready-prepared to save time), see tip, below left'),(2,3,100,'g frozen chopped onions'),(2,4,4,'heaped tbsp mild curry paste (we used korma)'),(2,5,400,'g can chopped tomatoes'),(2,6,400,'g can light coconut milk'),(2,7,400,'g can lentils, drained'),(2,8,200,'g bag baby spinach'),(2,9,150,'ml coconut yogurt (we used Rachel’s Organic), plus extra to serve'),(3,1,6,'hard-cooked eggs'),(3,2,2,'tablespoons mayonnaise'),(3,3,1,'teaspoon white sugar'),(3,4,1,'teaspoon white vinegar'),(3,5,1,'teaspoon prepared mustard'),(3,6,1,'teaspoon salt'),(3,7,1,'tablespoon finely chopped onion'),(3,8,1,'tablespoon finely chopped celery'),(3,9,1,'pinch paprika, or to taste'),(4,1,1,'pound lean ground beef'),(4,2,5,'cloves garlic, crushed'),(4,3,1,'tablespoon freshly grated ginger'),(4,4,2,'teaspoons toasted sesame oil'),(4,5,1,'cup reduced-sodium soy sauce'),(4,6,1,'cup light brown sugar'),(4,7,1,'teaspoon crushed red pepper'),(4,8,6,'green onions, chopped, divided'),(4,9,4,'cups hot cooked brown rice'),(4,10,1,'tablespoon toasted sesame seeds'),(5,1,1,'cup raw peas'),(5,2,1,'lemon, juiced'),(5,3,1,'salt and ground black pepper to taste'),(6,1,2,'tablespoon oil'),(6,2,2,'courgettes (about 500g), trimmed and coarsely grated'),(6,3,1,'large garlic clove , finely grated'),(6,4,1,'small red chilli , finely chopped'),(6,5,180,'g tagliatelle'),(6,6,150,'g raw king prawns , peeled and deveined'),(6,7,1,'lemon , zested and juiced'),(6,8,1,'small bunch of parsley , finely chopped'),(7,1,2,'skinless chicken breasts'),(7,2,1,'few thyme sprigs , leaves picked'),(7,3,1,'tablespoon olive oil'),(7,4,2,'teaspoon jerk seasoning'),(7,5,1,'juice lime'),(7,6,2,'large bread rolls'),(7,7,1,'small mango , stoned, peeled and sliced'),(7,8,1,'tomato , sliced'),(7,9,1,'Little Gem lettuce heart, shredded'),(7,10,2,'tablespoon mayonnaise and ketchup, to serve (optional)');
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
  `description` text,
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
INSERT INTO `instructions` VALUES (2,1,'Heat the oil in a large pan. Put the squash in a bowl with a splash of water. Cover with cling film and microwave on High for 10 mins or until tender. Meanwhile, add the onions to the hot oil and cook for a few mins until soft. Add the curry paste, tomatoes and coconut milk, and simmer for 10 mins until thickened to a rich sauce.'),(2,2,'Warm the naan breads in a low oven or in the toaster. Drain any liquid from the squash, then add to the sauce with the lentils, spinach and some seasoning. Simmer for a further 2-3 mins to wilt the spinach, then stir in the coconut yogurt. Serve with the warm naan and a dollop of extra yogurt.'),(3,1,'Slice eggs in half lengthwise and remove yolks; set whites aside. '),(3,2,'Mash yolks with a fork in a small bowl. Stir in mayonnaise, sugar, vinegar, mustard, salt, onion, and celery; mix well. '),(3,3,'Stuff or pipe egg yolk mixture into egg whites. Sprinkle with paprika. Refrigerate until serving.'),(4,1,'Heat a large skillet over medium-high heat. Add beef and cook, stirring and crumbling into small pieces until browned, 5 to 7 minutes. Drain excess grease.'),(4,2,'Stir in garlic, ginger, and sesame oil and cook until fragrant, about 2 minutes.'),(4,3,'Stir in soy sauce, brown sugar, and red pepper. '),(4,4,'Cook until beef absorbs some sauce, about 7 minutes. Add 1/2 of chopped green onions.'),(4,5,'Serve over hot cooked rice; garnish with sesame seeds and remaining green onions.'),(5,1,'Mix peas, lemon juice, salt, and pepper together in a bowl.'),(6,1,'Heat the oil in a frying pan and fry the courgette for 4-5 mins, then stir through the garlic and chilli.'),(6,2,'Cook the tagliatelle following pack instructions. Drain, reserving some of the cooking water.'),(6,3,'Add the prawns to the courgette mixture, and cook for 2 mins until pink.'),(6,4,'Toss through the tagliatelle, the lemon zest and juice, parsley, some seasoning and a splash of the reserved cooking water.'),(6,5,'Divide between bowls and serve.'),(7,1,'Put the chicken breasts in between pieces of cling film and bash with a rolling pin to flatten.'),(7,2,'Mix together the thyme, oil, jerk seasoning and half the lime juice in a bowl. Add the chicken and leave to marinate for 5 mins.'),(7,3,'Heat a griddle pan until hot and cook the chicken for 4-5 mins each side or until cooked through.'),(7,4,'Remove from the heat. Meanwhile, toast the cut sides of the buns for 1-2 mins.'),(7,5,'Put the chicken on the buns and top with the mango, tomato and a handful of lettuce.'),(7,6,'Squeeze over the rest of the lime and top with mayo and ketchup, if you like.');
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
  `recName` text NOT NULL,
  `recRegion` varchar(80) NOT NULL DEFAULT 'Others',
  `imageURL` text NOT NULL,
  `prepTime` int NOT NULL,
  `cookTime` int NOT NULL,
  `serve` int DEFAULT '1',
  PRIMARY KEY (`recipe_id`,`recRegion`),
  UNIQUE KEY `name_UNIQUE` (`recipe_id`),
  UNIQUE KEY `recipe_id_UNIQUE` (`recipe_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipes`
--

LOCK TABLES `recipes` WRITE;
/*!40000 ALTER TABLE `recipes` DISABLE KEYS */;
INSERT INTO `recipes` VALUES (2,'Coconut & squash dhansak','LA','/recipeImage/coconut-squash-dhansak.jpg',5,15,1),(3,'Simple Deviled Eggs','SA','/recipeImage/image.jpg',15,15,1),(4,'Easy Korean Ground Beef Bowl','SEA','/recipeImage/image2.jpg',10,15,1),(5,'Lemon Pea Salad','Others','/recipeImage/image1.jpg',10,10,1),(6,'Lemony prawn & courgette tagliatelle','EU','/recipeImage/Spinach-Shrimp-Fettuccine_EXPS_FTTMZ21_25028_E03_05_6b-1.jpg',10,10,1),(7,'Jerk chicken burger','A','/recipeImage/o.jpg',10,10,1);
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

-- Dump completed on 2022-06-29 17:30:23
