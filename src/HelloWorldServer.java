
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HelloWorldServer {
    public static void main(String[] args) {
        int port = 1234;
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Server: Esperant connexions...");
            Socket s = ss.accept();
            System.out.println("Server: Connexió establerta");

            InputStream is = s.getInputStream();
            DataInputStream dis = new DataInputStream(is);

            while (true) {
                try {
                    String message = dis.readUTF();
                    System.out.println("Server: Missatge rebut del client: " + message);
                } catch (IOException e) {
                    System.out.println("Server: Connexió tancada pel client.");
                    break;  // Salir del bucle si la conexión se cierra
                }
            }

            dis.close();
            s.close();
            ss.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}