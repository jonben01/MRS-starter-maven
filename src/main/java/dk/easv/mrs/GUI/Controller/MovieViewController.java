package dk.easv.mrs.GUI.Controller;

//PROJECT IMPORTS
import dk.easv.mrs.BE.Movie;
import dk.easv.mrs.GUI.Model.MovieModel;
//JAVA IMPORTS
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MovieViewController implements Initializable {


    public TextField txtMovieSearch;
    public ListView<Movie> lstMovies;
    private MovieModel movieModel;
    @FXML
    public TextField txtTitle, txtYear;
    @FXML
    private TableView<Movie> tblMovies;
    @FXML
    private TableColumn<Movie, String> colTitle;
    @FXML
    private TableColumn<Movie, Integer> colYear;
    @FXML
    private Button btnDelete;


    public MovieViewController()  {

        try {
            movieModel = new MovieModel();
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        tblMovies.setItems(movieModel.getObservableMovies());

        lstMovies.setItems(movieModel.getObservableMovies());

        txtMovieSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                movieModel.searchMovie(newValue);
            } catch (Exception e) {
                displayError(e);
                e.printStackTrace();
            }
        });

    }

    private void displayError(Throwable t)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }
    @FXML
    public void btnHandleAdd(ActionEvent actionEvent) throws Exception {
        //call model
        //get user data from UI
        String title = txtTitle.getText();
        int year = Integer.parseInt(txtYear.getText());
        //new movie object
        Movie newMovie = new Movie(-1, year, title);
        //call model to create movie in DAL
        movieModel.createMovie(newMovie);

    }

    @FXML
    public void btnHandleDelete(ActionEvent actionEvent) throws Exception {
        Movie movieToBeDeleted = tblMovies.getSelectionModel().getSelectedItem();
        //System.out.println(tblMovies.getSelectionModel().getSelectedItem().toString());
        if (movieToBeDeleted != null) {
            movieModel.deleteMovie(movieToBeDeleted);
            lstMovies.setItems(movieModel.getObservableMovies());


        }
    }
}
