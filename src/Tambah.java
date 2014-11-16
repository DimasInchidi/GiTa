
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

//nama file: Tambah.java

/**
 * @author Inchidi
 * Contact :
 *      Email Inchidi@yahoo.com 
 *      Twitter @DimasInchidi
 */
public class Tambah implements CommandListener {

    public Form tambah =new Form("Update Agenda");
    private Command cKeluar, cDaftar;
    private GiTa gita;
    private TextField tfAgenda;

    Tambah(GiTa gita) {
        this.gita = gita;
        cDaftar = new Command("Cari", Command.OK, 1);
        cKeluar = new Command("Kembali", Command.CANCEL, 2);
        tfAgenda = new TextField("Keywords : ", "", 32, TextField.ANY);
        tambah.append(tfAgenda);
        //daftar button
        tambah.addCommand(cDaftar);
        tambah.addCommand(cKeluar);
        tambah.setCommandListener(this);

    }

    public void commandAction(Command c, Displayable d) {
        String data = c.getLabel();
        if (data.equals(cKeluar.getLabel())) {
            Display.getDisplay(gita).setCurrent(gita.agendA.agenda);
        } else if (data.equals(cDaftar.getLabel())) {
                gita.POST = "user="+tfAgenda.getString();
                gita.tritNo = 3;
                gita.trit[gita.tritNo].start();
                if(gita.UserLogin == null){
            tambah.append("Data yang anda masukan salah atau sudah ada\n"
                    + "restart aplikasi untuk mencegah brute force");
        }
        }
    }
}