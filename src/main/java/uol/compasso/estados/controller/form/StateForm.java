package uol.compasso.estados.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import uol.compasso.estados.modelo.Region;
import uol.compasso.estados.modelo.State;
import uol.compasso.estados.repository.StateRepository;

public class StateForm {

	@Autowired
	private StateRepository stateRepository;

	@NotNull @NotEmpty
	private String name;

	@NotNull @NotEmpty
	private Region region;

	@NotNull @NotEmpty
	private String population;

	@NotNull @NotEmpty
	private String capital;

	@NotNull @NotEmpty
	private Long areaSize;

	public void setName(String name) {
		this.name = name;
	}

	public void setRegion(@NotNull @NotEmpty Region region) {
		this.region = region;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public void setAreaSize(Long areaSize) {
		this.areaSize = areaSize;
	}

	public <stateRepository> State converter() {
		State state = (State) stateRepository.findByName(name);
		return new State(name, region, population, capital, areaSize);
	}

}
