package com.example.aziz.service;

import com.example.aziz.entity.Client;
import com.example.aziz.entity.User;
import com.example.aziz.repository.ClientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getClientsByUser(User user) {
        return clientRepository.findByUser(user);  // Query to get clients associated with a user
    }

    public void addClient(Client client) {
        clientRepository.save(client);  // Save the new client to the database
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
    }

    public void updateClient(Client client) {
        clientRepository.save(client);  // Update the client in the database
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }
    private Map<String, Map<String, Integer>> questionStats;

    @PersistenceContext
    private EntityManager entityManager;

    // Method to count true values dynamically for the given attribute
    public long countByQuestionTrue(String questionAttribute) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Client> client = cq.from(Client.class);

        // Dynamically get the path for the specified attribute
        Path<Boolean> questionPath = client.get(questionAttribute);

        // Count where the question attribute is true
        cq.select(cb.count(client)).where(cb.isTrue(questionPath));

        TypedQuery<Long> query = entityManager.createQuery(cq);
        return query.getSingleResult();
    }

    // Method to count false values dynamically for the given attribute
    public long countByQuestionFalse(String questionAttribute) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Client> client = cq.from(Client.class);

        // Dynamically get the path for the specified attribute
        Path<Boolean> questionPath = client.get(questionAttribute);

        // Count where the question attribute is false
        cq.select(cb.count(client)).where(cb.isFalse(questionPath));

        TypedQuery<Long> query = entityManager.createQuery(cq);
        return query.getSingleResult();
    }

    public void updateStats(String question, int answer) {
        // Convert 0 or 1 to "Yes" or "No"
        String answerStr = (answer == 1) ? "Yes" : "No";

        // Update the respective stats in the map
        Map<String, Integer> stats = questionStats.get(question);
        stats.put(answerStr, stats.getOrDefault(answerStr, 0) + 1);
    }

    // Calculate the percentage of "Yes" answers
    public double calculateQuestionPercentage(String questionAttribute) {
        // Dynamically generate the count method based on the questionAttribute
        long trueCount = countByQuestionTrue(questionAttribute);
        long falseCount = countByQuestionFalse(questionAttribute);

        // Calculate the total responses for the given question
        long totalCount = trueCount + falseCount;

        // Calculate and return the percentage of "true" responses
        return (totalCount > 0) ? (trueCount / (double) totalCount) * 100 : 0;
    }

}
