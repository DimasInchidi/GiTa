
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
    String Diterima = "";
    int tritNo;  //Login, Daftar, Update, Beranda, Pemberitahuan, Follow, Favorit, Cari
    GiTa gita;
    String UserLogin = null;
    Form fr = new Form("Error");
    private String ServerURL = "http://127.0.0.1/GiTaServer/";
    private Thread Login, Daftar, Update, Beranda, Pemberitahuan, Follow, Favorit, Cari;
    private String[] phpURL = {"Login.php?", "Daftar.php?", "LihatAgenda.php?", "Beranda.php?", "Pemberitahuan.php?", "Follow.php?", "Favorit.php?", "Cari.php?"};
    private StringBuffer pesan = new StringBuffer();
    Masuk masuK;
    Agenda agendA;
    Daftar daftaR;
    Cari carI;
    Tambah tambaH;
    Thread[] trit = {Login, Daftar, Update, Beranda, Pemberitahuan, Follow, Favorit, Cari};

    public GiTa() {
        masuK = new Masuk(this);
        agendA = new Agenda(this);
        daftaR = new Daftar(this);
        carI = new Cari(this);
        tambaH = new Tambah(this);
        fr = masuK.masuk;
        this.trit[tritNo] = new Thread(new Runnable() {
            public void run() {
                String url = ServerURL + phpURL[tritNo];
                HttpConnection httpConn = null;
                InputStream is = null;
                OutputStream os = null;

                System.err.println("loaded " + tritNo);
                try {
                    httpConn = (HttpConnection) Connector.open(url);
                    httpConn.setRequestMethod(HttpConnection.POST);

                    httpConn.setRequestProperty("User-Agent",
                            "Profile/MIDP-1.0 Confirguration/CLDC-1.0");
                    httpConn.setRequestProperty("Accept_Language", "en-US");
                    httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
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
                            Display.getDisplay(gita).setCurrent(masuK.masuk);
                            System.out.println(UserLogin);
                            agendA.lihatAgenda(UserLogin);
                        }
                        break;
                    case 1:
                        UserLogin = pesan.toString().substring(0, pesan.toString().indexOf("<br>"));
                        if (pesan != null || !pesan.toString().equals("")) {
                            Display.getDisplay(gita).setCurrent(gita.agendA.agenda);
                            System.out.println(UserLogin);
                        }
                        break;
                    case 2:
                        System.err.println("loaded");
                        String dl = pesan.toString();
                        String Pesan = "";
                        while (dl.length() > 5) {
                            int batas = dl.indexOf("<br>");
                            Pesan = dl.substring(0, batas);
                            dl = dl.substring(batas + 4, dl.length());
                            batas = dl.indexOf("<br>");
                            Pesan = Pesan + dl.substring(0, batas);
                            dl = dl.substring(batas + 4, dl.length());
                            batas = dl.indexOf("<br>");
                            Pesan = Pesan + "\n" + dl.substring(0, batas);
                            dl = dl.substring(batas + 4, dl.length());
                            batas = dl.indexOf("<br>");
                            Pesan = Pesan + " di " + dl.substring(0, batas);
                            dl = dl.substring(batas + 4, dl.length());
                        }
                        System.out.println(Pesan);
                        agendA.agenda.append(Pesan);
                        break;
                    default:
                        Display.getDisplay(gita).setCurrent(gita.masuK.masuk);
                }
            }
        });
    }

    public void startApp() {

        Display.getDisplay(this).setCurrent(this.fr);
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