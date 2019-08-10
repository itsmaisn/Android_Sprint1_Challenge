package com.lambdaschool.movielist.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.lambdaschool.movielist.R
import com.lambdaschool.movielist.model.Movie
import kotlinx.android.synthetic.main.activity_list_page.*

class ListPageActivity : AppCompatActivity() {

    var movieList = mutableListOf<Movie>()

    companion object {
        const val REQUEST_CODE_EDIT_MOVIE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_page)

        add_button.setOnClickListener {
            var addMovieIntent = Intent(this, EditPageActivity::class.java)
            startActivityForResult(addMovieIntent, REQUEST_CODE_EDIT_MOVIE)
        }
    }

    fun refreshMovieList() {
        ll_movie_list.removeAllViews()
        for ((counter, movie) in movieList.withIndex()) {
            ll_movie_list.addView(createTextView(movie, counter))
        }
    }

    override fun onPostResume() {
        refreshMovieList()
        super.onPostResume()
    }

    fun createTextView(movie: Movie, index: Int): TextView {
        var newMovieView = TextView(this)
        newMovieView.textSize = 24f
        newMovieView.id = index
        newMovieView.text = movie.title
        if (movieList[index].watched) {
            newMovieView.paintFlags = newMovieView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }

        newMovieView.setOnClickListener {
            var tvIntent = Intent(this, EditPageActivity::class.java)
            tvIntent.putExtra("tvMovie", movieList[newMovieView.id])
            movieList.removeAt(newMovieView.id)
            startActivityForResult(tvIntent, REQUEST_CODE_EDIT_MOVIE)
        }
        return newMovieView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_EDIT_MOVIE && resultCode == Activity.RESULT_OK) {
            val newResultMovie = data!!.getSerializableExtra("movie") as Movie
            movieList.add(newResultMovie)
        }
    }
}