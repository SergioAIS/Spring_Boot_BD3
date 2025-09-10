package com.app.uvbd3_backe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data @AllArgsConstructor @NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    private String address;

    @Column(name = "email_id", nullable = false, unique = true)
    private String email;

    private String phone;

    //En JPA, y sobre todo en proyectos con spring boot, los FetchType definen la manera de como y cuando se cargan las relaciones entre clases/entodades.
    //Esto desde la BASE DE DATOS (en nuestro caso desde la BD que creamos en MySQL
    //FetchType.LAZY: Esto se conoce como carga perezosa. Lo que quiere decir que la relación entre las entidades participantes no se cargan de manera inmediata,
    //esto cuando se obtiene la entidad principal. Lo que quiere decir que se cargan cuando realmente se accede a esa relación, en este caso entre Employee y Department.
    //Esto es muy útil cuando queremos optimizar el rendimiento; por ejemplo, esto evita consultas que no son necesarias a la BD
    //Fetch.Type.EAGER: En este caso, las relaciones se cargan de manera inmediata, lo que quiere decir que la relación se carga al mismo tiempo que la entidad principal
    //(en este caso para nosotros, la entidad principal se llama Employee). Esto implica que se hará un JOIN o en algunos casos múltiples consultas de manera automática.
    //Debemos recordar que esto puede provocar cargas innecesarias si no siempre se usan esas relaciones
    //RESUMEN:
        //FetchType.LAZY:: Carga diferida. Solo se recomienda para la mayoría de las colecciones (@OneToMany y también en la relación @ManyToMany)
        //FetchType.EAGER: Carga inmediata. Se debe usar en relaciones que siempre se necesita @ManyToOne, @OneToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
}
