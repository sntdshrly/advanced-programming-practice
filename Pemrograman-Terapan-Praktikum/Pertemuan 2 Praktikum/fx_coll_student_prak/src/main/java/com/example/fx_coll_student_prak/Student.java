package com.example.fx_coll_student_prak;

public class Student {
    public String nama;
    public int nrp;
    public float ipa;
    public float ips;

    public Student(int nrp, String nama, float ipa, float ips) {
        this.nama = nama;
        this.nrp = nrp;
        this.ipa = ipa;
        this.ips = ips;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getNrp() {
        return nrp;
    }

    public void setNrp(int nrp) {
        this.nrp = nrp;
    }
    public float getIpa() {
        return ipa;
    }

    public void setIpa(float ipa) {
        this.ipa = ipa;
    }

    public float getIps() {
        return ips;
    }

    public void setIps(float ips) {
        this.ips = ips;
    }


    public String toString() {
        return "nama:" + nama + "nrp:" + nrp + "ipa:" + ipa + "ips:" + ips;
    }
}
