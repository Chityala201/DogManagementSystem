package DMS.controllers;

import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import DMS.Models.Dog;
import DMS.Models.Trainer;
import DMS.repository.DogRepository;
import DMS.repository.TrainerRepository;

@Controller
public class DogController {
	ModelAndView mv = new ModelAndView();
	@Autowired
	DogRepository dogRepo;
	@Autowired
	TrainerRepository trainerRepo;

	private Dog dog;

//	@RequestMapping("dogHome")
	// public String home() {
	// return"home";

//}
	@RequestMapping("dogHome")
	public ModelAndView home() {
		mv.setViewName("home");
		return mv;
	}

	@RequestMapping("add")
	public ModelAndView add() {
		mv.setViewName("addNewDog.html");
		Iterable<Trainer>trainerlist = trainerRepo.findAll();
		mv.addObject("trainers", trainerlist);
		return mv;
	}

	@RequestMapping("addNewDog")
	public ModelAndView addNewDog(Dog dog,@RequestParam ("trainerId")int id) {
		Trainer trainer = trainerRepo.findById(id).orElse(new Trainer());
		dog.setTrainer(trainer);
		dogRepo.save(dog);
		mv.setViewName("home");
		return mv;
	}

	@RequestMapping("addTrainer")
	public ModelAndView addTrainer() {
		mv.setViewName("addNewTrainer");
		return mv;
	}

	@RequestMapping("trainerAdded")
	public ModelAndView addTrainer(Trainer trainer) {
		trainerRepo.save(trainer);
		mv.setViewName("home");
		return mv;
	}

	@RequestMapping("viewModifyDelete")
	public ModelAndView viewDogs() {
		mv.setViewName("viewDogs");
		Iterable<Dog> dogList = dogRepo.findAll();
		mv.addObject("dogs", dogList);
		return mv;
	}

	@RequestMapping("editDog")
	public ModelAndView editDog(Dog dog) {
		dogRepo.save(dog);
		mv.setViewName("editDog");
		return mv;
	}
//	
//	@RequestMapping("deleteDog")
//	public ModelAndView deleteDog(Dog dog) {
//		// based in id
//		java.util.Optional<Dog> dogFound = dogRepo.findById(dog.getId());
//		if(dogFound.isPresent()) {
//			dogRepo.delete(dog);	
//		}
//		return home();

	// }
	// based on the name

//	List<Dog> dogsFound =dogRepo.findByName(dog.getName());
//	for(Dog d : dogsFound) {
//		dogRepo.delete(d);
//	}
//	return home();

//	Dog d = dogRepo.findById(dog.getId()).orElse(new Dog());
//	dogRepo.delete(d);
//	return home();
//}
	@RequestMapping("search")
	public ModelAndView searchById(int id) {
		Dog dogfound = dogRepo.findById(id).orElse(new Dog());
		mv.addObject(dogfound);
		mv.setViewName("searchResults");
		return mv;
	}
}
