package ua.rd.runner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ua.rd.domain.entities.pizza.Pizza;
import ua.rd.domain.entities.pizza.PizzaType;
import ua.rd.repository.GenericRepository;
import ua.rd.repository.jpa.JPAPizzaRepository;

public class JPAWhithoutSpringApp {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate");
        EntityManager em = emf.createEntityManager();
        GenericRepository<Pizza> pizzaRep = (GenericRepository<Pizza>) new JPAPizzaRepository(em);

        Pizza pizza1 = new Pizza("Pizza 1", 5.39, PizzaType.VEGETERIAN);
        Pizza pizza2 = new Pizza("Pizza 2", 4.45, PizzaType.SEA);
        Pizza pizza3 = new Pizza("Pizza 3", 6.46, PizzaType.MEAT);
        Pizza pizza4 = new Pizza("Pizza 4", 6.46, PizzaType.MEAT);
        Pizza pizza5 = new Pizza("Pizza 5", 6.46, PizzaType.MEAT);

        try {

            System.out.println(pizza1.getId());
            System.out.println(pizza2.getId());
            System.out.println(pizza3.getId());
            System.out.println(pizza4.getId());
            System.out.println(pizza5.getId());
            pizza2.setId(5L);
            pizzaRep.remove(3L);
            
            
//            for (int i = 1; i < 6; i++) {
//                System.out.println(pizzaRep.getPizzaByID(i));
//            }

//            System.out.println(pizzaRep.update(pizza1));
//            System.out.println(pizzaRep.update(pizza2));
//            System.out.println(pizzaRep.update(pizza3));
//            System.out.println(pizzaRep.update(pizza4));
//            System.out.println(pizzaRep.update(pizza5));

        } finally {
            em.close();
            emf.close();
        }
    }

    private static void placeObjectToDB(EntityManager em, Object obj) {
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }
}
