package com.ezediaz.login.ui.login;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.ezediaz.login.model.Usuario;
import com.ezediaz.login.request.ApiClient;
import com.ezediaz.login.ui.registro.RegistroActivity;

public class MainActivityViewModel extends AndroidViewModel {
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public void logearse(String email, String password) {
        Usuario usuario = ApiClient.login(getApplication().getApplicationContext(), email, password);
        if (usuario != null) {
            Intent intent = new Intent(getApplication(), RegistroActivity.class);
            intent.putExtra("usuario", true);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplication().startActivity(intent);
        } else {
            Toast.makeText(getApplication(), "Email o contraseña incorrecta", Toast.LENGTH_LONG).show();
        }
    }

    public void registrarse(){
        Intent intent = new Intent(getApplication(), RegistroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("usuario", false);
        getApplication().startActivity(intent);
    }
}
