public class MT02MethodLevelSynchronization {
    /*
=========================================   SYNCHRONIZED  ==========================================================
multi-threading çalışma koşullarında birden fazla thread'in aynı kaynağa (değişken metot, class, bellek vb)(Habil-Kabil kavgası )
erişimi (okuma veya yazma) sırasında verinin güncellenmesi ve tutarlılığı ile ilgili sorunlar çıkabilir.
Bu tutarsızlığı engellemek için synchronized keywordu kullanılabilir.
Kısaca, Syncronization bir kaynağın tread'ler tarafından eş zamanlı kullanımına kapatılması (Lock) işlemidir.
Synchronized keywordunun farklı kullanımları bulunmaktadır.
 1- Eğer bir metot "synchronized" yapılırsa (Method-Level Syncronization) bu metota aynı andan birden fazla thread'in
    erişimine izin verilmez.
 2- Eğer bir metot yerine o metodun ait olduğu class'ı aynı anda birden fazla thread'in kullanımına kapatmak
    (class-level Synchronization) istersek o zaman "synchronized static" kullanmalıyız.
 3- Eğer bir metot içerisinde belirli bir kismin eş zamanlı thread kullanımına kapatılmasını istenire
    "synchronized block" (block-level Synchronization) kullanılmalı.
     */
    public static int sayac=0;//ortak erisilen variable
    public static void main(String[] args) {
Thread thHabil=new Thread(new Runnable() {
    @Override
    public void run() {
        Sayici.say("habil");
    }
});
        Thread thKabil=new Thread(new Runnable() {
            @Override
            public void run() {
                Sayici.say("kabil");// es zamanli olamaz
//                falan class'da filan method calis // es zamanli olabilir
            }
        });
        thHabil.start();
//        thHabil.join();  join() method multithreadi tamamen kisitlar(öldürür).
//        join() kullanilmasa thHabel ve thKabil threadleri es zamanli sayac variable üzerinde action yapardi.
//        Bu durumu engellemek icin join() bir yöntem olsa da riski vardir. join() ile synchronized kullanimi iyi planlanmali
        thKabil.start();
    }
}
class Sayici{
    public synchronized static void say(String thread){//synchronized keywordu-->bu methodun threadler tarafindan es zamanli run edilmesini kisitlar
        for (int i = 1; i <21 ; i++) {
MT02MethodLevelSynchronization.sayac++;
            System.out.println("sayac - "+thread+" : "+MT02MethodLevelSynchronization.sayac);
        }
    }
}
