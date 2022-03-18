package uol.compasso.estados.modelo;

import javax.persistence.*;

@Entity
public class State {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Enumerated
	private Region region;
	private String population;
	private String capital;
	private float areaSize;

	public State() {
	}

	public State(String name, Region region, String population, String capital, float areaSize) {
		this.name = name;
		this.region = region;
		this.population = population;
		this.capital = capital;
		this.areaSize = areaSize;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Region getRegion() {
		return region;
	}

	public String getPopulation() {
		return population;
	}

	public String getCapital() {
		return capital;
	}

	public float getAreaSize() {
		return areaSize;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public void setAreaSize(float areaSize) {
		this.areaSize = areaSize;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
