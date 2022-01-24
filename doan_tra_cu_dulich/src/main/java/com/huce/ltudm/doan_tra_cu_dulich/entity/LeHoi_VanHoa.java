package com.huce.ltudm.doan_tra_cu_dulich.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@TypeDef(
        name = "json",
        typeClass = JsonStringType.class
)
public class LeHoi_VanHoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ten_VH")
    private String ten_VH;
    @Column(name = "ten_LH")
    private String ten_LH;
    @Column(name = "dacdiem_VH", columnDefinition = "TEXT")
    private String dacdiem_VH;
    @Column(name = "dacdiem_LH", columnDefinition = "TEXT")
    private String dacdiem_LH;
    @Type(type = "json")
    @Column(name = "anh_LHVH",columnDefinition = "json")
    private List<String> anh_LHVH;

}
