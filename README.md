# Hopital

Application de gestion d’hôpital développée avec Spring Boot, Spring Data JPA, Spring Security et Thymeleaf.

## Description

Ce projet est une application web permettant la gestion des patients d’un hôpital avec une interface sécurisée et une gestion des utilisateurs et des rôles. L’application propose une interface web pour les opérations courantes et expose également une API REST pour la récupération des patients.

## Fonctionnalités principales

- **Authentification et gestion des rôles** :
  - Utilisateurs avec rôles (USER, ADMIN)
  - Sécurisation des accès via Spring Security
- **Gestion des patients** :
  - Ajout, modification, suppression de patients (ADMIN)
  - Recherche et pagination sur la liste des patients
  - Consultation de la liste des patients (USER)
- **API REST** :
  - Endpoint `/patients` pour récupérer la liste des patients au format JSON
- **Gestion des erreurs et validation** :
  - Validation des champs (nom, score, etc.)
  - Gestion des accès non autorisés

## Technologies utilisées

- Java 17+
- Spring Boot
- Spring Data JPA
- Spring Security
- Thymeleaf
- H2 Database (par défaut, en mémoire)
- Lombok

## Installation et démarrage

1. **Cloner le dépôt** :
   ```bash
   git clone <url-du-repo>
   cd hopital
   ```
2. **Lancer l’application** :
   ```bash
   ./mvnw spring-boot:run
   ```
3. **Accéder à l’application** :
   - Interface web : [http://localhost:8080/](http://localhost:8080/)
   - Console H2 : [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

## Structure du projet

- `entities/` : Entités JPA (Patient, AppUser, AppRole)
- `repository/` : Repositories Spring Data JPA
- `security/` : Configuration de la sécurité, services et entités liées aux utilisateurs
- `web/` : Contrôleurs web (PatientController, SecurityController)
- `resources/templates/` : Vues Thymeleaf (patients, formulaires, login, etc.)
- `resources/application.properties` : Configuration de la base de données et de l’application

## Utilisateurs et rôles par défaut

Des utilisateurs et rôles sont initialisés au démarrage dans la classe `HopitalApplication` :
- Utilisateurs : `user1`, `user2`, `admin` (mot de passe : `1234`)
- Rôles : `USER`, `ADMIN`
- L’utilisateur `admin` possède les deux rôles.

## Exemples d’utilisation

- Accès à la liste des patients : `/user/index`
- Suppression d’un patient (ADMIN) : `/admin/delete?id=<id>`
- Formulaire d’ajout/modification (ADMIN) : `/admin/formPatients`, `/admin/editPatients`
- API REST : `/patients`

## Personnalisation

- Modifier `src/main/resources/application.properties` pour changer la configuration (port, base de données, etc).
- Adapter les entités ou les vues selon vos besoins métier.

## Auteur

Projet réalisé par Saad Khadir
