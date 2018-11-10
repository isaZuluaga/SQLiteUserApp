package isabel.cl.sqliteuserapp;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import isabel.cl.sqliteuserapp.utilidades.Utilidades;

public class RegistrarCliente extends AppCompatActivity {

    EditText regIdCliente, regNomCliente, regApellCliente;
    SQliteConnectionHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);

        //para añadir el boton de regresar a la actividad inicial
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        regIdCliente = (EditText) findViewById(R.id.regIdCliente);
        regNomCliente = (EditText) findViewById(R.id.regNombreCliente);
        regApellCliente=(EditText) findViewById(R.id.regApellidoCliente);


    }

     void registrarBtn (View view){

        if(regIdCliente.length()>0 && regNomCliente.length()>0 && regApellCliente.length()>0){

            addDataToDataBase();
        } else {

            Toast.makeText(this, "Error, por favor llene todos los campos solicitados", Toast.LENGTH_SHORT).show();

        }

    }

    private void addDataToDataBase (){
        // Guardamos la informacion ingresada por el usuario

        String ident = regIdCliente.getText().toString();
        String nombre = regNomCliente.getText().toString();
        String apellido = regApellCliente.getText().toString();


            // Crea conexion con la base de datos
            conn=new SQliteConnectionHelper(getApplicationContext(),"DBClientes",null,1);
            SQLiteDatabase db=conn.getWritableDatabase();

            // Se agregan los datos a la base de datos local
            String insert = "INSERT INTO " + Utilidades.CLIENT_TABLE
                    + " ("
                    + Utilidades.ID_FIELD + "," + Utilidades.NAME_FIELD + "," + Utilidades.LASTNAME_FIELD + ")" +
                    " VALUES (" + ident + ",'" + nombre + "','" + apellido + "')";

            db.execSQL(insert);

            db.close();
            Toast.makeText(this, "Usuario añadido exitosamente a la bd local", Toast.LENGTH_SHORT).show();
            regIdCliente.setText("");
            regNomCliente.setText("");
            regApellCliente.setText("");


    }


    // boton regresar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == android.R.id.home){

            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
