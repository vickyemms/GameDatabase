package com.example.gamedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etYear, etMinPlayers, etMaxPlayers;
    Button btnViewAll, btnAdd;
    RadioGroup rgGenres;
    ListView lvGameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etYear = findViewById(R.id.etYear);
        etMinPlayers = findViewById(R.id.etMinPlayers);
        etMaxPlayers = findViewById(R.id.etMaxPlayers);
        rgGenres = findViewById(R.id.rgGenres);
        btnViewAll = findViewById(R.id.btnViewAll);
        btnAdd = findViewById(R.id.btnAdd);
        lvGameList = findViewById(R.id.lvGameList);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String genre = ((RadioButton)findViewById(rgGenres.getCheckedRadioButtonId())).getText().toString();

                    Game game = new Game(-1, etName.getText().toString(),
                            Integer.parseInt(etYear.getText().toString()),
                            Integer.parseInt(etMinPlayers.getText().toString()),
                            Integer.parseInt(etMaxPlayers.getText().toString()), genre);

                    Toast.makeText(MainActivity.this, game.toString(), Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Missing input", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "View All", Toast.LENGTH_SHORT).show();
            }
        });

    }
}