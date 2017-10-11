package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="brand")
public class Brand extends AbstractEntity{

	private String name;
	
	@OneToMany(mappedBy="brand")
	private List<Transporter> transporters = new ArrayList<>();

	public Brand(String name) {
		this.name = name;
	}

	public Brand() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
