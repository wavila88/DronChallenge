package com.dron.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class DronModel {
  @Getter
  @Setter
  private String dronName;
  @Getter
  @Setter
  private Integer maxWeight;

  
}
