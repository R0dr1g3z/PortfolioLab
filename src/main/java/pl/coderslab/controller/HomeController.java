package pl.coderslab.controller;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.DonationRepository;
import pl.coderslab.charity.Institution;
import pl.coderslab.charity.InstitutionRepository;
import pl.coderslab.email.EmailServiceImpl;
import pl.coderslab.security.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Controller
public class HomeController {
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final RoleRepository roleRepository;
    private final UserService userService;
    private final EmailServiceImpl emailService;
    private final JavaMailSender javaMailSender;
    private final ApplicationEventPublisher eventPublisher;
    private final UserRepository userRepository;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository, RoleRepository roleRepository, UserService userService, EmailServiceImpl emailService, JavaMailSender javaMailSender, ApplicationEventPublisher eventPublisher, UserRepository userRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.emailService = emailService;
        this.javaMailSender = javaMailSender;
        this.eventPublisher = eventPublisher;
        this.userRepository = userRepository;
    }

    @RequestMapping("/")
    public String homeAction(Model model) {
        List<Institution> institutions = institutionRepository.findAll();
        Integer quantity = donationRepository.getQuantity();
        Integer donations = donationRepository.getDonations();
        model.addAttribute("donations", donations);
        model.addAttribute("quantity", quantity);
        model.addAttribute("institutions", institutions);
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid User user, BindingResult result) {
        if (result.hasErrors()){
            return "register";
        }
        if (user.getPassword().equals(user.getRePassword())){
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(user.getUsername());
            mail.setSubject("Potwierdzenie konta");
            mail.setText("Żeby potwierdzić konto klinknij w link http://localhost:8080/registerConfirm/"+token);
            javaMailSender.send(mail);
            userService.saveUser(user);
            return "redirect:/login";
        }
        return "redirect:/register";
    }
    @RequestMapping("/registerConfirm/{token}")
    public String registerConfirm(@PathVariable String token){
        User user = userRepository.findByToken(token);
        user.setEnabled(true);
        userRepository.save(user);
        return "redirect:/";
    }

    @RequestMapping("/check")
    public String check(@AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();
        Set<Role> roles = entityUser.getRoles();
        for (Role role : roles) {
            if (entityUser.isEnabled()==false){
                return "loginError";
            }
            if (role.getName().equals("ROLE_USER")) {
                return "redirect:/";
            }
            if (role.getName().equals("ROLE_ADMIN")) {
                return "redirect:/admin/home";
            }
        }
        return "redirect:/login";
    }
    @GetMapping("/mail")
    public String mail(Model model){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        model.addAttribute("mail",simpleMailMessage);
        return "mail";
    }
    @PostMapping("/mail")
    public String mail(SimpleMailMessage mail){
        javaMailSender.send(mail);
        return "redirect:/";
    }
    @GetMapping("/resetPassword")
    public String resetPassword(Model model){
        SimpleMailMessage mail = new SimpleMailMessage();
        model.addAttribute("mail",mail);
        return "resetPassword";
    }
    @PostMapping("/resetPassword")
    public String resetPassword(SimpleMailMessage mail){
        String[] to = mail.getTo();
        List<User> users = userRepository.findAll();
        for (User user:users){
            if (user.getUsername().equals(to[0])){
                User user1 = userRepository.findByUsername(to[0]);
                String token = UUID.randomUUID().toString();
                user1.setTokenPassword(token);
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(to);
                mailMessage.setSubject("Resetowanie hasła");
                mailMessage.setText("Aby zresetować hasło kliknij w link http://localhost:8080/changePassword/"+token);
                javaMailSender.send(mailMessage);
                userRepository.save(user1);
                return "resetPasswordSuccess";
            }
        }
        return "resetPasswordError";
    }
    @GetMapping("/changePassword/{token}")
    public String changePassword(@PathVariable String token,Model model){
        User user = userRepository.findByTokenPassword(token);
        model.addAttribute("user",user);
        return "changePassword";
    }
    @PostMapping("/changePassword/{token}")
    public String changePassword(User user){
        userService.saveUser(user);
        return "changePasswordSuccess";
    }
}
