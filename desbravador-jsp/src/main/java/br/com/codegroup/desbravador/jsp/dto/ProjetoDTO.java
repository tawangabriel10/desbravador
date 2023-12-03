package br.com.codegroup.desbravador.jsp.dto;

import br.com.codegroup.desbravador.jsp.enumeration.ProjetoRiscoEnum;
import br.com.codegroup.desbravador.jsp.enumeration.ProjetoStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjetoDTO implements Serializable {
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 200, message = "Nome deve ter entre 3 e 200 caracteres")
    private String nome;

    @NotNull(message = "Data início é obrigatória")
    private Date dataInicio;

    @NotNull(message = "Data previsão do fim é obrigatória")
    private Date dataPrevisaoFim;

    private Date dataFim;

    private String descricao;

    @NotNull(message = "Status é obrigatório")
    private String status;

    private Double orcamento;

    @NotNull(message = "Risco é obrigatório")
    private String risco;

    @NotNull(message = "Gerente é obrigatório")
    private Long idGerente;

    private PessoaDTO gerente;

    private List<PessoaDTO> membros;

    private String statusFormatado;

    private String riscoFormatado;

    public String getStatusFormatado() {
        if (Objects.nonNull(status) && !status.equals("")) {
            return ProjetoStatusEnum.getDescricao(status);
        }
        return "";
    }

    public String getRiscoFormatado() {
        if (Objects.nonNull(risco) && !risco.equals("")) {
            return ProjetoRiscoEnum.getDescricao(risco);
        }
        return "";
    }

}
