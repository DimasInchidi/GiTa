
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
    String UserLogin;
    Form frMasuk, frDaftar, frAgenda, frTambah, frCari = new Form("");
    private String ServerURL = "http://127.0.0.1/GiTaServer/";
    private Thread Login, Daftar, Update, Beranda, Pemberitahuan, Follow, Favorit, Cari;
    private String[] phpURL = {"Login.php?", "Daftar.php?", "LihatAgenda.php?", "Beranda.php?", "Pemberitahuan.php?", "Follow.php?", "Favorit.php?", "Cari.php?"};
    private StringBuffer pesan = new StringBuffer();
    private Display display;
    Masuk masuK;
    Agenda agendA;
    Daftar daftaR;
    Cari carI;
    Tambah tambaH;
    Thread[] trit = {Login, Daftar, Update, Beranda, Pemberitahuan, Follow, Favorit, Cari};

    public GiTa() {
        display = Display.getDisplay(this);
        agendA = new Agenda(this);
        masuK = new Masuk(this);
        daftaR = new Daftar(this);
        carI = new Cari(this);
        tambaH = new Tambah(this);
        frMasuk = masuK.masuk;
        frDaftar = daftaR.daftar;
        frCari = carI.cari;
        frTambah = tambaH.tambah;
        frAgenda = agendA.agenda;
        this.trit[tritNo] = new Thread(new Runnable() {
            private volatile boolean isRunning = true;

            public void run() {
                int i =0;
                int wew = -1;
                while (isRunning && i<5 ) {
                    try {
                        String url = ServerURL + phpURL[tritNo];
                        HttpConnection httpConn = null;
                        InputStream is = null;
                        OutputStream os = null;
    pesan = new StringBuffer();
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
                            System.err.println(url+POST);

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
                                try {
                                    UserLogin = new String(pesan.toString().substring(0, pesan.toString().indexOf("<br>")));
                                    if (pesan != null || !pesan.toString().equals("") || UserLogin != null || !UserLogin.equals("")) {
                                        berhasilLogin();
                                    }
                                } catch (NullPointerException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 1:
                                UserLogin = pesan.toString().substring(0, pesan.toString().indexOf("<br>"));
                                if (pesan != null || !pesan.toString().equals("")) {
                                    berhasilLogin();
                                }
                                break;
                            case 2:
                                try{
                                String dl = pesan.toString();
                                String Pesan = "";
                                while (dl.length() > 5 && wew == -1) {
                                    int batas = dl.indexOf("<br>");
                                    Pesan = dl.substring(0, batas);
                                    dl = dl.substring(batas + 4, dl.length());
                                    batas = dl.indexOf("<br>");
                                    Pesan = Pesan + dl.substring(0, batas)+" ";
                                    dl = dl.substring(batas + 4, dl.length());
                                    batas = dl.indexOf("<br>");
                                    Pesan = Pesan + "\n" + dl.substring(0, batas);
                                    dl = dl.substring(batas + 4, dl.length());
                                    batas = dl.indexOf("<br>");
                                    Pesan = Pesan + " di " + dl.substring(0, batas);
                                    dl = dl.substring(batas + 4, dl.length());
                                    agendA.DataBeranda(Pesan);
                                System.out.println(Pesan);
                                }
                                wew= i;
                                
            } catch (Exception e) {
                e.printStackTrace();
            }
                                break;
                            default:
                                Display.getDisplay(gita).setCurrent(gita.masuK.masuk);
                        }
                        is.close();
                        os.close();
                        i++;
                    } catch (IOException ex) {
                    }
                    i++;
                }
                System.err.println("kata melky link starto");
                kill();
            }

            public void kill() {
                isRunning = false;
            }
        });
    }

    public void startApp() {
        Display.getDisplay(this).setCurrent(this.frMasuk);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void Exit() {
        destroyApp(false);
        notifyDestroyed();
    }

    void setPOSTnTrit(String post, int i) {
        this.POST = post;
        this.tritNo = i;
    }

    private void berhasilLogin() {
        Display.getDisplay(this).setCurrent(frAgenda);
        try {
            setPOSTnTrit("user=" + UserLogin, 2);
            this.trit[tritNo].start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void TambahUser() {
        try {
            
        trit[tritNo].start();
                if(gita.UserLogin == null){
            daftaR.daftar.append("Data yang anda masukan salah atau sudah ada");
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}