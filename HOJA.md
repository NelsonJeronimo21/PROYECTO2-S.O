# PROYECTO2-S.O
package proyecto2;

import javax.swing.JOptionPane;



public class Hoja extends Thread{

    Proceso proceso;
    int hoja;
    int tamaÃ±o;
    int campo;
    String status = "Asignado";

    public Hoja(Proceso proceso, int hoja, int tamaÃ±o, int campo) {
        this.proceso = proceso;
        this.hoja = hoja;
        this.tamaÃ±o = tamaÃ±o;
        this.campo = campo;
    }

    public Hoja(Proceso proceso, int hoja, int tamaÃ±o) {
        this.proceso = proceso;
        this.hoja = hoja;
        this.tamaÃ±o = tamaÃ±o;
    }

    public Proceso getProceso() {
        return proceso;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }

    public int getHoja() {
        return hoja;
    }

    public void setHoja(int hoja) {
        this.hoja = hoja;
    }

    public int getTamaÃ±o() {
        return tamaÃ±o;
    }

    public void setTamaÃ±o(int tamaÃ±o) {
        this.tamaÃ±o = tamaÃ±o;
    }

    public int getCampo() {
        return campo;
    }

    public void setCampo(int campo) {
        this.campo = campo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public void run(){
        try{
            Thread.sleep(this.proceso.getQuantum());
            this.status = "Finalizado";
            this.proceso.setHojaFinalizadas(this.proceso.getHojaFinalizadas() + 1);
            this.proceso.setHojaEjecucion(this.proceso.getHojaEjecucion() - 1);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }


    
}
