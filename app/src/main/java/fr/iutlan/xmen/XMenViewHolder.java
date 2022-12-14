package fr.iutlan.xmen;

import android.view.ContextMenu;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import fr.iutlan.xmen.databinding.XMenBinding;

public class XMenViewHolder extends RecyclerView.ViewHolder {
    private final XMenBinding ui;
    private XMenAdapter.OnItemClickListener onItemClickListener;
    public static final int MENU_EDIT = 1;
    public static final int MENU_DELETE = 2;

    public void setOnItemClickListener(XMenAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public XMenViewHolder(@NonNull XMenBinding ui) {
        super(ui.getRoot());
        this.ui = ui;
        itemView.setOnClickListener(this::onClick);
        itemView.setOnCreateContextMenuListener(this::onCreateContextMenu);
    }
    public void setXMen(XMen xmen) {
        ui.nom.setText(xmen.getNom());
        ui.image.setImageResource(xmen.getIdImage());
        ui.alias.setText(xmen.getAlias());
        ui.description.setText(xmen.getDescription());
        ui.pouvoir.setText(xmen.getPouvoirs());
    }
    private void onClick(View v) {
        if (onItemClickListener != null) // test par précaution
            onItemClickListener.onItemClick(getAdapterPosition());
    }

    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        // position du X-Men concerné
        int position = getAdapterPosition();
        // titre du menu = nom du X-Men
        menu.setHeaderTitle(ui.nom.getText());
        // utiliser order (3e param) pour stocker la position du X-Men
        menu.add(0, MENU_EDIT, position, "Edit");
        menu.add(0, MENU_DELETE, position, "Delete");
    }

}