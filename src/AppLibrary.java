import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;
import java.util.Random;

public final class AppLibrary {
    public static DBConnection InitializeDB (String url, String user, String password){
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnfe){
            System.out.println("Class not found!");
        }
        try {
            conn = DriverManager.getConnection(
                    url, user, password);
            System.out.println("poszlo stmt");
            stmt = conn.createStatement();
        int queryResult = stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS `medtest` ;\n");
            queryResult = stmt.executeUpdate("USE `medtest`;\n");
            queryResult = stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `medtest`.`Badanie` (\n" +
                    "  `Nazwa` VARCHAR(45) NOT NULL,\n" +
                    "  `Lekarz` VARCHAR(50) NOT NULL,\n" +
                    "  `PoczatekBadan` DATE NULL,\n" +
                    "  `LiczbaPacjentow` INT(4) NULL,\n" +
                    "  `BadanyLek` VARCHAR(45) NOT NULL,\n" +
                    "  PRIMARY KEY (`Nazwa`))\n" +
                    "ENGINE = InnoDB;\n");
            queryResult = stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `medtest`.`Pacjent` (\n" +
                    "  `PESEL` VARCHAR(11) NOT NULL,\n" +
                    "  `Imie` VARCHAR(25) NOT NULL,\n" +
                    "  `Nazwisko` VARCHAR(25) NOT NULL,\n" +
                    "  `Wiek` INT(3) NOT NULL,\n" +
                    "  `NazwaBadania` VARCHAR(45) NOT NULL,\n" +
                    "  `Dawka` VARCHAR(30) NULL,\n" +
                    "  `Efekt` VARCHAR(300) NULL,\n" +
                    "  PRIMARY KEY (`PESEL`),\n" +
                    "  INDEX `nazwabad_idx` (`NazwaBadania` ASC),\n" +
                    "  CONSTRAINT `nazwabad`\n" +
                    "    FOREIGN KEY (`NazwaBadania`)\n" +
                    "    REFERENCES `medtest`.`Badanie` (`Nazwa`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION)\n" +
                    "ENGINE = InnoDB;");
        /* +
                "USE `medtest`;\n" +
                "CREATE TABLE IF NOT EXISTS `medtest`.`Badanie` (\n" +
                "  `Nazwa` VARCHAR(45) NOT NULL,\n" +
                "  `Lekarz` VARCHAR(50) NOT NULL,\n" +
                "  `PoczatekBadan` DATE NULL,\n" +
                "  `LiczbaPacjentow` INT(4) NULL,\n" +
                "  `BadanyLek` VARCHAR(45) NOT NULL,\n" +
                "  PRIMARY KEY (`Nazwa`))\n" +
                "ENGINE = InnoDB;\n" +
                "CREATE TABLE IF NOT EXISTS `medtest`.`Pacjent` (\n" +
                "  `PESEL` VARCHAR(11) NOT NULL,\n" +
                "  `Imie` VARCHAR(25) NOT NULL,\n" +
                "  `Nazwisko` VARCHAR(25) NOT NULL,\n" +
                "  `Wiek` INT(3) NOT NULL,\n" +
                "  `NazwaBadania` VARCHAR(45) NOT NULL,\n" +
                "  `Dawka` VARCHAR(30) NULL,\n" +
                "  `Efekt` VARCHAR(300) NULL,\n" +
                "  PRIMARY KEY (`PESEL`),\n" +
                "  INDEX `nazwabad_idx` (`NazwaBadania` ASC),\n" +
                "  CONSTRAINT `nazwabad`\n" +
                "    FOREIGN KEY (`NazwaBadania`)\n" +
                "    REFERENCES `medtest`.`Badanie` (`Nazwa`)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION)\n" +
                "ENGINE = InnoDB;");*/
                System.out.println("DB created");
        } catch (SQLException sqle){
            System.out.println("Connection error!");
            sqle.printStackTrace();
        }
        DBConnection MEDTESTConnection = new DBConnection(stmt, conn);
        return MEDTESTConnection;
    }

    public static Vector<Vector<Object>> getPatientsData (Statement stmt, char placebo){
        String sql = "SELECT * FROM MEDTEST.Pacjent WHERE Placebo = " + placebo;
        Vector<Vector<Object>> data = null;
        try {
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            data = new Vector<>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }
        }catch (SQLException sqle) {
            System.out.println("There was a problem executing query!");
        }
        return data;
    }

    public static Vector<Vector<Object>> getExperimentData (Statement stmt, String experimentName){
        String sql = "SELECT * FROM MEDTEST.Badanie WHERE Nazwa = "+ experimentName;
        Vector<Vector<Object>> data = null;
        try {
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            data = new Vector<>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }
        }catch (SQLException sqle) {
            System.out.println("There was a problem executing query!");
        }
        return data;
    }

    public static void addNewPatient (Statement stmt, String PESEL, String imie, String nazwisko, int wiek, String nazwaBadania){
        char placebo;
        Random randomGenerator = new Random();
        if (randomGenerator.nextInt(2) == 0)
            placebo = 'N';
        else
            placebo = 'T';
        String sql = "INSERT INTO MEDTEST.Pacjent (PESEL, Imie, Nazwisko, Wiek, NazwaBadania, Placebo) VALUES (" + PESEL+ ", " + imie + ", " + nazwisko + ", " + wiek + ", " + nazwaBadania + ", " + placebo + ");";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException sqle) {
            System.out.println("INSERT patient failure!");
        }
    }

    public static void addNewExperiment (Statement stmt, String nazwa, String lekarz, int dzien, int miesiac, int rok, String badanyLek){
        String sql = "INSERT INTO MEDTEST.Badanie (Nazwa, Lekarz, PoczatekBadan, BadanyLek) VALUES (" + nazwa + ", " + lekarz + ", " + rok + "-" + miesiac + "-" + dzien + ", " + badanyLek + ");";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException sqle) {
            System.out.println("INSERT experiment failure!");
        }
    }

    public static void deletePatient (Statement stmt, String PESEL){
        String sql = "DELETE FROM MEDTEST.Pacjent WHERE PESEL = " + PESEL;
        try{
            stmt.executeUpdate(sql);
        } catch (SQLException sqle) {
            System.out.println("Failure deleting patient!");
        }
    }

    public static void deleteExperiment (Statement stmt, String nazwa){
        String sql = "DELETE FROM MEDTEST.Pacjent WHERE NazwaBadania = " + nazwa +"; " +
                "DELETE FROM MEDTEST.Badanie WHERE Nazwa = " + nazwa;
        try{
            stmt.executeUpdate(sql);
        } catch (SQLException sqle) {
            System.out.println("Failure deleting experiment!");
        }
    }

    public static void updatePatient (Statement stmt, String dawka, String efekt, String PESEL){
        String sql = "UPDATE MEDTEST.Pacjent SET Dawka = " + dawka + ", Efekt = " + efekt + " WHERE PESEL = " + PESEL + ";";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException sqle) {
            System.out.println("Failure updating patient!");
        }
    }

    public static void getTablesFromDB (JTable medTable, JTable placTable, Statement stmt){
        Vector<Vector<Object>> medData = AppLibrary.getPatientsData(stmt, 'N');
        Vector<Vector<Object>> placData = AppLibrary.getPatientsData(stmt, 'T');
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Imię");
        columnNames.add("Nazwisko");
        columnNames.add("PESEL");
        columnNames.add("Wiek");
        columnNames.add("Dawka leku");
        columnNames.add("Efekt");
        medTable.setModel(new DefaultTableModel(medData, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        placTable.setModel(new DefaultTableModel(placData, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }

}
