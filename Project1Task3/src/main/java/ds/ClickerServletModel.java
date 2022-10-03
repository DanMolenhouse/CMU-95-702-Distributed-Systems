package ds;

// @Author Dan Molenhouse 02-11-2022
// Very simple model code that keeps track of global static a, b, c and d variables

public class ClickerServletModel {

    //Initialize static variables for each possible answer, and a results boolean stored as an int
    public static int a = 0;
    public static int b = 0;
    public static int c = 0;
    public static int d = 0;
    public static int resultsTally = 0;

    //AddAnswer simply takes the answer inputted by the user and adds it to the appropriate int
    public void addAnswer(String answer){
       if(answer.equals("a")){
            a++;
            resultsTally = 1;
       }
        if(answer.equals("b")){
            b++;
            resultsTally = 1;
        }
        if(answer.equals("c")){
            c++;
            resultsTally = 1;
        }
        if(answer.equals("d")){
            d++;
            resultsTally = 1;
        }
    }

}
