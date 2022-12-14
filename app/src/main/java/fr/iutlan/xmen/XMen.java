package fr.iutlan.xmen;

import androidx.annotation.DrawableRes;

public class XMen {
    private String nom;
    private @DrawableRes int idImage;
    private String alias;
    private String description;
    private String pouvoirs;

    public XMen() {
        nom = "inconnu";
        this.idImage = R.drawable.undef;
    }

    public XMen(String nom, @DrawableRes int idImage, String alias, String description, String pouvoirs) {
        this.nom = nom;
        this.idImage = idImage;
        this.alias = alias;
        this.description = description;
        this.pouvoirs = pouvoirs;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public @DrawableRes int getIdImage() {
        return idImage;
    }

    public void setIdImage(@DrawableRes int idImage) {
        this.idImage = idImage;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPouvoirs() {
        return pouvoirs;
    }

    public void setPouvoirs(String pouvoirs) {
        this.pouvoirs = pouvoirs;
    }
}
