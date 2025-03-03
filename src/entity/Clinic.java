package entity;
import jakarta.persistence.*;

@Entity
@Table(name="Clinic")
public class Clinic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clinicId")
	private int clinicId;

	@Column(name = "location")
	private String location;

	public Clinic() {
	}
	
	public int getClinicid() {
		return clinicId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
