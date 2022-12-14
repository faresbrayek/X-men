package fr.iutlan.xmen;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import fr.iutlan.xmen.databinding.XMenBinding;

public class XMenAdapter extends RecyclerView.Adapter<XMenViewHolder> {
    private List<XMen> liste;
    private XMenAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public XMenAdapter(List<XMen> liste) {
        this.liste = liste;
    }

    @NonNull
    @Override
    public XMenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        XMenBinding ui = XMenBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new XMenViewHolder(ui);
    }

    @Override
    public void onBindViewHolder(@NonNull XMenViewHolder holder, int position) {
        holder.setXMen(liste.get(position));
        holder.setOnItemClickListener(onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return this.liste.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
