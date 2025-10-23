package hellojpa;

import hellojpa.Entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaFlush {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // flush : 영속성 컨텍스트의 변경내용을 데이터베이스에 반영
            // 영속성 컨텍스트의 내용과 DB와 내용을 맞추는 과정이다.
            // 변경 감지, 수정된 엔티티 쓰기 지연 SQL 저장소에 등록, 저장소의 쿼리를 DB에 전송

            /*
             * 호출하는 방법
             * 1) em.flush() : 직접 호출
             * 2) commit() : 트랜잭션 커밋시 자동 호출
             * 3) JPQL 쿼리 실행 : JPQL 쿼리 실행시 호출됨.
             *
             * => 영속성 컨택스트 내용을 비우는 것이 아님.
             */
            Member member = new Member(201L, "member201");
            em.persist(member);

            // 미리 쿼리를 보고싶거나 미리 반영하고 싶다.
            // flush()를 호출하고 commit()을 호출을 안하면 DB에 반영되지는 않음.
            em.flush();
            System.out.println("=======================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

        em.close();
        emf.close();
    }
}
