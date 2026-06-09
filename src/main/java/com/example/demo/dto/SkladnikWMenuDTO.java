package com.example.demo.dto;

import com.example.demo.entity.Receptury;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkladnikWMenuDTO {
    private Long id;
    private String nazwa;
    private Float ilosc;
    private String jednostka;

    public SkladnikWMenuDTO(Receptury receptura) {
        this.id = receptura.getId();
        if (receptura.getSkladnik() != null) {
            this.nazwa = receptura.getSkladnik().getNazwa();
        }
        this.ilosc = receptura.getIloscSkladnika();
        this.jednostka = receptura.getJednostka();
    }
}