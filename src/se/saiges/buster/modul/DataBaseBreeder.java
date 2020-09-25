package se.saiges.buster.modul;

import se.saiges.buster.breeder.Breeder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseBreeder {

    public static final String INSERT_BREEDER =
            "INSERT INTO " + DataBaseSetup.TABLE_BREEDER + "(" +
            DataBaseSetup.COLUMN_BREEDER + ", " +
            DataBaseSetup.COLUMN_BREEDER_NAME + ", " +
            DataBaseSetup.COLUMN_BREEDER_EMAIL + ", " +
            DataBaseSetup.COLUMN_BREEDER_ADDRESS + ", " +
            DataBaseSetup.COLUMN_BREEDER_ZIP_CODE + ", " +
            DataBaseSetup.COLUMN_BREEDER_PHONE_NUMBER + ")" +
            " VALUES(?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_BREEDER =
            "UPDATE " + DataBaseSetup.TABLE_BREEDER + " SET " +
            DataBaseSetup.COLUMN_BREEDER  + " = ?, " +
            DataBaseSetup.COLUMN_BREEDER_NAME + " = ?, " +
            DataBaseSetup.COLUMN_BREEDER_EMAIL + " = ?, " +
            DataBaseSetup.COLUMN_BREEDER_ADDRESS + " = ?, " +
            DataBaseSetup.COLUMN_BREEDER_ZIP_CODE + " = ?, " +
            DataBaseSetup.COLUMN_BREEDER_PHONE_NUMBER + " = ? " +
            "WHERE " + DataBaseSetup.COLUMN_BREEDER_ID + " = ?";


    public static final String QUERY_BREEDER_LIST = "SELECT * FROM " + DataBaseSetup.TABLE_BREEDER;
    public static final String QUERY_BREEDER_BY_ID = "SELECT * FROM "  + DataBaseSetup.TABLE_BREEDER  + " WHERE _id = ?";
    public static final String DELETE_BREEDER_BY_ID = "DELETE FROM "  + DataBaseSetup.TABLE_BREEDER  + " WHERE _id = ?";

    private static DataBaseBreeder instance;
    private Connection conn = DataBaseSetup.getInstance().getConnection();

    public static DataBaseBreeder getInstance(){
        if(instance == null){
            instance = new DataBaseBreeder();
        }
        return  instance;
    }

    public void createBreeder(Breeder b){
        try(PreparedStatement createBreederStatement = conn.prepareStatement(INSERT_BREEDER)) {

            createBreederStatement.setString(1, b.getBreederName());
            createBreederStatement.setString(2, b.getOwnerName());
            createBreederStatement.setString(3, b.getOwnerEmail());
            createBreederStatement.setString(4, b.getOwnerAddress());
            createBreederStatement.setString(5, b.getOwnerZipCode());
            createBreederStatement.setString(6, b.getOwnerPhoneNumber());

            int affectedRows = createBreederStatement.executeUpdate();

            if(affectedRows != 1){
                throw new SQLException("To many breeders was created.");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Breeder> getBreederList(){
        try(Statement getBreedersStatement = conn.createStatement();
                ResultSet result = getBreedersStatement.executeQuery(QUERY_BREEDER_LIST)){

            return getBreederResult(result);

        } catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return  null;
        }
    }

    public Breeder getBreeder(int id){
        try(PreparedStatement getBreederStatement = conn.prepareStatement(QUERY_BREEDER_BY_ID)){

            getBreederStatement.setInt(1, id);
            ResultSet result = getBreederStatement.executeQuery();

            return getBreederResult(result).get(0); //Can return null, keep an eye on this!

        } catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return  null;
        }
    }

    public void removeBreeder(Breeder breeder){
        try(PreparedStatement deleteBreederStatement = conn.prepareStatement(DELETE_BREEDER_BY_ID)){

            deleteBreederStatement.setInt(1, breeder.getId());
            deleteBreederStatement.execute();

        } catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public  void updateBreeder(Breeder b){
        try(PreparedStatement updateBreederStatement = conn.prepareStatement(UPDATE_BREEDER)) {

            updateBreederStatement.setString(1, b.getBreederName());
            updateBreederStatement.setString(2, b.getOwnerName());
            updateBreederStatement.setString(3, b.getOwnerEmail());
            updateBreederStatement.setString(4, b.getOwnerAddress());
            updateBreederStatement.setString(5, b.getOwnerZipCode());
            updateBreederStatement.setString(6, b.getOwnerPhoneNumber());
            updateBreederStatement.setInt(7,b.getId());
            updateBreederStatement.execute();

        } catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    // Private methods
    private List<Breeder> getBreederResult(ResultSet result){
        try{
            List<Breeder> breederList = new ArrayList<>();

            while(result.next()){
                Breeder breeder = new Breeder();
                breeder.setId(result.getInt(1));
                breeder.setBreederName(result.getString(2));
                breeder.setOwnerName(result.getString(3));
                breeder.setOwnerEmail(result.getString(4));
                breeder.setOwnerAddress(result.getString(5));
                breeder.setOwnerZipCode(result.getString(6));
                breeder.setOwnerPhoneNumber(result.getString(7));

                breederList.add(breeder);
            }
            return breederList;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
