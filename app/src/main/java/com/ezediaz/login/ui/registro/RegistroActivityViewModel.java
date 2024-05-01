package com.ezediaz.login.ui.registro;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.ezediaz.login.model.Usuario;
import com.ezediaz.login.request.ApiClient;
import com.ezediaz.login.ui.login.MainActivity;

public class RegistroActivityViewModel extends AndroidViewModel {
    private MutableLiveData<Usuario> mUsuario;
    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Usuario> getMUsuario(){
        if(mUsuario == null){
            mUsuario = new MutableLiveData<>();
        }
        return mUsuario;
    }

    public void cargar(Intent intent){
        boolean existe = intent.getBooleanExtra("usuario", false);
        Usuario usuario = new Usuario();
        if(existe){
            usuario = ApiClient.leer(getApplication());
        }
        mUsuario.setValue(usuario);
    }

    public void guardar(String dni, String nombre, String apellido, String email, String password){
        if(!dni.equals("") && !nombre.equals("") && !apellido.equals("") && !email.equals("") && !password.equals("")){
            Usuario usuario = new Usuario(dni, nombre, apellido, email, password);
            ApiClient.guardar(getApplication(), usuario);
            Intent intent = new Intent(getApplication(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplication().startActivity(intent);
        } else {
            Toast.makeText(getApplication(), "Debe ingresar datos en todos los campos", Toast.LENGTH_LONG).show();
        }
    }
}
