package repository.RaportEmployee;

import model.RaportEmployee;
import model.builder.RaportEmployeeBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RaportEmployeeRepositoryMySQL implements RaportEmployeeRepository{

    private final Connection connection;

    public RaportEmployeeRepositoryMySQL(Connection connection)
    {
        this.connection=connection;
    }

    @Override
    public List<RaportEmployee> findall() {

        String sql="SELECT * FROM raportemployee;";

        List<RaportEmployee> raportEmployees= new ArrayList<>();
        try {
            Statement statement= connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sql);

            while(resultSet.next())
            {
                raportEmployees.add(getRaportEmployeeFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return raportEmployees;
    }

    public RaportEmployee getRaportEmployeeFromResultSet(ResultSet resultSet) throws SQLException
    {
        return new RaportEmployeeBuilder()
                .setId(resultSet.getLong("id"))
                .setText(resultSet.getString("text"))
                .build();
    }

    @Override
    public void save(String text) {

        String sql= "INSERT INTO raportemployee VALUES(null, ?)";

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, text);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
