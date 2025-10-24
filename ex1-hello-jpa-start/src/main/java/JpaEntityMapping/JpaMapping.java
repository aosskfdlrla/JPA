package JpaEntityMapping;

import JpaEntityMapping.Entity.MappingMember;
import hellojpa.Entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMapping {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // IDENTITY 설정에서는 DB에 INSERT 해봐야 기본키 값을 알 수 있기 때문에
            // 영속성 컨텍스트에서 1차 캐시에 관리하려면 기본키 값이 필요하다.
            // 그래서 우선적으로 insert 쿼리를 날린 후에 그 기본값을 읽어온다.
            System.out.println("===================");
//            MappingMember member = new MappingMember();
//            member.setAge(12);
//            member.setUserName("member");
            //em.persist(member);
            System.out.println("===================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

        em.close();
        emf.close();
    }
}
