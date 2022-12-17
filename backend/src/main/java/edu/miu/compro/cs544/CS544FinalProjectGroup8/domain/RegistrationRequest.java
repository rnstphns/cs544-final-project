package edu.miu.compro.cs544.CS544FinalProjectGroup8.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class RegistrationRequest {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Student student;
}
