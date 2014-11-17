
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

//nama file: Cari.java

/**
 * @author Inchidi
 * Contact :
 *      Email Inchidi@yahoo.com 
 *      Twitter @DimasInchidi
 */
public class Cari implements CommandListener {

   public Form cari =new Form("Temukan Teman Anda");
    private Command cKeluar, cCari, cIkuti;
    private StringItem tepili;
    private Alert a;
    private GiTa gita;
    private TextField tfCari;

    Cari(GiTa gita) {
        cari =new Form("Temukan Teman Anda");
        this.gita = gita;
        cIkuti = new Command("Ikuti", Command.SCREEN, 0);
        cCari = new Command("Cari", Command.OK, 1);
        cKeluar = new Command("Kembali", Command.CANCEL, 2);
        tfCari = new TextField("Keywords : ", "", 32, TextField.ANY);
        cari.append(tfCari);
        //daftar button
        cari.addCommand(cCari);
        cari.addCommand(cIkuti);
        cari.addCommand(cKeluar);
        cari.setCommandListener(this);

    }

    public void commandAction(Command c, Displayable d) {
        String data = c.getLabel();
        if (data.equals(cKeluar.getLabel())) {
            Display.getDisplay(gita).setCurrent(gita.agendA.agenda);
        } else if (data.equals(cCari.getLabel())) {
                String s = "keyword="+tfCari.getString();
                int i = 7;
                gita.Lakukan(s, i);
        } else if (data.equals(cIkuti.getLabel())){
            String s = "ikut="+tepili.getText()+"&user="+gita.UserLogin;
                int i = 5;
                gita.Lakukan(s, i);
        }
    }

    void Tampilkan(String Pesan) {
        if(Pesan.equals("")||Pesan ==null) {
            cari.append("Sepi sekali disini...");
             }
        else {
            tepili = new StringItem("Username : ",Pesan);
            this.cari.append(tepili);
        }
    }

    void errikut() {
        cari.append("Gagal follow :(");
    }
}