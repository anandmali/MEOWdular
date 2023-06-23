package com.anandmali.aisledesign.ui.view.notes;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.anandmali.aisledesign.R;
import com.anandmali.aisledesign.databinding.ItemViewLikesProfileBinding;
import com.anandmali.aisledesign.network.data.notes.CatProfiles;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LikesProfileAdapter extends RecyclerView.Adapter<LikesProfileViewHolder> {

    private final List<CatProfiles> likesProfileList;

    public LikesProfileAdapter(List<CatProfiles> hiddenProfileList) {
        this.likesProfileList = hiddenProfileList;
    }

    @NonNull
    @NotNull
    @Override
    public LikesProfileViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemViewLikesProfileBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_view_likes_profile, parent, false);
        return new LikesProfileViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LikesProfileViewHolder holder, int position) {
        CatProfiles profile = likesProfileList.get(position);
        holder.bind(profile);
    }

    @Override
    public int getItemCount() {
        return likesProfileList.size();
    }

}
