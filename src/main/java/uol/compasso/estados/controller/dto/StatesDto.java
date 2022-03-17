package uol.compasso.estados.controller.dto;

import org.springframework.data.domain.Page;
import uol.compasso.estados.modelo.State;

public class StatesDto {

	private Long id;
	private String name;
	private String region;
	private String population;
	private String Capital;
	private float areaSize;

	public StatesDto(Long id, String name, String region, String population, String capital, float areaSize) {
		this.id = id;
		this.name = name;
		this.region = region;
		this.population = population;
		Capital = capital;
		this.areaSize = areaSize;
	}

	public StatesDto(State state) {

	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public String getCapital() {
		return Capital;
	}

	public void setCapital(String capital) {
		Capital = capital;
	}

	public float getAreaSize() {
		return areaSize;
	}

	public void setAreaSize(float areaSize) {
		this.areaSize = areaSize;
	}

	public static Page<StatesDto> convert(Page<State> states) {
		return states.map(StatesDto::new);
	}

}
