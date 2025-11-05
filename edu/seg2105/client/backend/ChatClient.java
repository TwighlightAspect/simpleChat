// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package edu.seg2105.client.backend;

import ocsf.client.*;

import java.io.*;

import edu.seg2105.client.common.*;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 */
public class ChatClient extends AbstractClient
{
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI;
  boolean terminate = false;
  String login_id;

  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
  
  public ChatClient(String login_id,String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;
    openConnection();
  }

  
  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public void handleMessageFromServer(Object msg) 
  {
	  
	  if(msg instanceof Integer &&((int)msg)==-1)
	  {
		  terminate = true;
		  quit();
		  System.exit(0);
		  return;
	  }
	  else if(msg instanceof Integer && ((int)msg)==0)
	  {
		  try {
			  closeConnection();
		  }
		  catch(Exception e)
		  {
			  
		  }
	  }
	  
	  
//		  clientUI.display(e.toString());
	  clientUI.display(msg.toString());
    
    
    
  }

  /**
   * This method handles all data coming from the UI            
   *
   * @param message The message from the UI.    
   */
  public void handleMessageFromClientUI(String message) throws IOException
  {
//    try
//    {
	  sendToServer(message);
//    }
//    catch(IOException e)
//    {
//    	
//    	      clientUI.display
//        ("Could not send message to server.  Terminating client.");
//      quit();
//    }
  }
  
  
  
  /**
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
  public boolean getTerminate()
  {
	  return terminate;
  }
}
//End of ChatClient class
