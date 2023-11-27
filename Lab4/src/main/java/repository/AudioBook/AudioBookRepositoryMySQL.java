package repository.AudioBook;

import model.AudioBook;
import model.EBook;
import model.builder.AudioBookBuilder;
import model.builder.EBookBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AudioBookRepositoryMySQL implements AudioBookRepository{

    private final Connection connection;

    public AudioBookRepositoryMySQL(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<AudioBook> findAll() {
        String sql = "SELECT * FROM audiobook;";

        List<AudioBook> audioBooks = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                audioBooks.add(getAudioBookFromResultSet(resultSet));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }


        return audioBooks;
    }

    @Override
    public Optional<AudioBook> findById(Long id) {
        String sql = "SELECT * FROM audiobook where id= ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                AudioBook audioBook = getAudioBookFromResultSet(resultSet);
                return Optional.of(audioBook);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();

    }


    @Override
    public boolean save(AudioBook audioBook) {
        String sql = "INSERT INTO audiobook VALUES(null, ?, ?, ?, ?);";


        try{


            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, audioBook.getAuthor());
            preparedStatement.setString(2, audioBook.getTitle());
            preparedStatement.setInt(3,audioBook.getRunTime());
            preparedStatement.setDate(4, java.sql.Date.valueOf(audioBook.getPublishedDate()));

            int rowsInserted = preparedStatement.executeUpdate();

            return (rowsInserted != 1) ? false : true;

        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public void removeAll() {
        String sql = "DELETE FROM audiobook";

        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            int succes=preparedStatement.executeUpdate();
            System.out.println("SUCCES");

        }catch (SQLException e){
            e.printStackTrace();
        }

    }


    private AudioBook getAudioBookFromResultSet(ResultSet resultSet) throws SQLException {
        return new AudioBookBuilder()
                .setId(resultSet.getLong("id"))
                .setAuthor(resultSet.getString("author"))
                .setTitle(resultSet.getString("title"))
                .setRunTime(resultSet.getInt("runTime"))
                .setPublishedDate(new java.sql.Date((resultSet.getDate("publishedDate")).getTime()).toLocalDate())
                .build();
    }
}
