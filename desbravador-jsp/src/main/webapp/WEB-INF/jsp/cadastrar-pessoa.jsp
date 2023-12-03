
<%@ include file="common/header.jspf" %>
<%@ include file="common/nav.jspf" %>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3 ">
            <div class="panel panel-primary">
                <div class="panel-heading">Cadastrar Pessoa</div>
                <div class="panel-body">
                    <c:if test="${isFail}">
                        <div class="alert alert-danger" role="alert">${messageFail}</div>
                    </c:if>

                    <form:form action="${path_url_pessoa}" method="post" modelAttribute="pessoa">
                        <form:hidden path="id" />

                        <fieldset class="form-group">
                            <form:label path="nome">Nome: </form:label>
                            <form:input type="text" path="nome" class="form-control"
                                required="required" />
                            <form:errors path="nome" cssClass="text-warning" />
                        </fieldset>

                        <fieldset class="form-group">
                            <form:label path="dataNascimento">Data de nascimento: </form:label>
                            <form:input type="date" path="dataNascimento" class="form-control"
                                required="required" />
                            <form:errors path="dataNascimento" cssClass="text-warning" />
                        </fieldset>

                        <fieldset class="form-group">
                            <form:label path="cpf">CPF: </form:label>
                            <form:input type="text" path="cpf" class="form-control cpf"
                                required="required" />
                            <form:errors path="cpf" cssClass="text-warning" />
                        </fieldset>

                        <fieldset class="form-group">
                            <form:label path="funcionario">&#201; funcion&#225;rio ?: </form:label>
                            <form:checkbox path="funcionario" />
                        </fieldset>

                        <a type="button" class="btn btn-primary btn-md" href="/desbravador/pessoa/listar">Voltar</a>
                        <button type="submit" class="btn btn-success">Salvar</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf" %>