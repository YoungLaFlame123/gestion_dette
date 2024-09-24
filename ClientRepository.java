package com.ism.repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ism.entities.Client;


public class ClientRepository {
    // private List<Client> clients;
    private Connection connection;

    public ClientRepository(){
        // clients= new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ges_dette", "root", "root");
            System.out.println("Connecté  avec succès");

        } catch (ClassNotFoundException e){
            System.out.println("Error loading driver" + e.getMessage());

        }catch(SQLException e){
            System.out.println("erreur a la base de donnée" + e.getMessage());
        }
        if (connection == null){
            System.out.println("erreur de connexion");
        }
    }
    
    public void ajouterClient(Client client){
        try {
            // Créer une requête préparée pour insérer un client
            PreparedStatement ps = connection.prepareStatement("INSERT INTO clients (surname, telephone, adresse) VALUES (?, ?, ?)");
            ps.setString(1, client.getSurname());
            ps.setString(2, client.getTelephone());
            ps.setString(3, client.getAdresse());
            ps.executeUpdate();
            System.out.println("Client créé avec succès : " + client);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'insertion du client : " + e.getMessage());
        }
    }
    
            // clients.add(client);
        // System.out.println("client cree avec succes"+ client);
        // }
    
    
    
    public Client findClientByTelephone(String telephone){
        try {
            // Créer une requête préparée pour sélectionner un client par téléphone
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM clients WHERE telephone = ?");
            ps.setString(1, telephone);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Client client = new Client(telephone, telephone, telephone);
                client.setSurname(rs.getString("surname"));
                client.setTelephone(rs.getString("telephone"));
                client.setAdresse(rs.getString("adresse"));
                return client;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la sélection du client : " + e.getMessage());
            return null;
        }
    }
        
        
        
        // for(Client client :clients){
        //     if (client.getTelephone().equals(telephone)){
        //         return client;

        //     }
        
    public boolean updateClient(Client client){
        try {
            // Créer une requête préparée pour mettre à jour un client
            PreparedStatement ps = connection.prepareStatement("UPDATE clients SET surname = ?, adresse = ? WHERE telephone = ?");
            ps.setString(1, client.getSurname());
            ps.setString(2, client.getAdresse());
            ps.setString(3, client.getTelephone());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Le client a été mis à jour avec succès : " + client);
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du client : " + e.getMessage());
            return false;
        }
    }
        
    public void afficherClients() {
        try {
            // Créer une requête préparée pour sélectionner tous les clients
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM clients");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                while (rs.next()) {
                    Client client = new Client(null, null, null);
                    client.setSurname(rs.getString("surname"));
                    client.setTelephone(rs.getString("telephone"));
                    client.setAdresse(rs.getString("adresse"));
                    System.out.println(client);
                }
            } else {
                System.out.println("Aucun client n'est enregistré");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la sélection des clients : " + e.getMessage());
        }
    }
    public void close(){
        try{
            connection.close();
        }catch (SQLException e){
            System.out.println("Erreur lors de la fermeture de la connexion " + e.getMessage());
      
        }
    }    
}
