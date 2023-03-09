# PS-2023-Hibernate

*PS = Projet Scolaire*

## 📚 Projet Scolaire | Hibernate project

Mars 2023

Groupe : Juliette, Brice & Flavien

### 📌 Consignes du projet :
L’application à développer est une application permettant l'enregistrement de produits consommables :

- La création, l’édition, la suppression et le listing de produits consommables.

- La création d’un produit permet de renseigner au minimum le nom, le code du produit, la quantité et la date de péremption.

- L'édition permet de modifier tous les champs d'un produit à l'exception du code du produit.

- Le listing permet d’afficher le nom, le code du produit, la quantité, le nombre de jours restant avant péremption, et une information si le produit est périmé.


Build et exécution

Le projet utilise un build `Gradle` : le répertoire `app/src/main/java` contient le code source de l'application.

Pour compiler l'application : `./gradlew classes`

Pour démarrer l'application : `./gradlew run`


Rendu attendu

Le code source du projet est à rendre dans une archive.

**Avant tout rendu, on nettoiera l'archive en vidant les fichiers compilés : `gradlew clean`**

Il n’est pas attendu de configuration de la base de données déployée, on pourra utiliser une base en mémoire H2 pour le développement et le rendu final.
Cependant, l'application devra être capable d'effectuer la persistence des données métier en lien avec les fonctionnalités demandées.

Il n'est pas attendu d'interface utilisateur évoluée (frontend Web / application desktop / interface de terminal) : on pourra se limiter à une simple ligne de commandes.


Travail à réaliser

Pour réaliser la persistence des données dans notre application, il va falloir :

1. Définir les entités métier à persister ;
2. Définir et implémenter un mapping objet-relationnel à appliquer ;
3. Définir et implémenter le cycle de vie des sessions ;
4. Configurer Hibernate pour gérer les connections avec la base de données et pour instancier les mappings ;
5. Réaliser les appels à la base de données au travers d’Hibernate.


Choix des entités métier à persister

Analyser l’application pour définir les entités à persister en base de données, et à quel moment la persistance doit être réalisée.

### 💻 Applications et langages utilisés :

+ Java
+ Visual studio code



## 🌸 Merci !
© J-IFT
