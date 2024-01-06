package program.controllers;

import program.entities.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import program.services.ArticleService;

// Le controller contient toutes les fonctions de la page à l'url /accueil
@Controller                 // Signaler controller
@RequestMapping("/accueil")    // Assignation URL
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping             // Fonction get
    public String afficherPageAccueil(Model model) {
        model.addAttribute("article", new Article());
        return "accueil";   // Path du HTML depuis src\main\resources\templates\
    }

    @PostMapping            // Fonction post
    public String traitementForm(@ModelAttribute("article") Article article, Model model) {
        // Affichage Debug
        System.out.println("Nom : " + article.getNom());
        System.out.println("Prix : " + article.getPrix());
        System.out.println("Quantité stock : " + article.getStock());
        // Enregistrer dans Mongo
        articleService.enregistrerNouvelArticle(article);

        return "redirect:/accueil";
    }



}
