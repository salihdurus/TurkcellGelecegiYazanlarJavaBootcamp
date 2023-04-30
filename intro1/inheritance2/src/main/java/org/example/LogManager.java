package org.example;

public class LogManager {
    //Spagetti
    public void log(int logType){
        if(logType==1) System.out.println("Veritabanına loglandı");
        else if(logType==2) System.out.println("Dosyaya loglandı");
        else if(logType==3) System.out.println("Eposta gönderildi");
    }
}
