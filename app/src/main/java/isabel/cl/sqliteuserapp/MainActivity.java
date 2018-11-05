package isabel.cl.sqliteuserapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    void irRegistrarBtn (View view){

        Intent intention = new Intent(this, RegistrarCliente.class);
        startActivity(intention);

    }

    void irConsultarBtn (View view){

        Intent intention = new Intent(this, ConsultarCliente.class);
        startActivity(intention);

    }

    }
