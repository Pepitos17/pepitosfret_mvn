package pepitosfret.io.pepitosfret.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToMany(mappedBy="roles")
    private Set<User> users = new HashSet<>();
}
