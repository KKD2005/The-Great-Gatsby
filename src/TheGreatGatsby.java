import java.io.IOException;

public class TheGreatGatsby {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		TextGenerator gatsby = new TextGenerator ("As I Lay Dying.txt",6);
		gatsby.generateText("Output.txt", 100000);
	}

}
