# PROYECTO2-S.O
package proyecto2;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableMEMORIAModel;


public class Memoria extends Thread{

    private int nProcess = 0;
    private int quantum;
    private int memory = 200000;    
    private int PiD = 1;
    Hoja[] memoriaRam = new Hoja[15];
    ArrayList<Proceso> procesos = new ArrayList<Proceso>();
    Hoja[] AsignacionMemoriaVirtual = new Hoja[15];
    ArrayList<Hoja> hoja = new ArrayList<Hoja>();
    public JTable tblProcesos;
    public JTable tblHojas;
    public JTable tblTablahojas;
    public JTable tblAsignacionMemoriaVirtual;
    public JTable tblMemoriaRam;

    public Memoria(JTable tblProcesos, JTable tblhoja, JTable tblTablahoja, JTable tblAsignacionMemoriaVirtual, JTable tblMemoriaRam){
        this.tblProcesos = tblProcesos;
        this.tblHoja = tblhoja;
        this.tblTablaHoja = tblTablahoja;
        this.tblAsignacionMemoriaVirtual = tblAsignacionMemoriaVirtual;
        this.tblMemoriaRam = tblMemoriaRam;
        setTableMemoriaRam((DefaultTableModel) this.tblProcesos.getModel());
        this.tblProcesos.repaint();
    }

    public void setTableMemoriaRam(DefaultTableModel model){
        resetTable(model);
        int pos = 1;
        for(Hoja p : this.memoriaRam){
            if(p == null){
                model.addRow(new Object[]{pos,200000,0,200000,""});
            }else{
                model.addRow(new Object[]{pos,200000,p.getTamaÃ±o(),200000-p.getTamaÃ±o(),p.getProceso().getName()});
            }
            pos++;
        }
    }

    public void setTableAsignacionMemoriaVirtual(DefaultTableModel model){
        resetTable(model);
        int pos = 1;
        for(Hoja p : this.AsignacionMemoriaVirtual){
            if(p == null){
                model.addRow(new Object[]{pos,"","",""});
            }else{
                model.addRow(new Object[]{pos,p.getProceso().getName(),p.getProceso().getPiD(),p.getTamaÃ±o()});
            }
            pos++;
        }
    }

    public void setTableProcesos(DefaultTableModel model){
        resetTable(model);
        for(Proceso p : this.procesos){
            model.addRow(new Object[]{p.getName(), p.getPid(), p.getStatus(), p.getTamaÃ±o(), p.getHoja(), p.getHojaEjecucion(), p.getHojaVirtuales(), p.getHojaFinalizadas()});
        }
    }

    public void setTableHoja(DefaultTableModel model){
        resetTable(model);
        for(Hoja p : this.hoja){
            if(p.getStatus() == "Asignado"){
                model.addRow(new Object[]{p.proceso.getPiD(), p.getHoja(), p.getTamaÃ±o()});
            }
        }
    }

    public void setTableTablaHoja(DefaultTableModel model){
        resetTable(model);
        for(Hoja p : this.hoja){
            if(p.getStatus() == "Asignado"){
                model.addRow(new Object[]{p.proceso.getPiD(), p.get(), p.   Hojagetcampo()});
            }
        }
    }


    public void resetTable(DefaultTableModel model){
        int rows = model.getRowCount();
        for(int i=rows-1; i >= 0; i--){ model.removeRow(i); }
    }

    public int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public void run(){

        try{
            while(true){
                Thread.sleep(this.quantum);


                for(Hoja p : this.hoja){
                    if(p.getStatus() == "Finalizado"){
                        for(int i=0; i<this.memoriaRam.length; i++){
                            if((p).equals(this.memoriaRam[i])){
                                this.memoriaRam[i] = null;
                                this.memory += 200000;

                                for(int vi=0; vi<this.AsignacionMemoriaVirtual.length; vi++){
                                    if(this.memory > 0 && this.AsignacionMemoriaVirtual[vi] != null){
                                        this.AsignacionMemoriaVirtual[vi].getProceso().setHojaVirtuales(this.AsignacionMemoriaVirtual[vi].getProceso().getHojaVirtuales() - 1);
                                        this.AsignacionMemoriaVirtual[vi].getProceso().setHojaEjecucion(this.AsignacionMemoriaVirtual[vi].getProceso().getHojaEjecucion() + 1);
                                        Hoja ram = (Hoja) this.AsignacionMemoriaVirtual[vi];
                                        
                                        this.AsignacionMemoriaVirtual[vi] = null;

                                        this.memory -= 200000;
                                        addMemoryRAM(ram, getRandomNumberUsingNextInt(0, 13));
                                    }
                                }
                            }
                        }
                    }
                }

                for(Proceso p : this.procesos){
                    if(p.getHoja() == p.getHojaFinalizadas()){
                        p.setStatus("Finalizado");
                    }
                }


                setTableProcesos((DefaultTableModel) this.tblProcesos.getModel());
                setTableHoja((DefaultTableModel) this.tblHoja.getModel());
                setTableTablaHoja((DefaultTableModel) this.tblTablaHoja.getModel());
                setTableMemoriaRam((DefaultTableModel) this.tblMemoriaRam.getModel());
                setTableAsignacionMemoriaVirtual((DefaultTableModel) this.tblAsignacionMemoriaVirtual.getModel());
                this.tblProcesos.repaint();
                this.tblHoja.repaint();
                this.tblTablaHoja.repaint();
                this.tblMemoriaRam.repaint();
                this.tblAsignacionMemoriaVirtual.repaint();
            }
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }

    public void saveProcess(String name, int tamaÃ±o){
        if(tamaÃ±o < 4000000 && this.memory > 0){
            //resetTable((DefaultTableModel) jTblMemoria.getModel());
            Proceso p = new Proceso(name, this.PiD, tamaÃ±o, this.quantum);
            this.PiD += 1;
            this.nProcess += 1;
            boolean leafAssign = true;

            float partitions = (float)p.getTamaÃ±o()/200000;
            System.out.println(partitions);

            if(partitions <= 1){
                partitions = 1;
            }else if(partitions - (int)partitions > 0.00){
                partitions = (int)partitions+1;
            }
            p.setHoja((int) partitions);
            this.procesos.add(p);
            
            int leafHoja = p.getTamaÃ±o();
            for(int i=0; i<(int)partitions; i++){
                Hoja leaf = null;
                if(leafTamaÃ±o > 200000){
                    leaf = new Hoja(p,i+1,200000);
                }else{
                    leaf= new Hoja(p,i+1, leafTamaÃ±o);
                }
                this.hoja.add(leaf);
                leafTamaÃ±o -= 200000;
                if(this.memory > 0){
                    this.memory -= 200000;
                    addMemoryRAM(leaf, getRandomNumberUsingNextInt(0, 12));
                }else{
                    addAsignacionMemoryVirtual(leaf,getRandomNumberUsingNextInt(0, 14));
                }
            }


            setTableProcesos((DefaultTableModel) this.tblProcesos.getModel());
            setTableHoja((DefaultTableModel) this.tblhoja.getModel());
            setTableTablaHoja((DefaultTableModel) this.tblTablaHoja.getModel());
            setTableMemoriaRam((DefaultTableModel) this.tblMemoriaRam.getModel());
            setTableAsignacionMemoriaVirtual((DefaultTableModel) this.tblAsignacionMemoriaVirtual.getModel());
            this.tblProcesos.repaint();
            this.tblHoja.repaint();
            this.tblTablaHoja.repaint();
            this.tblMemoriaRam.repaint();
            this.tblAsignacionMemoriaVirtual.repaint();

        }else{
            JOptionPane.showMessageDialog(null, "EL PROCESO EXCEDE EL NUMERO DE MEMORIA 4000000 O NUMERO DE PROCESO EXCEDIDO.");
        }
    }

    public void addMemoryRAM(hoja leaf int pos){
        if(this.memoriaRam[pos] == null){
            leaf.setcampo(pos+1);
            leaf.getProceso().setHojaEjecucion(leaf.getProceso().getHojaEjecucion()+1);
            this.memoriaRam[pos] = leaf;
            leaf.start();
        }else{
            boolean flag = true;
            for(int i=0; i<13; i++){
                if(this.memoriaRam[i] == null && flag){
                    addMemoryRAM(leaf, i);
                    flag = false;
                }
            }
        }
    }

    public void addMemoryVirtual(Hoja leaf, int pos){
        if(this.AsignacionMemoriaVirtual[pos] == null){
            leaf.setcampo(pos+1);
            leaf.getProceso().sethojaVirtuales(leaf.getProceso().getAsignacionMemoriaVirtual()+1);
            this.AsignacionMemoriaVirtual[pos] = leaf;
        }else{
            boolean flag = true;
            for(int i=0; i<15; i++){
                if(this.AsignacionMemoriaVirtual[i] == null && flag){
                    addMemoryVirtual(leaf, i);
                    flag = false;
                }
            }
        }
    }



    public int getnProcess() {
        return nProcess;
    }

    public void setnProcess(int nProcess) {
        this.nProcess = nProcess;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getpId() {
        return PiD;
    }

    public void setpId(int PiD) {
        this.PiD = PiD;
    }

    public Hoja[] getMemoriaRam() {
        return memoriaRam;
    }

    public void setMemoriaRam(Hoja[] memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public ArrayList<Proceso> getProcesos() {
        return procesos;
    }

    public void setProcesos(ArrayList<Proceso> procesos) {
        this.procesos = procesos;
    }

    public Hoja[] getAsignacionMemoriaVirtual() {
        return AsignacionMemoriaVirtual;
    }

    public void setAsignacionMemoriaVirtual(Hoja[] AsignacionMemoriaVirtual) {
        this.AsignacionMemoriaVirtual = AsignacionMemoriaVirtual;
    }

    public ArrayList<Hoja> getHoja() {
        return hoja;
    }

    public void sethoja(ArrayList<Hoja> Hoja) {
        this.hoja = hoja;
    }

}
