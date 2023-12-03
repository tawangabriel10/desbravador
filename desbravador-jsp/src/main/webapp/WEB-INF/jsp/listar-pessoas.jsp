<%@ include file="common/header.jspf"%>
<%@ include file="common/nav.jspf"%>
<div class="container">
    <div>
        <a type="button" class="btn btn-primary btn-md" href="/desbravador/pessoa/cadastrar">Cadastrar</a>
    </div>
    <br>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3>Lista de Pessoas</h3>
        </div>
        <div class="panel-body">
            <c:if test="${isSuccess}">
                <div class="alert alert-success" role="alert">${messageSuccess}</div>
            </c:if>
            <c:if test="${isFail}">
                <div class="alert alert-danger" role="alert">${messageFail}</div>
            </c:if>

            <c:if test="${not empty listaPessoas}">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Data de nascimento</th>
                            <th>CPF</th>
                            <th>Funcion&#225;rio</th>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listaPessoas}" var="pessoa">
                            <tr>
                                <td>${pessoa.id}</td>
                                <td>${pessoa.nome}</td>
                                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${pessoa.dataNascimento}" /></td>
                                <td class="cpf">${pessoa.cpf}</td>
                                <td>
                                    <c:if test="${pessoa.funcionario}">Sim</c:if>
                                    <c:if test="${!pessoa.funcionario}">N&#227;o</c:if>
                                </td>
                                <td>
                                    <a type="button" class="btn btn-success"
                                        href="/desbravador/pessoa/alterar/${pessoa.id}">Alterar</a>
                                    <a type="button" class="btn btn-danger"
                                        href="/desbravador/pessoa/excluir/${pessoa.id}">Excluir</a>
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