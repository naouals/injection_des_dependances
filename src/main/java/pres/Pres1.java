package pres;

import Metier.MetierImpl;
import ext.DaoImplV2;

public class Pres1 {
    public static void main(String[] args) {
        DaoImplV2 d=new DaoImplV2();
        MetierImpl metier=new MetierImpl(d);
        //metier.setDao(d);// injection des despendances via le setter
        System.out.println("RES= "+metier.calcul());
    }

}
