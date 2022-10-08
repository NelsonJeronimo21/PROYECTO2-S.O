package proyecto2;

public class Proceso {
    String name;
    int PID;
    int tamaÃ±o;
    int hoja;
    int hojaEjecucion;
    int hojaVirtuales;
    int hojaFinalizadas;
    int quantum;
    String status;
    int campo;

    public Proceso(String name, int PID, int tamaÃ±o, int hoja, int quantum){
        this.name = name;
        this.PID=PID;
        this.tamaÃ±o = tamaÃ±o;
        this.hoja = hoja;
        this.quantum = quantum;
        this.hojaEjecucion = 0;
        this.hojaVirtuales = 0;
        this.hojaFinalizadas = 0;
        this.status = "Asignado";
    }

    public Proceso(String name, int PID, int tamaÃ±o, int quantum){
        this.name = name;
        this.PID = PID;
        this.tamaÃ±o = tamaÃ±o;
        this.quantum = quantum;
        this.hojaEjecucion = 0;
        this.hojaVirtuales = 0;
        this.hojaFinalizadas = 0;
        this.status = "Asignado";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return PID;
    }

    public void setPid(int PID) {
        this.PID= PID;
    }

    public int getTamaÃ±o() {
        return tamaÃ±o;
    }

    public void setTamaÃ±o(int tamaÃ±o) {
        this.tamaÃ±o = tamaÃ±o;
    }

    public int getHoja() {
        return hoja;
    }

    public void setHoja(int hoja) {
        this.hoja = hoja;
    }

    public int getHojaEjecucion() {
        return hojaEjecucion;
    }

    public void setHojaEjecucion(int hojaEjecucion) {
        this.hojaEjecucion = hojaEjecucion;
    }

    public int getHojaVirtuales() {
        return hojaVirtuales;
    }

    public void setHojaVirtuales(int hojaVirtuales) {
        this.hojaVirtuales = hojaVirtuales;
    }

    public int getHojaFinalizadas() {
        return hojaFinalizadas;
    }

    public void setHojaFinalizadas(int hojaFinalizadas) {
        this.hojaFinalizadas = hojaFinalizadas;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCampo() {
        return tamaÃ±o;
    }

    public void setCampo(int campo) {
        this.campo = campo;
    }

    


    
}
