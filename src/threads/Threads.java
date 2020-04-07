
package threads;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class EchoClientHandler extends Thread {
    private Socket s;
    
    public EchoClientHandler(Socket s){
        this.s=s;
    }
    
    public void run() {
        try {
           System.out.println("Connection Established");
           
           InputStreamReader isr = new InputStreamReader(s.getInputStream());
           BufferedReader br = new BufferedReader(isr);
           
           OutputStreamWriter osw = new OutputStreamWriter(s.getOutputStream());
           PrintWriter pw = new PrintWriter(osw);
        
           while(true) {
               String str = br.readLine();
               pw.println("Echo: "+str);
               pw.flush();
               if(str.equalsIgnoreCase("bye"))
                   break;
           }
           pw.close();
           br.close();
           s.close();
        } 
        catch(Exception ex){
            System.out.println(ex);
        }
    }
}

public class Threads {

   
    public static void main(String[] args)  
    {
         try {
           ServerSocket ss = new ServerSocket(4800);
           while(true){
'            Socket s = ss.accept();
            EchoClientHandler ech = new EchoClientHandler(s);
            ech.start();
           }
        }    
        catch(Exception ex) {
            System.out.println(ex);
        }
    }
    }
    

