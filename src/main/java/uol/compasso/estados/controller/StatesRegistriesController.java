package uol.compasso.estados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import uol.compasso.estados.controller.dto.StatesDto;
import uol.compasso.estados.controller.form.StateForm;
import uol.compasso.estados.controller.form.UptadeStateForm;
import uol.compasso.estados.modelo.Region;
import uol.compasso.estados.modelo.State;
import uol.compasso.estados.repository.StateRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StatesRegistriesController {
	
	@Autowired
	private StateRepository stateRepository;
	
	@GetMapping("/states")
	@Cacheable(value = "statesList")
	public Page<StatesDto> list(@RequestParam(required = false) Region region,
								@RequestParam(required = false) String population,
								@RequestParam(required = false) String areaSize,
								@PageableDefault(sort = "id", direction = Direction.ASC) Pageable pageable) {

		Page<State> state;
		if (region != null) {
			state = stateRepository.findByName(String.valueOf(region), pageable);
			return StatesDto.convert(state);
		} else if (population != null){
		state = stateRepository.findByName(population, pageable);
		return StatesDto.convert(state);
		} else if (areaSize != null) {
		state = stateRepository.findByName(areaSize, pageable);
		return StatesDto.convert(state);
		} else {
			state = stateRepository.findAll(pageable);
			return StatesDto.convert(state);
		}
	}
	
	@PostMapping("/states")
	@Transactional
	@CacheEvict(value = "stateList", allEntries = true)
	public ResponseEntity<StatesDto> register(@RequestBody @Valid StateForm form, UriComponentsBuilder uriBuilder) {
		State state = form.converter();
		stateRepository.save(state);
		
		URI uri = uriBuilder.path("/api/states/{id}").buildAndExpand(state.getId()).toUri();
		return ResponseEntity.created(uri).body(new StatesDto(state));
	}
	
	@PutMapping("/states/{id}")
	@Transactional
	@CacheEvict(value = "stateList", allEntries = true)
	public ResponseEntity<StatesDto> update(@PathVariable Long id, @RequestBody @Valid UptadeStateForm form) {
		Optional<State> optional = stateRepository.findById(id);
		if (optional.isPresent()) {
			State state = form.update(id);
			return ResponseEntity.ok(new StatesDto(state));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/states/{id}")
	@Transactional
	@CacheEvict(value = "stateList", allEntries = true)
	public ResponseEntity<?> remove(@PathVariable Long id) {
		Optional<State> optional = stateRepository.findById(id);
		if (optional.isPresent()) {
			stateRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}
