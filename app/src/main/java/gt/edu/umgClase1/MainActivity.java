public class MainActivity extends AppCompatActivity {

    Button bntSaludo,btnCrearDb,btn_nuevo;
    TextView tvSaludo;
    RecyclerView listaContactos;

    ArrayList<Contactos> listaArrayContactos;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //nuevo inicio
        //poner la lista
        String error;
        try{
            listaContactos = findViewById(R.id.listaContactos);

            listaContactos.setLayoutManager(new LinearLayoutManager(this));
        } catch (Exception e) {
            Toast.makeText(this, "Error al cargar lista:"+ e.getMessage(), Toast.LENGTH_SHORT).show();
            error = e.getMessage();
        }



  //
        DbContactos dbContactos = new DbContactos(this);
        listaArrayContactos =  new ArrayList<>();   //dbContactos.mostrarContactos() ;


        //llamamos a nuestro adaptador y le mandamos todos los contactos de nuestra consulta.
        ListaContactosAdapter adapter = new ListaContactosAdapter(dbContactos.mostrarContactos());
        //le pasamos el adaptador a nuestro recycler view y nuestra información estructurada.
        listaContactos.setAdapter(adapter);




        //codigo clase pasada
        bntSaludo = findViewById(R.id.btnSaludo);
        tvSaludo = findViewById(R.id.tvSaludo);
        btnCrearDb = findViewById(R.id.btnCrearDb);
        btn_nuevo = findViewById(R.id.btn_nuevo);
        //nueva actividad
        btn_nuevo.setOnClickListener(v -> {
            //Intent intent = new Intent(this, NuevoActivity.class);
            //startActivity(intent);
            //crear registro llama a la activity NuevoActivity
                Toast.makeText(this, "Creando Registro", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, NuevoActivity.class);
                startActivity(intent);
            //fin
        });

//crear db
        btnCrearDb.setOnClickListener(v -> {

            //crear base de datos
            DbHelper dbHelper = new DbHelper(this);
            dbHelper.getWritableDatabase();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if (db!=null) {
                Toast.makeText(this, "Base de datos creada", Toast.LENGTH_SHORT).show();
                tvSaludo.setText("Base de datos creada");
                //new DbContactos().insertaContacto("Ruldin", "12345678", "xxqgg.com");
            } else {
                Toast.makeText(this, "Error al crear base de datos", Toast.LENGTH_SHORT).show();
                tvSaludo.setText("Error al crear base de datos");
            }
        });


        bntSaludo.setOnClickListener(v -> {
            Toast.makeText(this, "Aviso Ruldin", Toast.LENGTH_SHORT).show();
            tvSaludo.setText("Hola Ruldin");
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}