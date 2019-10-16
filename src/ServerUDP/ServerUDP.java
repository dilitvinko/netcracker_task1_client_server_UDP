package ServerUDP;

import java.io.IOException;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ServerUDP {

    private static final DateFormat TIME = new SimpleDateFormat("HH:mm:ss:SSS");

    public static void main(String[] args) {

        try (DatagramSocket datagramSocket = new DatagramSocket()
        ) {
            byte[] data = new byte[1024];
            DatagramPacket datagramPacket;

            while (true) {
                data = TIME.format(Calendar.getInstance().getTime()).getBytes();
                datagramPacket = new DatagramPacket(data, data.length, InetAddress.getByName("238.1.1.1"), 8040);
                datagramSocket.send(datagramPacket);
                System.out.println("Данные отправленны успешно");
                Thread.sleep(1000);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
