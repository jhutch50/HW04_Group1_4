package com.example.hw04_group14;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    /*
     * The Movie list should be stored in the Main Activity. You should use an ArrayList to
     * store the list of Movie Objects. The list should be passed using intent extras to other
     * activities when needed. Do not declare the list as static.
     * */

    static final String TAG_IMAGE = "main";
    final ArrayList<Movie> movies = new ArrayList<>();
    Button btn_add_movie;
    Button btn_edit;
    Button btn_delete_movie;
    Button btn_show_list_by_year;
    Button btn_show_list_by_rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My Favorite Movies");
        btn_add_movie = findViewById(R.id.button_add_movie);
        btn_edit = findViewById(R.id.button_edit);
        btn_delete_movie = findViewById(R.id.button_delete_movie);
        btn_show_list_by_year = findViewById(R.id.button_show_list_by_year);
        btn_show_list_by_rating = findViewById(R.id.button_show_list_by_rating);
    /* //for testing delete
        movies.add(new Movie());
        movies.add(new Movie());
        movies.get(0).setTitle("Star Wars");
        movies.get(1).setTitle("Inception");
    */
        //using the buttons defined below, pass the movie list in the extras

        btn_add_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddMovieActivity.class);
                Bundle sentData = new Bundle();
                sentData.putSerializable("movies", movies);
                intent.putExtra(TAG_IMAGE, sentData);
                startActivity(intent);
                finish();
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditMovieActivity.class);
                Bundle sentData = new Bundle();
                sentData.putSerializable("movies", movies);
                intent.putExtra(TAG_IMAGE, sentData);
                startActivity(intent);
                finish();
            }
        });

        btn_delete_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Josh's note: When trying to do without using threads or Async, the app gave a mutex
                //error. This needs to be done in the same way we did it in the lab for the alert dialog
                //especially since the exact same list can be reused in the edit portion as well.

                //The Delete Movie process is displayed in Figure 4. Implement the following
                //requirements:
                StringBuilder sb = new StringBuilder();
                if(!movies.isEmpty()) {
                    for (Movie movie : movies) {
                        sb.append(";" + movie.getTitle());
                    }
                    new getMoviePick().execute(sb.toString());
                }else{
                    Toast.makeText(MainActivity.this, "No movies in list", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btn_show_list_by_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MovieByYearActivity.class);
                Bundle sentData = new Bundle();
                sentData.putSerializable("movies", movies);
                intent.putExtra(TAG_IMAGE, sentData);
                startActivity(intent);
                finish();
            }
        });

        btn_show_list_by_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MovieByRatingActivity.class);
                Bundle sentData = new Bundle();
                sentData.putSerializable("movies", movies);
                intent.putExtra(TAG_IMAGE, sentData);
                startActivity(intent);
                finish();
            }
        });




    }

    private class getMoviePick extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            if(strings[0].equals("")){
                Toast.makeText(MainActivity.this, "No movies in list", Toast.LENGTH_SHORT).show();

                return null;
            }else {
                return strings[0];
            }
        }

        @Override
        protected void onPostExecute(String s) {
            final String[] temp = s.split(";");
                        //1. When the user selects “Delete Movie” button in the Main Activity, just like the Edit
            //Movie part, an Alert Dialogue should come up with the movie list.
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Pick a Movie");
            builder.setItems(temp, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String title = temp[which];
                    Log.d("heyo",title + " "+which);
                    movies.remove(which-1);

                    Toast.makeText(MainActivity.this, title + " has been deleted", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            //2. When you select one of the movies in the list, it should delete it from the Movie list.



            //3. Toast a message showing that the movie is deleted that includes the movie name.


        }
    }


}
