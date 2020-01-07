[Retour](../README.md)

# Context

L'objectif de ce projet est de fourni deux api distinct ayant pour pu de fourni un service d'un coté et de l'autre le système d'authentification.
Le service (ou l'ensemble si on concidère l'évolution de ce système) est réservé uniquement au utilisateur authentifié.
Pour ce faire, l'API d'authentification fournira un JWT permettant d'authentifier l'utilisateur sur l'ensemble des autres services.

# Business domain

Pour ce projet, nous avons décidé de reprendre le sujet du projet numéro 1.
Le domaine est la gestion d'une médiathèque permettant de retrouver ses films déjà visionnés avec la note attribuée.
De plus, il est possible de tenir une liste de film à voir et de l'évaluer une fois vue.

## Structure de la base de données

La structure de la base de données à très peu changer par rapport au projet précédent qui avait cette forme :

- Users {id, username, firstname, lastname, email, member_since, password}
- MediaUser {id, user_id, media_id, rating, watched}
- Media {id, title, release, duration, main_genre, rating}

Le changement principal est le déplacement de la table `Users` dans une base de données utilisée uniquement par l'API d'authentification :

- Users {id, username, email, password, role, member_since}

La base de données de notre service sera alors présentée comme ceux-ci :

- Users {id}
- MediaUser {user_id, media_id, rating, watched}
- Media {id, title, release, duration, main_genre, rating}

La table `Users` est maintenant totalement vide, son objectif sera de stocker des informations supplémentaires uniquement nécessaires à ce service.
La clé primaire de cette table est aussi spéciale, car elle contient l'identifiant distant de l'utilisateur
, cela est possible, car cet identifiant est garanti unique et non réutiliser par l'API d'authentification.

Un changement majeur a été la suppression d'un `identifiant` propre à l'entité `MediaUser` pour permettre plus de facilité avec `hibernate`.

## Polymorphisme

Nous avons une relation polymorphique sur une même table qui contient alors deux types d'objets (ToWatch et Watched) pour les `MediaUser`.
Pour ce faire, nous avons une relation d'héritage dans les `entites` avec une relation de condition sur la présence de `watched` pour différencier les entitées.

//TODO

### Problèmes

//TODO

[Retour](../README.md)