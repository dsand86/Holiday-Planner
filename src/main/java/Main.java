import com.andreiciubotaru.holidayplanner.model.Database;

public class Main {
    public static void main(String[] args) {
        Database.createTables();
        Database.fillDefaultTables();
        Database.fillTablesWithDummyData();
    }
}