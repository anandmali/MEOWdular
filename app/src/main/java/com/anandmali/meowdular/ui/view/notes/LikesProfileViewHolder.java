package com.anandmali.meowdular.ui.view.notes;

import androidx.recyclerview.widget.RecyclerView;

import com.anandmali.meowdular.databinding.ItemViewLikesProfileBinding;
import com.anandmali.meowdular.network.data.notes.CatProfiles;

public class LikesProfileViewHolder extends RecyclerView.ViewHolder {

    ItemViewLikesProfileBinding binding;

    public LikesProfileViewHolder(ItemViewLikesProfileBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(CatProfiles likesProfile) {
        binding.setModel(likesProfile);
        binding.executePendingBindings();
    }

}
