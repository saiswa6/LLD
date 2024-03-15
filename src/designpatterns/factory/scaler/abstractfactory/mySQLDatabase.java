package src.designpatterns.factory.scaler.abstractfactory;

public class mySQLDatabase implements Database{
    @Override
    public void create(String input) {
        System.out.println("mySQL Create " + input);
    }

    @Override
    public void delete(String input) {
        System.out.println("mySQL delete " + input);
    }

    @Override
    public void update(String input) {
        System.out.println("mySQL update "+ input);
    }

    @Override
    public DatabaseFactory createDatabaseFactory() {
        return new mySQLDatabaseFactory();
    }
}
