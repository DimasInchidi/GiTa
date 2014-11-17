
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
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
    private Command cKeluar, cUpdate;
    private GiTa gita;
    private TextField tfKegiatan,tfTempat;
    private DateField tfWaktu;

    Tambah(GiTa gita) {
        this.gita = gita;
        cUpdate = new Command("Update", Command.OK, 1);
        cKeluar = new Command("Kembali", Command.CANCEL, 2);
        tfKegiatan = new TextField("Kegiatan : ", "", 55, TextField.ANY);
        tambah.append(tfKegiatan);
                tfWaktu = new DateField("Waktu",DateField.DATE_TIME);
        tambah.append(tfWaktu);
                        tfTempat = new TextField("Tempat : ", "", 32, TextField.ANY);
        tambah.append(tfTempat);

        //daftar button
        tambah.addCommand(cUpdate);
        tambah.addCommand(cKeluar);
        tambah.setCommandListener(this);

    }

    public void commandAction(Command c, Displayable d) {
        String data = c.getLabel();
        if (data.equals(cKeluar.getLabel())) {
            Display.getDisplay(gita).setCurrent(gita.agendA.agenda);
        } else if (data.equals(cUpdate.getLabel())) {
            // date belum slese
                String date = "2014-11-19 07:26:32"; //tfWaktu.getDate().toString();
                String bakpau = "user="+gita.UserLogin+"&keterangan="+tfKegiatan.getString()+"&tgl="+date.replace (' ','+')+"&lokasi="+tfTempat.getString();
                gita.setPOSTnTrit(bakpau, 3);
                gita.treat().start();
        }
    }

    void ErrTambah() {
        tambah.append("Gagal update :(");
    }
}