package br.com.codegroup.desbravador.jsp.service;

import br.com.codegroup.desbravador.jsp.api.MembrosRestTemplate;
import br.com.codegroup.desbravador.jsp.api.ProjetoRestTemplate;
import br.com.codegroup.desbravador.jsp.dto.PessoaDTO;
import br.com.codegroup.desbravador.jsp.dto.ProjetoDTO;
import br.com.codegroup.desbravador.jsp.dto.VincularMembroDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ProjetoService {

    private final ProjetoRestTemplate projetoRestTemplate;
    private final PessoaService pessoaService;
    private final MembrosRestTemplate membrosRestTemplate;

    public List<ProjetoDTO> buscarTodos() {
        return projetoRestTemplate.buscarTodos();
    }

    public ProjetoDTO cadastrar(ProjetoDTO projetoDTO) {
        if (Objects.nonNull(projetoDTO.getIdGerente())) {
            projetoDTO.setGerente(new PessoaDTO());
            projetoDTO.getGerente().setId(projetoDTO.getIdGerente());
        }
        return projetoRestTemplate.cadastrar(projetoDTO);
    }

    public void excluir(Long id) {
        projetoRestTemplate.excluir(id);
    }

    public ProjetoDTO consultarPorId(Long id) {
        final ProjetoDTO projetoDTO = projetoRestTemplate.consultarPorId(id);
        if (Objects.nonNull(projetoDTO.getGerente())) {
            projetoDTO.setIdGerente(projetoDTO.getGerente().getId());
        }
        return projetoDTO;
    }

    public ProjetoDTO alterar(ProjetoDTO projetoDTO) {
        if (Objects.nonNull(projetoDTO.getIdGerente())) {
            projetoDTO.setGerente(new PessoaDTO());
            projetoDTO.getGerente().setId(projetoDTO.getIdGerente());
        }
        return projetoRestTemplate.alterar(projetoDTO);
    }

    public Map<Long, String> getMapPessoas() {
        final Map<Long, String> mapPessoa = new HashMap<>();

        final List<PessoaDTO> pessoaDTOList = pessoaService.buscarTodos();
        if (!pessoaDTOList.isEmpty()) {
            pessoaDTOList.forEach(pessoaDTO -> {
                mapPessoa.put(pessoaDTO.getId(), pessoaDTO.getNome());
            });
        }
        return mapPessoa;
    }

    public void vincular(VincularMembroDTO vincularMembroDTO) {
        membrosRestTemplate.vincular(vincularMembroDTO);
    }

    public void desvincular(VincularMembroDTO vincularMembroDTO) {
        membrosRestTemplate.desvincular(vincularMembroDTO);
    }
}
