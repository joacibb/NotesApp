package ar.com.noteschallenge.repository;

import ar.com.noteschallenge.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository <Note,Long> {
}
