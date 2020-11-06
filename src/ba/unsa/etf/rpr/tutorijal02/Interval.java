package ba.unsa.etf.rpr.tutorijal02;

public class Interval {
    double pocetnaTacka, krajnjaTacka;
    boolean prvaPripada, drugaPripada;

    public Interval(double pocetnaTacka, double krajnjaTacka, boolean prvaPripadaIntervalu, boolean drugaPripadaIntervalu) {
        if(pocetnaTacka > krajnjaTacka) throw new IllegalArgumentException("vrijednost tacke a veca od tacke b");
        else {
            this.pocetnaTacka = pocetnaTacka;
            this.krajnjaTacka = krajnjaTacka;
            this.prvaPripada = prvaPripadaIntervalu;
            this.drugaPripada = drugaPripadaIntervalu;
        }
    }
    public Interval(){
        this.pocetnaTacka = 0.;
        this.krajnjaTacka = 0.;
        this.prvaPripada = false;
        this.drugaPripada = false;
    }

    public boolean isNull() {
        if(this.pocetnaTacka == 0. && this.krajnjaTacka == 0. && this.prvaPripada == false && drugaPripada == false) return true;
        return false;
    }

    public boolean isIn(double v) {
        if(prvaPripada){
            if(drugaPripada) return (v>=pocetnaTacka && v<=krajnjaTacka);
            else return (v>=pocetnaTacka && v<krajnjaTacka);
        }else{
            if(drugaPripada) return (v>pocetnaTacka && v<=krajnjaTacka);
            else return (v>pocetnaTacka && v<krajnjaTacka);
        }
    }

    public Interval intersect(Interval i2) {
        Interval povratni = new Interval();
        if(pocetnaTacka <= i2.pocetnaTacka && i2.krajnjaTacka <=krajnjaTacka) return i2;
        else if(krajnjaTacka < i2.pocetnaTacka) {
            return povratni;
        }else if(krajnjaTacka > i2.pocetnaTacka){
            povratni.pocetnaTacka = i2.pocetnaTacka;
            povratni.prvaPripada = i2.prvaPripada;
            povratni.krajnjaTacka = krajnjaTacka;
            povratni.drugaPripada = drugaPripada;
        }else if(i2.krajnjaTacka < pocetnaTacka) return povratni;
        else if(i2.krajnjaTacka > pocetnaTacka) {
            povratni.pocetnaTacka = pocetnaTacka;
            povratni.prvaPripada = prvaPripada;
            povratni.krajnjaTacka = i2.pocetnaTacka;
            povratni.drugaPripada = i2.prvaPripada;
        }else if(this.equals(i2)) return i2;
        else return this;
        return povratni;
    }

    public static Interval intersect(Interval i, Interval i2) {
        Interval povratni = new Interval();
        if(i.pocetnaTacka <= i2.pocetnaTacka && i2.krajnjaTacka <=i.krajnjaTacka) return i2;
        else if(i.krajnjaTacka < i2.pocetnaTacka) {
            return povratni;
        }else if(i.krajnjaTacka > i2.pocetnaTacka){
            povratni.pocetnaTacka = i2.pocetnaTacka;
            povratni.prvaPripada = i2.prvaPripada;
            povratni.krajnjaTacka = i.krajnjaTacka;
            povratni.drugaPripada = i.drugaPripada;
        }else if(i2.krajnjaTacka < i.pocetnaTacka) return povratni;
        else if(i2.krajnjaTacka > i.pocetnaTacka) {
            povratni.pocetnaTacka = i.pocetnaTacka;
            povratni.prvaPripada = i.prvaPripada;
            povratni.krajnjaTacka = i2.pocetnaTacka;
            povratni.drugaPripada = i2.prvaPripada;
        }else if(i.equals(i2)) return i;
        else return i;
        return povratni;
    }

    @Override
    public String toString() {
        String s="";
        if(prvaPripada) s+="[";
        else s+="(";
        if(!this.isNull()) s+=pocetnaTacka+","+krajnjaTacka;
        if(drugaPripada) s+="]";
        else s+=")";
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return Double.compare(interval.pocetnaTacka, pocetnaTacka) == 0 &&
                Double.compare(interval.krajnjaTacka, krajnjaTacka) == 0 &&
                prvaPripada == interval.prvaPripada &&
                drugaPripada == interval.drugaPripada;
    }

}
