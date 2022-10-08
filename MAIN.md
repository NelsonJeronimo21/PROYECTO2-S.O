# PROYECTO2-S.O
package proyecto2;


public class Main {
    
    //MyThread[15] memory = MyThread[15];
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MyThread hilo1 = new MyThread();
        MyThread hilo2 = new MyThread();
        
        hilo1.start();
        hilo2.start();
    }
    
}
