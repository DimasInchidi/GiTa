//nama file: ReMaS.java
import javax.microedition.rms.*;

/**
 * @author Inchidi Contact : Email Inchidi@yahoo.com Twitter
 * @DimasInchidi
 */
public class ReMaS {

    private RecordStore RS;
    private String s;
    private int i;

    ReMaS(String s, int i) {
        RS = null;
        this.s = s;
        this.i = i;
    }

    RecordStore getRS() {
        return RS;
    }

    boolean open() throws RecordStoreException {
        RS = RecordStore.openRecordStore(s, true);
        if (RS == null) {
            return false;
        }
        return true;
    }
    
    void close() throws RecordStoreException{
        if (RS != null) RS.closeRecordStore();
    }
    
    void delete() throws RecordStoreException{
        RS.deleteRecordStore(s);
    }
    
    void saveRecord(int recID, String str) {
        try {
            byte[] bytes = str.getBytes();
            if (recID == 0) RS.addRecord(bytes, 0, bytes.length);
            else RS.setRecord(recID, bytes, 0, bytes.length);
        } catch (RecordStoreNotOpenException ex) {
            ex.getMessage();
        } catch (RecordStoreException ex) {
            ex.getMessage();
        }
    }
    
    String ReadRecord(int recID) throws RecordStoreNotOpenException {
        try {
            byte[] recData = new byte[i];
            int len = RS.getRecord(recID, recData, 0);
            return new String(recData, 0, len);
        } catch (Exception ex) {
            return "";
        }
        }
    }
