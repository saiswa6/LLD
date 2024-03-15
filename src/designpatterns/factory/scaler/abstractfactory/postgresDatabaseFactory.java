package src.designpatterns.factory.scaler.abstractfactory;

public class postgresDatabaseFactory implements DatabaseFactory{
    @Override
    public Query createQuery() {
        System.out.println("postgres Create Query");
        return new postgresQuery();
    }

    @Override
    public Transaction createTransaction() {
        System.out.println("postgres Create Transaction");
        return new postgresTransaction();
    }
}
