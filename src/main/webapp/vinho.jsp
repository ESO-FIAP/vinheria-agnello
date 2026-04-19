<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="pt_BR"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<c:set var="w" value="${wine}"/>

<div class="grid gap-10 lg:grid-cols-2">
    <div>
        <img src="${w.imageUrl}" alt="" width="600" height="400"
             class="w-full rounded-2xl border border-stone-200 object-cover shadow-sm"/>
    </div>
    <div>
        <p class="text-sm uppercase tracking-wide text-red-800">${w.region}</p>
        <h1 class="mt-2 text-3xl font-bold text-stone-900">${w.name}</h1>
        <p class="mt-1 text-stone-600">Uva: ${w.grape}</p>
        <p class="mt-4 text-2xl font-bold text-red-900">
            <fmt:formatNumber value="${w.price}" type="currency" currencyCode="BRL"/>
        </p>
        <p class="mt-6 leading-relaxed text-stone-700">${w.description}</p>

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

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
