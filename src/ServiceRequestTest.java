import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ServiceRequestTest extends JFrame {
	private JTextField enterField;
	private JTextArea displayArea;
	private WebService obj = new WebService();
	private String request, xmlInput;

	// constructor to create the request window
	public ServiceRequestTest() throws IOException {
		super();
		enterField = new JTextField();
		enterField.setEditable(true);
		enterField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					sendData(event.getActionCommand());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		add(enterField, BorderLayout.NORTH);
		displayArea = new JTextArea(); // create displayArea
		add(new JScrollPane(displayArea), BorderLayout.CENTER);
		setSize(1000, 500);
		setVisible(true);
		setTextFieldEditable(false);
		displayArea.setFont(new Font("Serif", Font.PLAIN, 16));
		displayArea.setLineWrap(true);
		displayArea.setWrapStyleWord(true);
	}

	// the message foramt to be sent for the service
	private void sendData(String message) throws IOException {
		request = " <BodyText>" + message + " </BodyText>";
		xmlInput = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
				+ "<soap:Body>"
				+ "<CheckTextBodyV2 xmlns=\"http://ws.cdyne.com/\">" + request
				+ "</CheckTextBodyV2>" + "</soap:Body>" + "</soap:Envelope>";

		// Request for Web Service.
		obj.connexForWebService(xmlInput,
				"http://wsf.cdyne.com/SpellChecker/check.asmx");

		// Parameters for Web Service.
		obj.paramsForWebService();

		// Display the request and response in the text area
		displayArea.append("\n\nRequest==>  " + xmlInput + "\n\n\n");
		displayArea.append("Response==>  " + obj.responseFromWebService());
	}

	private void setTextFieldEditable(final boolean editable) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				displayArea.setEditable(editable);
			}
		});
	}
}