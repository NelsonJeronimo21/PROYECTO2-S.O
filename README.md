# PROYECTO2-S.O
package proyecto2;


public class MyThread extends Thread{
    
    public void run(){
        for(int i=0; i < 13; i++){
            System.out.println(i + this.getName());
        }
    }
}
