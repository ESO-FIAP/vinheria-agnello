<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:if test="${empty sessionScope.checkoutMensagem}">
    <c:redirect url="${pageContext.request.contextPath}/carrinho"/>
</c:if>
<c:set var="pageTitle" value="Pedido confirmado" scope="request"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="mx-auto max-w-lg rounded-2xl border border-emerald-200 bg-emerald-50 p-8 text-center shadow-sm">
    <p class="text-lg font-semibold text-emerald-900">${sessionScope.checkoutMensagem}</p>
    <p class="mt-2 text-sm text-emerald-800">Obrigado por comprar na Vinheria (ambiente de demonstração).</p>
    <a href="${pageContext.request.contextPath}/vinhos"
       class="mt-6 inline-block rounded-full bg-red-900 px-6 py-2.5 text-sm font-semibold text-white hover:bg-red-800">
        Voltar ao catálogo
    </a>
</div>

<c:remove var="checkoutMensagem" scope="session"/>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
