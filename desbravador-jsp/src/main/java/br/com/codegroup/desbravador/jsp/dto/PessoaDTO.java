package br.com.codegroup.desbravador.jsp.dto;

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
import java.util.Objects;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 200, message = "Nome deve ter entre 3 e 200 caracteres")
    private String nome;

    @NotNull(message = "Data de nascimento é obrigatória")
    private Date dataNascimento;

    @NotBlank(message = "CPF é obrigatório")
    @Size(min = 11, max = 11, message = "CPF deve ter 11 caracteres")
    private String cpf;

    private boolean funcionario;

    public void setCpf(String cpf) {
        if (Objects.nonNull(cpf)) {
            this.cpf = cpf.replaceAll("[^0-9]", "");
        }
    }
}
