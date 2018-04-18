package com.lwl.bishe.bean;
/**
 * date  2018/3/17
 * author liuwillow
 **/
public class Rect {
    private Integer id;
    private Location lo1;
    private Location lo2;
    private Location lo3;
    private Location lo4;

    public Rect() {
    }

    public Rect(Location lo1, Location lo2, Location lo3, Location lo4) {
        this.lo1 = lo1;
        this.lo2 = lo2;
        this.lo3 = lo3;
        this.lo4 = lo4;
    }

    public Location getLo1() {
        return lo1;
    }

    public void setLo1(Location lo1) {
        this.lo1 = lo1;
    }

    public Location getLo2() {
        return lo2;
    }

    public void setLo2(Location lo2) {
        this.lo2 = lo2;
    }

    public Location getLo3() {
        return lo3;
    }

    public void setLo3(Location lo3) {
        this.lo3 = lo3;
    }

    public Location getLo4() {
        return lo4;
    }

    public void setLo4(Location lo4) {
        this.lo4 = lo4;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String toParam(){
        return lo1.toParam() + ";"
                +  lo2.toParam() + ";"
                + lo3.toParam() + ";"
                + lo4.toParam() + ";"
                + lo1.toParam();
    }
}
