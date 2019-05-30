package cc.catface.iflytek.mail;

public class MailTest {
    public static void main(String[] args) {
        sendMailByJavaMail("test_mail", "test content");
    }

    public static int sendMailByJavaMail(final String tile,final String msg) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Mail m = new Mail("iflyrec_dev_monitor@iflytek.com", "Tjkj@2020");




                m.set_debuggable(false);
                String[] toArr = {
                        "yhwang22@iflytek.com",
//                        "zqcao2@iflytek.com",
//                        "quanli4@iflytek.com",
//                        "yacao@iflytek.com",
//                        "yangliu16@iflytek.com",
//                        "yangwang6@iflytek.com"
                };
                m.set_to(toArr);
                m.set_from("iflyrec_dev_monitor@iflytek.com");
                m.set_subject(tile);
                m.setBody(msg + "   快看看吧~！骚年");
                try {
                    //m.addAttachment("/sdcard/filelocation");
                    if(m.send()) {
                        System.out.println("Email was sent successfully.");
//                        Log.i(TAG,"Email was sent successfully.");

                    } else {
                        System.out.println("Email was sent failed.");
//                        Log.i(TAG,"Email was sent failed.");
                    }
                } catch (Exception e) {
                    System.out.println("Could not send email: " + e.toString());
//                    Log.e("MailApp", "Could not send email", e);
                }

            }
        }).start();

        return 1;
    }
}
