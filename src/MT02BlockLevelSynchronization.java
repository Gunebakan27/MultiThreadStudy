public class MT02BlockLevelSynchronization {
/*
Uygulamalarda bazen tüm metotudun synchronized yapılması gerekmeyebilir.
	  Bu durumda, sadece ilgili kısımları synchronized yapıp diğer kısımların klasik multi-threading mantığı ile
	  calışmasına izin vermek performans acisindan onemli katkı saglayacaktır.
​
	  İstenilen kısımların synchronized yapılması için "synchronized block" kullanılır.
	  Bu durumda block içerisindeki kısıma aynı anda birden fazla thread'in erişimine izin verilmez iken dışında kalan
	  kısımlara, aktif olan threadlar tarafından eş zamanlı erişim sağlabilir.
 */
    public static void main(String[] args) throws InterruptedException {
Parantez p1=new Parantez();
Thread thHabil=new Thread(new Runnable() {
    @Override
    public void run() {
        p1.parantezKoy();
    }
});
Thread thKabil= new Thread(new Runnable() {
    @Override
    public void run() {
        p1.parantezKoy();
    }
});
//long basla=System.currentTimeMillis();

thHabil.start();
thKabil.start();
//thHabil.join();
//thKabil.join();
//        long bitis=System.currentTimeMillis();
//        System.out.println("synchronized thread toplam süre : "+(bitis-basla));

    }
}
class Parantez{
    public void parantezKoy(){ // es zamanli olamaz
      synchronized (this){
        for (int i = 0; i < 10; i++) {

            if (i<5){
                System.out.print("[");
            }
         else{
                System.out.print("]");
            }


        } }  System.out.println();
        for (int i = 0; i <10 ; i++) {//es zamanli olabilir
            try {
                Thread.sleep(25);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }

    }
}