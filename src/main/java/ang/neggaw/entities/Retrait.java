package ang.neggaw.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "R")
@AllArgsConstructor @NoArgsConstructor
public class Retrait extends Operation{

}
