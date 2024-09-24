package com.ism.repository;
import java.util.ArrayList;
import java.util.List;
import com.ism.entities.User;

public class UserRepositoryBD{ 
    private static List<User> users;
    
    public UserRepositoryBD(){
        users= new ArrayList<>();

    }
    
    
    public void ajouterUser(User user){
        users.add(user);
        System.out.println("utilisateur ajouté avec succés "+user);
    }
    
    
    public void desactiverUser(String email){
        for(User user : users){
            if(user.getEmail().equals(email)){
                user.setActive(false);
                System.out.println("utilisateur désactivé avec succés "+user);
                break;
            }
        }
    }

    public void ActiverUser(String email){
        for(User user : users){
            if(user.getEmail().equals(email)){
                user.setActive(true);
                System.out.println("le user à ete active "+user);
                break;
            }
        }
    }
    public User findByLoginUser(String  login){
        for(User  user:users){
            if (user.getLogin().equals(login)) {
                return user;
                
            }
        }
        return null;
    }
    
    
    public boolean updateUser(User user){
        for (int i =0;i<users.size();i++){
            if (users.get(i).getLogin().equals(user.getLogin())){
                users.set(i, user);
                System.out.println("le user  a ete mis a jour avec succes"+ user);
                return true;

            }
        }
        return false;
    }        
    
        public static  void afficherUser(){
        if(users.isEmpty()){
            System.out.println("aucun utilisateur");

        }else{
            users.forEach(System.out::println);
        }
    }
    
    
}