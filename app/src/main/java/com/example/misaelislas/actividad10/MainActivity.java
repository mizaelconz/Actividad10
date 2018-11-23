import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    MyService myService;
    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE); //*
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            unbindService(mConnection);  //*
            isBound = false;
        }
    }

    public void iniciarServicio(View view) {
        Toast.makeText(this, "Servicio Iniciado", Toast.LENGTH_SHORT).show();
        onStart();
    }

    public void detenerServicio(View view) {
        Toast.makeText(this, "Servicio Detenido", Toast.LENGTH_SHORT).show();
        onStop();
    }

    public void numeroAleatorio(View view) {
        if (isBound) {
            int num = myService.getRandomNumber();
            Toast.makeText(this, "Numero generado: " + num, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Para generar el numero aleatorio, inicie primero el servicio", Toast.LENGTH_SHORT).show();
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.LocalBinder binder = (MyService.LocalBinder) service;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };
}