package src.designpatterns.factory.scaler.abstractfactory;

public interface DatabaseFactory {
    Query createQuery();
    Transaction createTransaction();
}
