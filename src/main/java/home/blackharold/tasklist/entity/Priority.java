package home.blackharold.tasklist.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "priority", schema = "tasklist")
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Priority {
    private Long id;
    private String title;
    private String color;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Basic
    @Column(name = "title", length = 45)
    public String getTitle() {
        return title;
    }

    @Basic
    @Column(name = "color", length = 45)
    public String getColor() {
        return color;
    }
}
