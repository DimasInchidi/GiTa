
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.StringItem;

//nama file: agendA.java

/**
 * @author Inchidi
 * Contact :
 *      Email Inchidi@yahoo.com 
 *      Twitter @DimasInchidi
 */

public class Agenda implements CommandListener, ItemCommandListener {

    public Form agenda =new Form("Beranda");
    private Command cKeluar, cFavorit, cCari, cAgendaBaru;
    private GiTa gita;
    private StringItem iCari;

    Agenda(GiTa gita) {
        this.gita = gita;
        cFavorit = new Command("Favorit", Command.OK, 2);
        cAgendaBaru = new Command("Agenda Baru", Command.OK, 3);
        cCari = new Command("Cari Teman", Command.OK, 4);
        cKeluar = new Command("Logout", Command.CANCEL, 2);
        //daftar button
        //agenda.addCommand(cFavorit);
        agenda.addCommand(cAgendaBaru);
        agenda.addCommand(cCari);
        agenda.addCommand(cKeluar);
        agenda.setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        String data = c.getLabel();
        if (data.equals(cKeluar.getLabel())) {
            agenda.deleteAll();
            Display.getDisplay(gita).setCurrent(gita.masuK.masuk);
        } else if (data.equals(cAgendaBaru.getLabel())) {
            Display.getDisplay(gita).setCurrent(gita.tambaH.tambah);
        } else if (data.equals(cCari.getLabel())) {
            Display.getDisplay(gita).setCurrent(gita.carI.cari);
        }
    }

    public void commandAction(Command c, Item item) {
    }

    void DataBeranda(String Pesan) {
        System.out.println("DataBeranda>>>>"+Pesan);
        if(!Pesan.trim().equals("")&&Pesan !=null) {
            this.agenda.append(Pesan);
        }
        else {
        iCari = new StringItem(null, "GiTa tidak menyenangkan tanpa teman :(", Item.BUTTON);
        iCari.setDefaultCommand(cCari);
        iCari.setItemCommandListener(this);
        this.agenda.append(iCari);
        }
    }
}
