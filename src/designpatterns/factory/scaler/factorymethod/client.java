package src.designpatterns.factory.scaler.factorymethod;

public class client {
    public static void main(String[] args) {
        Database mySQL = new mySQLDatabase();
        Query mysqlquery = mySQL.createQuery();  // mySQL Query

        Database postgres = new postgresDatabase();
        Query postgresquery = postgres.createQuery();  // postgres Query
    }
}
