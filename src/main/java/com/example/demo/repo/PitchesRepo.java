package com.example.demo.repo;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Pitch;

public interface PitchesRepo extends JpaRepository<Pitch, Integer>{
	@Modifying
	@Transactional
	@Query(value = "insert into Offers(id, amount, comment, equity, investor) values(:id, :amount, :comment, :equity, :investor)", nativeQuery = true)
	void saveInvestor(@Param("id") int pitchId, @Param("amount") float amount, @Param("comment") String comment, @Param("equity") float equity, @Param("investor") String investor);
	
	@Query(value = "select p.id, p.entrepreneur, p.pitchTitle, p.pitchIdea, p.askAmount, p.equity from Pitch p order by p.id desc", nativeQuery = true)
	List<Map<String, Object>> getEntrepreneurs();
	
	@Query(value = "select i.id, i.amount, i.comment, i.equity, i.investor from Offers i where i.id = :id", nativeQuery = true)
	List<Map<String, Object>> getInvestorsById(@Param("id") int id);
	
	@Query(value = "select p.id, p.entrepreneur, p.pitchTitle, p.pitchIdea, p.askAmount, p.equity from Pitch p where p.id = :id", nativeQuery = true)
	Map<String, Object> getPitchById(@Param("id") int id);
}
