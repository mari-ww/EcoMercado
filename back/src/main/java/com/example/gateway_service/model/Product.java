package com.example.gateway_service.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private String description;
    private int quantity;
    private float environmentalImpact; 

    public Product(){}
 
    public Product(int id, String name, double price, String description, int quantity, float environmentalImpact){
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.environmentalImpact = environmentalImpact;

    }

    public int getId() {
        return id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getEnvironmentalImpact() {
        return environmentalImpact;
    }

    public void setEnvironmentalImpact(float environmentalImpact) {
        this.environmentalImpact = environmentalImpact;
    }

    public boolean estaDisponivel(){
        return  this.getQuantity() > 0;
    }


}
