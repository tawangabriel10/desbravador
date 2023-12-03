package br.com.codegroup.desbravador.jsp.service;

import br.com.codegroup.desbravador.jsp.api.PessoaRestTemplate;
import br.com.codegroup.desbravador.jsp.dto.PessoaDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PessoaService {

    private final PessoaRestTemplate pessoaRestTemplate;

    public PessoaDTO cadastrar(PessoaDTO pessoaDTO) {
        return pessoaRestTemplate.cadastrar(pessoaDTO);
    }

    public List<PessoaDTO> buscarTodos() {
        return pessoaRestTemplate.buscarTodos();
    }

    public void excluir(Long id) {
        pessoaRestTemplate.excluir(id);
    }

    public PessoaDTO consultarPorId(Long id) {
        return pessoaRestTemplate.consultarPorId(id);
    }

    public PessoaDTO alterar(PessoaDTO pessoaDTO) {
        return pessoaRestTemplate.alterar(pessoaDTO);
    }
}
