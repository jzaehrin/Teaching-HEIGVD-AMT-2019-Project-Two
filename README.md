# Teaching-HEIGVD-AMT-2019-Project-Two

2e projet d'AMT ayant pour but d'acquérir de l'expérience sur le Framework Spring avec la création de deux API.

## Auteurs

- Pierre Kohler <pierre.kohler@heig-vd.ch>
- Jonathan Zaehringer <jonathan.zaehringer@heig-vd.ch>

## How to use

Lors de la première utilisation, il est nécessaire de correctement initialiser la base de données :
```bash
$ docker-compose up --build db
```

Une fois le message `Init finished` visible, vous pouvez interrompre la base de données avec `control + C`.

Il est maintenant possible de lancer l'ensemble de l'infrastructure :
```bash
$ docker-compose up --build
```

`Traefik` ecoute sur le port `80` pour l'infrastructure et `8080` pour l'interface de gestion.

Les apis sont préfixés par `/api/auth` pour accéder aux routes de l'api d'authentification et `/api/movie` pour accéder aux routes de l'api movies.
Par exemple `/api/auth/login` ou `/api/movie/movies`.

## Documentations

- [Contexte](docs/domain.md)
- [Implémentation des API](docs/api.md)
- [Structure](docs/struct.md)

