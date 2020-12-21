package pl.coderslab.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.*;
import pl.coderslab.security.CurrentUser;
import pl.coderslab.security.User;
import pl.coderslab.security.UserRepository;
import pl.coderslab.security.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final InstitutionRepository institutionRepository;
    private final CategoryRepository categoryRepository;
    private final DonationRepository donationRepository;

    public UserController(UserRepository userRepository, UserService userService, InstitutionRepository institutionRepository, CategoryRepository categoryRepository, DonationRepository donationRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.institutionRepository = institutionRepository;
        this.categoryRepository = categoryRepository;
        this.donationRepository = donationRepository;
    }

    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        User user = currentUser.getUser();
        Long id = user.getId();
        User user1 = userRepository.getOne(id);
        model.addAttribute("user",user1);
        return "user/profile";
    }
    @PostMapping("/profile")
    public String profile(User user){
        userService.saveUser(user);
        return "redirect:/";
    }
    @GetMapping("/donation")
    public String donation(Model model){
        Donation donation = new Donation();
        List<Institution> institutions = institutionRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("donation",donation);
        model.addAttribute("institutions",institutions);
        model.addAttribute("categories",categories);
        return "user/donation";
    }
    @PostMapping("/donation")
    public String donation(Donation donation,@AuthenticationPrincipal CurrentUser currentUser){
        User user = currentUser.getUser();
        List<Donation> donations = new ArrayList<>();
        donations.add(donation);
        user.setDonations(donations);
        donation.setUser(user);
        donationRepository.save(donation);
        userRepository.save(user);
        return "redirect:/";
    }
    @RequestMapping("/donations")
    public String donations(Model model,@AuthenticationPrincipal CurrentUser currentUser){
        User user = currentUser.getUser();
        Long id = user.getId();
        User user1 = userRepository.getOne(id);
        List<Donation> donations = donationRepository.findAllByUser(user1);
        model.addAttribute("donations",donations);
        return "user/donations";
    }
}
