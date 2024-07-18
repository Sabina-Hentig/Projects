package main.repository.Database;

import main.domain.Transaction;
import main.domain.validators.Validator;
import main.domain.validators.ValidatorException;
import main.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionDatabaseRepository implements Repository<Long, Transaction> {
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";

    protected Validator<Transaction> validator;

    public TransactionDatabaseRepository(Validator<Transaction> validator) {
        this.validator = validator;
    }

    @Override
    public Optional<Transaction> findOne(Long aLong) {
        String sqlString = "select * from transactions where (idTransactions=?)";
        try ( Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
              PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
              ResultSet resultSet = preparedStatement.executeQuery()){

            preparedStatement.setLong(1, aLong);

            if (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getLong("idtransactions"));
                transaction.setIdBook(resultSet.getLong("idbooks"));
                transaction.setIdClient(resultSet.getLong("idclients"));
                transaction.setAmount(resultSet.getInt("amount"));
                if (resultSet.getDate("dateofpurchase") != null) {
                    transaction.setDateOfPurchase(resultSet.getDate("dateofpurchase").toLocalDate());
                }

                return Optional.of(transaction);
            } else {
                return Optional.empty();
            }


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public Iterable<Transaction> findAll() {
        List<Transaction> transactionsList = new ArrayList<>();
        String sqlString = "select * from transactions";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getLong("idtransactions"));
                transaction.setIdBook(resultSet.getLong("idbooks"));
                transaction.setIdClient(resultSet.getLong("idclients"));
                transaction.setAmount(resultSet.getInt("amount"));
                transaction.setDateOfPurchase(resultSet.getDate("dateofpurchase").toLocalDate());
                transactionsList.add(transaction);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transactionsList;
    }


    @Override
    public Optional<Transaction> save(Transaction entity) throws ValidatorException {
        validator.validate(entity);
        String sqlString = "insert into transactions (idTransactions, idBooks, idClients, amount, dateOfPurchase) values (?,?,?,?,?)";
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString)) {

            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setLong(2, entity.getIdBook());
            preparedStatement.setLong(3, entity.getIdClient());
            preparedStatement.setInt(4, entity.getAmount());
            preparedStatement.setDate(5, Date.valueOf(entity.getDateOfPurchase()));
            preparedStatement.executeUpdate();

            return Optional.of(entity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Transaction> delete(Long idToDelete) {
        String sqlString = "delete from transactions where idTransactions = ?";
        try( Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlString) ){

            preparedStatement.setLong(1, idToDelete);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Transaction> update(Transaction entity) throws ValidatorException {
        validator.validate(entity);
        String sqlUpdate = "update transactions set amount = ? where idTransactions = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sqlUpdate);
        ) {
            preparedStatement.setInt(1, entity.getAmount());
            preparedStatement.setInt(2, entity.getId().intValue());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
