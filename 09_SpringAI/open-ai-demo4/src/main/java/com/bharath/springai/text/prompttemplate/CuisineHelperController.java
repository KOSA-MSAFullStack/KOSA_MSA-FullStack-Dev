package com.bharath.springai.text.prompttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bharath.springai.services.OpenAiService;

@Controller
public class CuisineHelperController {

    @Autowired
    private OpenAiService service;

    // Handles GET request to display the cuisine helper form
    @GetMapping("/showCuisineHelper")
    public String showChatPage() {
        return "cuisineHelper";  // returns the name of the Thymeleaf template
    }

    // Handles POST request when form is submitted
    @PostMapping("/cuisineHelper")
    public String getChatResponse(
            @RequestParam("country") String country,
            @RequestParam("numCuisines") String numCuisines,
            @RequestParam("language") String language,
            Model model) {
        
        // Calls the service to fetch cuisines based on input
        CountryCuisines countryCuisines = service.getCuisines(country, numCuisines, language);
        
        // Prints the result to console for debugging purposes
        System.out.println(countryCuisines);
        
        // Adds the result to the model to be displayed on the webpage
        model.addAttribute("countryCuisines", countryCuisines);
        
        return "cuisineHelper";  // returns the same view to display results
    }
}
