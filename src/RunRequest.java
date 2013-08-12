import java.io.IOException;
import javax.swing.JFrame;

public class RunRequest {
	public static void main(String[] args) throws IOException {
		ServiceRequestTest instance = new ServiceRequestTest(); //instance for running the service request window which takes input and displays the output in the window below
		instance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}