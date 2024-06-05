package com.gl.eventmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registration")
public class Registration {
    /*
        task is to add a new column id of type Long.
        It should support the auto-generation of the primary key.
        Use appropriate annotation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /*
        task is to add a new column name of type String.
        It should not be null.
        Use appropriate annotation.
     */
    @Column(nullable = false)
    private String name;
    /*
        task is to add a new column email of type String.
        It should not be null.
        Use appropriate annotation.
     */
    @Column(nullable = false)
    private String email;
    /*
        task is to add a new column contactNumber of type String.
        It should not be null.
        Use appropriate annotation.
     */
    @Column(name = "contactNo", nullable = false)
    private String contactNumber;
    /*
        task is to add a new column status of type RegistrationStatus.
        It should not be null.
        Use appropriate annotation.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RegistrationStatus status;

    /*
        task is to establish the mapping between Registration and Event
        it should be a appropriate mapping as per the diagram
     */
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
