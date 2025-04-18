package pres;

import Metier.IMetier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PresSpringAnnotation {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("dao", "metier","ext");
        IMetier metier = applicationContext.getBean(IMetier.class);
        System.out.println("RES= "+ metier.calcul());
    }
}
