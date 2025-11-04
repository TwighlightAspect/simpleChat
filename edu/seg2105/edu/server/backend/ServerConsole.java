package edu.seg2105.edu.server.backend;

import java.io.IOException;
import java.util.Scanner;

import edu.seg2105.client.backend.ChatClient;
import edu.seg2105.client.common.ChatIF;

public class ServerConsole implements ChatIF {
	EchoServer server;
	Scanner fromConsole;
	int port;
	
	public ServerConsole(int port)
	{
		this.port =port;
		fromConsole = new Scanner(System.in); 
//		System.out.println(host);
//		System.out.println(port);
//		System.out.println();
		  
	    
    	System.out.println("Server OFFLINE\nport: "+String.valueOf(port));
//    	String sethost = "sethost";
    	String setport = "setport";
    	int tmp_port = port;
    	while(!Connected())
    	{
    		display(processInput());
//	    	String message = fromConsole.nextLine();
//	    	String cmd = message.substring(1);
////		    	System.out.println(cmd);
//	    	
////		    	if(cmd.length()>sethost.length()&&(cmd.substring(0,sethost.length())).equals(sethost))
////		    	{
////		    		System.out.println("setting host...");
//////		    		tmp_host = cmd.substring(sethost.length()+1);
////		    		System.out.println("host updated to "+cmd.substring(sethost.length()+1));
////		    		System.out.println();
////		    	}
//	    	if(cmd.length()>setport.length()&&(cmd.substring(0,setport.length())).equals(setport))
//	    	{
//	    		System.out.println("setting port...");
//	    		tmp_port = Integer.parseInt(cmd.substring(setport.length()+1));
//	    		System.out.println("port updated to "+cmd.substring(setport.length()+1));
//	    		System.out.println();
//	    	}
//	    	else if(cmd.equals("start")&&server==null)
//	    	{
//	    		try {
//	    			server = new EchoServer(tmp_port);
//	    		}
//	    		catch(Exception e)
//	    		{
////		    			System.out.println(tmp_host);
////		    			System.out.println(tmp_port);
////		    			System.out.println(e);
//	    			System.out.println("Could not connect\n");
//	    		}
//	    	}
////		    	else if(cmd.equals("gethost"))
////		    	{
////		    		System.out.println(tmp_host);
////		    	}
//	    	else if(cmd.equals("getport"))
//	    	{
//	    		System.out.println(String.valueOf(tmp_port));
//	
//	    	}
//	    	else
//	    	{
//	    		System.out.println("invalid command in this context");
//	    	}
//	      System.out.println("Error: Can't setup connection!"
//	                + " Terminating client.");
//	      System.exit(1);
	    }
	    
	    // Create scanner object to read from console
	    
	    
	  }
	public String processInput()
	{
		String setport = "setport";
    	int tmp_port = port;
		String message = fromConsole.nextLine();
    	String cmd = message.substring(1);
//	    	System.out.println(cmd);
    	
//	    	if(cmd.length()>sethost.length()&&(cmd.substring(0,sethost.length())).equals(sethost))
//	    	{
//	    		System.out.println("setting host...");
////	    		tmp_host = cmd.substring(sethost.length()+1);
//	    		System.out.println("host updated to "+cmd.substring(sethost.length()+1));
//	    		System.out.println();
//	    	}
    	if(cmd.length()>setport.length()&&(cmd.substring(0,setport.length())).equals(setport))
    	{
    		System.out.println("setting port...");
    		tmp_port = Integer.parseInt(cmd.substring(setport.length()+1));
    		System.out.println("port updated to "+cmd.substring(setport.length()+1));
    		System.out.println();
    	}
    	else if(cmd.equals("start")&&server==null)
    	{
    		try {
    			server = new EchoServer(tmp_port);
    		}
    		catch(Exception e)
    		{
//	    			System.out.println(tmp_host);
//	    			System.out.println(tmp_port);
//	    			System.out.println(e);
    			System.out.println("Could not connect\n");
    		}
    	}
//	    	else if(cmd.equals("gethost"))
//	    	{
//	    		System.out.println(tmp_host);
//	    	}
    	else if(cmd.equals("getport"))
    	{
    		System.out.println("Port:"+String.valueOf(tmp_port));

    	}
    	else
    	{
    		System.out.println("invalid command in this context");
    	}
    	return message;
//      System.out.println("Error: Can't setup connection!"
//                + " Terminating client.");
//      System.exit(1);
    
	}

	public boolean Connected()
	{
		return server!=null;
	}
	
	@Override
	public void display(String message) {
		// TODO Auto-generated method stub
		String msg = "SERVER MSG>"+message;
		if(Connected()) 
		{
//			System.out.println("sending msg");
			server.sendToAllClients(msg);
			server.display(msg);
		}
//		System.out.println(msg);
	}
	public void accept()
	{
		try
	    {

	      String message;
	      

	      while (Connected()) 
	      {
	        message = processInput();
	        
	        display(message);
	      }
	    } 
	    catch (Exception ex) 
	    {
	    	

	      System.out.println
	        ("Unexpected error while reading from console!");
	    }
	  }
	
	public EchoServer getServer()
	{
		return server;
	}

}
