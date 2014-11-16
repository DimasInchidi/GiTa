
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author Inchidi
 */
public class GiTa extends MIDlet {

    String POST;
    String Diterima = "Inchidi%MainHP Jam 8 Pagi Di Rumah%Jeno%blabla%Matz%dusdus";
    int tritNo;  //Login, Daftar, Update, Beranda, Pemberitahuan, Follow, Favorit, Cari
    GiTa gita;
    Display display;
    String UserLogin = null;
    private String ServerURL = "http://127.0.0.1/GiTaServer/";
    private Thread Login, Daftar, Update, Beranda, Pemberitahuan, Follow, Favorit, Cari;
    private String[] phpURL = {"Login.php?", "Daftar.php?", "Update.php?", "Beranda.php?", "Pemberitahuan.php?", "Follow.php?", "Favorit.php?", "Cari.php?"};
    private StringBuffer pesan = new StringBuffer();
    private Masuk tampilan;
    private Agenda agenda;
    Thread[] trit = {Login, Daftar, Update, Beranda, Pemberitahuan, Follow, Favorit, Cari};

    public GiTa() {
        display = Display.getDisplay(this);
        this.trit[tritNo] = new Thread(new Runnable() {
            public void run() {
                String url = ServerURL + phpURL[tritNo];
                HttpConnection httpConn = null;
                InputStream is = null;
                OutputStream os = null;
                try {
                    httpConn = (HttpConnection) Connector.open(url);
                    httpConn.setRequestMethod(HttpConnection.POST);

                    httpConn.setRequestProperty("User-Agent",
                            "Profile/MIDP-1.0 Confirguration/CLDC-1.0");
                    httpConn.setRequestProperty("Accept_Language", "en-US");
                    httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    //getConnectionInformation(httpConn);
                    os = httpConn.openOutputStream();
                    String params;
                    System.err.println(POST);
                    params = POST;
                    os.write(params.getBytes());
                    os.flush();
                    is = httpConn.openDataInputStream();
                    int chr;
                    while ((chr = is.read()) != -1) {
                        pesan.append((char) chr);
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (os != null) {
                            os.close();
                        }
                        if (httpConn != null) {
                            httpConn.close();
                        }
                    } catch (IOException e) {
                    }
                }
                switch (tritNo) {
                    case 0:
                        UserLogin = pesan.toString().substring(0, pesan.toString().indexOf("<br>"));
                        
                        if (pesan != null || !pesan.toString().equals("") || UserLogin != null || !UserLogin.equals("")) {
                            agenda = new Agenda(gita, display);
                            display.setCurrent(agenda);
                            System.out.println(UserLogin);
                        }
                        break;
                    case 1:
                        UserLogin = pesan.toString().substring(0, pesan.toString().indexOf("<br>"));
                        if (pesan != null || !pesan.toString().equals("")) {
                            agenda = new Agenda(gita, display);
                            display.setCurrent(agenda);
                            System.out.println(UserLogin);
                        }
                        break;
                    case 2:
                        System.err.println("loaded");
                        String dl = pesan.toString();
                        String Pesan ="";
                        while(dl.length()>5) {
                            int batas = dl.indexOf("<br>");
                            Pesan = dl.substring(0, batas);
                            dl = dl.substring(batas + 4, dl.length());
                            batas = dl.indexOf("<br>");
                            Pesan = Pesan + dl.substring(0, batas);
                            dl = dl.substring(batas + 4, dl.length());
                            batas = dl.indexOf("<br>");
                            Pesan = Pesan+"\n"+dl.substring(0, batas);
                            dl = dl.substring(batas + 4, dl.length());
                            batas = dl.indexOf("<br>");
                            Pesan = Pesan +" di "+ dl.substring(0, batas);
                            dl = dl.substring(batas + 4, dl.length());
                        }
                            System.out.println(Pesan);
                            agenda.append(Pesan);
                        break;
                    default:
                        tampilan = new Masuk(gita, display);
                        display.setCurrent(tampilan);
                }
            }
        });
    }

    public void startApp() {
        tampilan = new Masuk(this, display);
        display.setCurrent(tampilan);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void Exit() {
        destroyApp(false);
        notifyDestroyed();
    }
}