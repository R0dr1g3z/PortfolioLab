package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.*;

import java.util.List;

@Controller
@RequestMapping("/donation")
public class DonationController {
    private final InstitutionRepository institutionRepository;
    private final CategoryRepository categoryRepository;
    private final DonationRepository donationRepository;

    public DonationController(InstitutionRepository institutionRepository, CategoryRepository categoryRepository, DonationRepository donationRepository) {
        this.institutionRepository = institutionRepository;
        this.categoryRepository = categoryRepository;
        this.donationRepository = donationRepository;
    }

    @GetMapping("/add")
    public String add(Model model){
        Donation donation = new Donation();
        List<Institution> institutions = institutionRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("donation",donation);
        model.addAttribute("institutions",institutions);
        model.addAttribute("categories",categories);
        return "donation/form";
    }
    @PostMapping("/add")
    public String add(Donation donation){
        donationRepository.save(donation);
        return "donation/formEnd";
    }
}
