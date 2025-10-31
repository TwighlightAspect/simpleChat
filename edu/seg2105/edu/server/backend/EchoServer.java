package edu.seg2105.edu.server.backend;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 


import java.io.IOException;

import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 */
public class EchoServer extends AbstractServer 
{
  //Class variables *************************************************
  
  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5555;
  final public static char COMMAND_PREFIX = '#';
  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  public EchoServer(int port) 
  {
    super(port);
  }

  
  //Instance methods ************************************************
  
  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
  public void handleMessageFromClient
    (Object msg, ConnectionToClient client)
  {
    System.out.println("Message received: " + msg + " from " + client);
    this.sendToAllClients(msg);
    if(msg.equals("/stop"))
    {
    	this.stopListening();
    }
    if(msg instanceof String&&((String) msg).length()>0&&((String)msg).charAt(0)==COMMAND_PREFIX)
    {
    	String cmd = ((String)msg).substring(1);
    	if(cmd.equals("quit"))
    	{
    		try {
    			client.close();
    		}
    		catch(Exception e){
    			try {
    				client.sendToClient("Disconnection attempt was unsuccessful.");
    			}
    			catch(Exception ex)
    			{
    				
    			}
    		}
    	}
    	else if(cmd.equals("logoff"))
    	{
    		
    	}
    	else if(cmd.equals("sethost"))
    	{
    		
    	}
    	else if(cmd.equals("setport"))
    	{
    		
    	}
    	else if(cmd.equals("login"))
    	{
    		
    	}
    	else if(cmd.equals("gethost"))
    	{
    		
    	}
    	else if(cmd.equals("getport"))
    	{
    		
    	}
    	else 
    	{
    		try {
    			client.sendToClient("The command: "+cmd+" does not exist in this context");
    		}
    		catch(Exception e)
    		{
    			
    		}
    	}
    }
  }
  @Override
  protected void clientConnected(ConnectionToClient client)
  {
	  String clientjoinmsg = "A new client has connected!";
	  this.sendToAllClients(clientjoinmsg);
	  System.out.println(clientjoinmsg);
	  try {
		  client.sendToClient("Welcome to our wonderful server!");
	  }
	  catch(Exception e) {
		  
	  }
  }
  
  @Override
  synchronized protected void clientDisconnected(ConnectionToClient client)
  {
	  try {
		  client.sendToClient("Disconnecting...");
	  }
	  catch(Exception e)
	  {
		  
	  }
	  super.clientDisconnected(client);
	  String clientjoinmsg = "A client has disconnected.";
	  this.sendToAllClients(clientjoinmsg);
	  System.out.println(clientjoinmsg);
	  
  }
    
  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    System.out.println
      ("Server listening for connections on port " + getPort());
  }
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
	  try {
		  this.sendToAllClients("Server has shut down.");
		  for(ConnectionToClient cli:this.getClientConnections())
		  {
//			  System.out.println("Closing client");
			  cli.sendToClient("Terminating Client...");
			  cli.close();
		  }
	  }
	  catch(Exception e){
		System.out.println(e);  
	  }
	 try {
		 
	  this.close();
	  System.out.println("\n=====================================\n\nServer has been shut down.");
	 }
	 catch(Exception e){
		 System.out.println("Could not stop server");
	 }
    System.out.println
      ("Server has stopped listening for connections.");
  }
  
  
  //Class methods ***************************************************
  
  /**
   * This method is responsible for the creation of 
   * the server instance (there is no UI in this phase).
   *
   * @param args[0] The port number to listen on.  Defaults to 5555 
   *          if no argument is entered.
   */
  public static void main(String[] args) 
  {
    int port = 0; //Port to listen on

    try
    {
      port = Integer.parseInt(args[0]); //Get port from command line
    }
    catch(Throwable t)
    {
      port = DEFAULT_PORT; //Set port to 5555
    }
	
    EchoServer sv = new EchoServer(port);
    
    try 
    {
      sv.listen(); //Start listening for connections
    } 
    catch (Exception ex) 
    {
      System.out.println("ERROR - Could not listen for clients!");
    }
  }
}
//End of EchoServer class
