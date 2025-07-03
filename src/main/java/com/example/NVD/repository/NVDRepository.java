package com.example.NVD.repository;
import java.util.Date;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.NVD.model.Cve;
@Repository
public interface NVDRepository extends JpaRepository<Cve,String> {
	List<Cve> findByIdContainingIgnoreCase(String id);  // CVE ID

	@Query("SELECT c FROM Cve c WHERE FUNCTION('YEAR', c.publishedDate) = :year")
	List<Cve> findByPublishedYear(@Param("year") int year);

	@Query("SELECT c FROM Cve c WHERE c.baseScore >= :score")
	List<Cve> findByBaseScoreGreaterThanEqual(@Param("score") double score);

	@Query("SELECT c FROM Cve c WHERE c.lastModifiedDate >= :since")
	List<Cve> findByLastModifiedSince(@Param("since") Date since);
}
