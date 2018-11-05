package isabel.cl.sqliteuserapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import isabel.cl.sqliteuserapp.entidades.Cliente;
import isabel.cl.sqliteuserapp.utilidades.Utilidades;

public class ConsultarCliente extends AppCompatActivity {

    Spinner comboCliente;
    TextView clienteId,clienteNombre,clienteApellido;
    ArrayList<String> listaCliente;
    ArrayList<Cliente> clienteLista;

    SQliteConnectionHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_cliente);

        //para añadir el boton de regresar a la actividad inicial
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        conn=new SQliteConnectionHelper(getApplicationContext(),"DBClientes",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();

        comboCliente= (Spinner) findViewById(R.id.spinnerUsers);

        clienteId= (TextView) findViewById(R.id.clienteID);
        clienteNombre = (TextView) findViewById(R.id.clienteNOMBRE);
        clienteApellido= (TextView) findViewById(R.id.clienteAPELLIDO);

        consultClientlist();


        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaCliente);

        comboCliente.setAdapter(adaptador);

        comboCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (position!=0){
                    String nombre = Integer.toString(clienteLista.get(position-1).getId());
                    clienteId.setText(nombre);
                    clienteNombre.setText(clienteLista.get(position-1).getNombre());
                    clienteApellido.setText(clienteLista.get(position-1).getApellido());




                }else{
                    clienteId.setText("");
                    clienteNombre.setText("");
                    clienteApellido.setText("");


                }

                Toast.makeText(parent.getContext(), "Seleccionado: "+parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





    }

    void btnEliminarCliente (View view){
        if(clienteId.length()>0 && clienteNombre.length()>0 && clienteApellido.length()>0){
            eliminateFromDataBase();
            finish();
            startActivity(getIntent());

        } else{
            Toast.makeText(this, "Por favor seleccione el cliente a eliminar", Toast.LENGTH_SHORT).show();

        }




    }

    private void consultClientlist() {

        SQLiteDatabase db=conn.getReadableDatabase();

        Cliente cliente = null;
        //crea instancia a la lista clientelista
        clienteLista=new ArrayList<Cliente>();

        //select * from libraries
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.CLIENT_TABLE,null);

        while (cursor.moveToNext()){
            cliente=new Cliente();
            cliente.setId(cursor.getInt(0));
            cliente.setNombre(cursor.getString(1));
            cliente.setApellido(cursor.getString(2));





            clienteLista.add(cliente);
        }

        obtainList();

    }

    private void obtainList() {
        listaCliente=new ArrayList<String>();
        listaCliente.add("seleccione un Cliente");

        for (int i=0;i<clienteLista.size();i++){
            listaCliente.add(clienteLista.get(i).getId()+" - "+clienteLista.get(i).getNombre());
        }
    }

    private void eliminateFromDataBase() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String id = clienteId.getText().toString();
        String nombre = clienteNombre.getText().toString();


        String delete = "DELETE FROM " + Utilidades.CLIENT_TABLE+ " WHERE "+Utilidades.ID_FIELD+"='"+id+"'";

        db.execSQL(delete);

        db.close();
        Toast.makeText(this, "cliente: " +nombre+ "eliminado en la base de datos", Toast.LENGTH_SHORT).show();
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
