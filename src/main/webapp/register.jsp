<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<h1 class="mb-6 text-3xl font-bold text-stone-900">Criar conta</h1>

<c:if test="${not empty erro}">
    <div class="mb-4 rounded-lg border border-red-200 bg-red-50 px-4 py-3 text-sm text-red-800">${erro}</div>
</c:if>

<form class="mx-auto max-w-md space-y-4 rounded-2xl border border-stone-200 bg-white p-6 shadow-sm"
      action="${pageContext.request.contextPath}/cadastro" method="post">
    <div>
        <label class="block text-sm font-medium text-stone-700" for="nome">Nome</label>
        <input class="mt-1 w-full rounded-lg border border-stone-300 px-3 py-2 shadow-sm focus:border-red-800 focus:outline-none focus:ring-1 focus:ring-red-800"
               id="nome" name="nome" type="text" required autocomplete="name"/>
    </div>
    <div>
        <label class="block text-sm font-medium text-stone-700" for="email">E-mail</label>
        <input class="mt-1 w-full rounded-lg border border-stone-300 px-3 py-2 shadow-sm focus:border-red-800 focus:outline-none focus:ring-1 focus:ring-red-800"
               id="email" name="email" type="email" required autocomplete="email"/>
    </div>
    <div>
        <label class="block text-sm font-medium text-stone-700" for="password">Senha</label>
        <input class="mt-1 w-full rounded-lg border border-stone-300 px-3 py-2 shadow-sm focus:border-red-800 focus:outline-none focus:ring-1 focus:ring-red-800"
               id="password" name="password" type="password" required autocomplete="new-password"/>
    </div>
    <button type="submit"
            class="w-full rounded-full bg-red-900 py-2.5 text-sm font-semibold text-white hover:bg-red-800">
        Cadastrar
    </button>
    <p class="text-center text-sm text-stone-600">
        Já tem conta?
        <a href="${pageContext.request.contextPath}/login" class="font-medium text-red-800 hover:underline">Entrar</a>
    </p>
</form>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
