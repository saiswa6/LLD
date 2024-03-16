package src.designpatterns.structural.decorator.TextFileEncrypter;

public class TextFile implements TextFileEncrypter{
    @Override
    public String TextPrinter() {
        return "Base Text File ";
    }
}
