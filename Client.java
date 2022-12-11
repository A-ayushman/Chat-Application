import java.net.*;
import java.io.*;
public class Client 
{
    Socket socket;

    BufferedReader br;
    PrintWriter out;

    public Client()
    {
        try 
        {
            System.out.println("Sending request to Server");
            socket=new Socket("192.168.1.37",7777);
            System.out.println("Connection Done");

            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();
        } 
        catch (Exception e) 
        {
            // To handle exception
        }
    }
    public void startReading()
    {
        // thread - read and will continously give data from client end
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
                        System.out.println("Server terminated the chat. ");
                        break;
                    }
                        System.out.println("Server: "+msg);
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
        public static void main(String[] args) {
        System.out.println("this is client");
        new Client();
    }
}