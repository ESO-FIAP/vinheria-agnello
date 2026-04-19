<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="pt_BR"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="mb-6 flex flex-col gap-2 sm:flex-row sm:items-end sm:justify-between">
    <div>
        <h1 class="text-3xl font-bold text-stone-900">Catálogo</h1>
    </div>
    <p class="text-sm text-stone-500">
        <c:choose>
            <c:when test="${totalResultados == 1}">1 vinho encontrado</c:when>
            <c:otherwise>${totalResultados} vinhos encontrados</c:otherwise>
        </c:choose>
    </p>
</div>

<div class="flex flex-col gap-8 lg:flex-row lg:items-start">
    <aside class="w-full shrink-0 lg:w-72">
        <div class="rounded-2xl border border-stone-200 bg-white p-5 shadow-sm">
            <h2 class="text-base font-semibold text-stone-900">Refinar busca</h2>
            <p class="mt-1 text-xs text-stone-500">Use um ou mais filtros. Os resultados atualizam na mesma página.</p>

            <form class="mt-4 space-y-4" method="get" action="${pageContext.request.contextPath}/vinhos">
                <div>
                    <label class="block text-xs font-medium text-stone-700" for="nome">Nome do vinho</label>
                    <input id="nome" name="nome" type="search" value="${param.nome}"
                           placeholder="Contém…"
                           class="mt-1 w-full rounded-lg border border-stone-300 px-3 py-2 text-sm shadow-sm focus:border-red-800 focus:outline-none focus:ring-1 focus:ring-red-800"/>
                </div>

                <div class="grid grid-cols-2 gap-3">
                    <div>
                        <label class="block text-xs font-medium text-stone-700" for="precoMin">Preço mín. (R$)</label>
                        <input id="precoMin" name="precoMin" type="number" min="0" step="0.01" value="${param.precoMin}"
                               class="mt-1 w-full rounded-lg border border-stone-300 px-2 py-2 text-sm shadow-sm focus:border-red-800 focus:outline-none focus:ring-1 focus:ring-red-800"/>
                    </div>
                    <div>
                        <label class="block text-xs font-medium text-stone-700" for="precoMax">Preço máx. (R$)</label>
                        <input id="precoMax" name="precoMax" type="number" min="0" step="0.01" value="${param.precoMax}"
                               class="mt-1 w-full rounded-lg border border-stone-300 px-2 py-2 text-sm shadow-sm focus:border-red-800 focus:outline-none focus:ring-1 focus:ring-red-800"/>
                    </div>
                </div>

                <div>
                    <label class="block text-xs font-medium text-stone-700" for="pais">País</label>
                    <select id="pais" name="pais"
                            class="mt-1 w-full rounded-lg border border-stone-300 px-3 py-2 text-sm shadow-sm focus:border-red-800 focus:outline-none focus:ring-1 focus:ring-red-800">
                        <option value="" ${empty param.pais ? 'selected' : ''}>Todos</option>
                        <c:forEach var="p" items="${paises}">
                            <option value="${p}" <c:if test="${param.pais eq p}">selected</c:if>>${p}</option>
                        </c:forEach>
                    </select>
                </div>

                <div>
                    <label class="block text-xs font-medium text-stone-700" for="uva">Tipo de uva</label>
                    <select id="uva" name="uva"
                            class="mt-1 w-full rounded-lg border border-stone-300 px-3 py-2 text-sm shadow-sm focus:border-red-800 focus:outline-none focus:ring-1 focus:ring-red-800">
                        <option value="" ${empty param.uva ? 'selected' : ''}>Todos</option>
                        <c:forEach var="u" items="${uvas}">
                            <option value="${u}" <c:if test="${param.uva eq u}">selected</c:if>>${u}</option>
                        </c:forEach>
                    </select>
                </div>

                <div>
                    <label class="block text-xs font-medium text-stone-700" for="safra">Safra</label>
                    <select id="safra" name="safra"
                            class="mt-1 w-full rounded-lg border border-stone-300 px-3 py-2 text-sm shadow-sm focus:border-red-800 focus:outline-none focus:ring-1 focus:ring-red-800">
                        <option value="" ${empty param.safra ? 'selected' : ''}>Todas</option>
                        <c:forEach var="s" items="${safras}">
                            <option value="${s}" <c:if test="${param.safra eq s}">selected</c:if>>${s}</option>
                        </c:forEach>
                    </select>
                </div>

                <div>
                    <label class="block text-xs font-medium text-stone-700" for="classificacao">Classificação</label>
                    <select id="classificacao" name="classificacao"
                            class="mt-1 w-full rounded-lg border border-stone-300 px-3 py-2 text-sm shadow-sm focus:border-red-800 focus:outline-none focus:ring-1 focus:ring-red-800">
                        <option value="" ${empty param.classificacao ? 'selected' : ''}>Todas</option>
                        <c:forEach var="cls" items="${classificacoes}">
                            <option value="${cls}" <c:if test="${param.classificacao eq cls}">selected</c:if>>${cls}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="flex flex-wrap gap-2 pt-1">
                    <button type="submit"
                            class="flex-1 rounded-full bg-red-900 px-4 py-2 text-sm font-semibold text-white shadow hover:bg-red-800">
                        Filtrar
                    </button>
                    <a href="${pageContext.request.contextPath}/vinhos"
                       class="inline-flex items-center justify-center rounded-full border border-stone-300 px-4 py-2 text-sm font-medium text-stone-700 hover:bg-stone-50">
                        Limpar
                    </a>
                </div>
            </form>
        </div>
    </aside>

    <div class="min-w-0 flex-1">
        <c:choose>
            <c:when test="${empty wines}">
                <div class="rounded-2xl border border-dashed border-stone-300 bg-stone-50 px-6 py-12 text-center">
                    <p class="text-stone-700">Nenhum vinho com esses critérios.</p>
                    <a href="${pageContext.request.contextPath}/vinhos"
                       class="mt-4 inline-block text-sm font-medium text-red-800 hover:underline">Limpar filtros e ver todo o catálogo</a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="grid gap-6 sm:grid-cols-2 xl:grid-cols-3">
                    <c:forEach var="w" items="${wines}">
                        <article class="overflow-hidden rounded-2xl border border-stone-200 bg-white shadow-sm transition hover:shadow-md">
                            <a href="${pageContext.request.contextPath}/vinho?id=${w.id}">
                                <img src="${pageContext.request.contextPath}/${w.imageUrl}" alt="" width="600" height="400"
                                     class="h-48 w-full object-cover"/>
                            </a>
                            <div class="p-4">
                                <h2 class="text-lg font-semibold text-stone-900">
                                    <a href="${pageContext.request.contextPath}/vinho?id=${w.id}" class="hover:text-red-900">${w.name}</a>
                                </h2>
                                <p class="text-sm text-stone-500">${w.region} · ${w.country}</p>
                                <p class="mt-2 text-lg font-bold text-red-900">
                                    <fmt:formatNumber value="${w.price}" type="currency" currencyCode="BRL"/>
                                </p>
                                <a href="${pageContext.request.contextPath}/vinho?id=${w.id}"
                                   class="mt-3 inline-block text-sm font-medium text-red-800 hover:underline">Ver detalhes</a>
                            </div>
                        </article>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
