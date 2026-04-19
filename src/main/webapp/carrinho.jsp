<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="pt_BR"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<h1 class="mb-4 text-3xl font-bold text-stone-900">Carrinho</h1>

<div class="mb-6 rounded-lg border border-stone-200 bg-white p-4 text-stone-800 shadow-sm">
    <p class="text-sm font-medium text-stone-600">Você está comprando como:</p>
    <p class="mt-1 text-lg font-semibold text-red-900">
        <c:choose>
            <c:when test="${not empty sessionScope.usuario}">
                ${sessionScope.usuario.displayName}
            </c:when>
            <c:otherwise>anonimo</c:otherwise>
        </c:choose>
    </p>
</div>

<c:choose>
    <c:when test="${empty cartLines}">
        <p class="text-stone-600">Seu carrinho está vazio.</p>
        <a href="${pageContext.request.contextPath}/vinhos" class="mt-4 inline-block font-medium text-red-800 hover:underline">Ver vinhos</a>
    </c:when>
    <c:otherwise>
        <c:if test="${not empty erroCheckout}">
            <div class="mb-4 rounded-lg border border-red-200 bg-red-50 px-4 py-3 text-sm text-red-800">
                ${erroCheckout}
            </div>
        </c:if>

        <div class="overflow-hidden rounded-2xl border border-stone-200 bg-white shadow-sm">
            <table class="min-w-full divide-y divide-stone-200 text-left text-sm">
                <thead class="bg-stone-100 text-stone-700">
                <tr>
                    <th class="px-4 py-3 font-semibold">Produto</th>
                    <th class="px-4 py-3 font-semibold">Preço</th>
                    <th class="px-4 py-3 font-semibold">Qtd</th>
                    <th class="px-4 py-3 font-semibold">Subtotal</th>
                    <th class="px-4 py-3"></th>
                </tr>
                </thead>
                <tbody class="divide-y divide-stone-100">
                <c:forEach var="line" items="${cartLines}">
                    <tr>
                        <td class="px-4 py-3">
                            <div class="font-medium text-stone-900">${line.wine.name}</div>
                            <div class="text-xs text-stone-500">${line.wine.region}</div>
                        </td>
                        <td class="px-4 py-3">
                            <fmt:formatNumber value="${line.wine.price}" type="currency" currencyCode="BRL"/>
                        </td>
                        <td class="px-4 py-3">
                            <form action="${pageContext.request.contextPath}/carrinho" method="post" class="flex items-center gap-2">
                                <input type="hidden" name="action" value="update"/>
                                <input type="hidden" name="wineId" value="${line.wine.id}"/>
                                <input name="quantity" type="number" min="1" value="${line.quantity}"
                                       class="w-16 rounded border border-stone-300 px-2 py-1 text-sm"/>
                                <button type="submit" class="text-xs font-medium text-red-800 hover:underline">Atualizar</button>
                            </form>
                        </td>
                        <td class="px-4 py-3 font-medium">
                            <fmt:formatNumber value="${line.subtotal}" type="currency" currencyCode="BRL"/>
                        </td>
                        <td class="px-4 py-3">
                            <form action="${pageContext.request.contextPath}/carrinho" method="post">
                                <input type="hidden" name="action" value="remove"/>
                                <input type="hidden" name="wineId" value="${line.wine.id}"/>
                                <button type="submit" class="text-sm text-red-700 hover:underline">Remover</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <form action="${pageContext.request.contextPath}/checkout" method="post"
              class="mt-8 space-y-6 rounded-2xl border border-stone-200 bg-white p-6 shadow-sm">
            <h2 class="text-lg font-semibold text-stone-900">Endereço de entrega</h2>
            <div class="grid gap-4 sm:grid-cols-2">
                <div class="sm:col-span-2">
                    <label class="block text-sm font-medium text-stone-700" for="rua">Rua</label>
                    <input id="rua" name="rua" type="text" required autocomplete="street-address"
                           class="mt-1 w-full rounded-lg border border-stone-300 px-3 py-2 shadow-sm focus:border-red-800 focus:outline-none focus:ring-1 focus:ring-red-800"/>
                </div>
                <div>
                    <label class="block text-sm font-medium text-stone-700" for="numero">Número</label>
                    <input id="numero" name="numero" type="text" required
                           class="mt-1 w-full rounded-lg border border-stone-300 px-3 py-2 shadow-sm focus:border-red-800 focus:outline-none focus:ring-1 focus:ring-red-800"/>
                </div>
                <div>
                    <label class="block text-sm font-medium text-stone-700" for="complemento">Complemento</label>
                    <input id="complemento" name="complemento" type="text" autocomplete="address-line2"
                           class="mt-1 w-full rounded-lg border border-stone-300 px-3 py-2 shadow-sm focus:border-red-800 focus:outline-none focus:ring-1 focus:ring-red-800"/>
                </div>
                <div>
                    <label class="block text-sm font-medium text-stone-700" for="bairro">Bairro</label>
                    <input id="bairro" name="bairro" type="text" required
                           class="mt-1 w-full rounded-lg border border-stone-300 px-3 py-2 shadow-sm focus:border-red-800 focus:outline-none focus:ring-1 focus:ring-red-800"/>
                </div>
                <div>
                    <label class="block text-sm font-medium text-stone-700" for="cep">CEP</label>
                    <input id="cep" name="cep" type="text" required inputmode="numeric" autocomplete="postal-code"
                           class="mt-1 w-full rounded-lg border border-stone-300 px-3 py-2 shadow-sm focus:border-red-800 focus:outline-none focus:ring-1 focus:ring-red-800"/>
                </div>
            </div>

            <div class="flex flex-col items-end gap-4 border-t border-stone-100 pt-6">
                <p class="text-lg font-semibold text-stone-900">
                    Total:
                    <fmt:formatNumber value="${cartTotal}" type="currency" currencyCode="BRL"/>
                </p>
                <button type="submit"
                        class="rounded-full bg-red-900 px-8 py-3 text-sm font-semibold text-white shadow hover:bg-red-800">
                    Confirmar compra
                </button>
            </div>
        </form>
    </c:otherwise>
</c:choose>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
