package Memories;

/**
 * Created by master on 24.10.2016.
 */
public class RealCounter implements Memory {
    private String rcname;
    private double rcounter;

    public RealCounter(String rcname, double rcounter) {
        this.rcname = rcname;
        this.rcounter = rcounter;
    }

    public String getName() {
        return rcname;
    }

    public int size() { return this.size(); }

    boolean write(double rcounter) {
        this.rcounter = rcounter;
        return (this.rcounter != 0.0);
    }

    public String read(String...args) {
        return this.rcounter+"";
    }

    @Override
    public boolean write(String... args) {
        return this.write(args[0]);
    }

    public boolean clear() {
        this.rcounter = 0.0;
        return true;
    }

    public String toString(){
        return "realcounter: "+rcname+"; value: "+rcounter;
    }
}
