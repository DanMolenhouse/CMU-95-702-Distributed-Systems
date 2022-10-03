package ds;
/*
 * Code inspired by instructor Joe Mertz's Interesting Picture Model/Servlet
 * As such, there will be many familiar lines of code present.
 *
 * Adapted to search for Dog breeds by @author Dan Molenhouse
 *
 */

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;



public class DogFinderModel {

    /* The doDogSearch method requests an API response from https://dog.ceo/api/breed/ and interprets the
     * JSON response using GSON, based on the breed type input by the user in the main view
     */
    public String doDogSearch(String dogBreed, String picSize)
            throws IOException {

        //initiliaze variables
        dogBreed = URLEncoder.encode(dogBreed, "UTF-8");
        String response = "";
        String dogpicURL;


        // Create a URL for the page to be screen scraped
        dogpicURL =
                "https://dog.ceo/api/breed/"
                        + dogBreed.toLowerCase()
                        + "/images";

        // Fetch the page using the fetch method created in Interesting Picture
        response = fetch(dogpicURL);

        //The block below is a simple JSON response handler
        /* It takes the JSON response from the above fetch method, and pulls out a random
         * URL from the array of dog picture urls. The URL is returned
         */
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        JsonArray URLlist = jsonObject.get("message").getAsJsonArray();
        int rnd = new Random().nextInt(URLlist.size());
        String url1 = URLlist.get(rnd).getAsString();

        //return URL fetched from API
        return url1;
    }

    /* doDogAttributeSearch takes a single parameter of dogBreed and scrapes the https://dogtime.com/dog-breeds/
     * page for that particular breed, and pulls snippets of characteristics of that breed from the site.
     *
     */
    public String doDogAttributeSearch(String dogBreed)
            throws UnsupportedEncodingException {
        dogBreed = URLEncoder.encode(dogBreed, "UTF-8");
        String response = "";
        String dogpicURL;


        // Create a URL for the page to be screen scraped
        dogpicURL =
                "https://dogtime.com/dog-breeds/"
                        + dogBreed.toLowerCase()
                        + "#/slide/1";

        // Fetch the page using the Interesting Picture fetch method
        response = fetch(dogpicURL);

        //Initialize attributes by searching the HTML returned by fetch above
        /* Each attribute has a unique method of being located - for example the friendliness and intelligence
         * attributes are always in the same place, so the located can be hardcoded
         * but the other three have a variable location, so a general snippet is taken and then cleaned up
         * utilizing the split / substring methods for strings
         */

        //Below is the code for pulling a snippet out for each attribute
        char friendliness = response.charAt(response.indexOf("All Around Friendliness")+89);
        char intelligence = response.charAt(response.indexOf("</div>Intelligence</div>")+85);
        String weight = response.substring(response.indexOf("vital-stat-weight") + 30, response.indexOf("vital-stat-weight") + 47);
        String height = response.substring(response.indexOf("vital-stat-height")+30,response.indexOf("vital-stat-height")+47);
        String lifespan = response.substring(response.indexOf("vital-stat-lifespan")+35,response.indexOf("vital-stat-lifespan")+48);

        //below is where they are split and cleaned up using regex
        height = height.split(">")[1].split(" in")[0] + " inches all at shoulder";
        if(weight.contains(">")) {
           weight = weight.split(">")[1].split(" po")[0] + " pounds";
        }else{
            weight = "No weight information found!";
        }
        lifespan = lifespan.split(">")[1].split(" ye")[0] + " years";

        //Below is the string assembled for output
        String attr = friendliness + "," + intelligence + "," + height + "," + weight + "," + lifespan;
        return attr;
    }

    /* Adapted from Interesting Picture code
     * Return a URL of an image of appropriate size
     * 
     * Arguments
     * @param picSize The string "mobile" or "desktop" indicating the size of
     * photo requested.
     * @return The URL an image of appropriate size.
     */
    private String dogPicSize(String pictureURL, String picSize) {
        int finalDot = pictureURL.lastIndexOf(".");
        /*
         * From the flickr online documentation, an underscore and a letter 
         * before the final "." and file extension is a size indicator.  
         * "_m" for small and "-z" for big.
         */
        String sizeLetter = (picSize.equals("mobile")) ? "m" : "z";
        if (pictureURL.indexOf("_", finalDot-2) == -1) {
            // If the URL currently did not have a _? size indicator, add it.
            return (pictureURL.substring(0, finalDot) + "_" + sizeLetter
                + pictureURL.substring(finalDot));
        } else {
            // Else just change it
            return (pictureURL.substring(0, finalDot - 1) + sizeLetter
                + pictureURL.substring(finalDot));
        }
    }

    /* Adapted from InterestingPicture code
     * Make an HTTP request to a given URL
     * 
     * @param urlString The URL of the request
     * @return A string of the response from the HTTP GET.  This is identical
     * to what would be returned from using curl on the command line.
     */
    private String fetch(String urlString) {
        String response = "";
        try {
            URL url = new URL(urlString);
            /*
             * Create an HttpURLConnection.  This is useful for setting headers
             * and for getting the path of the resource that is returned (which
             * may be different than the URL above if redirected).
             * HttpsURLConnection (with an "s") can be used if required by the site.
             */
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String str;
            // Read each line of "in" until done, adding each to "response"
            while ((str = in.readLine()) != null) {
                // str is one line of text readLine() strips newline characters
                response += str;
            }
            in.close();
        } catch (IOException e) {
            System.out.println("There was an unknown error");
        }
        return response;
    }

}
