package org.festimate.team.matching.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.festimate.team.common.entity.BaseTimeEntity;
import org.festimate.team.festival.entity.Festival;
import org.festimate.team.festival.entity.Participant;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Getter
@Entity
@DynamicUpdate
@Table(name = "matching")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Matching extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matchingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "festival_id", nullable = false)
    private Festival festival;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_participant_id", nullable = false)
    private Participant applicantParticipant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_participant_id", nullable = false)
    private Participant targetParticipant;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MatchingStatus status;

    @Column(nullable = false)
    private LocalDateTime matchDate;

    @Builder
    public Matching(Festival festival, Participant applicantParticipant, Participant targetParticipant,
                    MatchingStatus status, LocalDateTime matchDate) {
        this.festival = festival;
        this.applicantParticipant = applicantParticipant;
        this.targetParticipant = targetParticipant;
        this.status = status;
        this.matchDate = matchDate;
    }
}
