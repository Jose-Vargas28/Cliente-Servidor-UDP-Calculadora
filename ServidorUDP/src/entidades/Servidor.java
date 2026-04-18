package entidades;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Servidor {
    private Modelo modelo = new Modelo();

    public void operar(int puerto) throws Exception {
        DatagramSocket socket = new DatagramSocket(puerto);
        System.out.println("Servidor corriendo en el puerto " + puerto);

        while (true) {

            byte[] bufferE = new byte[1024];
            DatagramPacket entrada = new DatagramPacket(bufferE, bufferE.length);
            socket.receive(entrada);

            String recibido = new String(entrada.getData(), 0, entrada.getLength());
            String[] partes = recibido.trim().split(",");

            String respuesta;

            try {
                int op = Integer.parseInt(partes[0]);
                double n1 = Double.parseDouble(partes[1]);
                double n2 = Double.parseDouble(partes[2]);

                switch (op) {
                    case 1:
                        respuesta = String.valueOf(modelo.sumar(n1, n2));
                        break;
                    case 2:
                        respuesta = String.valueOf(modelo.restar(n1, n2));
                        break;
                    case 3:
                        respuesta = String.valueOf(modelo.multiplicar(n1, n2));
                        break;
                    case 4:
                        if (n2 != 0) {
                            respuesta = String.valueOf(modelo.dividir(n1, n2));
                        } else {
                            respuesta = "ERROR: No existe división para cero";
                        }
                        break;
                    default:
                        respuesta = "ERROR: Operación no válida";
                }

            } catch (Exception e) {
                respuesta = "ERROR: Ingrese solo números";
            }

            byte[] bufferS = respuesta.getBytes();
            DatagramPacket salida = new DatagramPacket(
                    bufferS,
                    bufferS.length,
                    entrada.getAddress(),
                    entrada.getPort()
            );

            socket.send(salida);
        }
    }
}