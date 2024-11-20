package dk.easv.mrs.GUI.Model;
//PROJECT IMPORTS
import dk.easv.mrs.BE.Movie;
import dk.easv.mrs.BLL.MovieManager;
//JAVA IMPORTS
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.List;

public class MovieModel {

    private ObservableList<Movie> moviesToBeViewed;

    private MovieManager movieManager;

    public MovieModel() throws Exception {
        movieManager = new MovieManager();
        moviesToBeViewed = FXCollections.observableArrayList();
        moviesToBeViewed.addAll(movieManager.getAllMovies());
    }



    public ObservableList<Movie> getObservableMovies() {
        return moviesToBeViewed;
    }

    public void searchMovie(String query) throws Exception {
        List<Movie> searchResults = movieManager.searchMovies(query);
        moviesToBeViewed.clear();
        moviesToBeViewed.addAll(searchResults);
    }
    //create movie, called from controller
    public Movie createMovie(Movie newMovie) throws Exception {
        Movie movieCreated = movieManager.createMovie(newMovie);
        moviesToBeViewed.add(movieCreated);
        return movieCreated;
    }

    public void deleteMovie(Movie movieToBeDeleted) throws Exception {
        moviesToBeViewed.remove(movieToBeDeleted);
        movieManager.deleteMovie(movieToBeDeleted);
    }
}
