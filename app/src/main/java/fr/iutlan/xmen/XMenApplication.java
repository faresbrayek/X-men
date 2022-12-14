package fr.iutlan.xmen;

import android.app.Application;
import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XMenApplication extends Application
{
    // variable globale de l'application : la liste des XMen
    private final List<XMen> liste = new ArrayList<>();

    public List<XMen> getListe() {
        return liste;
    }

    // initialisation du contexte
    @Override public void onCreate() {
        super.onCreate();
        initListe();

    }

    public void initListe() {
        this.liste.clear();
        // accès aux ressources
        Resources res = getResources();
        final String[] noms = res.getStringArray(R.array.noms);
        TypedArray images = res.obtainTypedArray(R.array.idimages);
        final String[] alias = res.getStringArray(R.array.alias);
        final String[] descriptions = res.getStringArray(R.array.descriptions);
        final String[] pouvoirs = res.getStringArray(R.array.pouvoirs);
        // recopier les données dans la liste
        for (int i=0; i<noms.length; ++i) {
            // constructeur avec tous les paramètres
            XMen xm = new XMen(noms[i],images.getResourceId(i, 0),alias[i], descriptions[i], pouvoirs[i]);
            // ajout à la liste
            liste.add(xm);
        }
        // libérer certaines ressources explicitement
        images.recycle();
    }
}