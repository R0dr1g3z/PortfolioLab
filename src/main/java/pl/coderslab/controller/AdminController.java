package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.Institution;
import pl.coderslab.charity.InstitutionRepository;
import pl.coderslab.security.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final InstitutionRepository institutionRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AdminController(InstitutionRepository institutionRepository, UserService userService, UserRepository userRepository, RoleRepository roleRepository) {
        this.institutionRepository = institutionRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @RequestMapping("/home")
    public String home() {
        return "admin/home";
    }

    @RequestMapping("/institution")
    public String institution(Model model) {
        List<Institution> institutions = institutionRepository.findAll();
        model.addAttribute("institutions", institutions);
        return "admin/institutions";
    }

    @GetMapping("/institutionAdd")
    public String institutionAdd(Model model) {
        Institution institution = new Institution();
        model.addAttribute("institution", institution);
        return "admin/institutionAdd";
    }

    @PostMapping("/institutionAdd")
    public String institutionAdd(Institution institution) {
        institutionRepository.save(institution);
        return "admin/institutions";
    }

    @GetMapping("/institutionEdit/{id}")
    public String institutionEdit(@PathVariable Long id, Model model) {
        Institution institution = institutionRepository.getOne(id);
        model.addAttribute("institution", institution);
        return "admin/institutionEdit";
    }

    @PostMapping("/institutionEdit/{id}")
    public String institutionEdit(Institution institution) {
        institutionRepository.save(institution);
        return "admin/institutions";
    }

    @RequestMapping("/institutionDelete/{id}")
    public String institutionDelete(@PathVariable Long id) {
        Institution institution = institutionRepository.getOne(id);
        institutionRepository.delete(institution);
        return "admin/institutions";
    }
    @RequestMapping("/admins")
    public String admins(Model model){
        Role admin = roleRepository.findByName("ROLE_ADMIN");
        List<User> admins = userRepository.findAllByRoles(admin);
        model.addAttribute("admins",admins);
        return "admin/admins";
    }
    @GetMapping("/adminAdd")
    public String adminAdd(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "admin/adminAdd";
    }
    @PostMapping("/adminAdd")
    public String adminAdd(User user){
        userService.saveAdmin(user);
        return "redirect:/admin/admins";
    }
    @GetMapping("/adminEdit/{id}")
    public String adminEdit(Model model,@PathVariable Long id){
        User user = userRepository.getOne(id);
        model.addAttribute("user",user);
        return "admin/adminEdit";
    }
    @PostMapping("/adminEdit/{id}")
    public String adminEdit(User user){
        userService.saveAdmin(user);
        return "redirect:/admin/admins";
    }
    @RequestMapping("/adminDelete/{id}")
    public String adminDelete(@PathVariable Long id){
        userRepository.deleteUserRole(id);
        userRepository.deleteById(id);
        return "redirect:/admin/admins";
    }
    @RequestMapping("/users")
    public String users(Model model){
        Role role_user = roleRepository.findByName("ROLE_USER");
        List<User> users = userRepository.findAllByRoles(role_user);
        model.addAttribute("users",users);
        return "admin/users";
    }
    @GetMapping("/userAdd")
    public String userAdd(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "admin/userAdd";
    }
    @PostMapping("/userAdd")
    public String userAdd(User user){
        userService.saveUser(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/userEdit/{id}")
    public String userEdit(Model model,@PathVariable Long id){
        User user = userRepository.getOne(id);
        model.addAttribute("user",user);
        return "admin/userEdit";
    }
    @PostMapping("/userEdit/{id}")
    public String userEdit(User user){
        userService.saveUser(user);
        return "redirect:/admin/users";
    }
    @RequestMapping("/userDelete/{id}")
    public String userDelete(@PathVariable Long id){
        userRepository.deleteUserRole(id);
        userRepository.deleteById(id);
        return "redirect:/admin/users";
    }
}
