package hellojpa;

import hellojpa.Entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member findMember = em.find(Member.class, 2L);
            findMember.setName("HelloJPA");

            List<Member> findMemberList = em.createQuery("select m from Member as m", Member.class).getResultList();

            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(2)
                    .getResultList();

            for (Member member : findMemberList) {
                System.out.println("member.name = " + member.getName());
            }

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            tx.commit();   
        } catch (Exception e) {
            tx.rollback();
        }

        em.close();
        emf.close();
    }
}
