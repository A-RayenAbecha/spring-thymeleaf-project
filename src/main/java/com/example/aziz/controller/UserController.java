package com.example.aziz.controller;
import com.example.aziz.entity.*;
import com.example.aziz.service.ClientService;
import com.example.aziz.service.InterventionService;
import com.example.aziz.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private UserService userService;
    @Autowired
    InterventionService interventionService;

    private static final String SECRET_CODE = "1234"; // Secure this properly

    @GetMapping("/verify") // Accessible at /users/verify
    public String showVerificationPage(HttpSession session) {

        return "codeVerification"; // Show the verification page
    }

    @PostMapping("/verify") // Post request at /users/verify
    public String verifyCode(@RequestParam String code, Model model, HttpSession session) {
        System.out.println("Entered Code: " + code); // Debugging line
        System.out.println("Expected Code: " + SECRET_CODE); // Debugging line

        if (SECRET_CODE.equals(code.trim())) { // Ensure no leading/trailing spaces
            session.setAttribute("isVerified", true);
            return "redirect:/users/login"; // Redirect to login if correct
        }

        model.addAttribute("error", "Code incorrect. Essayez encore.");
        return "codeVerification"; // Stay on verification page if incorrect
    }
    @GetMapping("/logout-verification")
    public String logoutVerification(HttpSession session) {
        session.removeAttribute("isVerified"); // Remove verification status
        return "redirect:/verify"; // Redirect to the verification page
    }


    @GetMapping("/login")
    public String showLoginPage(HttpSession session) {
        Boolean isVerified = (Boolean) session.getAttribute("isVerified");
        if (isVerified == null || !isVerified) {
            return "redirect:/users/verify"; // Redirect if not verified
        }
        return "login"; // Show login page if verified
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            Model model) {
        try {
            User user = userService.loginUser(username, password);
            model.addAttribute("user", user);
            return "home"; // Redirect to home page on success
        } catch (Exception e) {
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Stay on login page if failed
        }
    }


    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());

        // Add the list of roles to the model so we can use it in the view
        List<Role> roles = Arrays.asList(Role.values());  // Get all enum values
        model.addAttribute("roles", roles);

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/users/login";
    }
    @GetMapping("/home")
    public String home(Model model) {
        // Get authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();

            model.addAttribute("username", userDetails.getUsername());
            model.addAttribute("Role", userService.getCurrentUser().getRole());



        }

        model.addAttribute("question1Percentage", clientService.calculateQuestionPercentage("question1"));
        model.addAttribute("question2Percentage", clientService.calculateQuestionPercentage("question2"));
        model.addAttribute("question3Percentage", clientService.calculateQuestionPercentage("question3"));
        model.addAttribute("question4Percentage", clientService.calculateQuestionPercentage("question4"));
        model.addAttribute("question5Percentage", clientService.calculateQuestionPercentage("question5"));
        model.addAttribute("question6Percentage", clientService.calculateQuestionPercentage("question6"));
        model.addAttribute("question7Percentage", clientService.calculateQuestionPercentage("question7"));
        model.addAttribute("question8Percentage", clientService.calculateQuestionPercentage("question8"));
        model.addAttribute("question9Percentage", clientService.calculateQuestionPercentage("question9"));
        model.addAttribute("question10Percentage", clientService.calculateQuestionPercentage("question10"));
        model.addAttribute("question11Percentage", clientService.calculateQuestionPercentage("question11"));
        model.addAttribute("question12Percentage", clientService.calculateQuestionPercentage("question12"));
        model.addAttribute("question13Percentage", clientService.calculateQuestionPercentage("question13"));
        model.addAttribute("question14Percentage", clientService.calculateQuestionPercentage("question14"));
        model.addAttribute("question15Percentage", clientService.calculateQuestionPercentage("question15"));
        model.addAttribute("question16Percentage", clientService.calculateQuestionPercentage("question16"));
        model.addAttribute("question17Percentage", clientService.calculateQuestionPercentage("question17"));
        model.addAttribute("question18Percentage", clientService.calculateQuestionPercentage("question18"));
        model.addAttribute("question19Percentage", clientService.calculateQuestionPercentage("question19"));
        model.addAttribute("question20Percentage", clientService.calculateQuestionPercentage("question20"));
        model.addAttribute("question21Percentage", clientService.calculateQuestionPercentage("question21"));
        model.addAttribute("question22Percentage", clientService.calculateQuestionPercentage("question22"));
        model.addAttribute("question23Percentage", clientService.calculateQuestionPercentage("question23"));
        model.addAttribute("question24Percentage", clientService.calculateQuestionPercentage("question24"));

        // Fetch clients and add to model
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);

        return "dashboard";
    }



    @GetMapping("/users/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            request.getSession().invalidate(); // Invalidate session
            SecurityContextHolder.clearContext(); // Clear security context
        }
        response.sendRedirect("/users/login"); // Redirect to login page
    }


    @GetMapping("/clients/add")
    public String showAddClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "addClient";  // This view will display the form to add a new client
    }

    @PostMapping("/clients/add")
    public String addClient(@ModelAttribute Client client, Model model) {
        // Set all Boolean values to null
        client.setQuestion1(null);
        client.setQuestion2(null);
        client.setQuestion3(null);
        client.setQuestion4(null);
        client.setQuestion5(null);
        client.setQuestion6(null);
        client.setQuestion7(null);
        client.setQuestion8(null);
        client.setQuestion9(null);
        client.setQuestion10(null);
        client.setQuestion11(null);
        client.setQuestion12(null);
        client.setQuestion13(null);
        client.setQuestion14(null);
        client.setQuestion15(null);
        client.setQuestion16(null);
        client.setQuestion17(null);
        client.setQuestion18(null);
        client.setQuestion19(null);
        client.setQuestion20(null);
        client.setQuestion21(null);
        client.setQuestion22(null);
        client.setQuestion23(null);
        client.setQuestion24(null);
        client.setComment(null);

        // Get the logged-in user and set it to the client
        User currentUser = userService.getCurrentUser();  // Get the logged-in user
        client.setUser(currentUser);  // Set the logged-in user as the responsible user

        // Add the client to the service
        clientService.addClient(client);

        // Redirect back to the dashboard after adding the client
        return "redirect:/users/home";
    }


    @GetMapping("/clients/update/{id}")
    public String showUpdateClientForm(@PathVariable Long id, Model model) {
        Client client = clientService.getClientById(id);
        model.addAttribute("client", client);
        return "updateClient";  // This view will display the form to update an existing client
    }
    @PostMapping("/clients/update")
    public String updateClient(@ModelAttribute Client client) {
        // Get the currently authenticated user from your user service
        User currentUser = userService.getCurrentUser();  // Assuming userService.getCurrentUser() works correctly
        client.setUser(currentUser);  // Set the currently authenticated user to the client

        // Now update the client with the user information
        clientService.updateClient(client);

        // Redirect back to the dashboard after updating the client
        return "redirect:/users/home";
    }
    @GetMapping("/intervention/{clientId}")
    public String showInterventionForm(@PathVariable Long clientId, Model model) {
        Optional<Intervention> existingIntervention = interventionService.getInterventionByClientId(clientId);
        model.addAttribute("clientId", clientId);

        if (existingIntervention.isPresent()) {
            model.addAttribute("intervention", existingIntervention.get());
            model.addAttribute("isUpdate", true);
        } else {
            model.addAttribute("intervention", new Intervention());
            model.addAttribute("isUpdate", false);
        }

        return "interventionForm"; // Make sure this file exists
    }

    @PostMapping("/intervention/save")
    public String saveOrUpdateIntervention(
            @Valid @ModelAttribute("intervention") Intervention intervention,
            BindingResult result,
            @RequestParam Long clientId,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("clientId", clientId);
            model.addAttribute("isUpdate", intervention.getId() != null); // Check if it's an update
            return "interventionForm";
        }

        Client client = clientService.getClientById(clientId);
        if (client == null) {
            model.addAttribute("error", "Client introuvable");
            return "interventionForm";
        }

        intervention.setClient(client);
        interventionService.saveOrUpdateIntervention(intervention, clientId, client);
        return "redirect:/users/answerChecklist/" + clientId; // Directly use the clientId value
    }




    @GetMapping("/answerChecklist/{id}")
    public String showAnswerChecklist(@PathVariable("id") Long clientId, Model model) {
        Client client = clientService.findById(clientId);

        if (client == null) {
            // Handle case when client is not found (e.g. redirect to a list page)
            return "redirect:/users/home"; // Or show a 404 error page
        }

        model.addAttribute("client", client);
        return "answerChecklist";  // View for answer checklist page
    }
    @PostMapping("/home/answerChecklist/{id}")
    public String submitAnswers(@PathVariable("id") Long id, @ModelAttribute Client client) {
        // Retrieve the client from the database using the provided clientId
        Client existingClient = clientService.getClientById(id);  // Use the id to fetch the client

        // Update each boolean attribute from the form submission
        existingClient.setQuestion1(client.getQuestion1());
        existingClient.setQuestion2(client.getQuestion2());
        existingClient.setQuestion3(client.getQuestion3());
        existingClient.setQuestion4(client.getQuestion4());
        existingClient.setQuestion5(client.getQuestion5());
        existingClient.setQuestion6(client.getQuestion6());
        existingClient.setQuestion7(client.getQuestion7());
        existingClient.setQuestion8(client.getQuestion8());
        existingClient.setQuestion9(client.getQuestion9());
        existingClient.setQuestion10(client.getQuestion10());
        existingClient.setQuestion11(client.getQuestion11());
        existingClient.setQuestion12(client.getQuestion12());
        existingClient.setQuestion13(client.getQuestion13());
        existingClient.setQuestion14(client.getQuestion14());
        existingClient.setQuestion15(client.getQuestion15());
        existingClient.setQuestion16(client.getQuestion16());
        existingClient.setQuestion17(client.getQuestion17());
        existingClient.setQuestion18(client.getQuestion18());
        existingClient.setQuestion19(client.getQuestion19());
        existingClient.setQuestion20(client.getQuestion20());
        existingClient.setQuestion21(client.getQuestion21());
        existingClient.setQuestion22(client.getQuestion22());
        existingClient.setQuestion23(client.getQuestion23());
        existingClient.setQuestion24(client.getQuestion24());
        existingClient.setComment(client.getComment());

        // Set the current user who is updating the client
        User currentUser = userService.getCurrentUser();  // Assuming userService.getCurrentUser() works correctly
        existingClient.setUser(currentUser);

        // Save the updated client back to the database
        clientService.updateClient(existingClient);

        // Redirect back to the client dashboard or a confirmation page
        return "redirect:/users/home";  // Redirect to the client's page
    }


}
