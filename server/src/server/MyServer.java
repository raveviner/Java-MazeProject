package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * MyServer class is responsible for opening the server.
 *
 */
public class MyServer {

	int port;
	ServerSocket serverSocket;

	ClientHandler clinetHandler;
	int numOfClients;
	ExecutorService threadpool;

	volatile boolean stop;

	Thread mainServerThread;

	int clientsHandled = 0;

	//CTOR initializes port, clientHandler and number of clients
	public MyServer(int port, ClientHandler clinetHandler, int numOfClients) {
		this.port = port;
		this.clinetHandler = clinetHandler;
		this.numOfClients = numOfClients;
	}
	
	/**
	 * starts the running of the server.
	 * @throws Exception
	 */
	public void start() throws Exception {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(10 * 1000);
		threadpool = Executors.newFixedThreadPool(numOfClients);

		mainServerThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!stop) {
					try {
						final Socket someClient = serverSocket.accept();
						if (someClient != null) {
							threadpool.execute(new Runnable() {
								@Override
								public void run() {
									try {
										clientsHandled++;
										System.out.println("\thandling client " + clientsHandled);
										clinetHandler.handleClient(someClient.getInputStream(),
												someClient.getOutputStream());
										someClient.close();
										System.out.println("\tdone handling client " + clientsHandled);
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							});
						}
					} catch (SocketTimeoutException e) {
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("done accepting new clients.");
			} // end of the mainServerThread task
		});

		mainServerThread.start();

	}

	/**
	 * closes all threads and sockets.
	 * @throws Exception
	 */
	public void close() throws Exception {
		stop = true;
		// do not execute jobs in queue, continue to execute running threads
		System.out.println("shutting down");
		threadpool.shutdown();
		// wait 10 seconds over and over again until all running jobs have
		// finished
		@SuppressWarnings("unused")
		boolean allTasksCompleted = false;
		while (!(allTasksCompleted = threadpool.awaitTermination(10, TimeUnit.SECONDS)));

		System.out.println("all the tasks have finished");

		mainServerThread.join();
		System.out.println("main server thread is done");

		serverSocket.close();
		System.out.println("server is safely closed");
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Server Side");
		System.out.println("type \"close the server\" to stop it");
		ServerModel model = new ServerModel();
		
		MazeClientHandler clientHandler = new MazeClientHandler(model);
		model.addObserver(clientHandler);
		
		MyServer server = new MyServer(5400, clientHandler, 10);
		server.start();

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		while (!(in.readLine()).equals("close the server"));

		server.close();

	}
}
