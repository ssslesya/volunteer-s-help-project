package ru.lutsenlo.statistic.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {
    private String city;
    private String district;
    private String street;
    private String homeNumber;
    private String corpus;
    private String flatNumber;
}
