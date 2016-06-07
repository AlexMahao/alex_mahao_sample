package com.mahao.alex.architecture.dagger2;

/**
 *
 * 如果有如下需求，我们有一个咖啡机，他具有加热部件和抽水部件
 *  同时他具有煮咖啡的功能，我们根据描述，可构造如下对象
 *
 * Created by Alex_MaHao on 2016/5/19.
 */
public class CoffeeMakerOld {

    private final Heater heater;

    private final Pump pump;


    public CoffeeMakerOld() {
        this.heater = new ElectricHeater(); // 加热器
        this.pump = new Thermosiphon(heater); // 构造抽水器
    }

    public void brew() {
        //煮咖啡
    }

}

//----对于上面的类，存在一个弊端，加热器和抽水器实在类内构造-------


/**
 * 这样的优点是，我们可以根据需要组装不同的咖啡，
 */
class CoffeeMakerOld2{
    private final Heater heater;

    private final Pump pump;


    public CoffeeMakerOld2(Heater heater,Pump pump) {
        this.heater = heater; // 加热器
        this.pump = pump; // 构造抽水器
    }

    public void brew() {
        //煮咖啡
    }


    public static void main(String[] args){

        Heater heater = new ElectricHeater();

        Pump pump = new Thermosiphon(heater);

        CoffeeMakerOld2 coffeeMarker = new CoffeeMakerOld2(heater,pump);

        //这样，他们的依赖关系是在外部通过注入的方式，实现了
    }
}
