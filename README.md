# Gestion simplifiée d'une bibliothèque

API REST Spring Boot pour gérer les livres et leurs auteurs dans une bibliothèque.

## 🚀 Fonctionnalités

### Fonctionnalités principales
- [x] Créer un auteur
- [x] Lister tous les auteurs
- [x] Créer un livre lié à un auteur
- [x] Lister tous les livres
- [x] Afficher les livres d'un auteur donné
- [x] Modifier un livre
- [x] Supprimer un livre

### Bonus implémentés
- [x] Tests unitaires (Services et Controllers)
- [x] Base de données H2 avec console web
- [ ] Validation des champs
- [ ] Documentation Swagger
- [ ] Pagination

## 🛠 Technologies utilisées
- Java 21
- Spring Boot 3.4.5
- Spring Data JPA
- H2 Database
- Maven
- Lombok

## 📥 Installation

1. Cloner le projet
```bash
git clone [votre-repo]
cd spring-project
```

2. Compiler et lancer les tests
```bash
mvn clean install
```

3. Lancer l'application
```bash
mvn spring-boot:run
```

## 📚 Documentation API

### Auteurs

#### Créer un auteur
```bash
curl -X POST http://localhost:8081/api/auteurs \
-H "Content-Type: application/json" \
-d '{"nom":"Hugo","prenom":"Victor","age":83}'
```

#### Lister les auteurs
```bash
curl http://localhost:8081/api/auteurs
```

### Livres

#### Créer un livre
```bash
curl -X POST http://localhost:8081/api/livres \
-H "Content-Type: application/json" \
-d '{
    "titre":"Les Misérables",
    "isbn":"123-456",
    "datePublication":"1862-01-01",
    "genre":"Roman",
    "auteur":{"id":1}
}'
```

#### Lister les livres
```bash
curl http://localhost:8081/api/livres
```

#### Livres d'un auteur
```bash
curl http://localhost:8081/api/livres/auteur/1
```

#### Modifier un livre
```bash
curl -X PUT http://localhost:8081/api/livres/1 \
-H "Content-Type: application/json" \
-d '{
    "titre":"Les Misérables - Edition revue",
    "isbn":"123-456",
    "genre":"Roman historique"
}'
```

#### Supprimer un livre
```bash
curl -X DELETE http://localhost:8081/api/livres/1
```

## 🗄 Base de données

Accès à la console H2 :
- URL : http://localhost:8081/h2-console
- JDBC URL : jdbc:h2:mem:testdb
- Username : sa
- Password : (vide)

## 🧪 Tests

Exécuter les tests :
```bash
mvn test
```

## 📝 Notes supplémentaires

- La base de données H2 est réinitialisée à chaque redémarrage
- Les données de test sont chargées automatiquement au démarrage
- Port par défaut : 8081

