//nama file: Masuk.java

import javax.microedition.lcdui.*;
import javax.microedition.rms.RecordStoreException;

/**
 * @author Inchidi Contact : Email Inchidi@yahoo.com Twitter
 * @DimasInchidi
 */
public class Masuk extends Form implements ItemCommandListener{

    private Display display;
    private Command cKeluar, cLogin, cDaftar;
    private GiTa gita;
    private StringItem iKeluar, iDaftar, iLogin;
    private TextField tfUser, tfPass;
    private ReMaS rms;
    private ChoiceGroup cg = new ChoiceGroup (null,Choice.MULTIPLE);
    
    Masuk(GiTa gita, Display display) {
        super("Selamat Datang di GiTa");
        gita.UserLogin = null;
        Ticker tic = new Ticker("GiTa Agenda Kita");
        this.display = display;
        this.gita = gita;
        this.setTicker(tic);
        cLogin = new Command("Login", Command.SCREEN, 1);
        cDaftar = new Command("Daftar", Command.SCREEN, 2);
        cKeluar = new Command("Keluar", Command.SCREEN, 3);
        tfUser = new TextField("Username : ", "", 32, TextField.ANY);
        this.append(tfUser);
        tfPass = new TextField("Password : ", "", 32, TextField.PASSWORD);
        this.append(tfPass);
        cg.append("Simpan Password",null);
        this.append(cg);
        thisActLihat ();
        //login button
        iLogin = new StringItem(null, "Masuk", Item.BUTTON);
        iLogin.setDefaultCommand(cLogin);
        iLogin.setItemCommandListener(this);
        append(iLogin);
        //daftar button
        iDaftar = new StringItem(null, "Daftar", Item.BUTTON);
        iDaftar.setDefaultCommand(cDaftar);
        iDaftar.setItemCommandListener(this);
        append(iDaftar);
        //keluar button
        iKeluar = new StringItem(null, "Keluar", Item.BUTTON);
        iKeluar.setDefaultCommand(cKeluar);
        iKeluar.setItemCommandListener(this);
        append(iKeluar);
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
        gita.trit[gita.tritNo].start();
        if(gita.UserLogin == null){
            this.append("Username atau Password Salah\n"
                    + "restart aplikasi untuk mencegah brute force");
        }
    }

    private void cActDaftar() {
        Daftar daftar = new Daftar(gita, display);
        display.setCurrent(daftar);
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
