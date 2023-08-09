package com.anandmali.meowdular.ui.view.notes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.anandmali.meowdular.R;
import com.anandmali.meowdular.databinding.ActivityNotesBinding;
import com.google.android.material.badge.BadgeDrawable;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class NotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityNotesBinding binding = ActivityNotesBinding.inflate(getLayoutInflater());
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
