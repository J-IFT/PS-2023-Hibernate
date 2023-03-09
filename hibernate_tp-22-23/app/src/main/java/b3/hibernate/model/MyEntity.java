package b3.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
/**
 * Exemple simpliste d'entité.
 */
@Entity
@Table(name = "Entity")
public class MyEntity {
 
    @Id
    private Integer myId;

    public Integer getMyId() {
        return myId;
    }

	public void setMyId(Integer myId) {
		this.myId = myId;
	}
}
