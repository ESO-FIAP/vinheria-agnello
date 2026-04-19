<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<h1 class="mb-6 text-3xl font-bold text-stone-900">Entrar</h1>

<c:if test="${not empty erro}">
    <div class="mb-4 rounded-lg border border-red-200 bg-red-50 px-4 py-3 text-sm text-red-800">${erro}</div>
</c:if>

<form class="mx-auto max-w-md space-y-4 rounded-2xl border border-stone-200 bg-white p-6 shadow-sm"
      action="${pageContext.request.contextPath}/login" method="post">
    <div>
        <label class="block text-sm font-medium text-stone-700" for="email">E-mail</label>
        <input class="mt-1 w-full rounded-lg border border-stone-300 px-3 py-2 shadow-sm focus:border-red-800 focus:outline-none focus:ring-1 focus:ring-red-800"
               id="email" name="email" type="email" required autocomplete="username"/>
    </div>
    <div>
        <label class="block text-sm font-medium text-stone-700" for="password">Senha</label>
        <input class="mt-1 w-full rounded-lg border border-stone-300 px-3 py-2 shadow-sm focus:border-red-800 focus:outline-none focus:ring-1 focus:ring-red-800"
               id="password" name="password" type="password" required autocomplete="current-password"/>
    </div>
    <button type="submit"
            class="w-full rounded-full bg-red-900 py-2.5 text-sm font-semibold text-white hover:bg-red-800">
        Entrar
    </button>
    <p class="text-center text-sm text-stone-600">
        Não tem conta?
        <a href="${pageContext.request.contextPath}/cadastro" class="font-medium text-red-800 hover:underline">Cadastre-se</a>
    </p>
</form>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
