# MeinRecipe

"MeinRecipe" is a digital cook book invented to assist people who are annoyed by always having the necessity of writing grocery lists and carrying everything home from the supermarket. And it also resolves the confusion of having a few ingredients left but can’t imagine how to create something taste based on the existing ingredients.

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

#### Setup MySQL server
* Choose a TCP/IP port (take the default port)
* Choose a root password
* Import the DB dump: If you want to set a database with our existing data, import DB dump (**"MeinRecipe_FX/DataBase/cookbook.sql"**) in MySQL Workbench (Server-->Data Import-->Import from Self-Contained File-->Select default target schema-->Start Import). A tutorial about above:  
https://help.fasthosts.co.uk/app/answers/detail/a_id/2133/~/back-up-and-restore-mysql-databases-using-mysql-workbench-6
* Apply Database model: If you want to set a database without any existing data, open DB model file (**"MeinRecipe_FX/DataBase/cookbook.mwb"**) in MySQL Workbench (File-->Open Model) and apply it to database (Database-->Forward engineering), keep clicking "Next" until finished

#### Import in eclipse
* Download file "MeinRecipe.zip"
* Import the project as project from archive (File-->Import-->General-->Projects from Folder or Archive-->Archive), all needed library should be automaticly set.
* The entrance of this project is **"src/meinRecipe_Main/Main.java"**, find it and Run as Java Application

#### Design pattern
* MVC

## Usage

### GUI:
* Home page  
![`7~SY1D4{B33ALT3`NND8ZI](https://user-images.githubusercontent.com/107774939/175012529-f5fe945e-5fa0-40ec-9511-54ad34eae42b.png)
* Search page  
![84LZL)0X }1D`_U)XJ~94 Q](https://user-images.githubusercontent.com/107774939/175012553-4fb03ec0-530c-4a90-8c6f-9fcefb7abcd1.png)
* Display page  
![A3CM J}DP8Q@J~7S$FT @ 0](https://user-images.githubusercontent.com/107774939/175012569-b7f0e5b6-0f93-4389-ab29-e7974dc6858d.png)
* Edit page  
![@ MY(XSQGZ7$7GFOLM7UD53](https://user-images.githubusercontent.com/107774939/175012580-46941b33-53da-4339-850f-beb89d4f4750.png)

### Special function hint:
Most of interface in application have a "tooltip", pause the cursor over the control to get the action prompt
* Home page  
Click "Start to explore" to turn to search page, Click "I'm a chef" to turn to edit page
* Search page  
Input a recipe name or select a recipe region and click "suchen" to search, or click "View all" to get all existing recipe list
* Display page  
Input a serve number and click "Calculate" to calculate ingredient amount
* Edit page  
Table-like edit mode: Double click a table cell to edit, press "ENTER" on keyboard to commit edit

## Maintianer
@SecKona https://github.com/SecKona

## Contributing

### Contributors
#### Memembers in Group C:  
* Klara -> all about scene building, UML diagram drawing and report writing  
* SecKona -> all about coding and javadoc writing  
