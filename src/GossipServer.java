
import java.io.*;
import java.net.*;

public class GossipServer {
	public static void main(String[] args) throws Exception {
		ServerSocket server_socket = new ServerSocket(9924);
		System.out.println("Server is ready for chatting");
		Socket socket = server_socket.accept();
		// reading from keyboard
		BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
		// sending to client (out object)
		OutputStream o_stream = socket.getOutputStream();
		PrintWriter out = new PrintWriter(o_stream, true);

		// receiving from server [receiveRead object]
		InputStream i_stream = socket.getInputStream();
		BufferedReader buff_reader = new BufferedReader(new InputStreamReader(i_stream));

		String receive_msg, send_msg;
		while (true) {
			if ((receive_msg = buff_reader.readLine()) != null) {
				System.out.println("" + receive_msg);
			}
			send_msg = keyRead.readLine();
			out.println("" + send_msg);
			out.flush();
		}
	}
}