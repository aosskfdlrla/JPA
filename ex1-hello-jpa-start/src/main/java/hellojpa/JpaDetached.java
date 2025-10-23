package hellojpa;

import hellojpa.Entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaDetached {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /*
               준영속 상태
               영속 -> 준영속
               영속성 컨텍스트의 기능을 사용할 수 없는 상태
             */
            // 1차 캐시에는 없으므로 select 쿼리는 나간다.
            Member member = em.find(Member.class, 201L);
            member.setName("AAAAA");

            // 영속성 컨텍스트에서 제외
            em.detach(member);

            // 영속성 컨텍스트 초기화
            em.clear();

            // 1차 캐시 비었으므로 다시 DB 조회
            Member member2 = em.find(Member.class, 201L);
            System.out.println("member2" + member2.getName());
            System.out.println("=========================");

            // 제외 했음으로 아무런 일이 일어나지 않는다..
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

        em.close();
        emf.close();
    }
}
