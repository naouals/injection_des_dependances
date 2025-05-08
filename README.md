#  Système de Calcul avec Injection de Dépendances (Spring)

![Spring](https://img.shields.io/badge/Spring-5.3.34-green)
![Java](https://img.shields.io/badge/Java-8-blue)


Ce projet démontre plusieurs méthodes d'injection de dépendances en Java, avec une progression allant de l'approche manuelle à l'utilisation complète de Spring Framework.

##  Structure du Projet

```
src/
├── dao/               # Couche d'accès aux données
│   ├── IDao.java      # Interface
│   ├── DaoImpl.java   # Implémentation 1 (DB)
│   └── ext/           # Implémentation alternative
│       └── DaoImplV2.java # Implémentation 2 (Capteurs)
├── Metier/           # Couche métier
│   ├── IMetier.java   # Interface
│   └── MetierImpl.java # Logique de calcul
└── pres/             # Présentations (différents modes d'injection)
    ├── Pres1.java     # Injection manuelle
    ├── Pres2.java     # Injection par réflexion
    ├── PresSpringXML.java # Spring XML
    └── PresSpringAnnotation.java # Spring Annotation
```

## 🔧 Fonctionnement

### 1. Couche DAO
```java
public interface IDao {
    double getData(); // Retourne une valeur numérique
}

@Component("d")
public class DaoImpl implements IDao {
    public double getData() {
        System.out.println("Version base de données");
        return 34;
    }
}
```

### 2. Couche Métier
```java
public interface IMetier {
    double calcul(); // Effectue un calcul complexe
}

@Component("metier")
public class MetierImpl implements IMetier {
    @Autowired @Qualifier("d")
    private IDao dao; // Injection de dépendance
    
    public double calcul() {
        double t = dao.getData();
        return t*12*Math.PI/2*Math.cos(t);
    }
}
```

##  Modes d'Injection Démonstrés

### 1. Manuel (Pres1)
```java
DaoImplV2 d = new DaoImplV2();
MetierImpl metier = new MetierImpl(d);
```
![{0922D095-CF41-489F-BEE3-F95B362966E1}](https://github.com/user-attachments/assets/7de68bf5-cb20-4f28-9056-aa56398d294f)

**Résultat**
### 2. Par Réflexion (Pres2)
```java
// Lit la config depuis un fichier
Class cDao = Class.forName(daoClassName);
IDao d = (IDao) cDao.newInstance();
```
![image](https://github.com/user-attachments/assets/f2e7d696-e7e7-4544-bd7d-21d6721e18f2)

**Résultat**
### 3. Spring XML (PresSpringXML)
```xml
<beans>
    <bean id="d" class="dao.DaoImpl"/>
    <bean id="metier" class="Metier.MetierImpl">
        <property name="dao" ref="d"/>
    </bean>
</beans>
```
![{13E4E274-20DE-4B57-AC3A-D9AAE76E80CA}](https://github.com/user-attachments/assets/0d78b8c3-3871-45c6-9de3-1968a8ae122e)

**Résultat**
### 4. Spring Annotation (PresSpringAnnotation)
```java
@Configuration
@ComponentScan({"dao", "metier", "ext"})
public class AppConfig {}
```

##  Comparaison des Approches

| Méthode               | Avantages                          | Inconvénients                 |
|-----------------------|-----------------------------------|-------------------------------|
| **Manuelle**          | Simple, contrôle total            | Peu flexible                  |
| **Réflexion**         | Configurable (fichier externe)    | Complexe, peu type-safe       |
| **Spring XML**        | Configuration centralisée         | Verbose                       |
| **Spring Annotation** | Élégant, puissant                | Moins visible que XML         |



##  Pratiques Illustrées

- **Inversion de Contrôle** : Délégation à Spring
- **Couplage faible** : Interfaces entre couches
- **Configurabilité** : Plusieurs implémentations DAO
- **Testabilité** : Facilité de mock des dépendances

##  Dépendances

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.3.9</version>
    </dependency>
</dependencies>
```

