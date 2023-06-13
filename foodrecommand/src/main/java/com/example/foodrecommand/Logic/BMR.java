package com.example.foodrecommand.Logic;

import jakarta.servlet.http.PushBuilder;

public class BMR {
    public BMR(){

    }
    public float caloNeed(int gender, int heigh, int weigh, int age){
        if(gender ==1){
             return (float)((float)weigh * 2.204623f * 10 + 6.25 * (float)heigh / 2.54f - 5 * (float)age + 5);

        }
        return (float)((float)weigh * 2.204623f * 10 + 6.25 * (float)heigh / 2.54f - 5 * (float)age + -161);

    }


    public float calosuiwAim(int gender, int heigh, int weigh, int age,int mucdich)
        {
            if (mucdich == 0)
            {
                return caloNeed(gender,heigh,weigh,age);
            }
            else if(mucdich == 1)
            {
                return  caloNeed(gender,heigh,weigh,age)- 300;
            }
            else
            {
                return  caloNeed(gender,heigh,weigh,age) + 200;
            }
        }


        public float TDEE(int gender, int heigh, int weigh, int age,int mucdich,int hoatdong)
        {
            if (hoatdong == 0)
            {
                return calosuiwAim(gender,heigh,weigh,age,mucdich) * 1.2f;
            }
            else if (hoatdong == 1)
            {
                return calosuiwAim(gender,heigh,weigh,age,mucdich) * 1.3f;

            }
            else if (hoatdong == 2)
            {
                return calosuiwAim(gender,heigh,weigh,age,mucdich) * 1.5f;
            }
            else 
            {
                return calosuiwAim(gender,heigh,weigh,age,mucdich) * 1.7f;
            }

          
        }
}
