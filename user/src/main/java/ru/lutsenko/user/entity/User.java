package ru.lutsenko.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Users")
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "keycloak_id")
    private UUID keycloakId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "email")
    private String email;

    @Column(name = "passport")
    private String passport;

    @Column(name = "is_volunteer")
    private Boolean isVolunteer;

    @Temporal(TemporalType.DATE)
    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "is_approved_account")
    private Boolean isApprovedAccount;

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Feedback> sentFeedbacks = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Feedback> receivedFeedbacks = new ArrayList<>();

    @ElementCollection
    @Column(name = "document_id")
    @CollectionTable(name = "Users_documentIds", joinColumns = @JoinColumn(name = "owner_id"))
    private List<String> documentIds = new ArrayList<>();

    @Column(name = "avatar_id")
    private String avatarId;
}
