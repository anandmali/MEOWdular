package com.anandmali.aisledesign.ui.view.notes;

import androidx.recyclerview.widget.RecyclerView;

import com.anandmali.aisledesign.databinding.ItemViewLikesProfileBinding;
import com.anandmali.aisledesign.network.data.notes.LikesProfile;

public class LikesProfileViewHolder extends RecyclerView.ViewHolder {

    ItemViewLikesProfileBinding binding;

    public LikesProfileViewHolder(ItemViewLikesProfileBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(LikesProfile likesProfile) {
        binding.setModel(likesProfile);
        binding.executePendingBindings();
    }

}
