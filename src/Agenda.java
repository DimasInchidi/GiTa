//nama file: Agenda.java

import javax.microedition.lcdui.*;

/**
 * @author Inchidi Contact : Email Inchidi@yahoo.com Twitter
 * @DimasInchidi
 */

public class Agenda extends Form implements CommandListener {

    private Display display;
    private Command cKeluar, cDaftar,c1;
    private GiTa gita;
    
    Agenda(GiTa gita, Display display) {
        super("Agenda");
        this.display = display;
        this.gita = gita;
        lihatAgenda();
        c1 = new Command("GiTa tidak menyenangkan tanpa teman :(", Command.SCREEN, 2);
        cDaftar = new Command("Daftar", Command.OK, 1);
        cKeluar = new Command("Logout", Command.CANCEL, 2);
        
        this.append("cek");
        
        //daftar button
        this.addCommand(cDaftar);
        this.addCommand(cKeluar);
        this.setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
                String data = c.getLabel();
        if (data.equals(cKeluar.getLabel())) {
            Masuk tampilan = new Masuk(gita, display);
            display.setCurrent(tampilan);
        } else if (data.equals(cDaftar.getLabel())) {
            
        }
    }

    private void lihatAgenda() {
                gita.POST = "user="+gita.UserLogin;
                gita.tritNo = 2;
                gita.trit[gita.tritNo].start();
                
    }
}
