package com.neidev.tivia.security.enums;

public enum CargoEnum {

    USER(0, "user"),
    ADMIN(1, "admin");

    private final Integer codigo;
    private final String descricao;

    CargoEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public CargoEnum paraEnum(Integer codigo) {
        if (codigo == null) return null;

        for(CargoEnum o: CargoEnum.values()) {
           if (codigo.equals(o.getCodigo())) return o;
       }
        throw new IllegalArgumentException("Não foi possível obter cargo");
    }
}
