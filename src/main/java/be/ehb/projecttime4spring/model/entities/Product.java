package be.ehb.projecttime4spring.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Naam mag niet leeg zijn")
    @Size(min = 5, max = 30, message = "Lengte moet tussen 5 en 30 karakters bevatten")
    private String naam;
    @Size(max = 16, message = "Lengte mag max 16 karakters bevatten")
    private String categorie;
    @NotEmpty(message = "Omschrijving mag niet leeg zijn")
    @Size(max = 500, message = "Lengte mag max 500 karakters bevatten")
    private String omschrijving;

    @DecimalMin(value = "0.1", message = "Prijs moet minimum €0.1 zijn")
    @DecimalMax(value = "5000", message = "Prijs mag niet hoger dan €5000 zijn")
    private double vraagPrijs;

    @Size(max = 250, message = "Lengte mag max 250 karakters bevatten")
    private String afbeeldingUrl;

    @ManyToOne
    @JoinColumn(name = "verkoperId", nullable = false)
    private Verkoper productVerkoper;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public double getVraagPrijs() {
        return vraagPrijs;
    }

    public void setVraagPrijs(double vraagPrijs) {
        this.vraagPrijs = vraagPrijs;
    }

    public Verkoper getProductVerkoper() {
        return productVerkoper;
    }

    public void setProductVerkoper(Verkoper productVerkoper) {
        this.productVerkoper = productVerkoper;
    }

    public String getAfbeeldingUrl() {
        return afbeeldingUrl;
    }

    public void setAfbeeldingUrl(String afbeeldingUrl) {
        this.afbeeldingUrl = afbeeldingUrl;
    }
}
