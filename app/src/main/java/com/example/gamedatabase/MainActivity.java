package com.example.gamedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etName, etYear, etMinPlayers, etMaxPlayers;
    Button btnAdd;
    RadioGroup rgGenres;
    ListView lvGameList;

    GameDao gameDao;
    ArrayAdapter gameArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etYear = findViewById(R.id.etYear);
        etMinPlayers = findViewById(R.id.etMinPlayers);
        etMaxPlayers = findViewById(R.id.etMaxPlayers);
        rgGenres = findViewById(R.id.rgGenres);
        btnAdd = findViewById(R.id.btnAdd);
        lvGameList = findViewById(R.id.lvGameList);

        gameDao = new GameDao(MainActivity.this);

        showGamesOnListView(gameDao.getAllGames());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Game game;

                try {
                    String genre = ((RadioButton)findViewById(rgGenres.getCheckedRadioButtonId())).getText().toString();

                    game = new Game(-1, etName.getText().toString(),
                            Integer.parseInt(etYear.getText().toString()),
                            Integer.parseInt(etMinPlayers.getText().toString()),
                            Integer.parseInt(etMaxPlayers.getText().toString()), genre);
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Missing input", Toast.LENGTH_SHORT).show();
                    game = new Game(-1, "error", 0, 0, 0, "error");
                }

                GameDao gameDao = new GameDao(MainActivity.this);

                gameDao.addGame(game);

                showGamesOnListView(gameDao.getAllGames());

                Toast.makeText(MainActivity.this, "Added new game", Toast.LENGTH_SHORT).show();

            }
        });

        lvGameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Game game = (Game)adapterView.getItemAtPosition(position);
                gameDao.deleteGame(game);
                showGamesOnListView(gameDao.getAllGames());
                Toast.makeText(MainActivity.this, "Deleted game", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showGamesOnListView(List<Game> allGames) {
        gameArrayAdapter = new ArrayAdapter<Game>(MainActivity.this, android.R.layout.simple_list_item_1, allGames);
        lvGameList.setAdapter(gameArrayAdapter);
    }
}