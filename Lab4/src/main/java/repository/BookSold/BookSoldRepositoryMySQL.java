package repository.BookSold;

import model.Book;
import model.BookSold;
import model.builder.BookBuilder;
import model.builder.BookSoldBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookSoldRepositoryMySQL implements BookSoldRepository{

    private final Connection connection;

    public BookSoldRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void addBookSold(Book book) {
        String sql= "INSERT INTO bookSold VALUES(null, ?, ?);";

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1, book.getId());
            preparedStatement.setInt(2, 1);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateNrOfBooksSold(BookSold bookSold) {

        String sql= "UPDATE bookSold SET nrOfBooksSold=? where idBook=?";

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookSold.getNrOfBooksSold()+1);
            preparedStatement.setLong(2, bookSold.getIdBook());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Optional<BookSold> findBookById(Book book) {

        String sql="SELECT * from bookSold where idBook=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1, book.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                BookSold bookSold = getBookSoldFromResultSet(resultSet);
                return Optional.of(bookSold);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();

    }

    @Override
    public List<BookSold> findAll() {
        String sql= "SELECT * from bookSold";
        List<BookSold> books = new ArrayList<>();

        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery(sql);

            while (resultSet.next()) {
                books.add(getBookSoldFromResultSet(resultSet));
            }

        }catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
        return books;
    }

    private BookSold getBookSoldFromResultSet(ResultSet resultSet) throws SQLException {
        return new BookSoldBuilder()
                .setId(resultSet.getLong("id"))
                .setIdBook(resultSet.getLong("idBook"))
                .setNrOfBookSold(resultSet.getInt("nrOfBooksSold"))
                .build();
    }
}
