<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="pt_BR"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<c:set var="w" value="${wine}"/>

<div class="grid gap-10 lg:grid-cols-2 lg:items-start">
    <div>
        <img src="${pageContext.request.contextPath}/${w.imageUrl}" alt="" width="600" height="400"
             class="w-full rounded-2xl border border-stone-200 object-cover shadow-sm"/>
    </div>
    <div>
        <p class="text-sm uppercase tracking-wide text-red-800">${w.region} · ${w.country}</p>
        <h1 class="mt-2 text-3xl font-bold text-stone-900">${w.name}</h1>
        <p class="mt-1 text-sm text-stone-600">Uva: ${w.grape}</p>
        <p class="mt-4 text-2xl font-bold text-red-900">
            <fmt:formatNumber value="${w.price}" type="currency" currencyCode="BRL"/>
        </p>

        <form class="mt-8 flex flex-wrap items-end gap-4"
              action="${pageContext.request.contextPath}/carrinho" method="post">
            <input type="hidden" name="action" value="add"/>
            <input type="hidden" name="wineId" value="${w.id}"/>
            <input type="hidden" name="next" value="/vinho?id=${w.id}"/>
            <div>
                <label for="quantity" class="block text-sm font-medium text-stone-700">Quantidade</label>
                <input id="quantity" name="quantity" type="number" min="1" value="1"
                       class="mt-1 w-24 rounded-lg border border-stone-300 px-3 py-2 shadow-sm focus:border-red-800 focus:outline-none focus:ring-1 focus:ring-red-800"/>
            </div>
            <button type="submit"
                    class="rounded-full bg-red-900 px-6 py-2.5 text-sm font-semibold text-white shadow hover:bg-red-800">
                Adicionar ao carrinho
            </button>
        </form>
    </div>
</div>

<section class="mt-12 space-y-8">
    <div class="rounded-2xl border border-stone-200 bg-white p-6 shadow-sm">
        <h2 class="text-lg font-semibold text-stone-900">Sobre este vinho</h2>
        <p class="mt-3 leading-relaxed text-stone-700">${w.description}</p>
    </div>

    <div class="rounded-2xl border border-stone-200 bg-white p-6 shadow-sm">
        <h2 class="text-lg font-semibold text-stone-900">Ficha técnica</h2>
        <dl class="mt-4 grid gap-4 sm:grid-cols-2">
            <div>
                <dt class="text-xs font-medium uppercase tracking-wide text-stone-500">Tipo de uva</dt>
                <dd class="mt-1 font-medium text-stone-900">${w.grape}</dd>
            </div>
            <div>
                <dt class="text-xs font-medium uppercase tracking-wide text-stone-500">País</dt>
                <dd class="mt-1 font-medium text-stone-900">${w.country}</dd>
            </div>
            <div>
                <dt class="text-xs font-medium uppercase tracking-wide text-stone-500">Região</dt>
                <dd class="mt-1 font-medium text-stone-900">${w.region}</dd>
            </div>
            <div>
                <dt class="text-xs font-medium uppercase tracking-wide text-stone-500">Safra</dt>
                <dd class="mt-1 font-medium text-stone-900">${w.vintage}</dd>
            </div>
            <div>
                <dt class="text-xs font-medium uppercase tracking-wide text-stone-500">Classificação</dt>
                <dd class="mt-1 font-medium text-stone-900">${w.classification}</dd>
            </div>
            <div>
                <dt class="text-xs font-medium uppercase tracking-wide text-stone-500">Temperatura de serviço</dt>
                <dd class="mt-1 font-medium text-stone-900">${w.servingTemperature}</dd>
            </div>
            <div>
                <dt class="text-xs font-medium uppercase tracking-wide text-stone-500">Teor alcoólico</dt>
                <dd class="mt-1 font-medium text-stone-900">${w.abv}</dd>
            </div>
            <div>
                <dt class="text-xs font-medium uppercase tracking-wide text-stone-500">Volume</dt>
                <dd class="mt-1 font-medium text-stone-900">${w.volumeMl} ml</dd>
            </div>
        </dl>
    </div>

    <div class="rounded-2xl border border-stone-200 bg-white p-6 shadow-sm">
        <h2 class="text-lg font-semibold text-stone-900">Amadurecimento</h2>
        <p class="mt-3 leading-relaxed text-stone-700">${w.maturationNotes}</p>
    </div>

    <div class="rounded-2xl border border-stone-200 bg-white p-6 shadow-sm">
        <h2 class="text-lg font-semibold text-stone-900">Harmonização</h2>
        <p class="mt-2 text-sm text-stone-600">Sugestões para acompanhar este rótulo (conteúdo demonstrativo):</p>
        <ul class="mt-4 list-inside list-disc space-y-2 text-stone-800">
            <c:forEach var="item" items="${w.pairingItems}">
                <li>${item}</li>
            </c:forEach>
        </ul>
    </div>
</section>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
