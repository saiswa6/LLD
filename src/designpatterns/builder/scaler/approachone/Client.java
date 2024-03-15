package src.designpatterns.builder.scaler.approachone;



public class Client {
    public static void main(String[] args) {
        UserExamParameters userExamParameters = new UserExamParameters();
        userExamParameters.name = "Sai";
        userExamParameters.mathsMarks = 95;
        userExamParameters.englishMarks = 96;
        userExamParameters.scienceMarks = 86;

        UserExam userExam;

        try {
            userExam = new UserExam(userExamParameters);
        } catch (Exception exception) {
            System.out.println("Some parameters are wrong");
        }

//        UserExam userExam1 = new UserExam();
//        userExam1.setMathsMarks(99); --> Here, Object will be created first, later mathsMarks will be set. can't validate the value of attribute before object creation with setters.

        System.out.println("Done");
    }
}
