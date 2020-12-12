/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SendSms;

public class SendSms {

    public static void Way2SMS(String mobile, String message) {
        try {
            String username = "8655221446";//userid of Way2sms
            String password = "ningesh";//password of Way2sms
            
            ThreadSms th = new ThreadSms(username, password, mobile, message);
            Thread t = new Thread(th);
            t.start();
        } catch (Exception e) {
        }
    }

    public static void main(String args[]) throws InterruptedException {
        String username = "8655221446";//userid of Way2sms
        String password = "ningesh";//password of Way2sms
//        String message = "Testing Way2SMS";//msg to be s ent
        String mobile = "8655221446";//phonenumber for whoom msg to be sent;
        String message=" Appointment received for a on 8_4_2016 at 12:00 to 1:00 for ffgh";
        ThreadSms th = new ThreadSms(username, password, mobile, message);
        Thread t = new Thread(th);
        t.start();
    }
}
