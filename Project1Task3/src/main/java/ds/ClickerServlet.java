package ds;

// @Author DanMolenhouse
// Servlet for handling the clicker application

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "clickerServlet", value = {"/clickerServlet","/results"})
public class ClickerServlet extends HttpServlet {

    //Initilize model
    ClickerServletModel csm = null;
    public void init() {csm = new ClickerServletModel();}

    //The below method will handle the get request from the view
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //The below block is borrowed from InterestingPicture and simply handles the desktop/mobile cases
        //of viewing the applicaiton

        String ua = request.getHeader("User-Agent");
        boolean mobile;

        // prepare the appropriate DOCTYPE for the view pages
        if (ua != null && ((ua.indexOf("Android") != -1) || (ua.indexOf("iPhone") != -1))) {
            mobile = true;
            /*
             * This is the latest XHTML Mobile doctype. To see the difference it
             * makes, comment it out so that a default desktop doctype is used
             * and view on an Android or iPhone.
             */
            request.setAttribute("doctype", "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.2//EN\" \"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd\">");
        } else {
            mobile = false;
            request.setAttribute("doctype", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        }

        //The block below will handle whether the "/clickerServlet" or "/results" URL is requested
        // I am honestly not sure if this is the best way to handle this, but it does work
        String results = "";
        //if /results is requested
        if(request.getServletPath().equals("/results")){

            //checks if there are results
            if(csm.resultsTally == 0){
                results = "There are currently no results"; //if no results, this message will be displayed
                request.setAttribute("results",results);
            }else{
                //The block below sets up the results messages for the final tally
                results = "The results from the survey are as follows:";
                request.setAttribute("results",results);

                //if statements identify if each answer exist, and assign a request attribute if so
                if(csm.a > 0){
                    String resA = "A: " + csm.a;
                    request.setAttribute("resA",resA);
                }
                if(csm.b > 0){
                    String resB = "B: " + csm.b;
                    request.setAttribute("resB",resB);
                }
                if(csm.c > 0){
                    String resC = "C: " + csm.c;
                    request.setAttribute("resC",resC);
                }
                if(csm.d > 0){
                    String resD = "D: " + csm.d;
                    request.setAttribute("resD",resD);
                }

                //code below resets the global counters to zero
                csm.a = 0;
                csm.b = 0;
                csm.c = 0;
                csm.d = 0;
            }

            //Sets next view to result
            String nextView = "result.jsp";
            RequestDispatcher view = request.getRequestDispatcher(nextView);
            view.forward(request, response);
        }else {
            //The block below handles normal cases when "/result" isn't requested
            //This adds the user answer to the tally by invoking "addAnswer" method
            String answer = request.getParameter("answer");
            csm.addAnswer(answer);

            //This initilizes a response to the previous answer
            String lastAnswer = "Your '" + answer.toUpperCase() + "' response has been registered.";
            request.setAttribute("lastAnswer",lastAnswer);

            //set next view to be index still
            String nextView = "index.jsp";
            RequestDispatcher view = request.getRequestDispatcher(nextView);
            view.forward(request, response);
        }

    }

}