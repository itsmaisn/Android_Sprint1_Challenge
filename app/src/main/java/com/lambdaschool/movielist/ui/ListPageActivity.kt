package com.lambdaschool.movielist.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.lambdaschool.movielist.R
import com.lambdaschool.movielist.model.Movie
import kotlinx.android.synthetic.main.activity_edit_page.*
import kotlinx.android.synthetic.main.activity_list_page.*

class ListPageActivity : AppCompatActivity() {

    var movieList = mutableListOf<Movie>()
    var counter = 0

    companion object {
        const val REQUEST_CODE_EDIT_MOVIE = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_page)

        add_button.setOnClickListener {
            var saveMovieIntent = Intent(this, EditPageActivity::class.java)
            startActivityForResult(saveMovieIntent, REQUEST_CODE_EDIT_MOVIE)
        }
    }

    fun createTextView(movie: Movie, index: Int):TextView {
        var newMovieView = TextView(this)
        newMovieView.textSize = 24f
        newMovieView.id = index
        newMovieView.text = movie.title

        newMovieView.setOnClickListener {
            var tvIntent = Intent(this, EditPageActivity::class.java)
            tvIntent.putExtra("movieKey", movieList[newMovieView.id])
            movieList.removeAt(newMovieView.id)
            startActivityForResult(tvIntent, REQUEST_CODE_EDIT_MOVIE)
        }
        return newMovieView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_EDIT_MOVIE && resultCode == Activity.RESULT_OK) {
            val newResultMovie = data!!.getSerializableExtra("movie") as Movie
            movieList.add(newResultMovie)
            ll_movie_list.addView(createTextView(newResultMovie, counter))
            counter++
        }
    }
}

//movie list lines up (activity lifecycle)