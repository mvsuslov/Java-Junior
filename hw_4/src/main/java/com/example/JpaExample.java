import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaExample {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("personPU");

    public static void main(String[] args) {
        Person person1 = new Person("Alice", 25);
        Person person2 = new Person("Bob", 30);
        Person person3 = new Person("Charlie", 35);

        // Сохранение персоны
        savePerson(person1);
        savePerson(person2);
        savePerson(person3);

        // Обновление персоны
        updatePerson(person1);
        updatePerson(person2);
        updatePerson(person3);

        // Удаление персоны
        deletePerson(person1.getId());
        deletePerson(person2.getId());
        deletePerson(person3.getId());
    }

    public static void savePerson(Person person) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.close();
        System.out.println("Персона сохранена: " + person.getName());
    }

    
    public static void updatePerson(Person person) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(person);
        em.getTransaction().commit();
        em.close();
        System.out.println("Персона обновлена: " + person.getName());
    }

    public static void deletePerson(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Person person = em.find(Person.class, id);
        if (person != null) {
            em.remove(person);
            System.out.println("Персона удалена: " + person.getName());
        } else {
            System.out.println("Такой персоны нет.");
        }
        em.getTransaction().commit();
        em.close();
    }
}
