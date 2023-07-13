package kz.batyr.project.batapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_categories")
@Getter
@Setter
public class Category extends BaseModel {
  @Column(name = "category_name")
  private String name;
}
