package hellojpa.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


/**
 * 엔티티 매핑
 * 1) 객체와 테이블 매핑: @Entity, @Table
 * 2) 핑드와 컬럼 매칭: @Column
 * 3) 기본키 매핑: @Id
 * 4) 연관관계 매칭: @OneToMany, @ManyToOne
 * "@Entity"
 * 이것이 붙은 class는 JPA가 관리한다. 엔티티라 명했음.
 * 기본생성자가 필요, final, enum, interface, inner class X
 * 속성
 * "@Table" : 엔티티와 테이블 매핑
 * 1) name : 매핑할 테이블 이름
 * 2) catalog : DB Catalog 매핑
 * "@Column"
 * 제약조건 추가, 유니크 제약조건 추가 등 DDL 생성할 수 있음.
 */
@Entity
public class Member {

    @Id
    private Long id;

    @Column(unique = true, length = 10)
    private String name;

    private Integer age;

    public Member() {}

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
