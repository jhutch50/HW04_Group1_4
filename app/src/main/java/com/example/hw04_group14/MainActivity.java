package com.example.hw04_group14;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                /*
                The Delete Movie process is displayed in Figure 4. Implement the following
                requirements:


                //1. When the user selects “Delete Movie” button in the Main Activity, just like the Edit
                //Movie part, an Alert Dialogue should come up with the movie list.
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pick a Movie");
                builder.setItems((CharSequence[]) movies.toArray(new Movie[0]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String movie_title = movies.toArray(new Movie[0])[which].getTitle();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                //2. When you select one of the movies in the list, it should delete it from the Movie list.
                String title = movies.get(movies.indexOf(view)).getTitle();
                movies.remove(movies.indexOf(view));

                //3. Toast a message showing that the movie is deleted that includes the movie name.
                Toast.makeText(MainActivity.this, title + " has been deleted", Toast.LENGTH_SHORT).show();
*/
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
/*
    private class getMovieList extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            final String[] temp = s.split(";");
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Choose item");

            builder.setItems(temp, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String url = "http://dev.theappsdr.com/apis/photos/index.php" + "?" +"keyword="+temp[which];
                    tvKeyword.setText(temp[which]);
                    new getImageURL().execute(url);
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
    
 */
}
