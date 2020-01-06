[Retour](../README.md)

# Introduction

Afin de tester et valider notre projet, nous avons mis en place un environnement de test BDD (*Behavior Driven Development*).
Pour ce faire, nous avons fait usage du framework *Cucumber*.

Cucumber permet de fragmenter la rédaction de tests en deux étapes bien distinctes :

La première consiste en la rédaction (textuelle) de scénarii de tests. 
Chaque scénario sera alors composé d'un ensemble de directives définissant soit le contexte (`Given ...`), une action (`When ...`) ou encore un résultat (`Then ...`).

La deuxième étape consiste finalement à expliciter par programme la signification des directives susmentionnées.
Il est également possible d'intégrer des expressions régulières à ces dernières afin de pouvoir les généraliser.


# Contexte

Un project distinct nommé `api-specs` a été créé afin de mettre en avant le comportement *black box* des tests qu'il contient.
Il a pour rôle de tester l'ensemble des routes exposées sur les deux *APIs* présentées précédemment, sans pour autant prétendre à un *code coverage* de 100%, considérant que tous les retours d'erreur possible n'ont pas été systématiquement testés.

Ce projet se contente de générer les *APIs* afin de bénéficier des `DTOs` et donc de simplifier la construction des requêtes.
Il exécute finalement l'ensemble des tests directement sur l'application dans son contexte d'exécution habituel, sans gérer son exécution.
Nous vous conseillons donc d'utiliser le contexte d'exécution Docker qui garantie la bonne communication des divers services.

# Structure

- Le point d'entrée managé de Cucumber se trouve dans le fichier `SpecificationTest`.
Il se charge d'appeler l'exécution de tous les scénarii présents dans le dossier `test/resources/scenarios` du projet.
- Les étapes (ainsi que les scénariis associés) sont séparés selon l'API qu'il concerne.
- La classe `APISteps` regroupe les directives travaillant directement avec l'API (au niveau des requêtes/réponses) de manière générique.
- La classe `World` défini un contexte d'exécution utilisé par chaque test afin de permettre l'accès aux informations requises par les tests sans se soucier de la disponibilité des ressources (par l'usage des *pico containers*).

# Usage

1. S'assurer que l'application tourne (les deux APIs sous-jacente)
2. Modifier l'url présente de le fichier `PROJECT_ROOT/apis-specs/src/test/resources/environment.properties` accordément au contexte de l'application
3. Installer les dépendances du projet courant et le compiler (`mvn install` dans `PROJECT_ROOT/apis-specs`)
4. Lancer l'exécution des tests (le point d'entrée de Cucumber est situé dans le fichier `SpecificationTest`)

[Retour](../README.md)