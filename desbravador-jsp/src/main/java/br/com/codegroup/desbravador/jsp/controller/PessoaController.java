package br.com.codegroup.desbravador.jsp.controller;

import br.com.codegroup.desbravador.jsp.dto.PessoaDTO;
import br.com.codegroup.desbravador.jsp.exception.BunisessException;
import br.com.codegroup.desbravador.jsp.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@AllArgsConstructor
@RequestMapping("/pessoa")
public class PessoaController {

    private static final String VIEW_CADASTRAR_PESSOA = "cadastrar-pessoa";
    private static final String VIEW_LISTAR_PESSOAS = "listar-pessoas";

    private final PessoaService pessoaService;

    @GetMapping("/listar")
    public String viewListar(Model model) {
        model.addAttribute("listaPessoas", pessoaService.buscarTodos());
        return VIEW_LISTAR_PESSOAS;
    }

    @GetMapping("/cadastrar")
    public String viewCadastrar(Model model) {
        model.addAttribute("pessoa", new PessoaDTO());
        model.addAttribute("path_url_pessoa", "/desbravador/pessoa/cadastrar");
        return VIEW_CADASTRAR_PESSOA;
    }

    @PostMapping("/cadastrar")
    public RedirectView cadastrarPessoa(@ModelAttribute("pessoa") PessoaDTO pessoaDTO, RedirectAttributes redirectAttributes, ModelMap model) {
        try {
            final RedirectView redirectView = new RedirectView("/pessoa/listar", true);
            final PessoaDTO pessoaSalva = pessoaService.cadastrar(pessoaDTO);
            redirectAttributes.addFlashAttribute("pessoaSalva", pessoaSalva);
            redirectAttributes.addFlashAttribute("isSuccess", true);
            redirectAttributes.addFlashAttribute("messageSuccess", "Pessoa cadastrada com sucesso");
            return redirectView;
        } catch(BunisessException ex) {
            final RedirectView redirectView = new RedirectView("/projeto/cadastrar", true);
            redirectAttributes.addFlashAttribute("isFail", true);
            redirectAttributes.addFlashAttribute("messageFail", ex.getMessage());
            model.addAttribute("pessoa", pessoaDTO);
            return redirectView;
        }
    }

    @GetMapping(value = "/excluir/{id}")
    public RedirectView excluirPessoa(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            pessoaService.excluir(id);

            final RedirectView redirectView = new RedirectView("/pessoa/listar", true);
            redirectAttributes.addFlashAttribute("isSuccess", true);
            redirectAttributes.addFlashAttribute("messageSuccess", "Pessoa excluida com sucesso");
            return redirectView;
        } catch(BunisessException ex) {
            final RedirectView redirectView = new RedirectView("/pessoa/cadastrar", true);
            redirectAttributes.addFlashAttribute("isFail", true);
            redirectAttributes.addFlashAttribute("messageFail", ex.getMessage());
            return redirectView;
        }
    }

    @GetMapping(value = "/alterar/{id}")
    public String viewAlterar(@PathVariable Long id, ModelMap model) {
        final PessoaDTO pessoaDTO = pessoaService.consultarPorId(id);
        model.put("pessoa", pessoaDTO);
        model.addAttribute("path_url_pessoa", "/desbravador/pessoa/alterar");
        return VIEW_CADASTRAR_PESSOA;
    }

    @PostMapping(value = "/alterar")
    public RedirectView alterar(ModelMap model, @Valid PessoaDTO pessoaDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        try {
            if (result.hasErrors()) {
                return new RedirectView("/pessoa/cadastrar", true);
            }

            final RedirectView redirectView = new RedirectView("/pessoa/listar", true);
            final PessoaDTO pessoaSalva = pessoaService.alterar(pessoaDTO);
            redirectAttributes.addFlashAttribute("pessoaSalva", pessoaSalva);
            redirectAttributes.addFlashAttribute("isSuccess", true);
            redirectAttributes.addFlashAttribute("messageSuccess", "Pessoa alterada com sucesso");
            return redirectView;
        } catch(BunisessException ex) {
            final RedirectView redirectView = new RedirectView("/pessoa/cadastrar", true);
            redirectAttributes.addFlashAttribute("isFail", true);
            redirectAttributes.addFlashAttribute("messageFail", ex.getMessage());
            model.addAttribute("pessoa", pessoaDTO);
            return redirectView;
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
