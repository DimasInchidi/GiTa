//nama file: masuK.java

import javax.microedition.lcdui.*;
import javax.microedition.rms.RecordStoreException;

/**
 * @author Inchidi
 * Contact :
 *      Email Inchidi@yahoo.com 
 *      Twitter @DimasInchidi
 */

public class Masuk implements ItemCommandListener {
    public Form masuk =new Form("Selamat Datang di GiTa");
    private Command cKeluar, cLogin, cDaftar;
    private GiTa gita;
    private StringItem iKeluar, iDaftar, iLogin;
    private TextField tfUser, tfPass;
    private ReMaS rms;
    private ChoiceGroup cg = new ChoiceGroup (null,Choice.MULTIPLE);
    
    Masuk(GiTa gita) {
        masuk =new Form("Selamat Datang di GiTa");
        this.gita = gita;
        Ticker tic = new Ticker("GiTa Agenda Kita");
        masuk.setTicker(tic);
        cLogin = new Command("Login", Command.SCREEN, 1);
        cDaftar = new Command("Daftar", Command.SCREEN, 2);
        cKeluar = new Command("Keluar", Command.SCREEN, 3);
        tfUser = new TextField("Username : ", "", 32, TextField.ANY);
        masuk.append(tfUser);
        tfPass = new TextField("Password : ", "", 32, TextField.PASSWORD);
        masuk.append(tfPass);
        cg.append("Simpan Password",null);
        masuk.append(cg);
        thisActLihat ();
        //login button
        iLogin = new StringItem(null, "Masuk", Item.BUTTON);
        iLogin.setDefaultCommand(cLogin);
        iLogin.setItemCommandListener(this);
        masuk.append(iLogin);
        //daftar button
        iDaftar = new StringItem(null, "Daftar", Item.BUTTON);
        iDaftar.setDefaultCommand(cDaftar);
        iDaftar.setItemCommandListener(this);
        masuk.append(iDaftar);
        //keluar button
        iKeluar = new StringItem(null, "Keluar", Item.BUTTON);
        iKeluar.setDefaultCommand(cKeluar);
        iKeluar.setItemCommandListener(this);
        masuk.append(iKeluar);
    }

    public void commandAction(Command c, Item item) {
        String data = c.getLabel();
        if (data.equals(cKeluar.getLabel())) {
            cActKeluar();
        } else if (data.equals(cLogin.getLabel())) {
                cgActSimpan();
                cgActSimpan();
            cActLogin();
        } else if (data.equals(cDaftar.getLabel())) {
            cActDaftar();
            thisActLihat ();
        }
    }

    private void cActKeluar() {
        gita.Exit();
    }

    private void cActLogin() {
        gita.POST = "myusername="+tfUser.getString()+"&mypassword="+tfPass.getString();
        gita.tritNo = 0;
        gita.treat().setPriority(3);
        gita.treat().start();
        if(gita.UserLogin == null){
            masuk.append("Username atau Password Salah\n"
                    + "restart aplikasi untuk mencegah brute force");
        }
    }

    private void cActDaftar() {
        Display.getDisplay(gita).setCurrent(gita.daftaR.daftar);
    }
    
    private void cgActSimpan(){
        if(cg.isSelected(0)){
            try {
                rms = new ReMaS("Login",32);
                rms.open();
                rms.saveRecord(0, "garbage");
                rms.saveRecord(1, tfUser.getString());
                rms.saveRecord(2, tfPass.getString());
                rms.close();
            } catch (RecordStoreException ex) {
                ex.getMessage();
            }
        }
    }
    
    private void thisActLihat (){
        try {
            rms = new ReMaS("Login",32);
                rms.open();
            tfUser.setString(rms.ReadRecord(1));
            tfPass.setString(rms.ReadRecord(2));
            rms.close();
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
    }
}
