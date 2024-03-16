package src.designpatterns.structural.decorator.TextFileEncrypter;

public class encryptPrintText implements TextFileEncrypter{
    TextFileEncrypter textFileEncrypter;

    encryptPrintText(TextFileEncrypter textFileEncrypter){
        this.textFileEncrypter = textFileEncrypter;
    }
    @Override
    public String TextPrinter() {
        return textFileEncrypter.TextPrinter() + " : Text File is encrypted : ";
    }
}
