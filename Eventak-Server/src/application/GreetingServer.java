package application;


import java.net.*;
import java.io.*;

public class GreetingServer extends Thread {
	  private ServerSocket serverSocket;
	   
	   public GreetingServer(int port) throws IOException
	   {
	      serverSocket = new ServerSocket(port);
	      //serverSocket.setSoTimeout(100000);
	   }

	   public void run()
	   {
	      while(true)
	      {
	         try
	         {
	            System.out.println("Waiting for client on port " +
	            serverSocket.getLocalPort() + "...");
	            Socket server = serverSocket.accept();
	            System.out.println("Just connected to "
	                  + server.getRemoteSocketAddress());
	            DataInputStream in =
	                  new DataInputStream(server.getInputStream());
	            String req = in.readUTF();
	            System.out.println(req);
	            String[] arr = req.split(",");
	            String output = "CONCERT NOT FOUND";
	            for(int i=0; i<Main.CONCERT_LIST.size(); i++){
	            	if(Main.CONCERT_LIST.get(i).getName().equals(arr[0])){
	            		try {
							int n = Main.CONCERT_LIST.get(i).buyTicket(arr[1], Integer.parseInt(arr[2]), "Cash");
							output = "SUCCESS! That cost you: " + n;
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							output = "Number has to be an int";
						}
	            		catch ( OutofLimitException  e){
	            			output = "This tier has reached its limit.";
	            		}
	            		catch(NotAddedException e){
	            			output = "This tier does not exist for this concert.";
	            		}
	            		break;
	            	}
	            }
	            DataOutputStream out =
	                 new DataOutputStream(server.getOutputStream());
	            out.writeUTF(output);
	            server.close();
	         }catch(SocketTimeoutException s)
	         {
	            System.out.println("Socket timed out!");
	            break;
	         }catch(IOException e)
	         {
	            e.printStackTrace();
	            break;
	         }
	      }
	   }
	
}
