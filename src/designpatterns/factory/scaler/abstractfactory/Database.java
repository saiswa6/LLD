package src.designpatterns.factory.scaler.abstractfactory;

public interface Database {
    void create(String input);
    void delete(String input);
    void update(String input);

    DatabaseFactory createDatabaseFactory();
}
