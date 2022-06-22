# MeinRecipe

"MeinRecipe" is invented to assist people who are annoyed by always having the necessity of writing grocery lists and carrying everything home from the supermarket. And it also resolves the confusion of having a few ingredients left but can’t imagine how to create something taste based on the existing ingredients.

## Build
### Environment
* Operating System: Windows
* Software Development Kit: Java SE Development Kit 8
* Database: MySQL Server 8.0
* Others: Eclipse & JavaFX Scene builder 2.0, MySQL Workbench 8.0 SE
### Setup
#### Setup MySQL
* Download MySQL on Windows Installer Community https://dev.mysql.com/downloads/installer/
* Execute the installer and have a custom installation
* Choose the packages MySQL Server, MySQL Workbench, Connector/J, and MySQL Documentation for installation and apply these settings.
#### Setup the MySQL server
* Choose a TCP/IP port (take the default port)
* Choose a root password
* Import the DB dump: Import DB dump (**"MeinRecipe_FX/DataBase/cookbook.sql"**) in MySQL Workbench according to this tutorial https://help.fasthosts.co.uk/app/answers/detail/a_id/2133/~/back-up-and-restore-mysql-databases-using-mysql-workbench-6
#### Import in eclipse
* Clone or download this project
* Import the project as existing project (File-->Import-->General--> Existing Project into Workspace), all needed library should be automaticly set.
* The entrance of this project is **"src/meinRecipe_Main/Main.java"**, find it and Run as Java Application
## Usage
### GUI:
* Home page
* Search page
* Display page
* Edit page
## Maintianer
@SecKona
## Contributing

### Contributors
Memembers in Group C

