package dao;

import entity.Clinic;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class ClinicDAO {
    private EntityManagerFactory emf;

    public ClinicDAO() {
        emf = Persistence.createEntityManagerFactory("clinic");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void createClinic(Clinic clinic) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(clinic);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Clinic findClinic(int clinicId) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clinic.class, clinicId);
        } finally {
            em.close();
        }
    }

    public List<Clinic> findAllClinics() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Clinic c", Clinic.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void updateClinic(Clinic clinic) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(clinic);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void deleteClinic(int clinicId) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Clinic clinic = em.find(Clinic.class, clinicId);
            if (clinic != null) {
                em.remove(clinic);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}