package dk.easv.mrs.DAL;

import dk.easv.mrs.BE.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO_TempDBFile {

    private MyDBConnector dbConnector;

    public MovieDAO_TempDBFile() {
        dbConnector = new MyDBConnector();
    }


        public List<Movie> getAllMovies() throws SQLException {
            ArrayList<Movie> allMovies = new ArrayList<>();
            try (Connection connection = dbConnector.getConnection()) {
                String sql = "SELECT * FROM movie;";
                Statement statement = connection.createStatement();

                if (statement.execute(sql)) {
                    ResultSet resultSet = statement.getResultSet();
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String title = resultSet.getString("title");
                        int year = resultSet.getInt("year");

                        Movie movie = new Movie(id, year, title);
                        allMovies.add(movie);
                    }
                }


            }
            return allMovies;
        }
    public static void main(String[] args) throws SQLException {
        MovieDAO_TempDBFile movieDAO_Temp_dbFile = new MovieDAO_TempDBFile();
        List<Movie> allMovies = movieDAO_Temp_dbFile.getAllMovies();

        System.out.println(allMovies);
    }

}
