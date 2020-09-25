package se.saiges.buster.modul;

import se.saiges.buster.animals.AnimalType;
import se.saiges.buster.animals.EyeColor;
import se.saiges.buster.animals.Sex;
import se.saiges.buster.animals.bunny.*;
import se.saiges.buster.breeder.Breeder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataBaseBunny {

    public static final String INSERT_BUNNY =
            "INSERT INTO " + DataBaseSetup.TABLE_BUNNY + "(" +
            DataBaseSetup.COLUMN_BUNNY_NAME + ", " +
            DataBaseSetup.COLUMN_BUNNY_BREEDER_ID + ", " +
            DataBaseSetup.COLUMN_BUNNY_DATE_OF_BIRTH + ", " +
            DataBaseSetup.COLUMN_BUNNY_SEX + ", " +
            DataBaseSetup.COLUMN_BUNNY_ANIMAL_TYPE + ", " +
            DataBaseSetup.COLUMN_BUNNY_OWNED + ", " +
            DataBaseSetup.COLUMN_BUNNY_MOTHER_ID + ", " +
            DataBaseSetup.COLUMN_BUNNY_FATHER_ID + ", " +
            DataBaseSetup.COLUMN_BUNNY_BREED + ", " +
            DataBaseSetup.COLUMN_BUNNY_COLOR + ", " +
            DataBaseSetup.COLUMN_BUNNY_FUR_TYPE + ", " +
            DataBaseSetup.COLUMN_BUNNY_PATTERN + ", " +
            DataBaseSetup.COLUMN_BUNNY_EYE_COLOR + ", " +
            DataBaseSetup.COLUMN_BUNNY_LITTER_ID + ")" +

            " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_BUNNY =
            "UPDATE " + DataBaseSetup.TABLE_BUNNY + " SET " +
            DataBaseSetup.COLUMN_BUNNY_NAME + " = ? , " +
            DataBaseSetup.COLUMN_BUNNY_BREEDER_ID + " = ? , " +
            DataBaseSetup.COLUMN_BUNNY_DATE_OF_BIRTH + " = ? , " +
            DataBaseSetup.COLUMN_BUNNY_SEX + " = ? , " +
            DataBaseSetup.COLUMN_BUNNY_ANIMAL_TYPE + " = ? , " +
            DataBaseSetup.COLUMN_BUNNY_OWNED + " = ? , " +
            DataBaseSetup.COLUMN_BUNNY_MOTHER_ID + " = ? , " +
            DataBaseSetup.COLUMN_BUNNY_FATHER_ID + " = ? , " +
            DataBaseSetup.COLUMN_BUNNY_BREED + " = ? , " +
            DataBaseSetup.COLUMN_BUNNY_COLOR + " = ? , " +
            DataBaseSetup.COLUMN_BUNNY_FUR_TYPE + " = ? , " +
            DataBaseSetup.COLUMN_BUNNY_PATTERN + " = ? , " +
            DataBaseSetup.COLUMN_BUNNY_EYE_COLOR + " = ? " +
            "WHERE "  + DataBaseSetup.COLUMN_BUNNY_ID + " = ?";

    public static final String QUERY_BUNNY_PARENTS_BY_SEX =
            "SELECT * FROM "+ DataBaseSetup.TABLE_BUNNY +  " WHERE " +
            DataBaseSetup.TABLE_BUNNY + "."  + DataBaseSetup.COLUMN_BUNNY_SEX + " = ? AND " +
            DataBaseSetup.TABLE_BUNNY + "." + DataBaseSetup.COLUMN_BUNNY_ID + " != ?";

    public static final String DELETE_BUNNY_BY_ID = "DELETE FROM " + DataBaseSetup.TABLE_BUNNY + " WHERE " + DataBaseSetup.TABLE_BUNNY + "."   + DataBaseSetup.COLUMN_BUNNY_ID + " = ?";
    public static final String QUERY_BUNNY_BY_ID = "SELECT * FROM " + DataBaseSetup.TABLE_BUNNY + " WHERE " + DataBaseSetup.TABLE_BUNNY + "." + DataBaseSetup.COLUMN_BUNNY_ID + " = ?";
    public static final String QUERY_BUNNY_BY_BREEDER_ID = "SELECT * FROM " + DataBaseSetup.TABLE_BUNNY + " WHERE " + DataBaseSetup.TABLE_BUNNY + "." + DataBaseSetup.COLUMN_BUNNY_BREEDER_ID + " = ?";
    public static final String UPDATE_BUNNY_IS_OWNED = "UPDATE " + DataBaseSetup.TABLE_BUNNY + " SET " + DataBaseSetup.COLUMN_BUNNY_OWNED + " = ? WHERE " + DataBaseSetup.COLUMN_BUNNY_ID + " = ?";
//    public static final String ADD_LITTER_ID_TO_BUNNY = "UPDATE " + DataBaseSetup.TABLE_BUNNY + " SET " + DataBaseSetup.COLUMN_BUNNY_LITTER_ID + " = ? WHERE " + DataBaseSetup.COLUMN_BUNNY_ID + " = ?";

    private static DataBaseBunny instance;
    Connection conn = DataBaseSetup.getInstance().getConnection();

    public static DataBaseBunny getInstance(){
        if(instance == null){
            instance = new DataBaseBunny();
        }
        return instance;
    }

    public void createBunny(Bunny bunny, Breeder breeder){
        try(PreparedStatement createBunnyStatement = conn.prepareStatement(INSERT_BUNNY)){

            createBunnyStatement.setString(1, bunny.getName());
            createBunnyStatement.setInt(2, breeder.getId());
            createBunnyStatement.setString(3, bunny.getDateOfBirth().toString());
            createBunnyStatement.setString(4, bunny.getSex().toString());
            createBunnyStatement.setString(5, bunny.getAnimalType().toString());
            createBunnyStatement.setInt(6, bunny.isOwned());
            createBunnyStatement.setInt(7, bunny.getMotherId());
            createBunnyStatement.setInt(8, bunny.getFatherId());
            createBunnyStatement.setString(9, bunny.getBunnyBreed().toString());
            createBunnyStatement.setString(10, bunny.getBunnyColor().toString());
            createBunnyStatement.setString(11, bunny.getBunnyFurType().toString());
            createBunnyStatement.setString(12, bunny.getBunnyPattern().toString());
            createBunnyStatement.setString(13, bunny.getEyeColor().toString());
            createBunnyStatement.setInt(14, bunny.getLitterId());

            int affectedRows = createBunnyStatement.executeUpdate();
            if(affectedRows != 1){
                throw new SQLException("Created to many bunny's.");
            }

        } catch ( SQLException e){
            System.out.println("Couldn't add new bunny to database.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Bunny> getBunnyListByBreederId(int id){
        try(PreparedStatement getBunnyListStatement = conn.prepareStatement(QUERY_BUNNY_BY_BREEDER_ID)){

            getBunnyListStatement.setInt(1, id);
            ResultSet result = getBunnyListStatement.executeQuery();

            return bunnyFactory(result);

        } catch (SQLException e){
            System.out.println("Couldn't get bunny's from database");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public Bunny getBunnyById(int id){
        try(PreparedStatement getBunnyStatement = conn.prepareStatement(QUERY_BUNNY_BY_ID)){

            getBunnyStatement.setInt(1, id);
            ResultSet result = getBunnyStatement.executeQuery();

            return bunnyFactory(result).get(0); // may cause NullPointerException, need to check in to that!

        } catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void removeBunnyById(int id){
        try(PreparedStatement removeBunnyStatement = conn.prepareStatement(DELETE_BUNNY_BY_ID)){

            removeBunnyStatement.setInt(1, id);
            removeBunnyStatement.execute();

        } catch (SQLException e){
            System.out.println("Couldn't remove bunny from database.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateBunny(Bunny bunny, Breeder breeder){
        try(PreparedStatement updateBunnyStatement = conn.prepareStatement(UPDATE_BUNNY)){

            updateBunnyStatement.setString(1, bunny.getName());
            updateBunnyStatement.setInt(2, breeder.getId());
            updateBunnyStatement.setString(3, bunny.getDateOfBirth().toString());
            updateBunnyStatement.setString(4, bunny.getSex().toString());
            updateBunnyStatement.setString(5, bunny.getAnimalType().toString());
            updateBunnyStatement.setInt(6, bunny.isOwned());
            updateBunnyStatement.setInt(7, bunny.getMotherId());
            updateBunnyStatement.setInt(8, bunny.getFatherId());
            updateBunnyStatement.setString(9, bunny.getBunnyBreed().toString());
            updateBunnyStatement.setString(10, bunny.getBunnyColor().toString());
            updateBunnyStatement.setString(11, bunny.getBunnyFurType().toString());
            updateBunnyStatement.setString(12, bunny.getBunnyPattern().toString());
            updateBunnyStatement.setString(13, bunny.getEyeColor().toString());
            updateBunnyStatement.setInt(14, bunny.getId());
            updateBunnyStatement.execute();

        } catch (SQLException e){
            System.out.println("Couldn't update bunny in database.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

//    public void updateBunnyLitterId(int bunnyId, int litterId){
//        try(PreparedStatement updateLitterIdStatement = conn.prepareStatement(ADD_LITTER_ID_TO_BUNNY)){
//            updateLitterIdStatement.setInt(1, litterId);
//            updateLitterIdStatement.setInt(2, bunnyId);
//            updateLitterIdStatement.execute();
//
//        } catch (SQLException e){
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }

    public void updateBunnyIsOwned(int isOwned, int id){
        try(PreparedStatement isOwnedStatement = conn.prepareStatement(UPDATE_BUNNY_IS_OWNED)){

            isOwnedStatement.setInt(1, isOwned);
            isOwnedStatement.setInt(2, id);
            isOwnedStatement.execute();

        } catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Bunny> getParentsList(int id, Sex sex){
        try(PreparedStatement getParentsListStatement = conn.prepareStatement(QUERY_BUNNY_PARENTS_BY_SEX)){

            getParentsListStatement.setString(1, sex.toString());
            getParentsListStatement.setInt(2, id);
            ResultSet result = getParentsListStatement.executeQuery();

            return bunnyFactory(result);

        } catch (SQLException e){
            System.out.println("Failed to get parents list of value: " + sex.toString());
            System.out.println(e.getMessage());
            e.printStackTrace();
            return  null;
        }
    }

    // Private methods
    private ArrayList<Bunny> bunnyFactory(ResultSet result){
        try{
            ArrayList<Bunny> bunnyList=  new ArrayList<>();

            while(result.next()){
                Bunny bunny = new Bunny();
                bunny.setId(result.getInt(1));
                bunny.setName(result.getString(2));
                bunny.setBreederId(result.getInt(3));
                bunny.setDateOfBirth(LocalDate.parse(result.getString(4)));
                bunny.setSex(Sex.valueOf(result.getString(5)));
                bunny.setAnimalType(AnimalType.valueOf(result.getString(6)));
                bunny.setOwned(result.getInt(7));
                bunny.setMotherId(result.getInt(8));
                bunny.setFatherId(result.getInt(9));
                bunny.setBunnyBreed(BunnyBreed.valueOf(result.getString(10).toUpperCase()));
                bunny.setBunnyColor(BunnyColor.valueOf(result.getString(11).toUpperCase()));
                bunny.setBunnyFurType(BunnyFurType.valueOf(result.getString(12).toUpperCase()));
                bunny.setBunnyPattern(BunnyPattern.valueOf(result.getString(13).toUpperCase()));
                bunny.setEyeColor(EyeColor.valueOf(result.getString(14).toUpperCase()));
                bunny.setLitterId(result.getInt(15));

                bunnyList.add(bunny);
            }
            return bunnyList;

        } catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return  null;
        }
    }
}
