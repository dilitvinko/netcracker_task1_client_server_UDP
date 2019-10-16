package ClientUDP;

import java.io.IOException;
import java.net.*;

public class ClientUDP {

    public static void main(String[] args) {
        System.out.println("Receive data...");
        try {
            acceptFile(8040, InetAddress.getByName("238.1.1.1"), 1024);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    private static void acceptFile(int port, InetAddress address , int pacSize) {
        byte data[] = new byte[pacSize];
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
        try (MulticastSocket multicastSocket = new MulticastSocket(port)
        ) {
            multicastSocket.joinGroup(address);

            /* установка времени ожидания: если в течение 60 секунд
            не принято ни одного пакета, прием данных заканчивается */
            multicastSocket.setSoTimeout(60_000);

            while (true) {
                multicastSocket.receive(datagramPacket);
                System.out.println("(1) Time from ServerUDP.ServerUDP: " + new String(data));
            }

        } catch (SocketTimeoutException e) {
            // если время ожидания вышло
            System.err.println("Истекло время ожидания, прием данных закончен");
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
