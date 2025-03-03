package application;

import dao.ClinicDAO;
import entity.Clinic;
import entity.Customer;
import entity.ContractCustomer;
import entity.BasicProfile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.Date;

public class ClinicApp {
    public static void main(String[] args) {
        ClinicDAO dao = new ClinicDAO();

        Clinic newClinic = new Clinic();
        newClinic.setLocation("New York");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinic");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(newClinic);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }

        ContractCustomer newCustomer = new ContractCustomer();
        newCustomer.setClinic(newClinic);
        newCustomer.setFirstName("Joonas");
        newCustomer.setLastName("Mäkinen");
        newCustomer.setStartDate(new Date());
        newCustomer.setEndDate(new Date());

        BasicProfile basicProfile = new BasicProfile();
        basicProfile.setCustomer(newCustomer);
        basicProfile.setBirthyear(1999);
        basicProfile.setWeight(70);
        basicProfile.setHeight(175);

        newCustomer.setBasicProfile(basicProfile);

        em = emf.createEntityManager();
        tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(newCustomer);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}