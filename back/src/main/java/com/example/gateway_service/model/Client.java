package com.example.gateway_service.model;

public class Client extends User {
    public Client(){
        
    }
    public Client(int id, String name, String email, String password){
        super(id,name,email,password);
        // super();
        
    }

    public static void main(String[] args) {
        Client cliente = new Client(1, "Carlos", "carlos@email.com", "senha123");

        // Exibindo os dados do cliente
        System.out.println("ID: " + cliente.getId());
        System.out.println("Nome: " + cliente.getName());
        System.out.println("Email: " + cliente.getEmail());
        System.out.println("Senha: " + cliente.getPassword());
        // System.out.println("Telefone: " + cliente.getTelefone());
    }
}
