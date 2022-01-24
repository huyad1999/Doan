package com.huce.ltudm.doan_tra_cu_dulich.repository;

import com.huce.ltudm.doan_tra_cu_dulich.entity.LeHoi_VanHoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Lehoi_Vanhoa_Repository extends JpaRepository<LeHoi_VanHoa, Integer> {
}
