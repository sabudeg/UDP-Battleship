package battleship;


import java.net.*;
import java.util.Scanner;

public class Client extends Game {

    static Client clientInside = new Client();


    public static void main(String[] args) throws Exception
    {
        welcome();
        gameobject.fillBoardClient();
        clientInside.clientTurn();
            }

    static void isOver(){
        boolean contains = false;
        for (char c : gameobject.boardClient) {
            if (c == 'S') {
                contains = true;
                break;
            }
        }
        if (!contains) {
            System.out.println("GAME OVER! SERVER WON.");
            System.exit(0);
        }
    }


    public void clientTurn() throws Exception{
         //DatagramSocket class is used creating UDP socket
        DatagramSocket dsclient = new DatagramSocket(777);
        dsclient.setBroadcast(true);
        dsclient.setSoTimeout(15000);
        InetAddress ia = InetAddress.getByName("255.255.255.255");
        //InetAddress class is used to send broadcast message

        try{
            while(true) {
                System.out.print("Hit ServerBoard: ");
                Scanner input = new Scanner(System.in);
                String y = input.next();
                    //converting this string in to byte array with help of getBytes() method
                byte[] b = y.getBytes();
                    //creating DatagramPacket of byte array
                DatagramPacket dp = new DatagramPacket(b, b.length, ia, 666);
                    // send to the server socket
                dsclient.send(dp);


                //b1 is a byte array is created because its use in DatagramPacket
                byte[] b1 = new byte[1024];
                //now the DatagramPacket object is created with byte array
                DatagramPacket packetServer = new DatagramPacket(b1, b1.length);
                //recieve() method of datagram socket objectis used to recive data from put in the byte array
                dsclient.receive(packetServer);
                String st1 = new String(packetServer.getData(),packetServer.getOffset(),packetServer.getLength());
                int konum =  Integer.parseInt(st1.trim());
                //ALDIGI PAKET ILE CLIENTBOARD
                hitClientsBoard(konum);
            }
        }
        catch(SocketTimeoutException ste){
            System.out.println("GAME OVER! YOU WON.");
        }
        catch (SocketException se) {System.out.println(se);}
        catch (Exception e) {System.out.println(e); }
    }
}
