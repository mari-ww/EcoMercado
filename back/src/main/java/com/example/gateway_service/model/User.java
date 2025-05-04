package com.example.gateway_service.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "users" )
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String name;
    private String email;
    private String password;
    public int getId() {
        return id;
    }
    public User(){}

    public User(int id, String name, String email, String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) {
        User usuario = new User(1, "Carlos", "carlos@email.com", "senha123");

        // Exibindo os dados do cliente
        System.out.println("ID: " + usuario.getId());
        System.out.println("Nome: " + usuario.getName());
        System.out.println("Email: " + usuario.getEmail());
        System.out.println("Senha: " + usuario.getPassword());
        // System.out.println("Telefone: " + usuario.getTelefone());
    }
}
