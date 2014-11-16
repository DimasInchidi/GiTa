//nama file: Daftar.java

import javax.microedition.lcdui.*;

/**
 * @author Inchidi Contact : Email Inchidi@yahoo.com Twitter
 * @DimasInchidi
 */
public class Daftar extends Form implements CommandListener {

    private Display display;
    private Command cKeluar, cDaftar;
    private Alert a;
    private GiTa gita;
    private TextField tfUser, tfPass, tfPass2, tfHP, tfEmail;
    private TextField[] tf = {tfUser, tfPass, tfPass2, tfHP, tfEmail};

    Daftar(GiTa gita, Display display) {
        super("Bergabung dengan kami!");
        this.display = display;
        this.gita = gita;
        cDaftar = new Command("Daftar", Command.OK, 1);
        cKeluar = new Command("Kembali", Command.CANCEL, 2);
        tfUser = new TextField("Username : ", "", 32, TextField.ANY);
        this.append(tfUser);
        tfPass = new TextField("Password : ", "", 55, TextField.PASSWORD);
        this.append(tfPass);
        tfPass2 = new TextField("Ulangi Password : ", "", 55, TextField.PASSWORD);
        this.append(tfPass2);
        tfHP = new TextField("Nomor HP : ", "", 15, TextField.NUMERIC);
        this.append(tfHP);
        tfEmail = new TextField("Email : ", "", 55, TextField.EMAILADDR);
        this.append(tfEmail);
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
            gita.trit[gita.tritNo].interrupt();
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
            this.append("Data yang anda masukan salah atau sudah ada\n"
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
