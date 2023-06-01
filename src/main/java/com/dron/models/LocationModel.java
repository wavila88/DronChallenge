package com.dron.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class LocationModel {
  @Getter
  @Setter
  private String locationName;
  @Getter
  @Setter
  private Integer packageWeight;
  public LocationModel(String name) {
  }
}
