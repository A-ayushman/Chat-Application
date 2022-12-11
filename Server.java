import java.net.*;
import java.io.*;
class Server
{
    ServerSocket server;
    Socket socket;

    BufferedReader br;
    PrintWriter out;

    // Constructor
    public Server()
    {
        try 
        {
            server=new ServerSocket(7777);
            System.out.println("Server is resdy to except connections ");
            System.out.println("Waiting");
            socket=server.accept();

            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();

        } catch (Exception e) 
        {
           e.printStackTrace();
        }
    
    }

    public void startReading()
    {
        // thread - read and will continously give data
        Runnable r1=()->
        {

            System.out.println("Reader Started... ");

            while(true)
            {
                try
                {
                    String msg=br.readLine(); // we receive message from the Clint
                    if(msg.equals("exit"))
                    {
                        System.out.println("Client terminated the chat. ");
                        break;
                    }
                        System.out.println("Client: "+msg);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        };  
            new Thread(r1).start();
    }

    public void startWriting()
    {
        // thread - User will take data and give it to clint
        Runnable r2=()->
        {
            System.out.println("Witer started..");
            while(true)
            {
                try 
                {
                    
                    BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
                    String content=br1.readLine();
                    out.println(content);
                    out.flush();
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
        };
            new Thread(r2).start();

    }

    public static void main(String[] args) 
    {
        System.out.println("Let's Start");
        new Server();
    }
}