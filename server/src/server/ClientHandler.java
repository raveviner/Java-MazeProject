package server;

import java.io.InputStream;
import java.io.OutputStream;
/**
 * ClientHandler class handles the clients that connect to the server.
 *
 */
public interface ClientHandler {
	/**
	 * handleClient knows how to receive InputStream from client and send OutputStream back to client.
	 * @param inFromClient
	 * @param outToClient
	 */
	void handleClient(InputStream inFromClient, OutputStream outToClient);
}
