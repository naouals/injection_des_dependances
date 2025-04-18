package Metier;

import dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("metier")
public class MetierImpl implements IMetier {
    @Autowired
    @Qualifier("d")

    private IDao dao;
    public MetierImpl(IDao dao){
        this.dao=dao;// couplage faible
    }
    public MetierImpl(){}
    @Override
    public double calcul() {
        double t=dao.getData();
        double res=t*12*Math.PI/2*Math.cos(t);
        return res;
    }
    // permet de injecter dans la variable dao un objet de type IDao (d'une classe implement l'interface IDao)
    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
