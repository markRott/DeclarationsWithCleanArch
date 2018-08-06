package app.com.dataonsubmitteddeclarations.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public abstract class BaseRecyclerAdapter<Item, Holder extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<Holder> {

    private List<Item> data;

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(@NonNull List<Item> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void addData(Item data) {
        this.data.add(data);
        notifyDataSetChanged();
    }

    public void addAllData(@NonNull List<Item> newData) {
        this.data.addAll(newData);
    }

    public List<Item> getData() {
        return data == null ? Collections.emptyList() : data;
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void clear() {
        if (data != null) {
            data.clear();
            notifyDataSetChanged();
        }
    }

    protected Item getItemByPosition(int position) {
        return data.get(position);
    }
}
