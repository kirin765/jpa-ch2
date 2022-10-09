package jpabook.start.example;

import jpabook.start.Member;

import javax.persistence.*;
import java.util.List;

/**
 * @author holyeye
 */
public class ex3_6 {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {


            tx.begin(); //트랜잭션 시작

            Member member = new Member();
            member.setId("member1");
            member.setUsername("hello");
            member.setAge(20);

            em.persist(member);

            //JPQL 실행시 자동으로 em.flush() 수행된다.
            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
            List<Member> resultList = query.getResultList();
            for (Member member1 : resultList) {
                System.out.println(member1.getId());
            }

            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }
}
