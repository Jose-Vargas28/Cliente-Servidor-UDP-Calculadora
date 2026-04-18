package entidades;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {

    public String enviar(String host, int puerto, int op, double n1, double n2) throws Exception {

        DatagramSocket socket = new DatagramSocket();

        String mensaje = op + "," + n1 + "," + n2;
        byte[] buffer = mensaje.getBytes();

        InetAddress direccion = InetAddress.getByName(host);

        DatagramPacket paquete =
                new DatagramPacket(buffer, buffer.length, direccion, puerto);

        socket.send(paquete);

        // Recibir respuesta
        byte[] bufferR = new byte[1024];
        DatagramPacket respuesta =
                new DatagramPacket(bufferR, bufferR.length);

        socket.receive(respuesta);

        String resultado =
                new String(respuesta.getData(), 0, respuesta.getLength());

        socket.close();

        return resultado;
    }
}