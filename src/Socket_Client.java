
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Socket_Client {

	static LocalDateTime now;
	static DateTimeFormatter format;
	static String formatDateTime;

	public static void main(String[] args) throws Exception {

		// Socket socket = new Socket("127.0.0.1", 9913);
		Socket socket = new Socket("localhost", 9924);
		// reading from keyboard
		BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
		// sending to server [out object]
		OutputStream o_stream = socket.getOutputStream();
		PrintWriter out = new PrintWriter(o_stream, true);

		// receiving from server [receiveRead object]
		InputStream i_stream = socket.getInputStream();
		BufferedReader receiveRead = new BufferedReader(new InputStreamReader(i_stream));

		System.out.println("Welcome to Chat Application !!!");

		String receive_msg, send_msg;
		format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		while (true) {
			send_msg = keyRead.readLine(); // keyboard reading

			BufferedWriter buff_writer_for_client = new BufferedWriter(new FileWriter("F:\\chat_app.txt", true));
			now = LocalDateTime.now();
			formatDateTime = now.format(format);

			buff_writer_for_client.write("\n");
			buff_writer_for_client.write(formatDateTime);
			buff_writer_for_client.write("\tClient: " + send_msg);
			buff_writer_for_client.close();

			out.println("Client: " + send_msg); // sending to server
			out.flush(); // flush the data

			if ((receive_msg = receiveRead.readLine()) != null) // receive from server
			{
				System.out.println("Server : " + receive_msg); // displaying at Console....

				BufferedWriter buff_writer_for_server = new BufferedWriter(new FileWriter("F:\\chat_app.txt", true));
				now = LocalDateTime.now();
				formatDateTime = now.format(format);

				buff_writer_for_server.write("\n");
				buff_writer_for_server.write(formatDateTime);
				buff_writer_for_server.write("\tServer: " + receive_msg);
				buff_writer_for_server.close();

			}

		}

	}

}
