[Retour](../README.md)

# Structure des Services

Dans le cas de nos services, nous avons choisi plusieurs conventions pour essayer de fournir une API clair d'utilisation.
Pour commencer, les routes privilégiés sont séparer des autres par `/admin`.

Nous avons largement utilisé le principe de `nested ressource` avec les différentes ressources, ce choisi a été pris, car l'ensemble des données sont liés directement avec un utilisateur.

Nous avons décidé de ne pas faire de distinction sur les routes pouvant être travaillées avec les différents utilisateurs et les routes permettant uniquement de travailler avec son utilisateur.
Ceux-ci permettraient d'instaurer un système plus complexe de droit ne travaillant pas avec `/admin`.
Pour l'instant, il est nécessaire de définir le même `identifiant` entre l'URL et celle qui est dans le token pour pouvoir faire certaines opérations qui altère un utilisateur ou le contenu le concernant.

## API Authentification

Ce service permet dans un premier temps d'authentifier un utilisateur par la route `/login` qui a été imposée par `Java EE` car il n'est pas possible de différencier les `nested ressources` et les routes conventionnelles.
Car il n'est pas possible de faire la différence entre un `identifiant` utiliser dans l'URL est une simple route pour la création d'un filtre vérifiant l'authentification d'un utilisateur.

La partie `/admin` contient la route permettant la création de nouveaux utilisateurs avec un `POST` sur `/users`.
La partie `/users` contient la partie non privilégiée qui contient la route permettant à l'utilisateur de modifier son mot de passe.

## API Movies

l'API Movies permet de travailler avec les données de média et d'interagir avec sa bibliothèque de média.

Elle comporte une route `/media` permettant de lister de manière paginée les médias stockés.
Ainsi que l'ajout possible de médium par l'envoi des informations en `post`.

La partie principale de l'API est toutes les données qui sont liées à un utilisateur qui se trouve sur `/users/{user_id}`.
Ces routes se distinguent par le type de données voulant être traité qui sont les `towatch` et les `watched` pour respectivement média `à voir` et média `vue`.
Cette catégorie est suivie d'un `identifiant` de média quand on parle d'une donnée déjà existante pour faire des mises à jour ou de la suppression de ces entités.
Sans cet identifiant on parle alors de la récupération de la liste des entités sous forme de page ainsi que la création de ces entités par un `POST`.


[Retour](../README.md)