package se.saiges.buster.modul;

import java.sql.*;

public class DataBaseSetup {

    public static final String DATABASE_NAME = "buster.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:Data\\" + DATABASE_NAME;

    public static final String TABLE_BUNNY = "Bunny";
    public static final String COLUMN_BUNNY_ID = "_id";
    public static final String COLUMN_BUNNY_NAME = "name";
    public static final String COLUMN_BUNNY_BREEDER_ID = "breeder";
    public static final String COLUMN_BUNNY_DATE_OF_BIRTH = "date_of_birth";
    public static final String COLUMN_BUNNY_SEX = "sex";
    public static final String COLUMN_BUNNY_ANIMAL_TYPE = "animal_type";
    public static final String COLUMN_BUNNY_OWNED ="owned";
    public static final String COLUMN_BUNNY_MOTHER_ID = "mother";
    public static final String COLUMN_BUNNY_FATHER_ID = "father";
    public static final String COLUMN_BUNNY_BREED = "breed";
    public static final String COLUMN_BUNNY_COLOR = "color";
    public static final String COLUMN_BUNNY_FUR_TYPE = "fur_type";
    public static final String COLUMN_BUNNY_PATTERN = "pattern";
    public static final String COLUMN_BUNNY_EYE_COLOR = "eye_color";
    public static final String COLUMN_BUNNY_LITTER_ID = "litter";

    public static final String CREATE_TABLE_BUNNY =
            "CREATE TABLE IF NOT EXISTS " + TABLE_BUNNY + "(" +
            COLUMN_BUNNY_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_BUNNY_NAME + " TEXT NOT NULL, " +
            COLUMN_BUNNY_BREEDER_ID + " INTEGER, " +
            COLUMN_BUNNY_DATE_OF_BIRTH + " TEXT, " +
            COLUMN_BUNNY_SEX + " INTEGER, " +
            COLUMN_BUNNY_ANIMAL_TYPE + " TEXT, " +
            COLUMN_BUNNY_OWNED + " INTEGER, " +
            COLUMN_BUNNY_MOTHER_ID + " INTEGER, " +
            COLUMN_BUNNY_FATHER_ID + " INTEGER ," +
            COLUMN_BUNNY_BREED + " TEXT, " +
            COLUMN_BUNNY_COLOR + " TEXT, " +
            COLUMN_BUNNY_FUR_TYPE + " TEXT, " +
            COLUMN_BUNNY_PATTERN + " TEXT, " +
            COLUMN_BUNNY_EYE_COLOR + " TEXT, " +
            COLUMN_BUNNY_LITTER_ID + " INTEGER " +
            ");";

    public static final String TABLE_BREEDER = "Breeder";
    public static final String COLUMN_BREEDER_ID = "_id";
    public static final String COLUMN_BREEDER = "breeder_name";
    public static final String COLUMN_BREEDER_NAME = "name";
    public static final String COLUMN_BREEDER_EMAIL = "email";
    public static final String COLUMN_BREEDER_ADDRESS = "address";
    public static final String COLUMN_BREEDER_ZIP_CODE = "zip_code";
    public static final String COLUMN_BREEDER_PHONE_NUMBER = "phone_number";

    public static final String CREATE_TABLE_BREEDER =
            "CREATE TABLE IF NOT EXISTS " + TABLE_BREEDER + "(" +
            COLUMN_BREEDER_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_BREEDER + " TEXT NOT NULL, " +
            COLUMN_BREEDER_NAME + " TEXT, " +
            COLUMN_BREEDER_EMAIL + " TEXT," +
            COLUMN_BREEDER_ADDRESS + " TEXT, " +
            COLUMN_BREEDER_ZIP_CODE + " TEXT, " +
            COLUMN_BREEDER_PHONE_NUMBER + " TEXT " +
            ");";

    public static final String TABLE_LITTER = "Litter";
    public static final String COLUMN_LITTER_ID = "_id";
    public static final String COLUMN_LITTER_MOTHER_ID = "mother_id";
    public static final String COLUMN_LITTER_FATHER_ID = "father_id";
    public static final String COLUMN_LITTER_BORN = "born";
    public static final String COLUMN_LITTER_DEFECT = "defect";
    public static final String COLUMN_LITTER_ALIVE = "alive";
    public static final String COLUMN_LITTER_FEMALE = "female";
    public static final String COLUMN_LITTER_MALE = "male";
    public static final String COLUMN_LITTER_DATE_OF_BIRTH = "date_of_birth";

    public static final String CREATE_TABLE_LITTER =
            "CREATE TABLE IF NOT EXISTS " + TABLE_LITTER + "(" +
            COLUMN_LITTER_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_LITTER_MOTHER_ID + " INTEGER, " +
            COLUMN_LITTER_FATHER_ID + " INTEGER, " +
            COLUMN_LITTER_BORN + " INTEGER , " +
            COLUMN_LITTER_DEFECT + " INTEGER, " +
            COLUMN_LITTER_ALIVE + " INTEGER, " +
            COLUMN_LITTER_FEMALE + " INTEGER, " +
            COLUMN_LITTER_MALE + " INTEGER, " +
            COLUMN_LITTER_DATE_OF_BIRTH + " TEXT " +
            ");";


    private static DataBaseSetup instance;
    private Connection conn;

    public DataBaseSetup() {
    }

    public static DataBaseSetup getInstance(){
        if(instance == null){
            instance = new DataBaseSetup();
        }
        return  instance;
    }

    public void open(){
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            createTables();

        } catch (SQLException e){
            System.out.println("Failed to open database.\n");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void close(){
        try{
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e){
            System.out.println("Couldn't close database connection.\n");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    private void createTables(){
        try{
            Statement statement = conn.createStatement();
            statement.execute(CREATE_TABLE_BUNNY);
            statement.execute(CREATE_TABLE_BREEDER);
            statement.execute(CREATE_TABLE_LITTER);
            statement.close();
        } catch (SQLException e) {
            System.out.println("Couldn't create tables.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return  conn;
    }

}
