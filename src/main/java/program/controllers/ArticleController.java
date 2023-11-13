package program.controllers;

import program.entities.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller                 // Signaler controller
@RequestMapping("/form")    // Assignation URL
public class ArticleController {
    @GetMapping             // Fonction get
    public String showForm(Model model) {
        model.addAttribute("article", new Article());
        return "accueil2";   // Path du HTML depuis src\main\resources\templates\
    }


    @PostMapping            // Fonction post
    public String processForm(@ModelAttribute("article") Article article) {
        // Traitement
        System.out.println("Nom: " + article.getNom());
        System.out.println("Prix: " + article.getPrix());
        System.out.println("Quantité stock: " + article.getStock());

        return "redirect:/form";
    }


}
