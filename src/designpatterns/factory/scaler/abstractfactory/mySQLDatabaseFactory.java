package src.designpatterns.factory.scaler.abstractfactory;

public class mySQLDatabaseFactory implements DatabaseFactory{
    @Override
    public Query createQuery() {
        System.out.println("mySQL Create Query");
        return new mySQLQuery();
    }

    @Override
    public Transaction createTransaction() {
        System.out.println("mySQL Create Transaction");
        return new mySQLTransaction();
    }
}
