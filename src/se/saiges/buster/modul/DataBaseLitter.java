package se.saiges.buster.modul;

import se.saiges.buster.animals.litters.Litter;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataBaseLitter {

    public static final String INSERT_LITTER =
            "INSERT INTO " + DataBaseSetup.TABLE_LITTER + "(" +
            DataBaseSetup.COLUMN_LITTER_MOTHER_ID + ", " +
            DataBaseSetup.COLUMN_LITTER_FATHER_ID + ", " +
            DataBaseSetup.COLUMN_LITTER_BORN + ", " +
            DataBaseSetup.COLUMN_LITTER_DEFECT + ", " +
            DataBaseSetup.COLUMN_LITTER_ALIVE + ", " +
            DataBaseSetup.COLUMN_LITTER_FEMALE + ", " +
            DataBaseSetup.COLUMN_LITTER_MALE + ", " +
            DataBaseSetup.COLUMN_LITTER_DATE_OF_BIRTH + ")" +
            " VALUES(?, ?, ?, ?, ?, ?, ?, ? )";

    public static final String UPDATE_LITTER =
            "UPDATE " + DataBaseSetup.TABLE_LITTER + " SET " +
            DataBaseSetup.COLUMN_LITTER_MOTHER_ID + " = ? , " +
            DataBaseSetup.COLUMN_LITTER_FATHER_ID + " = ? , " +
            DataBaseSetup.COLUMN_LITTER_BORN + " = ? , " +
            DataBaseSetup.COLUMN_LITTER_DEFECT + " = ? , " +
            DataBaseSetup.COLUMN_LITTER_ALIVE + " = ?, " +
            DataBaseSetup.COLUMN_LITTER_DATE_OF_BIRTH + " = ? " +
            "WHERE " + DataBaseSetup.COLUMN_LITTER_ID + " = ?";

    public static final String DELETE_LITTER_BY_ID = "DELETE FROM " + DataBaseSetup.TABLE_LITTER + " WHERE " + DataBaseSetup.TABLE_LITTER + "." + DataBaseSetup.COLUMN_LITTER_ID + " = ?";
    public static final String QUERY_LITTER_BY_ID = "SELECT * FROM " + DataBaseSetup.TABLE_LITTER + " WHERE " + DataBaseSetup.TABLE_LITTER + "." + DataBaseSetup.COLUMN_LITTER_ID + " = ?";
    public static final String QUERY_LITTERS = "SELECT * FROM " + DataBaseSetup.TABLE_LITTER;
    public static final String QUERY_LAST_ENTERED_LITER = "SELECT * FROM " + DataBaseSetup.TABLE_LITTER  + " WHERE " + DataBaseSetup.COLUMN_LITTER_ID + " = (SELECT MAX(" + DataBaseSetup.COLUMN_LITTER_ID + ") FROM " + DataBaseSetup.TABLE_LITTER + ")";

    private static DataBaseLitter instance;
    private Connection conn = DataBaseSetup.getInstance().getConnection();

    public static DataBaseLitter getInstance() {
        if(instance == null){
            instance = new DataBaseLitter();
        }
        return instance;
    }

    public void createLitter(Litter litter){
        try(PreparedStatement createLitterStatement = conn.prepareStatement(INSERT_LITTER)){
            createLitterStatement.setInt(1,litter.getMotherId());
            createLitterStatement.setInt(2, litter.getFatherId());
            createLitterStatement.setInt(3, litter.getBorn());
            createLitterStatement.setInt(4, litter.getDefect());
            createLitterStatement.setInt(5, litter.getAlive());
            createLitterStatement.setInt(6, litter.getFemales());
            createLitterStatement.setInt(7, litter.getMales());
            createLitterStatement.setString(8, litter.getDateOfBirth().toString());
            createLitterStatement.execute();


        } catch (SQLException e){
            System.out.println("Couldn't create litter.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateLitter(Litter litter){
        try(PreparedStatement updateLitterStatement = conn.prepareStatement(UPDATE_LITTER)){
            updateLitterStatement.setInt(1,litter.getMotherId());
            updateLitterStatement.setInt(2, litter.getFatherId());
            updateLitterStatement.setInt(3, litter.getBorn());
            updateLitterStatement.setInt(4, litter.getDefect());
            updateLitterStatement.setInt(5, litter.getAlive());
            updateLitterStatement.setString(6, litter.getDateOfBirth().toString());
            updateLitterStatement.setInt(7, litter.getLitterId());
            updateLitterStatement.execute();

        } catch (SQLException e){
            System.out.println("Couldn't update litter.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeLitterById(int id){
        try(PreparedStatement removeLitterStatement = conn.prepareStatement(DELETE_LITTER_BY_ID)){
            removeLitterStatement.setInt(1, id);
            removeLitterStatement.execute();

        } catch (SQLException e){
            System.out.println("Couldn't remove litter.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Litter getLitterListById(int id){
        try(PreparedStatement litterByIdStatement = conn.prepareStatement(QUERY_LITTER_BY_ID)){
            litterByIdStatement.setInt(1, id);
            ResultSet result = litterByIdStatement.executeQuery();

            return getLitterResult(result).get(0);

        } catch (SQLException e){
            System.out.println("Couldn't get litter by id.");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return  null;
        }
    }

    public List<Litter> getLitters(){
        try(Statement getLitterStatement = conn.createStatement();
        ResultSet resultSet = getLitterStatement.executeQuery(QUERY_LITTERS)){

            return getLitterResult(resultSet);

        } catch (SQLException e){
            System.out.println("Couldn't get litters.");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return  null;
        }
    }

    public Litter getLastEnteredLitter(){
        try(Statement getLastEnteredLitterStatement = conn.createStatement();
        ResultSet resultSet = getLastEnteredLitterStatement.executeQuery(QUERY_LAST_ENTERED_LITER)){

            return getLitterResult(resultSet).get(0);

        } catch (SQLException e){
            System.out.println("Couldn't get last entered litter.");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return  null;
        }
    }

    private List<Litter> getLitterResult(ResultSet result){
        try{
            List<Litter> litterList = new ArrayList<>();

            while(result.next()){
                Litter litter = new Litter();

                litter.setLitterId(result.getInt(1));
                litter.setMotherId(result.getInt(2));
                litter.setFatherId(result.getInt(3));
                litter.setBorn(result.getInt(4));
                litter.setDefect(result.getInt(5));
                litter.setAlive(result.getInt(6));
                // add female and male count
                litter.setDateOfBirth(LocalDate.parse(result.getString(9)));

                litterList.add(litter);
            }

            return litterList;

        } catch (SQLException e){
            System.out.println("Couldn't get litter result.");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return  null;
        }
    }
}
