package entity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@DiscriminatorValue("ContractCustomer")
public class ContractCustomer extends Customer {

	@Column(name = "startDate")
	private Date startDate;

	@Column(name = "endDate")
	private Date endDate;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
