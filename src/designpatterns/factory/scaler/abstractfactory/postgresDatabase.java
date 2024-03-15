package src.designpatterns.factory.scaler.abstractfactory;

public class postgresDatabase implements Database{
    @Override
    public void create(String input) {
        System.out.println("postgres create " + input);
    }

    @Override
    public void delete(String input) {
        System.out.println("postgres delete " + input);
    }

    @Override
    public void update(String input) {
        System.out.println("postgres update " + input);
    }

    @Override
    public DatabaseFactory createDatabaseFactory() {
        return new postgresDatabaseFactory();
    }
}
