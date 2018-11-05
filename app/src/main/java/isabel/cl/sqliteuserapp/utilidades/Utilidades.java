package isabel.cl.sqliteuserapp.utilidades;

public class Utilidades {

    //constantes campos tabla cliente
    public static final String CLIENT_TABLE="cliente";
    public static final String ID_FIELD="id";
    public static final String NAME_FIELD="nombre";
    public static final String LASTNAME_FIELD="apellido";



    public static final String CREATE_CLIENT_TABLE = "CREATE TABLE "+CLIENT_TABLE+" ("+ID_FIELD+" INTEGER, "+NAME_FIELD+" TEXT, "+LASTNAME_FIELD+" TEXT)";


}
