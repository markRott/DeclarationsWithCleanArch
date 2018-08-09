package app.com.dataonsubmitteddeclarations.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public abstract class BaseRecyclerAdapter<Item, Holder extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<Holder> {

    private List<Item> data;
    private Context context;

    public BaseRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(@NonNull List<Item> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public List<Item> getData() {
        return data == null ? Collections.emptyList() : data;
    }

    public Context getContext() {
        return context;
    }

    protected Item getItemByPosition(int position) {
        return data.get(position);
    }
}
