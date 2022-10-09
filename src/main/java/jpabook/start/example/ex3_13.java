package jpabook.start.example;

import jpabook.start.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author holyeye
 */
public class ex3_13 {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    public static void main(String[] args) {
        Member member = createMember("memberA", "user1");

        member.setUsername("hi");

        mergeMember(member);
    }

    static Member createMember(String id, String username){
        EntityManager em1 = emf.createEntityManager();
        EntityTransaction tx1 = em1.getTransaction();
        tx1.begin();

        Member member = new Member();
        member.setId(id);
        member.setUsername(username);
        member.setAge(15);

        em1.persist(member);
        tx1.commit();

        em1.close();

        return member;
    }

    static void mergeMember(Member member){
        EntityManager em2 = emf.createEntityManager();
        EntityTransaction tx2 = em2.getTransaction();

        tx2.begin();
        Member mergeMember = em2.merge(member);
        tx2.commit();

        System.out.println("member= " + member.getUsername());
        System.out.println("merge member= " + mergeMember.getUsername());

        System.out.println("em2 contains member = " + em2.contains(member));
        System.out.println("em2 contains merge member = " + em2.contains(mergeMember));

        em2.close();
    }
}
