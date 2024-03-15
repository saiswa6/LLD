package src.designpatterns.factory.scaler.factorymethod;

public class mySQLDatabase implements Database{
    @Override
    public Query createQuery() {
        return new mySQLQuery();
    }
}
