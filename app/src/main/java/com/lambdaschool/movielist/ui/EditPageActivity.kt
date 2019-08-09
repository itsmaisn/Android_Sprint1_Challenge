package com.lambdaschool.movielist.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lambdaschool.movielist.R
import com.lambdaschool.movielist.model.Movie
import kotlinx.android.synthetic.main.activity_edit_page.*

class EditPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_page)

        save_button.setOnClickListener {
            var intentAddMovie = Intent()
            intentAddMovie.putExtra("movie",createMovie())
            setResult(Activity.RESULT_OK,intentAddMovie)
            finish()
        }

        var bundle: Bundle? = intent.extras
        if(bundle != null) {
            loadMovie(bundle!!.getSerializable("tvMovie") as Movie)
        }
    }

    fun loadMovie(movie: Movie) {
        movie_title.setText(movie.title)
    }

    fun createMovie():Movie {
        var newMovie = Movie(movie_title.text.toString())
        return newMovie
    }
}
