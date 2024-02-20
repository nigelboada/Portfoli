import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class HelloWorldClient {
    public static void main(String[] args) {
        System.out.println("Client: Inicio intento de conexión");
        //String host = "172.16.109.174";  //Pol
        //String host = "127.0.0.1";
        String host = "172.16.119.90"; //Hugo eduroam
        /*String host = "192.168.43.95";*/
        //String host = "10.100.1.26"; //profe
        //String host = "185.219.139.131"; //Internet CASA profe
        //String host = "192.168.1.154"; //Ordinador CASA profe
        //String host = "193.144.12.183"; //Ordinador Classe profe
        //String host = "172.16.109.174";  //Pol
        // String host = "127.0.0.1";
        int port = 1234;

        try {
            Socket s = new Socket(host, port);
            OutputStream os = s.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Ingrese el mensaje a enviar (o escriba 'exit' para salir): ");
                String mensaje = scanner.nextLine();

                if (mensaje.equalsIgnoreCase("exit")) {
                    break; // Salir del bucle si el usuario ingresa 'exit'
                }

                dos.writeUTF(mensaje);
                dos.flush();
                InputStream is = s.getInputStream();
                DataInputStream dis = new DataInputStream(is);
                String result = dis.readUTF();
                System.out.println(result);
            }

            // Cerrar recursos
            dos.close();
            s.close();
            scanner.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}