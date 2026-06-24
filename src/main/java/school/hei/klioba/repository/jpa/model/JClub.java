package school.hei.klioba.repository.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "club")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JClub {

  @Id private String id;

  private String name;

  @Column(nullable = false)
  private boolean active = true;

  @ManyToMany(mappedBy = "clubs")
  private List<JUser> users = new ArrayList<>();
}
