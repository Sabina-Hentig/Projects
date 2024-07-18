package main.repository.Database;

import main.domain.Client;
import main.domain.validators.Validator;
import main.domain.validators.ValidatorException;
import main.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDatabaseRepository implements Repository<Long, Client>{
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";
    private static final String URL_JDBC = "jdbc:postgresql://localhost:5432/postgres";

    protected Validator<Client> validator;
    public ClientDatabaseRepository(Validator<Client> validator) {
        this.validator = validator;
    }

    @Override
    public Optional<Client> findOne(Long aLong) {
        try {
            Connection connection = DriverManager.getConnection(URL_JDBC, USER, PASSWORD);
            String sqlString = "select * from clients where (idClients=?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setLong(1, aLong);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getLong("idclients"));
                client.setCNP(resultSet.getString("cnp"));
                client.setLastName(resultSet.getString("lastname"));
                client.setFirstName(resultSet.getString("firstname"));
                client.setAge(resultSet.getDouble("age"));

                connection.close();
                return Optional.of(client);
            } else {
                connection.close();
                return Optional.empty();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Iterable<Client> findAll() {
        List<Client> bookList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL_JDBC, USER, PASSWORD)) {
            String sqlString = "select * from clients";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getLong("idClients"));
                client.setCNP(resultSet.getString("cnp"));
                client.setLastName(resultSet.getString("lastName"));
                client.setFirstName(resultSet.getString("firstName"));
                client.setAge(resultSet.getDouble("age"));
                bookList.add(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookList;
    }


    @Override
    public Optional<Client> save(Client entity) throws ValidatorException {
        validator.validate(entity);
        try {
            Connection connection = DriverManager.getConnection(URL_JDBC, USER, PASSWORD);
            String sqlString = "insert into clients (idClients, cnp,lastname, firstname, age) values (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getCNP());
            preparedStatement.setString(3, entity.getLastName());
            preparedStatement.setString(4, entity.getFirstName());
            preparedStatement.setDouble(5, entity.getAge());
            preparedStatement.executeUpdate();
            connection.close();
            return Optional.of(entity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Client> delete(Long idToDelete) {
        try {
            Connection connection = DriverManager.getConnection(URL_JDBC, USER, PASSWORD);
            String sqlString = "delete from clients where idClients = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setLong(1, idToDelete);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> update(Client entity) throws ValidatorException {
        validator.validate(entity);
        String sqlUpdate = "update clients set lastname = ? where idClients = ?";
        try (Connection conn = DriverManager.getConnection(URL_JDBC, USER, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sqlUpdate);
        ) {
                preparedStatement.setString(1, entity.getLastName());
                preparedStatement.setInt(2, entity.getId().intValue());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

}