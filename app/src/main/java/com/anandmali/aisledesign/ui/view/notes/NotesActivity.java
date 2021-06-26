package com.anandmali.aisledesign.ui.view.notes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.anandmali.aisledesign.R;
import com.anandmali.aisledesign.databinding.ActivityNotesBinding;
import com.google.android.material.badge.BadgeDrawable;


public class NotesActivity extends AppCompatActivity {

    private ActivityNotesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_notes);
        NavigationUI.setupWithNavController(binding.navView, navController);

        //These static badge counts are for testing presentation only,
        BadgeDrawable notesBadge = binding.navView.getOrCreateBadge(R.id.navigation_notes);
        notesBadge.setBackgroundColor(getColor(R.color.purple));
        notesBadge.setNumber(9);

        BadgeDrawable matchesBadge = binding.navView.getOrCreateBadge(R.id.navigation_matches);
        matchesBadge.setBackgroundColor(getColor(R.color.purple));
        matchesBadge.setNumber(50);

    }

}