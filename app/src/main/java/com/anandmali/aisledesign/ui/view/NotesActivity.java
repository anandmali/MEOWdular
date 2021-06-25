package com.anandmali.aisledesign.ui.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.anandmali.aisledesign.R;
import com.anandmali.aisledesign.databinding.ActivityNotesBinding;


public class NotesActivity extends AppCompatActivity {

    private ActivityNotesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_notes);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}