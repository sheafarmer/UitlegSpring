package com.example.techiteasy.repositories;
import com.example.techiteasy.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TelevisionRepository extends JpaRepository <Television, Long> {
    List<Television> findByBrand(String brand);

}
