package src.designpatterns.factory.scaler.factorymethod;

public class postgresDatabase implements Database{

    @Override
    public Query createQuery() {
        return new postgresQuery();
    }
}
