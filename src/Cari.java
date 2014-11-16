
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
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
    private Command cKeluar, cCari;
    private Alert a;
    private GiTa gita;
    private TextField tfCari;

    Cari(GiTa gita) {
        this.gita = gita;
        cCari = new Command("Cari", Command.OK, 1);
        cKeluar = new Command("Kembali", Command.CANCEL, 2);
        tfCari = new TextField("Keywords : ", "", 32, TextField.ANY);
        cari.append(tfCari);
        //daftar button
        cari.addCommand(cCari);
        cari.addCommand(cKeluar);
        cari.setCommandListener(this);

    }

    public void commandAction(Command c, Displayable d) {
        String data = c.getLabel();
        if (data.equals(cKeluar.getLabel())) {
            Display.getDisplay(gita).setCurrent(gita.masuK.masuk);
        } else if (data.equals(cCari.getLabel())) {
                gita.POST = "keywords="+tfCari.getString();
                gita.tritNo = 5;
                gita.trit[gita.tritNo].start();
                if(gita.UserLogin == null){
            cari.append("Sepi sekali disini...");
        }
        }
    }
}