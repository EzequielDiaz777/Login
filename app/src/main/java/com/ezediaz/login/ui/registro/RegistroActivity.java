package com.ezediaz.login.ui.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ezediaz.login.R;
import com.ezediaz.login.databinding.ActivityRegistroBinding;
import com.ezediaz.login.model.Usuario;

public class RegistroActivity extends AppCompatActivity {
    private RegistroActivityViewModel vm;
    private ActivityRegistroBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);
        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dni = binding.etDNI.getText().toString();
                String nombre = binding.etNombre.getText().toString();
                String apellido = binding.etApellido.getText().toString();
                String email = binding.etEmailPerfil.getText().toString();
                String password = binding.etPasswordPerfil.getText().toString();
                vm.guardar(dni, nombre, apellido, email, password);
            }
        });
        vm.getMUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.etDNI.setText(usuario.getDni());
                binding.etNombre.setText(usuario.getNombre());
                binding.etApellido.setText(usuario.getApellido());
                binding.etEmailPerfil.setText(usuario.getEmail());
                binding.etPasswordPerfil.setText(usuario.getPassword());
            }
        });
        Intent intent = getIntent();
        vm.cargar(intent);
    }
}