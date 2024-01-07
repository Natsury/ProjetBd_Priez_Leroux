package program.entities;

import java.util.List;

public class Recherche {

    private String nom;
    private List<Article> articles;

    // Pas de constructeur

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
