import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ConcurrentServer {
    private static final int port = 1234;

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(port);

            while (true) {
                Socket s = ss.accept();
                System.err.println("Connexió acceptada.");
                Thread t = new Thread(new Handler(s));
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        private static class Handler implements Runnable {
            private Socket socket;

            public Handler(Socket socket) {
                this.socket = socket;
            }
        }

        public void run () {
            try {
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                String str = "";

                while (!str.equals("FI")) {
                    str = dis.readUTF();
                    System.out.println("He rebut el missatge \"" + str + "\"");
                    str = str.toUpperCase();
                    dos.writeUTF(str);
                    dos.flush();
                }
                dis.close();
                dos.close();
                s.close();
                System.out.println("Connexió tancada.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}