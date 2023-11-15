package program.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import program.entities.Article;
import program.entities.Recherche;
import program.services.ArticleService;

import java.util.List;

@Controller
@RequestMapping("/recherche")
public class RechercheController {

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public String afficherPageRecherche(Model model) {
        model.addAttribute("recherche", new Recherche());
        List<Article> tousLesArticles = articleService.findAll();
        model.addAttribute("articles", tousLesArticles);
        return "recherche";
    }

    @PostMapping
    public String rechercherArticles(@ModelAttribute("recherche") Recherche recherche, Model model) {
        String nomRecherche = recherche.getNom();
        List<Article> articlesTrouves = articleService.findByNomContainingIgnoreCase(nomRecherche);
        model.addAttribute("articles", articlesTrouves);
        return "recherche";
    }
}
