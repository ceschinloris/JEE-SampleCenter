# SampleCenter

Application web de gestion de samples

## Installation

### Base de données

Importez le fichier database_file.sql dans votre base de données, un schema samplecenter devrait être créé.


### Glassfish

Connection Pool

Dans la console d'administration du serveur Glassfish, créez un nouveau Connection Pool depuis Resources > JDBC > JDBC Connection Pools.

* Pool Name: SampleCenterPool
* Resource Type: javax.sql.ConnectionPoolDataSource
* Database Driver Vendor: MySql
* Driver Classname: com.mysql.jdbc.Driver
* Datasource Classname: com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource

Dans additionnal property, ajoutez user, password, et URL de votre base de données.

Resource

Il reste à configurer un JDBC Resource:

* JNDI Name: SampleCenterResource
* Pool Name: SampleCenterPool


### Accès aux samples

Les samples présents dans la base de données sont contenus dans l'archive Resources/samples.zip. Il faut décompresser cette archive à la racine de C. Les samples doivent être dans C:\samples\. 
Si vous voulez changer cet emplacement :

* Modifier la dernière propriété dans le fichier samplecenter\web\WEB-INF\glassfish-web.xml. 
* Dans samplecenter.SampleController.java, modifiez la variable baseSampleFolder.

### Tests Selenium

Le test UploadTest.java va faire des mises en lignes de fichiers depuis l'ordinateur. Il y a deux fichiers: C:\meuh.mp3 et C:\image.jpg. Il faut donc pour cela placer les deux fichiers à la racine de C: (ou dans l'emplacement adéquat en ramplaçant les chemins dans le code). Les fichiers d'exemples sont dans le dossier Resources.