/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.SpecialtyEnum;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class VetController {

	private static final String VIEWS_VET_CREATE_OR_UPDATE_FORM = "vets/createOrUpdateVetForm";

	private final VetService vetService;

	@Autowired
	public VetController(final VetService clinicService) {
		this.vetService = clinicService;
	}

	  @ModelAttribute("specialty_enum")
	    public List<SpecialtyEnum> getSpecialtyEnum(){
	        return Arrays.stream(SpecialtyEnum.class.getEnumConstants()).collect(Collectors.toList());
	    }
	  
	@GetMapping(value = { "/vets" })
	public String showVetList(final Map<String, Object> model) {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for Object-Xml mapping
		final Vets vets = new Vets();
		vets.getVetList().addAll(this.vetService.findVets());
		model.put("vets", vets);
		return "vets/vetList";
	}

	@GetMapping(value = { "/vets.xml" })
	public @ResponseBody Vets showResourcesVetList() {
		// Here we are returning an object of type 'Vets' rather than a collection of
		// Vet
		// objects
		// so it is simpler for JSon/Object mapping
		final Vets vets = new Vets();
		vets.getVetList().addAll(this.vetService.findVets());
		return vets;
	}
	
@GetMapping("/vets/{id}/delete")
        public String deleteCliente(@PathVariable("id") final int vetId,final ModelMap model) {
            final Vet vet =this.vetService.findVetById(vetId);
            this.vetService.delete(vet);
            model.addAttribute("message","Client deleted");
            final Collection<Vet> results = this.vetService.findVets();
            model.put("selections", results);
            return "redirect:/vets";
        }

	@GetMapping(value = "/vets/new")
	public String initCreationForm(ModelMap model) {
		Vet vet = new Vet();
		List<SpecialtyEnum> especialidades  = new ArrayList<SpecialtyEnum>();
		especialidades.add(SpecialtyEnum.DENTISTRY);
		especialidades.add(SpecialtyEnum.SURGERY);
		especialidades.add(SpecialtyEnum.RADIOLOGY);
		model.addAttribute("specialties", especialidades);
		model.addAttribute("vet", vet);
		return VIEWS_VET_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/vets/new")
	public String processCreationForm(@Valid Vet vet, BindingResult result) {//, @Valid @ModelAttribute("specialty") Specialty specialty ) {

		if (result.hasErrors()) {
			return VIEWS_VET_CREATE_OR_UPDATE_FORM;
		} else {
			
		
			this.vetService.saveVet(vet);

			return "redirect:/vets";
		}
	}

	@GetMapping(value = "/vets/{vetId}/edit")
	public String initUpdateVetForm(@PathVariable("vetId") int vetId, Model model) {
		
		List<SpecialtyEnum> especialidades  = new ArrayList<SpecialtyEnum>();
		especialidades.add(SpecialtyEnum.DENTISTRY);
		especialidades.add(SpecialtyEnum.SURGERY);
		especialidades.add(SpecialtyEnum.RADIOLOGY);
		model.addAttribute("specialties", especialidades);
		Vet vet = this.vetService.findVetById(vetId);
		model.addAttribute(vet);
		return VIEWS_VET_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/vets/{vetId}/edit")
	public String processUpdateVetForm(@Valid Vet vet, BindingResult result, @PathVariable("vetId") int vetId) {
		if (result.hasErrors()) {
			return VIEWS_VET_CREATE_OR_UPDATE_FORM;
		} else {
			vet.setId(vetId);
			this.vetService.saveVet(vet);
			return "redirect:/vets";
		}
	}

}

