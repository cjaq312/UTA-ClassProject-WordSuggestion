import java.io.IOException;

public class ServiceRequest {

	public static void main(String[] args) throws IOException {

		//Declaring variables and objects for service
		WebService obj = new WebService();
		String text = "fcuk yuo";
		String request = " <BodyText>" + text + " </BodyText>";
		String xmlInput = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
				+ "<soap:Body>"
				+ "<CheckTextBodyV2 xmlns=\"http://ws.cdyne.com/\">" + request
				+ "</CheckTextBodyV2>" + "</soap:Body>" + "</soap:Envelope>";

		// Request for Web Service.
		obj.connexForWebService(xmlInput,
				"http://wsf.cdyne.com/SpellChecker/check.asmx");

		// Parameters for Web Service.
		obj.paramsForWebService();

		// Response from Web Service.
		System.out.println(obj.responseFromWebService());
	}
}