package src.designpatterns.builder.scaler.approachone;

import java.security.InvalidParameterException;

public class UserExam {
    private String name;
    private int mathsMarks;
    private int englishMarks;
    private int scienceMarks;

    UserExam(UserExamParameters userExamParameters) {
        // Validation
        if(userExamParameters.mathsMarks + userExamParameters.scienceMarks > 199) {
            throw new InvalidParameterException("Maths and Science Marks can't be greater than 199");
        }

        if(userExamParameters.mathsMarks > 100) {
            throw new InvalidParameterException("Maths marks can't be grater than 100");
        }

        if(userExamParameters.name.startsWith("0")){
            throw new InvalidParameterException("Name can't start with 0");
        }

        //Object creation
        this.name = userExamParameters.name;
        this.mathsMarks = userExamParameters.mathsMarks;
        this.englishMarks = userExamParameters.englishMarks;
        this.scienceMarks = userExamParameters.scienceMarks;
    }

//    public void setMathsMarks(int mathsMarks) {  --> Here, Object will be created first, later mathsMarks will be set. can't validate the value of attribute before object creation with setters.
//        if (mathsMarks > 100) {
//            throw new Exception();
//        }
//        this.mathsMarks = mathsMarks;
//    }

}
