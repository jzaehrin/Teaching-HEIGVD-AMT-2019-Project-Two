[Retour](../README.md)

Structure et particularité

Nous avons suivi un structure conventionnel pour `Spring`.

- `api` contient l'ensemble des endpoints, filtres et outils permettant de travailler les points d'accès à l'API
    - `exceptions` contient les exceptions customs
    - `utils` contient des classes génériques permettant de manipuler les DTO et entités correspondantes ainsi que des helpers pour les mots de passe et JWT
- `entities` contient les entités objet de la base de données
- `repositories` contient les repositories définissant les différentes méthodes de récupération des données de la base de données

## Gestions d'erreur

Dans le cadre des `endpoints`, nous avons utilisé un système de gestion d'erreur et de `handler` pour formater et répondre de manière générique la réponse d'erreur.
Pour ce faire `ApiError` peut être envoyé depuis un endpoint et sera `catch` dans la classe `ErrorExceptionHandler` qui s'occupera de répondre correctement à l'utilisateur.
Cette classe implémente aussi différent `catch` pour des erreurs spécifiques comme le système de validation de paramètre.

Ce système semble possible pour les `filters` par l'utilisation d'un filtre `handler` utilisé en premier pour `catch` les exceptions lancer par les filtres suivants.
Cependant, nous n’avons pas réussi à le mettre en place, c'est pour cette raison que la fonction des erreurs dans les filtres sont plus austère.

## Gestions des versions d'API

//TODO

[Retour](../README.md)