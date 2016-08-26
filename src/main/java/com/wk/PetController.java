package com.wk;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author WaleedK
 * @since 0.0.0
 */
@RestController
@RequestMapping("/pet")
public class PetController {
	@Autowired
	private PetService petService;

	@RequestMapping(method = RequestMethod.POST, consumes = {"multipart/form-data"})
	public void create(@RequestPart("pet") String petString, @RequestPart("image") MultipartFile csvFile) throws IOException {
		petService.save(new ObjectMapper().readValue(petString, Pet.class), csvFile);
	}

	@RequestMapping(value = "{petId}", method = RequestMethod.GET)
	public void get(@PathVariable("petId") int petId) {

	}

	@RequestMapping(value = "{petId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable long petId) {
		petService.delete(petId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Pet> getAll(String query) {
		return petService.getAll(query);
	}

}
