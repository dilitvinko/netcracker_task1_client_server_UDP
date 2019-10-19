package ClientUDP;

import java.io.IOException;
import java.net.*;

public class ClientUDP {

    public static void main(String[] args) {
        System.out.println("Receive data...");
        try {
            acceptFile(InetAddress.getByName("238.1.1.1"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    private static void acceptFile(InetAddress address) {
        byte[] data = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
        try (MulticastSocket multicastSocket = new MulticastSocket(8040)
        ) {
            multicastSocket.joinGroup(address);


            multicastSocket.setSoTimeout(60_000);

            //noinspection InfiniteLoopStatement
            while (true) {
                multicastSocket.receive(datagramPacket);
                System.out.println("(1) Time from ServerUDP.ServerUDP: " + new String(data));
            }

        } catch (SocketTimeoutException e) {
            // если время ожидания вышло
            System.err.println("Истекло время ожидания, прием данных закончен");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
