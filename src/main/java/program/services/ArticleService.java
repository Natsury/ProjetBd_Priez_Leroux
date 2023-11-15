package program.services;

import program.entities.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import program.repositories.ArticleRepository;

import java.util.List;

// Le service contient toutes les méthodes en rapport avec la gestion des objets Article
@Service        // Signaler service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void enregistrerNouvelArticle(Article nouvelArticle) {
        articleRepository.save(nouvelArticle);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public List<Article> findByNomContainingIgnoreCase(String nom) {
        return articleRepository.findByNomContainingIgnoreCase(nom);
    }

}