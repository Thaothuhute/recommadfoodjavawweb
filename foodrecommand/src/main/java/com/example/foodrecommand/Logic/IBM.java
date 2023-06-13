package com.example.foodrecommand.Logic;

public class IBM {
    private int weigh;
    private int heigh;

    public IBM(int weigh,int heigh){
        this.heigh = heigh;
        this.weigh = weigh;
    }


    public float coIBM(){
         float heighM = (float)this.heigh * 0.01f;
        return  (float)this.weigh / ((float)heighM * (float)heighM);
    }


    public String comIBM(){
        String kq ="";
         if (coIBM() < 18.5)
                kq = "Gầy";
            if (18.5 < coIBM() && coIBM() < 24.9)
                kq = "Bình thường";
            if (coIBM() >= 25 && coIBM() < 30)
                kq = "Tiền béo phì";
            if (coIBM() >= 30 && coIBM() < 34.9)
                kq = "Béo phì độ I";
            if (coIBM() >= 35 && coIBM() < 39.9)
                kq = "Béo phì độ II";
            if (coIBM() >= 40)
                kq = "Béo phì độ III";
            return kq;
    }
}
