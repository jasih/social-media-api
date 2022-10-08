package com.cookysys.social_media_project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cookysys.social_media_project.entities.Hashtag;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long>{

	Optional<Hashtag> findByLabelContainingIgnoreCase(String label);

	List<Hashtag> findAllByLabelContainingIgnoreCase(String label);
}
