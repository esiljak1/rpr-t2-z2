package ba.unsa.etf.rpr.tutorijal02;

import static java.lang.Math.abs;

public class Interval {
    private double pocetna;
    private double krajnja;
    private boolean pripadaPocetna;
    private boolean pripadaKrajnja;
    private final double EPS = 0.0001;

    public Interval(double pocetna, double krajnja, boolean pripadaPocetna, boolean pripadaKrajnja)  throws IllegalArgumentException{
        if(pocetna > krajnja) throw new IllegalArgumentException();
        this.pocetna = pocetna;
        this.krajnja = krajnja;
        this.pripadaPocetna = pripadaPocetna;
        this.pripadaKrajnja = pripadaKrajnja;
    }
    public Interval(){
        this(0,0,false, false);
    }
    public boolean isNull(){
        return (pocetna == 0 && krajnja == 0);
    }
    public boolean isIn(double point){
        if(point > pocetna || abs(point - pocetna) < EPS && pripadaPocetna) {
            if (point < krajnja || abs(krajnja - point) < EPS && pripadaKrajnja) return true;
            return false;
        }return false;
    }
    public Interval intersect(Interval i){
        if(this.pocetna > i.pocetna){
            if(this.pocetna < i.krajnja){
                if(this.krajnja < i.krajnja) return new Interval(this.pocetna, this.krajnja, this.pripadaPocetna, this.pripadaKrajnja);
                else return new Interval(this.pocetna, i.krajnja, this.pripadaPocetna, i.pripadaKrajnja);
            }else return new Interval();
        }else{
            if(i.pocetna > this.krajnja) return new Interval();
            else if(i.krajnja < this.krajnja) return new Interval(i.pocetna, i.krajnja, i.pripadaPocetna, i.pripadaKrajnja);
            else return new Interval(i.pocetna, this.krajnja, i.pripadaPocetna, this.pripadaKrajnja);
        }
    }
    public static Interval intersect(Interval i1, Interval i2){
        return i1.intersect(i2);
    }
    @Override
    public String toString(){
        String ret = new String();
        if(this.equals(new Interval())){
            ret = ret + "()";
            return ret;
        }
        if(pripadaPocetna) ret = ret + "[";
        else ret = ret + "(";
        Double poc = pocetna, kraj = krajnja;
        ret = ret + poc.toString() + "," + kraj.toString();
        if(pripadaKrajnja) ret = ret + "]";
        else ret = ret + ")";
        return ret;
    }
    @Override
    public boolean equals(Object o){
        Interval i = (Interval) o;
        return (abs(pocetna-i.pocetna) < EPS && abs(krajnja - i.krajnja) < EPS && pripadaKrajnja == i.pripadaKrajnja && pripadaPocetna == i.pripadaPocetna);
    }
}
