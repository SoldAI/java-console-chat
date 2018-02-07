import java.util.Scanner;

public class HermesConsole {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String query = "start";
		int count = 0;
		RestClient c = new RestClient();
		System.out.println("Execution started... Hi!!");
		System.out.println("Hola, ¿En qué te puedo ayudar?");
		
		while(query.length() > 0 && query != "" && query != null) {
			query = input.nextLine();
			if(query == "" || query == null || query.length() == 0)
				break;
			count++;
			System.out.println("Usuario[" + count + "]:" + query);
			System.out.println("Hermes[" + count + "]:" + c.getResponse(query));
		}
		input.close();
		System.out.println("Execution finished... Bye!!");
	}
}
