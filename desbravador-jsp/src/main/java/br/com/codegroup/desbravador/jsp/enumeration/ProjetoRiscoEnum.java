package br.com.codegroup.desbravador.jsp.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum ProjetoRiscoEnum implements Serializable {

    BAIXO("Baixo"),
    MEDIO("Médio"),
    ALTO("Alto");

    private final String descricao;

    public static String getDescricao(String risco) {
        return ProjetoRiscoEnum.valueOf(risco).getDescricao();
    }

    public static Map<String, String> getMap() {
        final Map<String, String> map = new HashMap<>();
        for (ProjetoRiscoEnum riscoEnum : ProjetoRiscoEnum.values()) {
            map.put(riscoEnum.name(), riscoEnum.getDescricao());
        }
        return map;
    }
}
