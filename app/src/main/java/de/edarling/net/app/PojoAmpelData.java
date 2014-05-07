package de.edarling.net.app;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class GATE {

    @SerializedName("P")
    @Expose
    private Integer p;
    @SerializedName("S")
    @Expose
    private Integer s;

    public Integer getP() {
        return p;
    }

    public void setP(Integer p) {
        this.p = p;
    }

    public Integer getS() {
        return s;
    }

    public void setS(Integer s) {
        this.s = s;
    }

}


class L1 {

    @SerializedName("R")
    @Expose
    private Integer r;
    @SerializedName("Y")
    @Expose
    private Integer y;
    @SerializedName("G")
    @Expose
    private Integer g;

    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getG() {
        return g;
    }

    public void setG(Integer g) {
        this.g = g;
    }

}

class L2 {

    @SerializedName("R")
    @Expose
    private Integer r;
    @SerializedName("Y")
    @Expose
    private Integer y;
    @SerializedName("G")
    @Expose
    private Integer g;

    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getG() {
        return g;
    }

    public void setG(Integer g) {
        this.g = g;
    }

}

class L3 {

    @SerializedName("R")
    @Expose
    private Integer r;
    @SerializedName("Y")
    @Expose
    private Integer y;
    @SerializedName("G")
    @Expose
    private Integer g;

    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getG() {
        return g;
    }

    public void setG(Integer g) {
        this.g = g;
    }

}

class L4 {

    @SerializedName("R")
    @Expose
    private Integer r;
    @SerializedName("Y")
    @Expose
    private Integer y;
    @SerializedName("G")
    @Expose
    private Integer g;

    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getG() {
        return g;
    }

    public void setG(Integer g) {
        this.g = g;
    }

}

public class PojoAmpelData {

    @SerializedName("L1")
    @Expose
    private L1 l1;
    @SerializedName("L2")
    @Expose
    private L2 l2;
    @SerializedName("L3")
    @Expose
    private L3 l3;
    @SerializedName("L4")
    @Expose
    private L4 l4;
    @SerializedName("GATE")
    @Expose
    private GATE gATE;
    @SerializedName("VOL")
    @Expose
    private VOL vOL;

    public L1 getL1() {
        return l1;
    }

    public void setL1(L1 l1) {
        this.l1 = l1;
    }

    public L2 getL2() {
        return l2;
    }

    public void setL2(L2 l2) {
        this.l2 = l2;
    }

    public L3 getL3() {
        return l3;
    }

    public void setL3(L3 l3) {
        this.l3 = l3;
    }

    public L4 getL4() {
        return l4;
    }

    public void setL4(L4 l4) {
        this.l4 = l4;
    }

    public GATE getGATE() {
        return gATE;
    }

    public void setGATE(GATE gATE) {
        this.gATE = gATE;
    }

    public VOL getVOL() {
        return vOL;
    }

    public void setVOL(VOL vOL) {
        this.vOL = vOL;
    }

}

class VOL {

    @Expose
    private Integer dBA;
    @SerializedName("W")
    @Expose
    private Integer w;
    @SerializedName("A")
    @Expose
    private Integer a;
    @SerializedName("C")
    @Expose
    private Integer c;
    @SerializedName("L")
    @Expose
    private Integer l;

    public Integer getDBA() {
        return dBA;
    }

    public void setDBA(Integer dBA) {
        this.dBA = dBA;
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public Integer getL() {
        return l;
    }

    public void setL(Integer l) {
        this.l = l;
    }

}