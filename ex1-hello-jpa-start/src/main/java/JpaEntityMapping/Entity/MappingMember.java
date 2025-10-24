package JpaEntityMapping.Entity;

import jakarta.persistence.*;
import org.hibernate.sql.results.graph.embeddable.internal.AggregateEmbeddableResultImpl;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 매핑 어노테이션
 * 1) "@Column": 컬럼 매핑
 * 2) "@Temporal": 날짜 타입 매핑
 * 3) "@Enumerated": enum 타입 매핑
 * 4) "@Lob": BLOB, CLOB 매핑
 * 5) "@Transient": 특정 필드를 컬럼에 매핑하지 않음 (매핑 무시)
 */
@Entity
@Table(name = "MappingMember")
// 시퀀스 설정
//@SequenceGenerator(
//        name = "MappingMemberSeq",
//        sequenceName = "member_seq",
//        initialValue = 1, allocationSize = 1)
// 테이블 설정
//@TableGenerator(
//        name = "MEMBER_SEQ_GENERATOR",
//        table = "MY_SEQUENCE",
//        pkColumnName = "MEMBER_SEQ", allocationSize = 1)
public class MappingMember {

    // 기본키 매핑 어노테이션
    // "@GeneratedValue": 기본키 자동 생성
    // 속성
    // 1) IDENTITY : 데이터베이스에 위임
    // 2) SEQUENCE : 데이터베이스 시퀀스 오브젝트 사용
    // -> @SequenceGenerator로 이름과 사이즈를 직접 설정해줄 수 있다.
    // 3) TABLE : 키 생성용 테이블 사용
    // 4) AUTO : DB에 따라 자동 지정, 기본값으로 지정됨.
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MappingMemberSeq")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY에선 DB에 넣어봐야 기본값을 알 수있다.
    private Long Id;

    // "@Column" 속성
    // 1) name: 필드와 매핑할 테이블의 컬럼 이름
    // 2) insertable: 등록 가능 여부 (기본값: true)
    // 3) updatable: 변경 가능 여부 (기본값: true)
    // -> 업데이트문, insert 문 나갈때 변경 할건지 말건지 정함.
    // 4) nullable: null 값의 허용 여부를 설정. false로 하면 DDL 생성시 not null 제약조건이 붙음.
    // 5) unique: 한 컬럼에 유니크 제약조건을 걸 때 사용 (이름 설정이 안됨.)
    // 6) length: 길이 설정
    // 7) columnDefinition(DDL): 데이터베이스 컬럼 정보를 직접 줄 수 있다.
    // 8) precision, scale: 매우 큰수를 설정할때
    @Column(name = "name")
    private String userName;

    private Integer age;

    // "@Enumerated" 속성
    // 1) EnumType.ORDINAL: enum 순서를 데이터베이스에 저장.
    // 2) EnumType.STRING: enum 이름을 데이터베이스에 저장.
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // 최신버전은 LocalDateTime 으로 하면 생략 가능.
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    private LocalDateTime deleteDate;

    // 속성은 없음. 문자면 BLOB, byte면 CLOB
    @Lob
    private String description;

    // 필드 매핑 X,
    // 주로 메모리 상에 임시로 값을 가지고 싶을 떄만 사용.
    @Transient
    private Integer tmep;

    public MappingMember() {}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
