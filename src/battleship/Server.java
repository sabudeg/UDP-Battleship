package battleship;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Server extends Game {

    static Server serverInside = new Server();

    public static void main(String[] args) throws Exception
    {

        welcome();
        gameobject.fillBoardServer();
        serverInside.serverTurn();
     }

    static void isOver(){
        boolean contains = false;
        for (char c : gameobject.boardServer) {
            if (c == 'S') {
                contains = true;
                break;
            }
        }

        if (!contains) {
            System.out.println("GAME OVER! CLIENT WON.");
            System.exit(0);
        }
    }

    public void serverTurn() throws Exception{
        //creating a UDP socket(datagramsocket) class object where 666 is port number
        DatagramSocket dsServer = new DatagramSocket(666);
        dsServer.setSoTimeout(15000);
        try{
            while(true) {
                //b1 is a byte array is created because its use in DatagramPacket
                byte[] b1 = new byte[1024];
                //now the DatagramPacket object is created with byte array
                DatagramPacket in = new DatagramPacket(b1, b1.length);
                System.out.println("Waiting for client to hit...");
                //recieve() method of datagram socket object is used to recive data from put in the byte array
                dsServer.receive(in);
                String st = new String(in.getData(),in.getOffset(),in.getLength());
                int konum =  Integer.parseInt(st.trim());
                //ALDIGI PAKET ILE SERVER VURULACAK
                hitServersBoard(konum);

           //    checkOpponents();
           //     printOpponent(gameobject.serversOpponent);


                System.out.print("Hit ClientBoard:  ");
                Scanner input = new Scanner(System.in);
                String y = input.next();
                byte[] b = y.getBytes();
                DatagramPacket out = new DatagramPacket(b, b.length, in.getAddress(), in.getPort());
                // send method of DatagramPacket is used to sending the data to client socket
                dsServer.send(out);
            }
        }
        //BindException, ConnectException, NoRouteToHostException
        catch(SocketTimeoutException ste){
            System.out.println("GAME OVER! YOU WON.");
        }
        catch(SocketException se){System.out.println(se);}
        catch(Exception e){System.out.println(e);}

        }
    }