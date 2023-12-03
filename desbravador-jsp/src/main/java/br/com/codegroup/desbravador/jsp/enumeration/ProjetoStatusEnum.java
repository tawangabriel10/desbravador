package br.com.codegroup.desbravador.jsp.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum ProjetoStatusEnum implements Serializable {

    EM_ANALISE("Em análise"),
    ANALISE_REALIZADA("Análise realizada"),
    ANALISE_APROVADA("Análise aprovada"),
    INICIADO("Iniciado"),
    PLANEJADO("Planejado"),
    EM_ANDAMENTO("Em andamento"),
    ENCERRADO("Encerrado"),
    CANCELADO("Cancelado");

    private final String descricao;

    public static String getDescricao(String statuc) {
        return ProjetoStatusEnum.valueOf(statuc).getDescricao();
    }

    public static Map<String, String> getMap() {
        final Map<String, String> map = new HashMap<>();
        for (ProjetoStatusEnum statusEnum : ProjetoStatusEnum.values()) {
            map.put(statusEnum.name(), statusEnum.getDescricao());
        }
        return map;
    }
}
