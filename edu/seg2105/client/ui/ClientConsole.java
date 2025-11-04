package edu.seg2105.client.ui;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import java.util.Scanner;

import edu.seg2105.client.backend.ChatClient;
import edu.seg2105.client.common.*;

/**
 * This class constructs the UI for a chat client.  It implements the
 * chat interface in order to activate the display() method.
 * Warning: Some of the code here is cloned in ServerConsole 
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge  
 * @author Dr Robert Lagani&egrave;re
 */
public class ClientConsole implements ChatIF 
{
  //Class variables *************************************************
  
  /**
   * The default port to connect on.
   */
  final public static int DEFAULT_PORT = 5555;
  
  //Instance variables **********************************************
  
  /**
   * The instance of the client that created this ConsoleChat.
   */
  ChatClient client;
  
  
  
  /**
   * Scanner to read from the console
   */
  Scanner fromConsole; 

  
  //Constructors ****************************************************

  /**
   * Constructs an instance of the ClientConsole UI.
   *
   * @param host The host to connect to.
   * @param port The port to connect on.
   */
  public ClientConsole(String host, int port) 
  {
	fromConsole = new Scanner(System.in); 
//	System.out.println(host);
//	System.out.println(port);
//	System.out.println();
	  
    try 
    {
      client= new ChatClient(host, port, this);
      
      
    } 
    catch(IOException exception) 
    {
    	whenDisconnected(host,port);
//    	System.out.println("Not Connected to Server (host: '"+host+"', port: "+String.valueOf(port)+")");
//    	String sethost = "sethost";
//    	String setport = "setport";
//    	String tmp_host = host;
//    	int tmp_port = port;
//    	while(!Connected())
//    	{
//	    	String message = fromConsole.nextLine();
//	    	String cmd = message.substring(1);
////	    	System.out.println(cmd);
//	    	
//	    	if(cmd.length()>sethost.length()&&(cmd.substring(0,sethost.length())).equals(sethost))
//	    	{
//	    		System.out.println("setting host...");
//	    		tmp_host = cmd.substring(sethost.length()+1);
//	    		System.out.println("host updated to "+cmd.substring(sethost.length()+1));
//	    		System.out.println();
//	    	}
//	    	else if(cmd.length()>setport.length()&&(cmd.substring(0,setport.length())).equals(setport))
//	    	{
//	    		System.out.println("setting port...");
//	    		tmp_port = Integer.parseInt(cmd.substring(setport.length()+1));
//	    		System.out.println("port updated to "+cmd.substring(setport.length()+1));
//	    		System.out.println();
//	    	}
//	    	else if(cmd.equals("login"))
//	    	{
//	    		try {
//	    			client = new ChatClient(tmp_host,tmp_port,this);
//	    		}
//	    		catch(Exception e)
//	    		{
////	    			System.out.println(tmp_host);
////	    			System.out.println(tmp_port);
////	    			System.out.println(e);
//	    			System.out.println("Could not connect\n");
//	    		}
//	    	}
//	    	else if(cmd.equals("gethost"))
//	    	{
//	    		System.out.println(tmp_host);
//	    	}
//	    	else if(cmd.equals("getport"))
//	    	{
//	    		System.out.println(String.valueOf(tmp_port));
//	
//	    	}
//	    	else
//	    	{
//	    		System.out.println("invalid command in this context");
//	    	}
//    	}
//      System.out.println("Error: Can't setup connection!"
//                + " Terminating client.");
//      System.exit(1);
    }
    
    // Create scanner object to read from console
    
    
  }
  
  public void whenDisconnected(String host, int port)
  {
	System.out.println("Not Connected to Server (host: '"+host+"', port: "+String.valueOf(port)+")");
  	String sethost = "sethost";
  	String setport = "setport";
  	String tmp_host = host;
  	int tmp_port = port;
  	while(!Connected())
  	{
	    	String message = fromConsole.nextLine();
	    	String cmd = message.substring(1);
//	    	System.out.println(cmd);
	    	
	    	if(cmd.length()>sethost.length()&&(cmd.substring(0,sethost.length())).equals(sethost))
	    	{
	    		System.out.println("setting host...");
	    		tmp_host = cmd.substring(sethost.length()+1);
	    		System.out.println("host updated to "+cmd.substring(sethost.length()+1));
	    		System.out.println();
	    	}
	    	else if(cmd.length()>setport.length()&&(cmd.substring(0,setport.length())).equals(setport))
	    	{
	    		System.out.println("setting port...");
	    		tmp_port = Integer.parseInt(cmd.substring(setport.length()+1));
	    		System.out.println("port updated to "+cmd.substring(setport.length()+1));
	    		System.out.println();
	    	}
	    	else if(cmd.equals("login"))
	    	{
	    		try {
	    			client = new ChatClient(tmp_host,tmp_port,this);
	    		}
	    		catch(Exception e)
	    		{
//	    			System.out.println(tmp_host);
//	    			System.out.println(tmp_port);
//	    			System.out.println(e);
	    			System.out.println("Could not connect\n");
	    		}
	    	}
	    	else if(cmd.equals("gethost"))
	    	{
	    		System.out.println(tmp_host);
	    	}
	    	else if(cmd.equals("getport"))
	    	{
	    		System.out.println(String.valueOf(tmp_port));
	
	    	}
	    	else
	    	{
	    		System.out.println("invalid command in this context");
	    	}
  	}
  }
  

  
  //Instance methods ************************************************
  
  /**
   * This method waits for input from the console.  Once it is 
   * received, it sends it to the client's message handler.
   */
  public void accept() 
  {
    try
    {

      String message;

      while (Connected()) 
      {
        message = fromConsole.nextLine();
        try {
            client.handleMessageFromClientUI(message);
        }
        catch(Exception e) {
        	System.out.println("Could not send message");
        }
        
      }
    } 
    catch (Exception ex) 
    {
    	

      System.out.println
        ("Unexpected error while reading from console!");
    }
  }
  
  public boolean getTerminate()
  {
	  if(client!=null)return client.getTerminate();
	  return false;
  }
  
  public boolean Connected()

  {
//	  if(client!=null&&client.getDisconnected())
	  return client!=null;
  }
  /**
   * This method overrides the method in the ChatIF interface.  It
   * displays a message onto the screen.
   *
   * @param message The string to be displayed.
   */
  public void display(String message) 
  {
    System.out.println("> " + message);
  }

//  public void set
  
  //Class methods ***************************************************
  
  /**
   * This method is responsible for the creation of the Client UI.
   *
   * @param args[0] The host to connect to.
   */
  public static void main(String[] args) 
  {
    String host = "";
    int port = DEFAULT_PORT;

    try
    {
      host = args[0];
    }
    catch(ArrayIndexOutOfBoundsException e)
    {
      host = "localhost";
    }
    try
    {
    	port = Integer.valueOf(args[1]);
    }
    catch(Exception e)
    {
//    	System.out.println(e);
    }
    
    while(true) {
	    ClientConsole chat= new ClientConsole(host, port);
	    
	    chat.accept();  //Wait for console data
	    if(chat.getTerminate()) break;
    }
    
  }
}
//End of ConsoleChat class
