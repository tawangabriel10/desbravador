
<%@ include file="common/header.jspf" %>
<%@ include file="common/nav.jspf" %>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3 ">
            <div class="panel panel-primary">
                <div class="panel-heading">Cadastrar Projeto</div>
                <div class="panel-body">
                    <c:if test="${isFail}">
                        <div class="alert alert-danger" role="alert">${messageFail}</div>
                    </c:if>

                    <form:form action="${path_url_projeto}" method="post" modelAttribute="projeto">
                        <form:hidden path="id" />

                        <fieldset class="form-group">
                            <form:label path="nome">Nome: </form:label>
                            <form:input type="text" path="nome" class="form-control"
                                required="required" />
                            <form:errors path="nome" cssClass="text-warning" />
                        </fieldset>

                        <fieldset class="form-group">
                            <form:label path="dataInicio">Data de in&#237;cio: </form:label>
                            <form:input type="date" path="dataInicio" class="form-control"
                                required="required" />
                            <form:errors path="dataInicio" cssClass="text-warning" />
                        </fieldset>

                        <fieldset class="form-group">
                            <form:label path="dataPrevisaoFim">Data previs&#227;o fim: </form:label>
                            <form:input type="date" path="dataPrevisaoFim" class="form-control"
                                required="required" />
                            <form:errors path="dataPrevisaoFim" cssClass="text-warning" />
                        </fieldset>

                        <fieldset class="form-group">
                            <form:label path="dataFim">Data fim: </form:label>
                            <form:input type="date" path="dataFim" class="form-control" />
                        </fieldset>

                        <fieldset class="form-group">
                            <form:label path="descricao">Descri&#231;&#227;o: </form:label>
                            <form:input type="text" path="descricao" class="form-control" />
                        </fieldset>

                        <fieldset class="form-group">
                            <form:label path="status">Status: </form:label>
                            <form:select path="status" class="form-control" >
                                <form:options items="${listaStatus}" />
                            </form:select>
                        </fieldset>

                        <fieldset class="form-group">
                            <form:label path="orcamento">Or&#231;amento: </form:label>
                            <form:input type="text" path="orcamento" class="form-control" />
                        </fieldset>

                        <fieldset class="form-group">
                            <form:label path="risco">Risco: </form:label>
                            <form:select path="risco" class="form-control" >
                                <form:options items="${listaRisco}" />
                            </form:select>
                        </fieldset>

                        <fieldset class="form-group">
                            <form:label path="idGerente">Gerente: </form:label>
                            <form:select path="idGerente" class="form-control" >
                                <form:option value="${null}"/>
                                <form:options items="${listaPessoas}" />
                            </form:select>
                        </fieldset>

                        <a type="button" class="btn btn-primary btn-md" href="/desbravador/projeto/listar">Voltar</a>
                        <button type="submit" class="btn btn-success">Salvar</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf" %>