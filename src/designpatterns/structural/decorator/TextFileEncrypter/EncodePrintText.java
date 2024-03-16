package src.designpatterns.structural.decorator.TextFileEncrypter;

public class EncodePrintText implements TextFileEncrypter{
    TextFileEncrypter textFileEncrypter;

    EncodePrintText(TextFileEncrypter textFileEncrypter){
        this.textFileEncrypter = textFileEncrypter;
    }
    @Override
    public String TextPrinter() {
        return textFileEncrypter.TextPrinter() + " : Text File is encoded : ";
    }
}
