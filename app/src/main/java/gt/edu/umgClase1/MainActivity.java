package gt.edu.umgClase1;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import gt.edu.umgClase1.BaseDatos.DbHelper;

public class MainActivity extends AppCompatActivity {

    Button btnSaludo, btnCrearDb;
    TextView tvSaludo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // CODIGO NUEVO
        btnSaludo = findViewById(R.id.btnSaludo);
        tvSaludo = findViewById(R.id.tvSaludo);
        btnCrearDb = findViewById(R.id.btnCrearDb);

        btnSaludo.setOnClickListener(v -> {
            Toast.makeText(this, "Aviso Ruldin", Toast.LENGTH_LONG).show();
            tvSaludo.setText("Hola Jairo");
        });

        btnCrearDb.setOnClickListener(v -> {

            //crear base de datos
            DbHelper dbHelper = new DbHelper(this);
            dbHelper.getWritableDatabase();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if(db != null){
                Toast.makeText(this, "Base de datos creada", Toast.LENGTH_SHORT).show();
                tvSaludo.setText("Base de datos creada");
            }else{
                Toast.makeText(this, "Error al crear la base de datos", Toast.LENGTH_SHORT).show();
                tvSaludo.setText("Error al crear la base de datos");
            }
            //CREAR UN BOTON NUEVO QUE LLAME AL NUEVO ACTIVIY CON EL COD QUE MANDO EL INGE
            //EN EL SEGUNDO ACTIVITY COLOCAR PARA GRABAR INFO

        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}