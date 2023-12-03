
<%@ include file="common/header.jspf"%>
<%@ include file="common/nav.jspf"%>
<div class="container">
    <div>
        <a type="button" class="btn btn-primary btn-md" href="/desbravador/projeto/cadastrar">Cadastrar</a>
    </div>
    <br>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3>Lista de Projetos</h3>
        </div>
        <div class="panel-body">
            <c:if test="${isSuccess}">
                <div class="alert alert-success" role="alert">${messageSuccess}</div>
            </c:if>
            <c:if test="${isFail}">
                <div class="alert alert-danger" role="alert">${messageFail}</div>
            </c:if>

            <c:if test="${not empty listaProjetos}">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Data in&#237;cio</th>
                            <th>Data previs&#227;o fim</th>
                            <th>Data fim</th>
                            <th>Descri&#231;&#227;o</th>
                            <th>Status</th>
                            <th>Risco</th>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listaProjetos}" var="projeto">
                            <tr>
                                <td>${projeto.id}</td>
                                <td>${projeto.nome}</td>
                                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${projeto.dataInicio}" /></td>
                                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${projeto.dataPrevisaoFim}" /></td>
                                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${projeto.dataFim}" /></td>
                                <td>${projeto.descricao}</td>
                                <td>${projeto.statusFormatado}</td>
                                <td>${projeto.riscoFormatado}</td>
                                <td>
                                    <a type="button" class="btn btn-success"
                                        href="/desbravador/projeto/alterar/${projeto.id}">Alterar</a>
                                    <a type="button" class="btn btn-danger"
                                        href="/desbravador/projeto/excluir/${projeto.id}">Excluir</a>
                                    <a type="button" class="btn btn-warning"
                                        href="/desbravador/projeto/vincular/${projeto.id}">Vincular</a>
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