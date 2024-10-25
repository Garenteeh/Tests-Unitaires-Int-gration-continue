package com.test.exo_spring.objet;

import java.util.Date;

public class Personne {

    int id;
    String nom;
    String prenom;
    Date dateNaissance;

    String pays;

    public Personne() {
    }

    public Personne(int id, String nom, String prenom, Date dateNaissance, String pays) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.pays = pays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Boolean isMajeur() {
        if (dateNaissance != null) {
            Date dateNow = new Date();
            long diff = dateNow.getTime() - dateNaissance.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            long diffYears = diffDays / 365;
            if (diffYears >= 18) {
                return true;
            }
        }
        return false;
    }

    public Boolean livesInFrance() {
        if (pays != null) {
            if (pays.equals("France")) {
                return true;
            }
        }
        return false;
    }

    public String getNomComplet() {
        return prenom + " " + nom;
    }

}
