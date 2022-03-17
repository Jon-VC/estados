package uol.compasso.estados.controller.form;

import org.springframework.beans.factory.annotation.Autowired;
import uol.compasso.estados.modelo.State;
import uol.compasso.estados.repository.StateRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UptadeStateForm {

	@Autowired
	private StateRepository stateRepository;
	
	@NotNull @NotEmpty
	private String name;
	
	@NotNull @NotEmpty
	private String region;

	@NotNull @NotEmpty
	private String population;

	@NotNull @NotEmpty
	private String capital;

	@NotNull @NotEmpty
	private Long areaSize;

	public void setName(String name) {
		this.name = name;
	}

	public void setRegion(String region) {
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

	public <stateRepository> State update(Long id) {
		State state = stateRepository.getById(id);

		state.setName(this.name);
		state.setRegion(this.region);
		state.setPopulation(this.population);
		state.setCapital(this.capital);
		state.setAreaSize(this.areaSize);
		
		return state;
	}
	
}
