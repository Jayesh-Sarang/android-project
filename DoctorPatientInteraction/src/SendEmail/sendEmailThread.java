/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SendEmail;

/**
 *
 * @author Administrator
 */
public class sendEmailThread implements Runnable {

    String toemail = "";
    String Subject = "";
    String message = "";
    String Attachment = "";

    public sendEmailThread(String temail, String sbject, String msg, String attachement) {
        toemail = temail;
        Subject = sbject;
        message = msg;
        Attachment = attachement;
    }

    @Override
    public void run() {
        try {
            SendEmail smp = new SendEmail(toemail, Subject, message, Attachment);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
}