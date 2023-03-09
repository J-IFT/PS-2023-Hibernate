Hibernate project
=================

Templates pour le projet Hibernate.

L’application à développer est une application permettant l'enregistrement de produits consommables :

- La création, l’édition, la suppression et le listing de produits consommables.
- La création d’un produit permet de renseigner au minimum le nom, le code du produit, la quantité et la date de péremption.
- L'édition permet de modifier tous les champs d'un produit à l'exception du code du produit
- Le listing permet d’afficher le nom, le code du produit, la quantité, le nombre de jours restant avant péremption, et une information si le produit est périmé.

# Build et exécution

Le projet utilise un build `Gradle` : le répertoire `app/src/main/java` contient le code source de l'application.

Pour compiler l'application : `./gradlew classes`

Pour démarrer l'application : `./gradlew run`

# Rendu attendu

Le code source du projet est à rendre dans une archive.

**Avant tout rendu, on nettoiera l'archive en vidant les fichiers compilés : `gradlew clean`**

Il n’est pas attendu de configuration de la base de données déployée, on pourra utiliser une base en mémoire H2 pour le développement et le rendu final.
Cependant, l'application devra être capable d'effectuer la persistence des données métier en lien avec les fonctionnalités demandées.

Il n'est pas attendu d'interface utilisateur évoluée (frontend Web / application desktop / interface de terminal) : on pourra se limiter à une simple ligne de commandes.

# Travail à réaliser

Pour réaliser la persistence des données dans notre application, il va falloir :

1. Définir les entités métier à persister ;
2. Définir et implémenter un mapping objet-relationnel à appliquer ;
3. Définir et implémenter le cycle de vie des sessions ;
4. Configurer Hibernate pour gérer les connections avec la base de données et pour instancier les mappings ;
5. Réaliser les appels à la base de données au travers d’Hibernate.

## Choix des entités métier à persister

Analyser l’application pour définir les entités à persister en base de données, et à quel moment la persistance doit être réalisée.

## Mapping objet-relationnel

Une fois les entitées à persister définies, il faut ensuite réaliser un mapping Objet-Relationnel pour définir quels sont les attributs à persister et comment.

Implémenter ensuite ce schéma, soit par l’utilisation d’un fichier `XML` soit par l’utilisation d’annotations (les 2 méthodes sont équivalentes).

Exemple d’un fichier XML `MyEntity.hbm.xml` situé dans le même package que la classe `MyEntity` :

```xml
<hibernate-mapping>

<class name="org.myPackage.MyEntity" table="MY\_ENTITY">
	<id name="id">
		<generator class="native"/>
	</id>

	<property name="myString" type="string" not-null="true" />

</class>

</hibernate-mapping>
```

*Attention : dans le cas d’un fichier XML, celui-ci devra être placé dans le répertoire `src/main/resources` de l’application pour être lu. A cause de cette contrainte, il faudra donc spécifier le nom complet de la classe précédé du package dans le champ `<class name=...>`*

Exemple d’une classe `MyEntity` annotée :

```java
@Entity
@Table(name = "MY\_ENTITY")
public class MyEntity {

	private Integer myId;
	private String myString;

	@Id
	public Integer getMyId() {
		return myId;
	}
	
	@Basic(optional = false)
	public String getMyString() {
		return myString;
	}
```

## Cycle de vie des sessions

*Rappel : tout dialogue avec la base de données doit être réalisé dans une transaction !*

Avant de déléguer le contrôle de la base de données à Hibernate, il faut donc se poser la question du cycle de vie d’une `Session` (quand la créer, quand la libérer), et du cycle de vie de la `SessionFactory`.

_Note : le commit ou rollback d’une transaction ferment en général la session, sauf dans des architectures où la session a un cycle de vie un peu spécial._

_Pour plus d’information : <https://docs.jboss.org/hibernate/core/3.3/reference/en/html/transactions.html#transactions-basics-uow>_

## Configuration d’Hibernate

### Fichier de configuration

Hibernate se configure à l’aide d’un fichier `hibernate.cfg.xml` à placer dans `src/main/resources`

Ce fichier contient à la fois la configuration de la connexion à la BD, le cycle de vie des sessions, et les options d’hibernate (cache, ...) mais également les références vers les différents mappings à importer.

Pour chaque mapping utilisant des annotations, ajouter une ligne de la forme :

```xml
<!-- Names the annotated entity class -->
<mapping class="org.epsi.b3.MyEntity"/>
```

Pour chaque mapping utilisant un fichier XML, ajouter une ligne de la forme :

```xml
<!-- Names the XML mappings -->
<mapping resource="MyEntity.hbm.xml"/>
```

### Génération de la SessionFactory

Nous allons créer une instance de SessionFactory, qui va nous servir de point d’entrée pour utiliser Hibernate.

On pourra utiliser le code suivant :

```java
new Configuration().configure().buildSessionFactory()
```

Attention à bien respecter le cycle de vie de la `SessionFactory` ! Utiliser un design pattern adéquat pour partager la même instance de `SessionFactory` dans les différentes classes de votre code lorsque nécessaire.

### Transaction BD déléguée à Hibernate

Il est maintenant temps de réaliser les requêtes en base de données en utilisant des méthodes ORM (Object-Relational Mapping) directement.

On pourra utiliser les méthodes `save()`, `update()`, `delete()` de la classe `Session` pour effectuer des opérations de modification sur les données persistées et les méthodes “queries” de la classe `Session` pour lister les données persistées.

Pour rappel, toute opération doit avoir lieu dans une transaction : on pourra démarrer cette transaction avec la méthode `beginTransaction()` de l’objet `Session`.

Pour que les opérations soient réellement effectuées, on devra également exécuter la transaction : on pourra utiliser la méthode `commit()` de l’objet `Transaction`, et la méthode `rollback()` en cas d’erreur.

