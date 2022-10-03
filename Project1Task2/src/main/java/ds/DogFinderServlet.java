package ds;
/*
 * Code inspired by instructor Joe Mertz's Interesting Picture Model/Servlet
 * As such, there will be many familiar lines of code present.
 *
 * Adapted to search for Dog breeds by @author Dan Molenhouse
 *
 */

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DogFinderServlet",
        urlPatterns = {"/dogFinderServlet"})
public class DogFinderServlet extends HttpServlet {

    DogFinderModel dfm = null;  // The Model for this app

    // Initialize the model
    @Override
    public void init() {
        dfm = new DogFinderModel();
    }

    // This servlet will reply to HTTP GET requests via this doGet method
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // get the dog breed
        String search = request.getParameter("dogBreed");

        // determine what type of device our user is (The following is borrowed from Interesting Picture)
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

        String nextView;
        /*
         * Check if the search parameter is present.
         * If not, then give the user instructions and prompt for a search string.
         * If there is a search parameter, then do the search and return the result.
         */
        if(search!=null) {
            String picSize = (mobile) ? "mobile" : "desktop";
            // use model to do the search and choose the result view
            /*
             * Attributes on the request object can be used to pass data to
             * the view.  These attributes are name/value pairs, where the name
             * is a String object.  Here the pictureURL is passed to the view
             * after it is returned from the model interestingPictureSize method.
             */


            //
            /* The following block of code calls on the doDogAttributeSearch method in the
             * Model, which outputs a string of all attributes, each attribute seperated by a comma.
             * The five lines after that split this output and assign to the appropriate attribute.
             * The model itself handles all the splitting/trimming of each individual attribute.
             */
            String attr = dfm.doDogAttributeSearch(search);
            String friend = attr.split(",")[0];
            String intel = attr.split(",")[1];
            String weight = attr.split(",")[2];
            String height = attr.split(",")[3];
            String lifespan = attr.split(",")[4];

            //The following block assigns each variable to an attribute and sends to a request
            request.setAttribute("friend", friend);
            request.setAttribute("intel", intel);
            request.setAttribute("weight", weight);
            request.setAttribute("height", height);
            request.setAttribute("lifespan", lifespan);


            // The following block calls upon the doDogSearch method, which gives a picture URL for the
            // Selected dog breed. This is all handled by the model itself. The following code assigns it
            // to an attribute for the view to use.
            String pictureURL = dfm.doDogSearch(search, picSize);
            request.setAttribute("pictureURL", pictureURL);

            // Pass the user search string (pictureTag) also to the view.
            nextView = "result.jsp";
        }else{
            //sets nextview to index if no search is present
            nextView = "index.jsp";
        }

        // Transfer control over the the correct "view"
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
    }
}

