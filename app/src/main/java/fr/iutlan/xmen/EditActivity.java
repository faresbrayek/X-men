package fr.iutlan.xmen;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

import fr.iutlan.xmen.databinding.ActivityEditBinding;

public class EditActivity extends AppCompatActivity {

    private ActivityEditBinding ui;
    private XMen xmen;
    private List<XMen> liste;
    public static final String EXTRA_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());
        // récupérer l'instance de XMen
        Intent intent = getIntent();
        // obtenir la liste
        XMenApplication application = (XMenApplication) getApplication();
        liste = application.getListe();
        // récupérer la position du XMen dans la liste
        int position = intent.getIntExtra(EXTRA_POSITION, -1);
        if (position != -1) {
            // TODO récupérer le XMen à l'index position
            xmen = liste.get(position);
            setXMen(xmen);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // ajouter mes items de menu
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        // ajouter les items du système s'il y en a
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * créer une nouvelle instance de XMen et la remplir avec les EditText de
     * l'interface
     */

    public void onAccept(MenuItem item) {
        // TODO si xmen est null, il faut le créer
        if (xmen == null) {
            xmen = new XMen();
            liste.add(xmen);
        }
        xmen.setNom(ui.nom.getText().toString());
        xmen.setAlias(ui.alias.getText().toString());
        xmen.setPouvoirs(ui.pouvoir.getText().toString());
        xmen.setDescription(ui.description.getText().toString());
        // TODO ajouter ce xmen dans la liste


        // terminer l'activité en indiquant un succès
        setResult(RESULT_OK);
        finish();
    }

    private void setXMen(XMen xmen) {
        // TODO mettre les informations du XMen dans les vues
        ui.nom.setText(xmen.getNom());
        ui.alias.setText(xmen.getAlias());
        ui.pouvoir.setText(xmen.getPouvoirs());
        ui.description.setText(xmen.getDescription());
    }
}
