package edu.miu.cs544.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class RegistrationEvent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")

    private LocalDate startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate endDate;
    @OneToMany(cascade= CascadeType.PERSIST)
    private Collection<RegistrationGroup> registrationGroups;
}
