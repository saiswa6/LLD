package src.designpatterns.structural.decorator.TextFileEncrypter;

public class Client {
    public static void main(String[] args) {

        /*
        Assignment
        // implement a text file encrypter interface
        // TextPrinter {
        //   printText()
        // }
        // add following decorations:
        // - encrypt printedText
        // - encode printed Text
         */


        TextFileEncrypter textFileEncrypter = new EncodePrintText(new encryptPrintText(new TextFile()));
        System.out.println(textFileEncrypter.TextPrinter());// Base Text File  : Text File is encrypted :  : Text File is encoded :

        TextFileEncrypter textFileEncrypter2 = new encryptPrintText(new encryptPrintText(new encryptPrintText(new TextFile())));
        System.out.println(textFileEncrypter2.TextPrinter()); // Base Text File  : Text File is encrypted :  : Text File is encrypted :  : Text File is encrypted :

        TextFileEncrypter textFileEncrypter3 = new encryptPrintText(new TextFile());
        System.out.println(textFileEncrypter3.TextPrinter()); // Base Text File  : Text File is encrypted :
    }
}
