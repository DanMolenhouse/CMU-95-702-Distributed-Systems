package ds;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
/*
*   @author Dan Molenhouse 02-11-2022
*   Controller/Servlet for MVC of hash computing program
*
 */

@WebServlet(name = "Compute Hashes", value = "/ComputeHashes")
public class ComputeHashes extends HttpServlet {

    //Initialize model of MVC
    ComputeHashesModel chm = null;
    public void init() {
        chm = new ComputeHashesModel();
    }

    //Main method for when get request it placed
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //
        byte[] finalHash = new byte[0];
        String hash = request.getParameter("hashWord");     //get word to be hashed submitted by user
        String type = request.getParameter("hashType");     //get hash type submitted by user
        try {
            finalHash = chm.hash(hash, type);                   //populate finalHash variable using model
        } catch (NoSuchAlgorithmException e) {
            System.out.println("ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"); //Very useless error message
        }

        String hash64 = javax.xml.bind.DatatypeConverter.printBase64Binary(finalHash); //Convert hash to String
        String hashHex = javax.xml.bind.DatatypeConverter.printHexBinary(finalHash);    //Convert hash to String
        request.setAttribute("hash64", hash64);             //Send hash64 to View
        request.setAttribute("hashHex", hashHex);           //Send hashHex to View

        String nextView = "result.jsp";                         //set next view to result page

        RequestDispatcher view = request.getRequestDispatcher(nextView);    //
        view.forward(request, response);                                    //Go to next view

    }

}