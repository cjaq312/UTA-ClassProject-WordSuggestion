import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class WebService {

	//Declaring global variables used in the program
	private URL url = null;
	private URLConnection connection = null; //establishing url connection
	private HttpURLConnection httpConn = null; // conversion to http connection
	private String responseString = null; //output string from server 
	private String outputString = ""; //output string returned to the calling function
	private ByteArrayOutputStream bout = null;
	private OutputStream out = null;
	private InputStreamReader isr = null;
	private BufferedReader in = null;
	private byte[] b;

	public void connexForWebService(String xmlInput, String wsURL) {

		try {

			// Establish connection to web service
			url = new URL(wsURL);
			connection = url.openConnection(); //opening url connection 
			httpConn = (HttpURLConnection) connection; //conversion to http conneciton
			bout = new ByteArrayOutputStream();
			byte[] buffer = new byte[xmlInput.length()]; //buffer length for processing
			buffer = xmlInput.getBytes();
			bout.write(buffer); //write to buffer
			b = bout.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void paramsForWebService() throws IOException {

		// Set the appropriate HTTP parameters.
		httpConn.setRequestProperty("Content-Length", String.valueOf(b.length)); //set content length
		httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8"); //set content type
		httpConn.setRequestProperty("SOAPAction",
				"http://ws.cdyne.com/CheckTextBodyV2"); //set soap action
		httpConn.setRequestMethod("POST");//set request method
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		out = httpConn.getOutputStream();
		out.write(b); //send the message over connection established(request for service)
		out.close();
	}

	public String responseFromWebService() throws IOException {
		
		//Receiving response from server
		isr = new InputStreamReader(httpConn.getInputStream()); //openeing stream for input from server
		in = new BufferedReader(isr); 
		while ((responseString = in.readLine()) != null) {
			outputString = outputString + responseString; //read each line in the message got from server till its null
		}
//		System.out.println(outputString);
		return outputString; //return the output to the calling function
	}
}