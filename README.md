# Back end Projet SIR Doodle 
 Etudiant : LANGNITO Constant
 
 Diagramme de classe  :  https://github.com/Constant23/tpjpa2019sir/blob/master/doodle.PNG 
 
L’objectif de ce projet est de construire une application type doodle permetant de creer 
des reunion et des propositions de sondage.(#version Api)

# Le diagramme de classe explique :

Un utilisateur peut créer un ou plusieurs réunion 
 
Une réunion est crée par un utilisateur 
 
Une réunion des proposition
 
Un proposition peut être de type date, de type lieu ou de type date et lieu(ce qui 
explique l'héritage)

Un utilisateur peut voter pour un proposition

avant la réunion les participants sont invité par un lien unique pour renseigner 
leur préférence et allergie 

Package
Nous avons principalement 3 packages :

# DAO
Pour la gestion de la base de données , pour faire les liasons entre les tables tout ce qui est lié aux requêtes avec .merge pour la modification , .persisit pour l’ajout , .delete pour la supression a partir de la classe EntityManager .

# Entites
Là où nous avons définit les classes précedentes ( la partie Objects) et les  classes qui servent a la generation du modele de la base de données.

# Rest
Dans ce package nous avons geré les ressources rest pour assurer les interactions avec postamn.

# Prerequisites
Java  -
Maven -
Apache tomcat -
Hibernate -
Javax servlet -
jersey -  
Postman -

# Installation
1: Demarer votre server de base de données mysql et creer un base de non dooble

2: Configuration du server

Dans le ficher persistence.xml (src/java/resources/META-INF) Utiliser les informations de votre server 'nom' 'password' et nom de la BD "dooble" dans notre cas

        <property name="hibernate.connection.password" value=""/>
        <property name="hibernate.connection.url" value="jdbc:mysql://localhost/dooble"/>
        <property name="hibernate.connection.username" value="root"/>


3: laisser Maven télécharger les dépendances
 
4: Lancer l'application JpaTest(Java/jpa)

il crée les tables dans la BD dooble

4: Lancer tomcat avec  Maven:
Clic droit sur votre projet. run as -> maven build …-> mettre tomcat7:run dans le goal. équivalent de lancer mvn tomcat7:run dans la console

ou sur intellij 
Onglet Maven a droite  -> Plugins -> tomcat7 -> double cliquer sur tomcat7:run

# NB  il est demarré sur le port 8181 

5: Faite vos requetes avec Postman https://www.getpostman.com/ ou autre outil pour les interactions avec les données. Il donc possible de manipuler:

Exemple: 

# Liste de tout les utilisateurs 
GET http://localhost:8181/rest/utilisateurs 

# Ajouter un utilisateur 
POST http://localhost:8181/rest/utilisateurs/add

JSON DATA a utiliser 
{
    "nom": "HONGBETE",
    "prenom": "Divine",
    "email": "andy@niji.Fr"
}

# Lister le detail d'un utilisateur 
GET http://localhost:8181/rest/utilisateurs/6

# supprimer un utilisateur 
DELETE http://localhost:8181/rest/utilisateurs/delete/6

# Update un utilisateur 
POST http://localhost:8181/rest/utilisateurs/update

JSON DATA a utiliser
{
    "id": 5,
    "nom": "HONGBETE",
    "prenom": "Dinive",
    "email": "andy@niji.Fr",
    "mesSondages": [],
    "mesParticipations": []
}


les autres fonctionaliter sont aussi dispoble dans l'api

comme proposer une reunion avec des sondages
participer a des sondages 
On n'adapetera la suite en tenant compte des contrainte du front end  

