

import java.io.IOException;
import java.util.Scanner;

public class WriteThreadRestaurantClient implements Runnable {

    private Thread thr;
    private SocketWrapper socketWrapper;
    String []clientname=new String[3];

    public WriteThreadRestaurantClient(SocketWrapper socketWrapper, String []clientname) {
        this.socketWrapper = socketWrapper;
        this.clientname = clientname;
        this.thr = new Thread(this);
        thr.start();
        
    }

    public void run() {
        try {
            // Scanner input = new Scanner(System.in);
            while (true) {
                // String from = name;
                // System.out.print("Enter name of the client to send: ");
                // String to = input.nextLine();
                // System.out.print("Enter the message: ");
                // String text = input.nextLine();
                // Message message = new Message();
                // message.setFrom(from);
                // message.setTo(to);
                // message.setText(text);
                socketWrapper.write(clientname);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                socketWrapper.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

