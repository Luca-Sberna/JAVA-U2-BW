package BEBuildWeek2.Epic_Energy_Services_CRM.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Indirizzo;
import BEBuildWeek2.Epic_Energy_Services_CRM.repositories.IndirizzoRepository;

@Service
public class IndirizzoService {
	private final IndirizzoRepository indirizzoRepository;

	@Autowired
	public IndirizzoService(IndirizzoRepository indirizzoRepository) {
		this.indirizzoRepository = indirizzoRepository;
	}

	public List<Indirizzo> getAllIndirizzi() {
		return indirizzoRepository.findAll();
	}

	public Indirizzo getIndirizzoById(UUID idIndirizzo) {
		return indirizzoRepository.findById(idIndirizzo).orElse(null);
	}

	public Indirizzo createIndirizzo(Indirizzo indirizzo) {
		return indirizzoRepository.save(indirizzo);
	}

	public Indirizzo updateIndirizzo(UUID idIndirizzo, Indirizzo indirizzo) {
		if (indirizzoRepository.existsById(idIndirizzo)) {
			indirizzo.setIdIndirizzo(idIndirizzo);
			return indirizzoRepository.save(indirizzo);
		}
		return null;
	}

	public void deleteIndirizzo(UUID idIndirizzo) {
		indirizzoRepository.deleteById(idIndirizzo);
	}
}