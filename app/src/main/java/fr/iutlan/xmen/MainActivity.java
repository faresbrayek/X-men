package fr.iutlan.xmen;

import static fr.iutlan.xmen.EditActivity.EXTRA_POSITION;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;
import java.util.zip.Inflater;
import fr.iutlan.xmen.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding ui;
    private List<XMen> liste;
    private XMenAdapter adapter;
    private ActivityResultLauncher<Intent> editActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());

        editActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                this::onEditActivityEnded);

        // obtenir la liste
        XMenApplication application = (XMenApplication) getApplication();
        liste = application.getListe();

        // créer l'adaptateur
        adapter = new XMenAdapter(liste);

        // fournir l'adaptateur au recycler
        ui.recycler.setAdapter(adapter);

        // layout manager
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        ui.recycler.setLayoutManager(lm);

        // écouteur pour les clics sur les éléments
        adapter.setOnItemClickListener(this::onItemClick);
    }

    private void onItemClick(int position) {
        // récupérer le X-Men concerné
        XMen xmen = liste.get(position);
        // changer l'image du X-Men
        xmen.setIdImage(R.drawable.undef);
        // signaler à l'adaptateur que l'élément a changé
        adapter.notifyItemChanged(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint({"NonConstantResourceId", "NotifyDataSetChanged"})
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        XMenApplication application = (XMenApplication) getApplication();
        switch (item.getItemId()) {
            case R.id.reinit:
                // vider la liste
                application.initListe();
                // FIXME signaler le changement
                ui.recycler.getAdapter().notifyDataSetChanged();
                return true;
            case R.id.create:
                // TODO voir exercice suivant
                onEdit(-1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // récupérer la position de l'élément concerné par le menu (dans order)
        int position = item.getOrder();

        // selon le bouton de menu
        switch (item.getItemId()) {
            case XMenViewHolder.MENU_EDIT:
                onEdit(position);
                return true;
            case XMenViewHolder.MENU_DELETE:
                // TODO décommenter quand cette méthode sera ajoutée, voir la fin du sujet
                onDelete(position);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    /**
     * lancer l'édition sur un XMen indiqué par position, -1 si création
     * @param position: index dans la liste, mettre -1 si on veut créer un X-Men
     */
    private void onEdit(int position) {
        // TODO créer un Intent pour lancer EditActivity
        Intent intent = new Intent(this, EditActivity.class);
        // TODO ajouter un extra "position" contenant position
        intent.putExtra("position", position);
        // démarrer l'activité avec attente du résultat
        editActivityLauncher.launch(intent);
    }

    private void onEditActivityEnded(ActivityResult result) {
        if (result.getResultCode() == RESULT_CANCELED) return;
        // TODO prévenir l'adaptateur que la liste a changé
        adapter.notifyDataSetChanged();
    }

    private void onDelete(int position) {
        XMen xmen = liste.get(position);
        new AlertDialog.Builder(this)
                .setTitle(xmen.getNom())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Vous confirmez la suppression ?")
                // le bouton "oui" supprime vraiment
                .setPositiveButton(android.R.string.ok,
                        (dialog, idbtn) -> onReallyDelete(position))
                // le bouton "non" ne fait rien
                .setNegativeButton(android.R.string.cancel, null)
                // affichage du dialogue
                .show();
    }

    private void onReallyDelete(int position)
    {
        liste.remove(position);
        // TODO signaler à l'adaptateur que cette position a été supprimée
        adapter.notifyItemRemoved(position);
    }

}