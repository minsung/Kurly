package ms.study.kurly.domain.verification;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PRIVATE)
@Getter
@ToString
@Builder
@DynamicUpdate
@Entity
@EntityListeners(AuditingEntityListener.class)
public class MobileVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    private String verificationCode;

    @Column()
    private String hashValue;

    @Column(nullable = false)
    private boolean isVerified;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;
}
