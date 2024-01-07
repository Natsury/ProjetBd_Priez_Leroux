package program.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import program.entities.Article;
import program.entities.Recherche;
import program.services.ArticleService;
import program.services.RedisCacheService;

import java.util.List;

@Controller
@RequestMapping("/recherche")
public class RechercheController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisCacheService redisCacheService;

    @GetMapping
    public String afficherPageRecherche(Model model) {
        model.addAttribute("recherche", new Recherche());

        // On récup tout de la base mongo
        List<Article> tousLesArticles = articleService.findAll();

        model.addAttribute("articles", tousLesArticles);
        return "recherche";
    }

    @PostMapping
    public String rechercherArticles(@ModelAttribute("recherche") Recherche recherche, Model model) {
        String nomRecherche = recherche.getNom();

        // On check dans redis
        Recherche rechercheCachee = redisCacheService.trouverRecherche(nomRecherche);

        if (rechercheCachee == null) {
            // Si pas trouvé dans redis on cherche dans mongo
            List<Article> articlesTrouves = articleService.findByNomContainingIgnoreCase(nomRecherche);

            // Enregistrer la recherche dans redis
            recherche.setArticles(articlesTrouves);
            redisCacheService.sauvegarderRecherche(recherche);

            System.out.println("Trouvé depuis Mongo");
            model.addAttribute("articles", articlesTrouves);
        } else {
            // Si trouvé dans redis on utilise le résultat du cache
            System.out.println("Trouvé depuis Redis");
            model.addAttribute("articles", rechercheCachee.getArticles());
        }
        return "recherche";
    }
}
