package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Entre;
import com.example.demo.model.Entrepreneur;
import com.example.demo.model.Investor;
import com.example.demo.model.Pitch;
import com.example.demo.repo.PitchesRepo;

@RestController
public class PitchesController {
	@Autowired
	PitchesRepo repo;
	
	@PostMapping(value = "/pitches")
	public Map<String, String> createPitch(@RequestBody Entrepreneur entre) {
		if(entre.getEntrepreneur() == null || entre.getPitchIdea() == null || entre.getPitchTitle() == null || entre.getEquity() == 0.0f || entre.getEquity() > 100) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body error");
		}
		Pitch pitch = new Pitch(
			entre.getEntrepreneur(), 
			entre.getPitchTitle(), 
			entre.getPitchIdea(), 
			entre.getAskAmount(), 
			entre.getEquity()
		);
		repo.save(pitch);
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("id", Integer.toString(pitch.getId()));
		
		return responseMap;
	}
	
	@PostMapping(value = "/pitches/{pitchId}/makeOffer")
	public Map<String, String> makeOffer(@RequestBody Investor invest, @PathVariable("pitchId") int pitchId) {
		if(invest.getInvestor() == null || invest.getComment() == null || invest.getAmount() == 0.0f || invest.getEquity() == 0.0f || invest.getEquity() > 100) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body error");
		}
		Map<String, Object> entreMap = repo.getPitchById(pitchId);
		if(entreMap.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested id doesn't exist");
		}
		
		// Save this counter offer to database where id is pitchId
		repo.saveInvestor(pitchId, invest.getAmount(), invest.getComment(), invest.getEquity(), invest.getInvestor());
		
		Map<String, String> responseMap = new HashMap<>();
		
		responseMap.put("id", Integer.toString(pitchId));
		return responseMap;
	}
	
	@GetMapping(value = "/")
	public List<Map<String, Object>> getPitchesRoot() {
		List<Map<String, Object>> entres = repo.getEntrepreneurs();
		List<Map<String, Object>> pitches = new ArrayList<>();

		for(Map<String, Object> e : entres) {
			List<Map<String, Object>> invest = repo.getInvestorsById((int)e.get("id"));
			Map<String, Object> mp = new HashMap<>(e);
			mp.put("id", Integer.toString((int)mp.get("id")));
			mp.put("pitchTitle", mp.get("pitchtitle"));
			mp.remove("pitchtitle");
			mp.put("pitchIdea", mp.get("pitchidea"));
			mp.remove("pitchidea");
			mp.put("askAmount", mp.get("askamount"));
			mp.remove("askamount");
			mp.put("offers", invest);
			pitches.add(mp);
		}

		return pitches;
	}
	
	@GetMapping(value = "/pitches")
	public List<Map<String, Object>> getPitches() {
		List<Map<String, Object>> entres = repo.getEntrepreneurs();
		List<Map<String, Object>> pitches = new ArrayList<>();

		for(Map<String, Object> e : entres) {
			List<Map<String, Object>> invest = repo.getInvestorsById((int)e.get("id"));
			Map<String, Object> mp = new HashMap<>(e);
			mp.put("id", Integer.toString((int)mp.get("id")));
			mp.put("pitchTitle", mp.get("pitchtitle"));
			mp.remove("pitchtitle");
			mp.put("pitchIdea", mp.get("pitchidea"));
			mp.remove("pitchidea");
			mp.put("askAmount", mp.get("askamount"));
			mp.remove("askamount");
			mp.put("offers", invest);
			pitches.add(mp);
		}

		return pitches;
	}
	
	@GetMapping(value = "/pitches/{pitchId}")
	public Map<String, Object> getPitch(@PathVariable("pitchId") int pitchId) {
		Map<String, Object> entreMap = repo.getPitchById(pitchId);
		if(entreMap.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested id doesn't exist");
		}
		Map<String, Object> mp = new HashMap<>(entreMap);
		List<Map<String, Object>> invest = repo.getInvestorsById(pitchId);
		mp.put("id", Integer.toString(pitchId));
		mp.put("pitchTitle", mp.get("pitchtitle"));
		mp.remove("pitchtitle");
		mp.put("pitchIdea", mp.get("pitchidea"));
		mp.remove("pitchidea");
		mp.put("askAmount", mp.get("askamount"));
		mp.remove("askamount");
		mp.put("offers", invest);
		return mp;
	}
}
