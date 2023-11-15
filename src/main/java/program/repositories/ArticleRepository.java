package program.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import program.entities.Article;

import java.util.List;

// Interface CRUD de gestion des objets Article dans la base de données
// Bonne pratique : utiliser l'interface via le service ArticleService
public interface ArticleRepository extends MongoRepository<Article, String> {
     // MongoRepository hérite de CrudRepository qui hérite de Repository
     // Checker la doc de MongoRepo et CrudRepo pour voir les méthodes de base

    List<Article> findByNomContainingIgnoreCase(String nom);

    // Possilité d'ajouter des méthodes personnalisées si besoin
}

