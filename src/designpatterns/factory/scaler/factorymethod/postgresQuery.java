package src.designpatterns.factory.scaler.factorymethod;

public class postgresQuery implements Query{
    postgresQuery(){
        System.out.println("postgres SQL Query");
    }
}
