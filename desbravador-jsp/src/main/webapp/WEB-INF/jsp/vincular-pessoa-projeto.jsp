
<%@ include file="common/header.jspf"%>
<%@ include file="common/nav.jspf"%>
<div class="container">
    <br>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3>Lista de  Membros</h3>
        </div>
        <div class="panel-body">
            <c:if test="${isSuccess}">
                <div class="alert alert-success" role="alert">${messageSuccess}</div>
            </c:if>
            <c:if test="${isFail}">
                <div class="alert alert-danger" role="alert">${messageFail}</div>
            </c:if>

                <form:form action="/desbravador/projeto/vincular" method="post" modelAttribute="vincular">
                    <form:hidden path="idProjeto" value="${projeto.id}" />

                    <fieldset class="form-group">
                        <form:label path="idPessoa">Gerente: </form:label>
                        <form:select path="idPessoa" class="form-control" >
                            <form:option value=""/>
                            <form:options items="${listaPessoas}" />
                        </form:select>
                    </fieldset>

                    <a type="button" class="btn btn-primary btn-md" href="/desbravador/projeto/listar">Voltar</a>
                    <button type="submit" class="btn btn-success">Vincular</button>
                </form:form>

            <c:if test="${not empty projeto.membros}">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Nome do projeto</th>
                            <th>Nome do funcion&#225;rio</th>
                            <th>Data in&#237;cio</th>
                            <th>Status</th>
                            <th>Risco</th>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${projeto.membros}" var="membro">
                            <tr>
                                <td>${projeto.nome}</td>
                                <td>${membro.nome}</td>
                                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${projeto.dataInicio}" /></td>
                                <td>${projeto.statusFormatado}</td>
                                <td>${projeto.riscoFormatado}</td>
                                <td>
                                    <a type="button" class="btn btn-danger"
                                        href="/desbravador/projeto/desvincular/${projeto.id}/${membro.id}">Desvincular</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
        </div>
    </div>

</div>
<%@ include file="common/footer.jspf"%>