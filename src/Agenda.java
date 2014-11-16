
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
    private Command cKeluar, cFavorit, cCari, cAgendaBaru, c1;
    private GiTa gita;
    private StringItem iCari;

    Agenda(GiTa gita) {
        this.gita = gita;
        c1 = new Command("Cari Teman", Command.SCREEN, 1);
        cFavorit = new Command("Favorit", Command.OK, 2);
        cAgendaBaru = new Command("Agenda Baru", Command.OK, 3);
        cCari = new Command("Cari Teman", Command.OK, 4);
        cKeluar = new Command("Logout", Command.CANCEL, 2);
        iCari = new StringItem(null, "GiTa tidak menyenangkan tanpa teman :(", Item.BUTTON);
        iCari.setDefaultCommand(cCari);
        iCari.setItemCommandListener(this);
        agenda.append(iCari);
        //daftar button
        agenda.addCommand(cKeluar);
        agenda.setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        String data = c.getLabel();
        if (data.equals(cKeluar.getLabel())) {
            Display.getDisplay(gita).setCurrent(gita.masuK.masuk);
        } else if (data.equals(cFavorit.getLabel())) {
        } else if (data.equals(cKeluar.getLabel())) {
            Display.getDisplay(gita).setCurrent(gita.carI.cari);
        }
    }

    public void lihatAgenda(String User) {
        System.out.println("___>" + User + "<___");
        try {
            System.out.println("___>" +gita.POST);
            gita.POST = "user=" + User;
            gita.tritNo = 2;
            gita.trit[gita.tritNo].start();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void commandAction(Command c, Item item) {
    }
}
