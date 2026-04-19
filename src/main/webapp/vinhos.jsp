<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="pt_BR"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<h1 class="mb-2 text-3xl font-bold text-stone-900">Catálogo</h1>
<p class="mb-8 text-stone-600">Seleção dummy de rótulos para o projeto Vinheria.</p>

<div class="grid gap-6 sm:grid-cols-2 lg:grid-cols-3">
    <c:forEach var="w" items="${wines}">
        <article class="overflow-hidden rounded-2xl border border-stone-200 bg-white shadow-sm transition hover:shadow-md">
            <a href="${pageContext.request.contextPath}/vinho?id=${w.id}">
                <img src="${w.imageUrl}" alt="" width="600" height="400"
                     class="h-48 w-full object-cover"/>
            </a>
            <div class="p-4">
                <h2 class="text-lg font-semibold text-stone-900">
                    <a href="${pageContext.request.contextPath}/vinho?id=${w.id}" class="hover:text-red-900">${w.name}</a>
                </h2>
                <p class="text-sm text-stone-500">${w.region}</p>
                <p class="mt-2 text-lg font-bold text-red-900">
                    <fmt:formatNumber value="${w.price}" type="currency" currencyCode="BRL"/>
                </p>
                <a href="${pageContext.request.contextPath}/vinho?id=${w.id}"
                   class="mt-3 inline-block text-sm font-medium text-red-800 hover:underline">Ver detalhes</a>
            </div>
        </article>
    </c:forEach>
</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
