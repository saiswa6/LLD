package src.designpatterns.factory.scaler.abstractfactory;

public class client {
    public static void main(String[] args) {
        Database mySQL = new mySQLDatabase();
        mySQL.create("Hey"); //mySQL Create Hey
        mySQL.delete("Hello"); // mySQL delete Hello
        mySQL.update("Hooo"); // mySQL update Hooo
        Query mySQLQuery1 = mySQL.createDatabaseFactory().createQuery(); // mySQL Create Query

        Database postgres = new postgresDatabase();
        postgres.create("Hey"); // postgres create Hey
        postgres.delete("Hello"); // postgres delete Hello
        postgres.update("Hooo"); // postgres update Hooo
        Query postgresQuery1 = postgres.createDatabaseFactory().createQuery(); // postgres Create Query

    }
}
