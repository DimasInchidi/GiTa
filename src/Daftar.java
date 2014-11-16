
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

//nama file: Daftar.java

/**
 * @author Inchidi
 * Contact :
 *      Email Inchidi@yahoo.com 
 *      Twitter @DimasInchidi
 */
public class Daftar implements CommandListener {

   public Form daftar =new Form("Bergabung dengan kami!");
    private Command cKeluar, cDaftar;
    private Alert a;
    private GiTa gita;
    private TextField tfUser, tfPass, tfPass2, tfHP, tfEmail;
    private TextField[] tf = {tfUser, tfPass, tfPass2, tfHP, tfEmail};

    Daftar(GiTa gita) {
        this.gita = gita;
        cDaftar = new Command("Daftar", Command.OK, 1);
        cKeluar = new Command("Kembali", Command.CANCEL, 2);
        tfUser = new TextField("Username : ", "", 32, TextField.ANY);
        daftar.append(tfUser);
        tfPass = new TextField("Password : ", "", 55, TextField.PASSWORD);
        daftar.append(tfPass);
        tfPass2 = new TextField("Ulangi Password : ", "", 55, TextField.PASSWORD);
        daftar.append(tfPass2);
        tfHP = new TextField("Nomor HP : ", "", 15, TextField.NUMERIC);
        daftar.append(tfHP);
        tfEmail = new TextField("Email : ", "", 55, TextField.EMAILADDR);
        daftar.append(tfEmail);
        //daftar button
        daftar.addCommand(cDaftar);
        daftar.addCommand(cKeluar);
        daftar.setCommandListener(this);

    }

    public void commandAction(Command c, Displayable d) {
        String data = c.getLabel();
        if (data.equals(cKeluar.getLabel())) {
            Display.getDisplay(gita).setCurrent(gita.masuK.masuk);
        } else if (data.equals(cDaftar.getLabel())) {
            int lanjut = 1;
            for (int i = 0; i < tf.length; i++) {
                if (tf[i] == null || tf[i].getString().equals("")) {
                    lanjut = 0;
                }
            }
            if (tfPass.getString().equals(tfPass2.getString())) {
                lanjut = 0;
            }
            if (lanjut == 1) {
                gita.POST = "user="+tfUser.getString()+"&pass="+tfPass.getString()+
                        "hp="+tfHP.getString()+"&email="+tfEmail.getString();
                gita.tritNo = 1;
                gita.trit[gita.tritNo].start();
                if(gita.UserLogin == null){
            daftar.append("Data yang anda masukan salah atau sudah ada\n"
                    + "restart aplikasi untuk mencegah brute force");
        }
            } else {
                System.err.println("form belum diisi");
                a = new Alert("Kesalahan", "Terdapat form yang belum terisi dengan benar", null, AlertType.ERROR);
                a.setTimeout(Alert.FOREVER);
            }
        }
    }
}
