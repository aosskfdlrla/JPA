package hellojpa;

import hellojpa.Entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

/*
* 영속성 컨텍스트
* 엔티티 매니저를 통해 Persistence Context에 접근하는것.
*
* 챙기는 이점
* 1) 1차 캐시에서 조회 - 한 트랜젝션 안에서만 의미가 있음.
* 2) 영속성 텐티티의 동일성 보장
* 3) 트랜잭션을 지원하는 쓰기 지연
* 4) 변경감지
* */
public class PersistenceContextMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
//            // 1. 비영속 상태 - 객체 생성만
//            Member member = new Member();
//            member.setId(100L);
//            member.setName("AAAAA");
//
//            // 2. 영속 - Persistence Context에 추가
//            System.out.println("=====BEFORE=====");
//            em.persist(member); // 1차 캐시에 추가됨.
//            System.out.println("===== AFTER=====");
//            // -> BEFORE AFTER 상관없이 쿼리가 나가지 않음.
//
//            // 1차 캐시에서 조회 - SELECT 쿼리가 나가지 않았음.
//            Member findMember = em.find(Member.class, 100L);
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());

            // 중복조회
            Member findMember1 = em.find(Member.class, 100L);
            Member findMember2 = em.find(Member.class, 100L);

            // 1) 영속성 컨텍스트의 1차 캐시를 먼저 조회
            // 2) 1차 캐시에 없으므로 DB에서 조회해서 가져와 1차캐시에 저장후 반환
            // 3) 2번째 조회할 때 1차 캐시에 있으므로 1차 캐시 반환
            System.out.println("findMember1 = " + findMember1.getName());
            System.out.println("findMember2 = " + findMember2.getName());

            // 동일성도 보장해준다.
            System.out.println("findMember1 == findMEmber2 >> " + (findMember1 == findMember2));

            // 트랜잭션 지연 쓰기
            System.out.println("===== BEFORE =====");
            Member member = new Member();
            member.setId(101L);
            member.setName("NewMember1");

            Member member2 = new Member();
            member2.setId(102L);
            member2.setName("NewMember2");

            Member member3 = new Member();
            member3.setId(103L);
            member3.setName("NewMember3");

            em.persist(member);
            em.persist(member2);
            em.persist(member3);

            // 변경감지 - persist 또는 조회해서 1차 캐시에 등록되었을 순간의 값과 나중에 달라진 값을 다 비교해서 달라진 값을 Update 쿼리 날림
            member3.setName("OldMember");
            System.out.println("===== AFTER =====");
            System.out.println("쿼리 실행 없고 SQL 저장소에 저장");

            tx.commit(); // 한번에 쿼리 실행
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
